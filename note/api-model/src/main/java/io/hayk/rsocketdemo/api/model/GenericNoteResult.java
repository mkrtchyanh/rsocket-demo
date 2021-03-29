package io.hayk.rsocketdemo.api.model;

import java.util.List;

public class GenericNoteResult extends AbstractResult {

    private NoteContentDto note;

    public GenericNoteResult(final List<String> errors) {
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
