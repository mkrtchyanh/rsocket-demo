package io.hayk.demo.client.note;

import io.hayk.demo.note.content.*;
import io.hayk.demo.note.user.BindExternalAccountRequest;
import io.hayk.demo.note.user.BindExternalAccountResponse;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidRequest;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidResult;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class DefaultNoteApiClient implements NoteApiClient {

    private final Mono<RSocketRequester> notesApiRequester;

    public DefaultNoteApiClient(final Mono<RSocketRequester> notesApiRequester) {
        this.notesApiRequester = notesApiRequester;
    }

    @Override
    public Mono<BindExternalAccountResponse> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest) {
        return notesApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("user:bind-external-account")
                        .data(bindExternalAccountRequest)
                        .retrieveMono(BindExternalAccountResponse.class));
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
    public Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccountUid(final GetUserIdByExternalAccountUidRequest request) {
        return notesApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("user:id")
                                .data(request)
                                .retrieveMono(GetUserIdByExternalAccountUidResult.class)

        );
    }
}
