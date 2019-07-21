package com.task.notes.core.services.note;

import com.task.notes.commons.exception.ResourceNotFoundException;
import com.task.notes.core.services.AbstractServiceIntegrationTest;
import com.task.notes.core.services.helper.TestHelper;
import com.task.notes.core.services.note.model.NoteModificationRequest;
import com.task.notes.data.entities.Note;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class NoteServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private NoteService service;

    @Test
    public void get() {
        final long id = -1;
        final long userId = -1;

        final Note result = service.get(id, userId);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUser().getId()).isEqualTo(userId);
    }

    @Test
    public void create() {
        final long userId = -1;

        final Note note = service.create(userId, TestHelper.getModificationRequest());
        flushAndClear();
        final Note result = service.get(note.getId(), userId);
        assertThat(result).isNotNull();
        assertThat(result.getUser().getId()).isEqualTo(userId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void create_userNotFound() {
        final long userId = -3;

        service.create(userId, TestHelper.getModificationRequest());
    }

    @Test
    public void update() {
        final long id = -1;
        final long userId = -1;
        final String titleBefore = "Title -1";
        final String descriptionBefore = "Description -1";

        final NoteModificationRequest modificationRequest = TestHelper.getModificationRequest();
        final String titleAfter = modificationRequest.getTitle();
        final String descriptionAfter = modificationRequest.getDescription();

        final Note resultBefore = service.get(id, userId);
        assertThat(resultBefore).isNotNull();
        assertThat(resultBefore.getTitle()).isEqualTo(titleBefore);
        assertThat(resultBefore.getDescription()).isEqualTo(descriptionBefore);

        final Note note = service.update(id, userId, modificationRequest);
        flushAndClear();

        final Note resultAfter = service.get(note.getId(), userId);
        assertThat(resultAfter).isNotNull();
        assertThat(resultAfter.getTitle()).isEqualTo(titleAfter);
        assertThat(resultAfter.getDescription()).isEqualTo(descriptionAfter);
    }

    @Test
    public void delete() {
        final long id = -1;
        final long userId = -1;

        final Note result = service.get(id, userId);
        assertThat(result).isNotNull();

        service.delete(id, userId);

        try {
            service.get(id, userId);
            fail("ResourceNotFoundException is not thrown");
        } catch (ResourceNotFoundException ex) {
            //This means the reource is deleted
        }
    }
}
