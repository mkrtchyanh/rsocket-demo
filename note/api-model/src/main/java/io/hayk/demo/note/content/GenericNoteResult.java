package io.hayk.demo.note.content;

import io.hayk.demo.common.ApplicationError;

import java.util.Collections;
import java.util.List;

public class GenericNoteResult {

    private NoteContentDto noteContent;

    private List<? extends ApplicationError> errors;

    public GenericNoteResult(List<? extends ApplicationError> errors) {
        this.errors = errors;
    }

    public GenericNoteResult(NoteContentDto noteContent) {
        this.noteContent = noteContent;
    }

    public GenericNoteResult() {
        super();
    }

    public NoteContentDto getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(NoteContentDto noteContent) {
        this.noteContent = noteContent;
    }

    public List<? extends ApplicationError> getErrors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    public void setErrors(List<? extends ApplicationError> errors) {
        this.errors = errors;
    }
}
