package io.hayk.demo.client.note;

import io.hayk.demo.note.content.*;
import io.hayk.demo.note.user.BindExternalAccountRequest;
import io.hayk.demo.note.user.BindExternalAccountResponse;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidRequest;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidResult;
import reactor.core.publisher.Mono;

public interface NoteApiClient {

    Mono<BindExternalAccountResponse> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest);

    Mono<GetUserNotesResult> getUserNotes(final GetUserNotesRequest request);

    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccountUid(final GetUserIdByExternalAccountUidRequest request);

    Mono<GenericNoteResult> getNote(final GetNoteRequest request);

    Mono<GenericNoteResult> update(final UpdateNoteRequest updateNoteRequest);

    Mono<GenericNoteResult> create(final CreateNoteRequest createNoteRequest);
}
