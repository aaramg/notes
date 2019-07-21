package com.task.notes.api.commons.model.note;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NoteModificationRequestDto {
    @NotBlank
    @Size(max = 50)
    private final String title;

    @Size(max = 1000)
    private final String description;

    @JsonCreator
    public NoteModificationRequestDto(
            @JsonProperty("title") final String title,
            @JsonProperty("description") final String description
    ) {
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

        if (!(o instanceof NoteModificationRequestDto)) {
            return false;
        }

        final NoteModificationRequestDto that = (NoteModificationRequestDto) o;

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
