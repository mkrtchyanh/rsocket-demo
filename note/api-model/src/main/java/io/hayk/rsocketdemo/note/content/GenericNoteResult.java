package io.hayk.rsocketdemo.note.content;

import io.hayk.rsocketdemo.note.common.AbstractResult;
import io.hayk.rsocketdemo.note.common.ApplicationError;

import java.util.List;

public class GenericNoteResult extends AbstractResult {

    private NoteContentDto note;

    public GenericNoteResult(final List<? extends ApplicationError> errors) {
        super(errors);
    }

    public GenericNoteResult(final NoteContentDto noteContent) {
        this.note = noteContent;
    }

    public GenericNoteResult() {
        super();
    }

    public NoteContentDto getNote() {
        return note;
    }

    public void setNote(final NoteContentDto note) {
        this.note = note;
    }
}
