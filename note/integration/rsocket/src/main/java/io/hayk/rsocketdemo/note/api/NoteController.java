package io.hayk.rsocketdemo.note.api;

import io.hayk.rsocketdemo.api.model.*;
import io.hayk.rsocketdemo.note.CreateUserNoteParam;
import io.hayk.rsocketdemo.note.UserNoteService;
import io.hayk.rsocketdemo.note.UpdateUserNoteParam;
import io.hayk.rsocketdemo.note.UserNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Controller
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    private final UserNoteService userNoteService;

    private final Executor executor;


    public NoteController(final UserNoteService userNoteService,
                          @Qualifier("dbCallBoundExecutor") final Executor executor) {
        this.userNoteService = userNoteService;
        this.executor = executor;
    }

    private static NoteContentDto toNoteContentDto(final UserNote userNote) {
        final NoteContentDto dto = new NoteContentDto();
        dto.setLastUpdated(userNote.updated());
        dto.setText(userNote.text());
        dto.setTitle(userNote.title());
        dto.setUserId(userNote.userId());
        dto.setId(userNote.noteId());
        return dto;
    }

    @MessageMapping("note:create")
    public Mono<GenericNoteResult> create(final CreateNoteRequest request) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(() ->
                                userNoteService.
                                        createUserNote(
                                                CreateUserNoteParam.of(request.getUserId(),
                                                        request.getTitle(),
                                                        request.getText())
                                        )
                        , executor)
                        .whenComplete((res, th) -> {
                            if (th != null) {
                                logger.error("Failed to create note!", th);
                            } else {
                                logger.debug("A new note with id - {} was successfully created.", res.noteId());
                            }
                        })
                        .thenApply(NoteController::toGenericNoteResult)
        );
    }

    @MessageMapping("note:update")
    public Mono<GenericNoteResult> update(final UpdateNoteRequest request) {
        return Mono.fromCompletionStage(
                updateNoteContent(request)
                        .whenComplete((res, th) -> {
                            if (th != null) {
                                logger.error("Failed to update note with id = '{}'!", request.getNoteId(), th);
                            } else {
                                logger.debug("A note with id - {} was successfully updated.", res.noteId());
                            }
                        })
                        .thenApply(NoteController::toGenericNoteResult)
        );
    }

    private CompletableFuture<UserNote> updateNoteContent(UpdateNoteRequest request) {
        return CompletableFuture.supplyAsync(() ->
                        userNoteService.
                                updateUserNote(
                                        UpdateUserNoteParam.of(
                                                request.getUserId(),
                                                request.getNoteId(),
                                                request.getTitle(),
                                                request.getText())
                                )
                , executor);
    }

    @MessageMapping("note:get")
    public Mono<GenericNoteResult> getNote(final GetNoteRequest request) {
        return Mono.fromCompletionStage(
                findNote(request)
                        .whenComplete((res, th) -> {
                            if (th != null) {
                                logger.error("Failed to get note with id = '{}'!", request.getNoteId(), th);
                            } else {
                                res.ifPresent(userNote -> logger.debug("A note with id - {} was successfully fetched.", userNote.noteId()));
                            }
                        })
        )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(NoteController::toGenericNoteResult);
    }

    private CompletableFuture<Optional<UserNote>> findNote(GetNoteRequest request) {
        return CompletableFuture.supplyAsync(() ->
                        userNoteService.
                                getUserNote(request.getNoteId())
                , executor);
    }

    private static GenericNoteResult toGenericNoteResult(final UserNote userNote) {
        return new GenericNoteResult(
                toNoteContentDto(userNote)
        );
    }

    @MessageMapping("note:userNotes")
    public Flux<GetUserNotesResult> getUserNotet(final GetUserNotesRequest request) {
        return Mono.fromCompletionStage(
                getUserNotes(request)
                        .whenComplete((res, th) -> {
                            if (th != null) {
                                logger.error("Failed to get notes for user with id = '{}'!", request.getUserId(), th);
                            } else {
                                logger.debug("The '{}' user notes were successfully fetched.", res.size());
                            }
                        })
                        .thenApply(NoteController::toGetUserNotesResult)
        ).flux();
    }

    private CompletableFuture<List<UserNote>> getUserNotes(GetUserNotesRequest request) {
        return CompletableFuture.supplyAsync(() ->
                        userNoteService.
                                getUserNotes(request.getUserId())
                , executor);
    }

    private static GetUserNotesResult toGetUserNotesResult(final List<UserNote> noteContents) {
        return GetUserNotesResult.withNotes(
                noteContents.stream()
                        .map(NoteController::toNoteContentDto)
                        .collect(Collectors.toList())
        );

    }
}
