package io.hayk.rsocketdemo.client.note;

import io.hayk.rsocketdemo.*;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

class DefaultNoteApiClient implements NoteApiClient {

    private final Mono<RSocketRequester> notesApiRequester;

    DefaultNoteApiClient(final Mono<RSocketRequester> notesApiRequester) {
        this.notesApiRequester = notesApiRequester;
    }


    @Override
    public Mono<GenericNoteResult> create(final CreateNoteRequest createNoteRequest) {
        return notesApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("note:create")
                        .data(createNoteRequest)
                        .retrieveMono(GenericNoteResult.class));
    }

    @Override
    public Mono<GenericNoteResult> update(final UpdateNoteRequest updateNoteRequest) {
        return notesApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("note:update")
                        .data(updateNoteRequest)
                        .retrieveMono(GenericNoteResult.class));
    }

    @Override
    public Mono<GenericNoteResult> getNote(final GetNoteRequest request) {
        return notesApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("note:get")
                        .data(request)
                        .retrieveMono(GenericNoteResult.class));
    }

    @Override
    public Mono<GetUserNotesResult> getUserNotes(final GetUserNotesRequest request) {
        return notesApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("note:userNotes")
                                .data(request)
                                .retrieveMono(GetUserNotesResult.class)

        );
    }
}
