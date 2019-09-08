package io.hayk.rsocketdemo;

public interface UpdateNoteContentParam extends BaseSaveNoteContentParam{

    Long noteId();

    static UpdateNoteContentParam of(final Long userId, final Long noteId, final String title, final String text) {
        return new UpdateNoteContentParam() {

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
