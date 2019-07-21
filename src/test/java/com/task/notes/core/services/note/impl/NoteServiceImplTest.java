package com.task.notes.core.services.note.impl;

import com.task.notes.commons.exception.ResourceNotFoundException;
import com.task.notes.core.services.helper.TestHelper;
import com.task.notes.core.services.user.UserService;
import com.task.notes.data.entities.Note;
import com.task.notes.data.repositories.NoteRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {

    @Mock
    private NoteRepository repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private NoteServiceImpl service;

    @After
    public void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(repository, userService);
    }

    @Test
    public void get() {
        final long id = -1;
        final long userId = -1;

        when(repository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(TestHelper.createNote()));

        final Note result = service.get(id, userId);
        assertThat(result).isNotNull();

        verify(repository).findByIdAndUserId(id, userId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_NotFound() {
        final long id = -1;
        final long userId = -1;

        when(repository.findByIdAndUserId(id, userId)).thenReturn(Optional.empty());

        try {
            service.get(id, userId);
        } finally {
            verify(repository).findByIdAndUserId(id, userId);
        }
    }

    @Test
    public void create() {
        final long userId = -1;

        when(userService.get(userId)).thenReturn(TestHelper.createUser(userId));
        when(repository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final Note result = service.create(userId, TestHelper.getModificationRequest());
        assertThat(result).isNotNull();

        verify(userService).get(userId);
        verify(repository).save(any(Note.class));
    }

    @Test
    public void update() {
        final long id = -1;
        final long userId = -1;

        when(userService.get(userId)).thenReturn(TestHelper.createUser(userId));
        when(repository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(TestHelper.createNote()));
        when(repository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final Note result = service.update(id, userId, TestHelper.getModificationRequest());
        assertThat(result).isNotNull();

        verify(userService).get(userId);
        verify(repository).findByIdAndUserId(id, userId);
        verify(repository).save(any(Note.class));
    }

    @Test
    public void delete() {
        final long id = -1;
        final long userId = -1;

        doNothing().when(repository).delete(any(Note.class));
        when(repository.findByIdAndUserId(id, userId)).thenReturn(Optional.of(TestHelper.createNote()));

        service.delete(id, userId);

        verify(repository).findByIdAndUserId(id, userId);
        verify(repository).delete(any(Note.class));
    }
}
