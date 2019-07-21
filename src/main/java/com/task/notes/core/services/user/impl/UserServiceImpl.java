package com.task.notes.core.services.user.impl;

import com.task.notes.commons.exception.ResourceNotFoundException;
import com.task.notes.core.services.user.UserService;
import com.task.notes.data.entities.User;
import com.task.notes.data.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String ID_FIELD_NAME = "id";

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> find(final long id) {
        logger.trace("Finding User:{}...", id);

        final Optional<User> result = repository.findById(id);

        logger.debug("Done finding User:{}.", id);
        return result;
    }

    @Override
    public User get(final long id) {
        logger.trace("Getting User:{}...", id);

        final User result = find(id).orElseThrow(() -> ResourceNotFoundException.createInstance(
                User.class,
                ID_FIELD_NAME,
                id
        ));

        logger.debug("Done getting User:{}.", id);
        return result;
    }
}
