package com.todo.todo.service;

import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface NoteService {
    Boolean createNote(NoteCreateRequest noteCreateRequest);
}
