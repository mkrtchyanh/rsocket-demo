package io.hayk.rsocketdemo.note.content;

import java.util.List;
import java.util.Optional;

public interface NoteContentService {

    UserNote createNoteContent(final CreateNoteContentParam param);

    UserNote updateNoteContent(final UpdateNoteContentParam param);

    Optional<UserNote> getUserNote(final Long id);

    List<UserNote> getUserNotes(final Long userId);
}
