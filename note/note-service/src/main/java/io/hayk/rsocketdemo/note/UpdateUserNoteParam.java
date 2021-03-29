package io.hayk.rsocketdemo.note;

public interface UpdateUserNoteParam extends BaseSaveNoteContentParam{

    Long noteId();

    static UpdateUserNoteParam of(final Long userId, final Long noteId, final String title, final String text) {
        return new UpdateUserNoteParam() {

            @Override
            public Long userId() {
                return userId;
            }

            @Override
            public Long noteId() {
                return noteId;
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
