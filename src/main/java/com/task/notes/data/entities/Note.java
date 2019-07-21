package com.task.notes.data.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "note")
public class Note extends BaseEntity {

    @Id
    @SequenceGenerator(name = "note_sequence", sequenceName = "note_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_sequence")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    // Name has been changed to 'description' to avoid the confusion between class and field name
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Note(final String title, final User user, final LocalDateTime created) {
        super(created);
        this.title = title;
        this.user = user;
    }

    public Note(final String title, final String description, final User user, final LocalDateTime created) {
        super(created);
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Note)) {
            return false;
        }

        final Note note = (Note) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(id, note.id)
                .append(title, note.title)
                .append(description, note.description)
                .append(user, note.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(id)
                .append(title)
                .append(description)
                .append(user)
                .toHashCode();
    }
}
