package io.hayk.rsocketdemo.rest.note.model;

public class UpdateNoteWebRequest extends SaveNoteWebRequest {

    private Long noteId;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}
