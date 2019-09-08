package io.hayk.rsocketdemo.note.content;

public interface CreateNoteContentParam {

    Long userId();

    String text();

    String title();

    static CreateNoteContentParam of(final Long userId,
                                     final String text,
                                     final String title) {
        return new CreateNoteContentParam() {
            @Override
            public Long userId() {
                return userId;
            }

            @Override
            public String text() {
                return text;
            }

            @Override
            public String title() {
                return title;
            }
        };
    }
}
