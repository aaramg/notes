package com.task.notes.core.services.user;

import com.task.notes.data.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> find(long id);

    User get(long id);
}
