package com.todo.todo.service;

import com.todo.todo.model.Note;
import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import com.todo.todo.model.payload.request.note.NoteUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NoteService {
    Optional<Note> createNote(NoteCreateRequest noteCreateRequest);

    Optional<Note> updateNote(NoteUpdateRequest noteUpdateRequest);

    Long deleteNote(Long userId, Long noteId);

    List<Note> getAllNotes(Long userId, int page, int size);
}
