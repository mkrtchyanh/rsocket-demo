package io.hayk.demo.note.content;

import io.hayk.demo.common.ApplicationError;

import java.util.Collections;
import java.util.List;

public class GetUserNotesResult {

    private List<NoteContentDto> notes;

    private List<? extends ApplicationError> errors;

    private GetUserNotesResult(final List<NoteContentDto> notes, final List<? extends ApplicationError> errors) {
        this.notes = notes;
        this.errors = errors;
    }

    public GetUserNotesResult() {
        super();
    }

    public static GetUserNotesResult failedWith(final List<? extends ApplicationError> errors) {
        return new GetUserNotesResult(Collections.emptyList(), errors);
    }

    public static GetUserNotesResult withNotes(final List<NoteContentDto> notes) {
        return new GetUserNotesResult(notes, Collections.emptyList());
    }

    public List<NoteContentDto> getNotes() {
        return notes == null ? Collections.emptyList() : notes;
    }

    public void setNotes(List<NoteContentDto> notes) {
        this.notes = notes;
    }

    public List<? extends ApplicationError> getErrors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    public void setErrors(List<? extends ApplicationError> errors) {
        this.errors = errors;
    }
}
