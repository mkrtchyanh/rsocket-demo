package io.hayk.rsocketdemo;

import java.time.LocalDateTime;

public interface UserNote {

    Long noteId();

    Long userId();

    String title();

    String text();

    LocalDateTime created();

    LocalDateTime updated();
}
