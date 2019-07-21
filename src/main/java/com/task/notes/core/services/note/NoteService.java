package com.task.notes.core.services.note;

import com.task.notes.core.services.note.model.NoteModificationRequest;
import com.task.notes.data.entities.Note;

import java.util.Optional;

public interface NoteService {

    Optional<Note> find(long id, long userId);

    Note get(long id, long userId);

    Note create(long userId, NoteModificationRequest request);

    Note update(long id, long userId, NoteModificationRequest request);

    void delete(long id, long userId);
}
