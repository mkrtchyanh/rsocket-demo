package io.hayk.rsocketdemo;

public interface CreateNoteContentParam extends BaseSaveNoteContentParam{

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
