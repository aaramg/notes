package com.task.notes.api.facade.controllers.impl;

import com.task.notes.api.commons.model.note.NoteDto;
import com.task.notes.api.commons.model.note.NoteModificationRequestDto;
import com.task.notes.api.facade.controllers.NotesController;
import com.task.notes.api.facade.security.model.UserPrincipal;
import com.task.notes.core.services.note.NoteService;
import com.task.notes.core.services.note.model.NoteModificationRequest;
import com.task.notes.core.services.user.impl.UserServiceImpl;
import com.task.notes.data.entities.Note;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

@RestController
public class NotesControllerImpl implements NotesController {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private NoteService service;

    @Override
    public NoteDto get(final long id) {
        final long userId = Objects.requireNonNull(getUserId());
        logger.debug("Getting Note:{} for User:{}...", id, userId);

        final Note result = service.get(id, userId);
        final NoteDto resultDto = mapper.map(result, NoteDto.class);

        logger.info("Done getting Note:{} for User:{}.", id, userId);
        return resultDto;
    }

    @Override
    public NoteDto create(@NotNull @Valid final NoteModificationRequestDto requestDto) {
        notNull(requestDto, "requestDto cannot be null");

        final long userId = Objects.requireNonNull(getUserId());
        logger.debug("Creating Note for User:{} with given request:{}", userId, requestDto);

        final NoteModificationRequest request = mapper.map(requestDto, NoteModificationRequest.class);
        final Note result = service.create(userId, request);
        final NoteDto resultDto = mapper.map(result, NoteDto.class);

        logger.info("Done creating Note:{} for User:{} with given request:{}", resultDto.getId(), userId, requestDto);
        return resultDto;
    }

    @Override
    public NoteDto update(final long id, @NotNull @Valid final NoteModificationRequestDto requestDto) {
        notNull(requestDto, "requestDto cannot be null");

        final long userId = Objects.requireNonNull(getUserId());
        logger.debug("Updating Note:{} for User:{} with given request:{}", id, userId, requestDto);

        final NoteModificationRequest request = mapper.map(requestDto, NoteModificationRequest.class);
        final Note result = service.update(id, userId, request);
        final NoteDto resultDto = mapper.map(result, NoteDto.class);

        logger.info("Done updating Note:{} for User:{} with given request:{}", id, userId, requestDto);
        return resultDto;
    }

    @Override
    public void delete(final long id) {
        final long userId = Objects.requireNonNull(getUserId());
        logger.debug("Deleting Note:{} for User:{}...", id, userId);

        service.delete(id, userId);
        logger.info("Done deleting Note:{} for User:{}.", id, userId);
    }

    private static Long getUserId() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserPrincipal) {
            return ((UserPrincipal) userDetails).getId();
        }
        return null;
    }
}
