package io.hayk.rsocketdemo.client.note;

import io.hayk.rsocketdemo.api.model.*;
import reactor.core.publisher.Mono;

public interface NoteApiClient {

    Mono<GetUserNotesResult> getUserNotes(final GetUserNotesRequest request);

    Mono<GenericNoteResult> getNote(final GetNoteRequest request);

    Mono<GenericNoteResult> update(final UpdateNoteRequest updateNoteRequest);

    Mono<GenericNoteResult> create(final CreateNoteRequest createNoteRequest);
}
