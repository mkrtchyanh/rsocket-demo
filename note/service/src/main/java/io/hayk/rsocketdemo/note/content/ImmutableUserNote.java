package io.hayk.rsocketdemo.note.content;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

final class ImmutableUserNote implements UserNote {


    private final Long noteId;

    private final Long userId;

    private final String title;

    private final String text;

    private final LocalDateTime created;

    private final LocalDateTime updated;

    ImmutableUserNote(final NoteContent noteContent) {
        this.noteId = noteContent.getId();
        this.userId = noteContent.getCreatedBy().getId();
        this.title = noteContent.getTitle();
        this.text = noteContent.getText();
        this.created = noteContent.getCreated();
        this.updated = noteContent.getLastUpdated();
    }

    @Override
    public Long noteId() {
        return noteId;
    }

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public LocalDateTime created() {
        return created;
    }

    @Override
    public LocalDateTime updated() {
        return updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final ImmutableUserNote that = (ImmutableUserNote) o;
        return new EqualsBuilder()
                .append(noteId, that.noteId)
                .append(userId, that.userId)
                .append(title, that.title)
                .append(text, that.text)
                .append(created, that.created)
                .append(updated, that.updated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(noteId)
                .append(userId)
                .append(title)
                .append(text)
                .append(created)
                .append(updated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("noteId", noteId)
                .append("userId", userId)
                .append("title", title)
                .append("text", text)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }
}
