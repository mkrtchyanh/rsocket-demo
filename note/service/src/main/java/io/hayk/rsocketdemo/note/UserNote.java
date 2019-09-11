package io.hayk.rsocketdemo.note;

import java.time.LocalDateTime;

public interface UserNote {

    Long noteId();

    Long userId();

    String title();

    String text();

    LocalDateTime created();

    LocalDateTime updated();
}
