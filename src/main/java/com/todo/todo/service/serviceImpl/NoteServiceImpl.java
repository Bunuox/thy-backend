package com.todo.todo.service.serviceImpl;

import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import com.todo.todo.repository.NoteRepository;
import com.todo.todo.service.NoteService;
import org.springframework.stereotype.Component;

@Component
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Boolean createNote(NoteCreateRequest noteCreateRequest) {
        return null;
    }
}
