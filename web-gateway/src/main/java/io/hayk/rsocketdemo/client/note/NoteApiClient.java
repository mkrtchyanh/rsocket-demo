package io.hayk.rsocketdemo.client.note;

import io.hayk.rsocketdemo.note.content.*;
import io.hayk.rsocketdemo.note.user.BindExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.BindExternalAccountResult;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountUidResult;
import reactor.core.publisher.Mono;

public interface NoteApiClient {

    Mono<BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest);

    Mono<GetUserNotesResult> getUserNotes(final GetUserNotesRequest request);

    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccount(final GetUserIdByExternalAccountRequest request);

    Mono<GenericNoteResult> getNote(final GetNoteRequest request);

    Mono<GenericNoteResult> update(final UpdateNoteRequest updateNoteRequest);

    Mono<GenericNoteResult> create(final CreateNoteRequest createNoteRequest);
}
