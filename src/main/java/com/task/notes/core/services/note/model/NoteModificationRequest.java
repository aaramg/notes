package com.task.notes.core.services.note.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.springframework.util.Assert.hasText;

public class NoteModificationRequest {
    private final String title;
    private final String description;

    public NoteModificationRequest(final String title, final String description) {
        hasText(title, "title cannot be empty");
        hasText(description, "description cannot be empty");
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof NoteModificationRequest)) {
            return false;
        }

        final NoteModificationRequest that = (NoteModificationRequest) o;

        return new EqualsBuilder()
                .append(title, that.title)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("description", description)
                .toString();
    }
}
