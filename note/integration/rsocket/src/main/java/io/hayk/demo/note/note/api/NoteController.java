package io.hayk.demo.note.note.api;

import io.hayk.demo.common.SimpleApplicationError;
import io.hayk.demo.note.content.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Controller
public class NoteController {

    private final NoteContentService noteContentService;

    private final Executor executor;

    public NoteController(final NoteContentService noteContentService,
                          @Qualifier("dbCallBoundExecutor") final Executor executor) {
        this.noteContentService = noteContentService;
        this.executor = executor;
    }

    private static NoteContentDto toNoteContentDto(final NoteContent content) {
        final NoteContentDto dto = new NoteContentDto();
        dto.setLastUpdated(content.getLastUpdated());
        dto.setText(content.getText());
        dto.setTitle(content.getTitle());
        dto.setUserId(content.getCreatedBy().getId());
        return dto;
    }

    @MessageMapping("note:create")
    Mono<GenericNoteResult> create(final CreateNoteRequest request) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(() ->
                                noteContentService.
                                        createNoteContent(
                                                CreateNoteContentParam.of(request.getUserId(),
                                                        request.getTitle(),
                                                        request.getText())
                                        )
                        , executor)
                        .handleAsync(this::toGenericNoteResult)
        );
    }

    @MessageMapping("note:update")
    Mono<GenericNoteResult> create(final UpdateNoteRequest request) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(() ->
                                noteContentService.
                                        updateNoteContent(
                                                UpdateNoteContentParam.of(request.getNoteId(),
                                                        request.getTitle(),
                                                        request.getText())
                                        )
                        , executor)
                        .handleAsync(this::toGenericNoteResult)
        );
    }

    @MessageMapping("note:get")
    Mono<GenericNoteResult> getNote(final GetNoteRequest request) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(() ->
                                noteContentService.
                                        getNoteContent(request.getNoteId())
                        , executor)
                        .handleAsync(this::toGenericNoteResult)
        );
    }

    private GenericNoteResult toGenericNoteResult(final NoteContent noteContent, final Throwable th) {
        if (th != null) {
            return new GenericNoteResult(
                    Collections.singletonList(new SimpleApplicationError(th.getMessage()))
            );
        } else {
            return new GenericNoteResult(
                    toNoteContentDto(noteContent)
            );
        }
    }

    @MessageMapping("note:getUserNotes")
    Flux<GetUserNotesResult> getNote(final GetUserNotesRequest request) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(() ->
                                noteContentService.
                                        getUserNotes(request.getUserId())
                        , executor)
                        .handleAsync(this::toGetUserNotesResult)
        ).flux();
    }

    private GetUserNotesResult toGetUserNotesResult(List<NoteContent> noteContents, Throwable th) {
        if (th != null) {
            return GetUserNotesResult.failedWith(
                    Collections.singletonList(new SimpleApplicationError(th.getMessage()))
            );
        } else {
            return
                    GetUserNotesResult.withNotes(
                            noteContents.stream()
                                    .map(NoteController::toNoteContentDto)
                                    .collect(Collectors.toList())
                    );
        }
    }
}
