package io.hayk.rsocketdemo;

import java.util.Collections;
import java.util.List;

public class GetUserNotesResult extends AbstractResult {

    private List<NoteContentDto> notes;

    private List<String> errors;

    private GetUserNotesResult(final List<NoteContentDto> notes, final List<String> errors) {
        super(errors);
        this.notes = notes;
    }

    public GetUserNotesResult() {
        super();
    }

    public static GetUserNotesResult failedWith(final List<String> errors) {
        return new GetUserNotesResult(Collections.emptyList(), errors);
    }

    public static GetUserNotesResult failedWithInternalError() {
        return new GetUserNotesResult(
                Collections.emptyList(),
                Collections.singletonList("Internal server error!")
        );
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
}
