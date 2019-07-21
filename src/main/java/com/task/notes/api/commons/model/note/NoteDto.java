package com.task.notes.api.commons.model.note;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.notes.api.commons.model.user.UserDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class NoteDto {
    private long id;
    private String title;
    private String description;
    private UserDto user;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated;

    public NoteDto() {
        super();
    }

    public NoteDto(
            final long id,
            final String title,
            final String description,
            final UserDto user,
            final LocalDateTime created,
            final LocalDateTime updated
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof NoteDto)) {
            return false;
        }

        final NoteDto noteDto = (NoteDto) o;

        return new EqualsBuilder()
                .append(id, noteDto.id)
                .append(title, noteDto.title)
                .append(description, noteDto.description)
                .append(user, noteDto.user)
                .append(created, noteDto.created)
                .append(updated, noteDto.updated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(title)
                .append(description)
                .append(user)
                .append(created)
                .append(updated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .append("user", user)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }
}
