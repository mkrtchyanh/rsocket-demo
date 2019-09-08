package io.hayk.rsocketdemo.note.content;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class NoteContentDto {

    private Long id;

    private Long userId;

    private String text;

    private String title;

    private LocalDateTime lastUpdated;

    public NoteContentDto() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NoteContentDto that = (NoteContentDto) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(userId, that.userId)
                .append(text, that.text)
                .append(title, that.title)
                .append(lastUpdated, that.lastUpdated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(userId)
                .append(text)
                .append(title)
                .append(lastUpdated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("text", text)
                .append("title", title)
                .append("lastUpdated", lastUpdated)
                .toString();
    }
}
