package io.hayk.rsocketdemo.note.content;

import java.time.LocalDateTime;

public interface UserNote {

    Long noteId();

    Long userId();

    String title();

    String text();

    LocalDateTime created();

    LocalDateTime updated();

    static UserNote of(final NoteContent noteContent){
        return new ImmutableUserNote(noteContent);
    }
}
