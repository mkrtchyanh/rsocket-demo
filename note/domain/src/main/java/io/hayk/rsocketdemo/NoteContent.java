package io.hayk.rsocketdemo;

import io.hayk.rsocketdemo.common.AbstractDomainEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note", indexes = {
        @Index(name = "idx_note_user_id", columnList = "user_id")
})
@Access(AccessType.FIELD)
public class NoteContent extends AbstractDomainEntity {

    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    /**
     * Accessible by reflection.
     * <p>
     * Will be used by frameworks depending on default constructor
     */
    NoteContent() {
        super();
    }

    public NoteContent(final Long userId, final String text, final String title) {
        this.userId = userId;
        this.text = text;
        this.title = title;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void changeText(final String text) {
        this.text = text;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void changeTitle(String title) {
        this.title = title;
        this.lastUpdated = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
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
