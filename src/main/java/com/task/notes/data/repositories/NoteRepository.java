package com.task.notes.data.repositories;

import com.task.notes.data.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByIdAndUserId(long id, long userId);
}
