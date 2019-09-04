package io.hayk.demo.note.content;

import java.util.List;

public interface NoteContentService {

    NoteContent createNoteContent(final CreateNoteContentParam param);

    NoteContent updateNoteContent(final UpdateNoteContentParam param);

    NoteContent getNoteContent(final Long id);

    List<NoteContent> getUserNotes(final Long userId);
}
