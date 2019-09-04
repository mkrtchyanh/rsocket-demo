package io.hayk.demo.note.content;

public class GetNoteRequest {

    private Long noteId;

    public GetNoteRequest() {
        super();
    }

    public GetNoteRequest(final Long noteId) {
        this.noteId = noteId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}
