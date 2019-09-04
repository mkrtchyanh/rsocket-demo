package io.hayk.demo.resources.note.content;


import io.hayk.demo.note.content.CreateNoteRequest;
import io.hayk.demo.note.content.NoteContentDto;
import io.hayk.demo.note.content.UpdateNoteRequest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final Mono<RSocketRequester> notesApiRequester;

    private SaveNoteWebRequestValidator noteRequestValidator;

    public NoteController(final Mono<RSocketRequester> notesApiRequester) {
        this.notesApiRequester = notesApiRequester;
    }

    @PostMapping("/create")
    public Mono<NoteContentDto> create(final Mono<CreateNoteWebRequest> createNoteRequest) {
        return createNoteRequest
                .flatMap(request -> notesApiRequester.flatMap(
                        rSocketRequester ->
                                rSocketRequester.route("note:create").data(request)
                                        .retrieveMono(NoteContentDto.class))

                );
    }

    @PutMapping("/update")
    public Mono<NoteContentDto> update(final Mono<UpdateNoteWebRequest> updateNoteRequest) {
        return updateNoteRequest
                .flatMap(request -> notesApiRequester.flatMap(
                        rSocketRequester ->
                                rSocketRequester.route("note:update").data(request)
                                        .retrieveMono(NoteContentDto.class))

                );
    }

    @GetMapping("{id}")
    public Mono<NoteContentDto> getNoteContentDto(@PathVariable("id") final Long id) {
        return notesApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("note:get").data(id)
                                .retrieveMono(NoteContentDto.class)

        );
    }

    @GetMapping("/user{id}")
    public Mono<NoteContentDto> getUserNotes(@RequestParam("user_id") final Long userId) {
        return notesApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("note:userNotes").data(userId)
                                .retrieveMono(NoteContentDto.class)

        );
    }
}
