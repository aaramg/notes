package com.task.notes.core.services.helper;

import com.task.notes.core.services.note.model.NoteModificationRequest;
import com.task.notes.data.entities.Note;
import com.task.notes.data.entities.User;

import java.time.LocalDateTime;

public final class TestHelper {
    private TestHelper() {
        throw new IllegalAccessError("Utility class cannot be instantiated.");
    }

    public static Note createNote(final String title, final String description, final long userId) {
        return new Note(title, description, createUser(userId), LocalDateTime.now());
    }

    public static Note createNote() {
        return createNote("title", "description", -1);
    }

    public static User createUser(final long userId) {
        final User user = new User(
                "email@email.com",
                "$2a$12$.lR3Zmi4ZLUInYyS9dsKSOMINGItl.5VI2gFoaQ2bGHCv5bWTrccG",
                LocalDateTime.now()
        );
        user.setId(userId);
        return user;
    }

    public static NoteModificationRequest getModificationRequest() {
        return new NoteModificationRequest("name", "description");
    }
}
