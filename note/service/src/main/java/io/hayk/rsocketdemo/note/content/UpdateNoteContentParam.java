package io.hayk.rsocketdemo.note.content;

public interface UpdateNoteContentParam {

    Long userId();

    Long noteId();

    String text();

    String title();

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
