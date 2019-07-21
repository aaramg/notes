package com.task.notes.core.services.note.impl;

import com.task.notes.commons.exception.ResourceNotFoundException;
import com.task.notes.core.services.note.NoteService;
import com.task.notes.core.services.note.model.NoteModificationRequest;
import com.task.notes.core.services.user.UserService;
import com.task.notes.data.entities.Note;
import com.task.notes.data.entities.User;
import com.task.notes.data.repositories.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
public class NoteServiceImpl implements NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
    private static final String ID_FIELD_NAME = "id";

    @Autowired
    private NoteRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Note> find(final long id, final long userId) {
        logger.trace("Finding Note:{} for User:{}...", id, userId);

        final Optional<Note> result = repository.findByIdAndUserId(id, userId);

        logger.debug("Done finding Note:{} for User:{}.", id, userId);
        return result;
    }

    @Override
    public Note get(final long id, final long userId) {
        logger.trace("Getting Note:{} for User:{}...", id, userId);

        final Note result = find(id, userId).orElseThrow(() -> ResourceNotFoundException.createInstance(
                Note.class,
                ID_FIELD_NAME,
                id
        ));

        logger.debug("Done getting Note:{} for User:{}.", id, userId);
        return result;
    }

    @Override
    public Note create(final long userId, final NoteModificationRequest request) {
        notNull(request, "request cannot be null");

        logger.trace("Creating Note for User:{} with given request:{}", userId, request);

        final User user = userService.get(userId);

        final Note note = new Note(request.getTitle(), request.getDescription(), user, LocalDateTime.now());
        final Note result = repository.save(note);

        logger.debug("Done creating Note:{} for User:{} with given request:{}", result.getId(), userId, request);
        return result;
    }

    @Override
    public Note update(final long id, final long userId, final NoteModificationRequest request) {
        notNull(request, "request cannot be null");

        logger.trace("Updating Note:{} for User:{} with given request:{}", id, userId, request);

        final Note note = get(id, userId);
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());

        final User user = userService.get(userId);
        note.setUser(user);
        note.setUpdated(LocalDateTime.now());

        final Note result = repository.save(note);

        logger.debug("Done updating Note:{} for User:{} with given request:{}", id, userId, request);
        return result;
    }

    @Override
    public void delete(final long id, final long userId) {
        logger.trace("Deleting Note:{} for User:{}...", id, userId);

        final Note result = get(id, userId);
        repository.delete(result);

        logger.debug("Done deleting Note:{} for User:{}.", id, userId);
    }
}
