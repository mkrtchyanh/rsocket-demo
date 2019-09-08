package io.hayk.rsocketdemo.note.content;

public class UpdateNoteRequest extends SaveNoteRequest {

    private Long noteId;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
}

