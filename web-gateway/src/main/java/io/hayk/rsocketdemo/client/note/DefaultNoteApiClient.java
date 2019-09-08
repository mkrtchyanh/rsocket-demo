package io.hayk.rsocketdemo.client.note;

import io.hayk.rsocketdemo.note.content.*;
import io.hayk.rsocketdemo.note.user.BindExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.BindExternalAccountResult;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountUidResult;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

class DefaultNoteApiClient implements NoteApiClient {

    private final Mono<RSocketRequester> notesApiRequester;

    DefaultNoteApiClient(final Mono<RSocketRequester> notesApiRequester) {
        this.notesApiRequester = notesApiRequester;
    }

    @Override
    public Mono<BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest) {
        return notesApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("user:bind-external-account")
                        .data(bindExternalAccountRequest)
                        .retrieveMono(BindExternalAccountResult.class));
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

    @Override
    public Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccount(final GetUserIdByExternalAccountRequest request) {
        return notesApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("user:search-by-external-account")
                                .data(request)
                                .retrieveMono(GetUserIdByExternalAccountUidResult.class)

        );
    }
}
