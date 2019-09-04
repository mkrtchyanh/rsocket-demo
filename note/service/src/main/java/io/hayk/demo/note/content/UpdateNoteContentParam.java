package io.hayk.demo.note.content;

public interface UpdateNoteContentParam {

    static UpdateNoteContentParam of(final Long noteId, final String title, final String text) {
        return new UpdateNoteContentParam() {
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

    Long noteId();

    String text();

    String title();
}
