package io.hayk.rsocketdemo.note;

import java.util.List;
import java.util.Optional;

public interface UserNoteService {

    UserNote createUserNote(final CreateUserNoteParam param);

    UserNote updateUserNote(final UpdateUserNoteParam param);

    Optional<UserNote> getUserNote(final Long id);

    List<UserNote> getUserNotes(final Long userId);
}
