package io.hayk.demo.note.content;

import io.hayk.demo.AbstractDomainEntity;
import io.hayk.demo.note.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
@Access(AccessType.FIELD)
public class NoteContent extends AbstractDomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User createdBy;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "tite", nullable = false)
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

    public NoteContent(final User createdBy, final String text, final String title) {
        this.createdBy = createdBy;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createdBy.id", createdBy.getId())
                .append("text", text)
                .append("title", title)
                .append("lastUpdated", lastUpdated)
                .toString();
    }
}
