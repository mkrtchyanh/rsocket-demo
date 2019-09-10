package io.hayk.rsocketdemo.api.model;

public class UpdateNoteRequest extends SaveNoteRequest {

    private Long noteId;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}

