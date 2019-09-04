package io.hayk.demo.note.content;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class NoteContentDto {

    private Long userId;

    private String text;

    private String title;

    private LocalDateTime lastUpdated;

    public NoteContentDto() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final NoteContentDto that = (NoteContentDto) o;
        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(text, that.text)
                .append(title, that.title)
                .append(lastUpdated, that.lastUpdated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(text)
                .append(title)
                .append(lastUpdated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("text", text)
                .append("title", title)
                .append("lastUpdated", lastUpdated)
                .toString();
    }
}
