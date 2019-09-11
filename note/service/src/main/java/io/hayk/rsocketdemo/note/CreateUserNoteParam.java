package io.hayk.rsocketdemo.note;

public interface CreateUserNoteParam extends BaseSaveNoteContentParam{

    static CreateUserNoteParam of(final Long userId,
                                  final String text,
                                  final String title) {
        return new CreateUserNoteParam() {
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
