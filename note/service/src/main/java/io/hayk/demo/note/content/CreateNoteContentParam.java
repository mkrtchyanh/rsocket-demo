package io.hayk.demo.note.content;

public interface CreateNoteContentParam {

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

    Long userId();

    String text();

    String title();
}
