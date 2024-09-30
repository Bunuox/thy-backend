package com.todo.todo.service.serviceImpl;

import com.todo.todo.model.Note;
import com.todo.todo.model.enumaration.NoteStatus;
import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import com.todo.todo.model.payload.request.note.NoteUpdateRequest;
import com.todo.todo.repository.NoteRepository;
import com.todo.todo.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {


    private final NoteRepository noteRepository;


    @Override
    public Optional<Note> createNote(NoteCreateRequest noteCreateRequest) {
       Note note = Note.builder()
               .title(noteCreateRequest.getTitle())
               .userId(noteCreateRequest.getUserId())
               .desc(noteCreateRequest.getDesc())
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .dueDate(noteCreateRequest.getDueDate())
               .status(NoteStatus.ON_GOING)
               .build();
       try {
           noteRepository.save(note);
           log.info("Note created successfully");
       } catch (Exception e) {
            log.info(e.getLocalizedMessage());
            return Optional.empty();
       }
       return Optional.of(note);
    }

    @Override
    public Optional<Note> updateNote(NoteUpdateRequest noteUpdateRequest) {
        Optional<Note> note = noteRepository.findById(noteUpdateRequest.getId());
        if (note.isPresent()) {
            if(checkUserId(note.get(), noteUpdateRequest.getUserId())) {
                Optional.ofNullable(noteUpdateRequest.getTitle()).ifPresent(note.get()::setTitle);
                Optional.ofNullable(noteUpdateRequest.getDesc()).ifPresent(note.get()::setDesc);
                Optional.ofNullable(noteUpdateRequest.getStatus()).ifPresent(note.get()::setStatus);
                Optional.ofNullable(noteUpdateRequest.getDueDate()).ifPresent(note.get()::setDueDate);
                note.get().setUpdatedAt(LocalDateTime.now());

                try {
                    noteRepository.save(note.get());
                    log.info("Note updated successfully");
                    return note;
                } catch (Exception e) {
                    log.info(e.getLocalizedMessage());
                }
            } else {
                log.info("Note userId does not match request userId {}", noteUpdateRequest.getTitle());
            }
        }
        return Optional.empty();
    }

    @Override
    public Long deleteNote(Long userId ,Long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            if(checkUserId(note.get(), userId)) {
                noteRepository.delete(note.get());
                return noteId;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override
    public List<Note> getAllNotes(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return noteRepository.findByUserId(userId, pageable).getContent();
    }

    boolean checkUserId(Note note, Long userId) {
        return note.getUserId() == userId;
    }
}
