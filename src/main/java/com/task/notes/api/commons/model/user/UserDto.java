package com.task.notes.api.commons.model.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class UserDto {
    private long id;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;

    public UserDto() {
        super();
    }

    public UserDto(final long id, final String email, final LocalDateTime created, final LocalDateTime updated) {
        this.id = id;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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

        if (!(o instanceof UserDto)) {
            return false;
        }

        final UserDto userDto = (UserDto) o;

        return new EqualsBuilder()
                .append(id, userDto.id)
                .append(email, userDto.email)
                .append(created, userDto.created)
                .append(updated, userDto.updated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(email)
                .append(created)
                .append(updated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", email)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }
}
