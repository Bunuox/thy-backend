package com.todo.todo.controller;

import com.todo.todo.exception.ValidationException;
import com.todo.todo.model.Note;
import com.todo.todo.model.enumaration.ResponseCodes;
import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import com.todo.todo.model.payload.request.note.NoteUpdateRequest;
import com.todo.todo.model.payload.response.GenericRestResponse;
import com.todo.todo.service.NoteService;
import com.todo.todo.utils.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NotesController {

    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{userId}/{page}/{size}")
    public ResponseEntity<GenericRestResponse<List<Note>>> getAllNotes(
            @Validated @PathVariable Long userId, @PathVariable int page, @PathVariable int size) {

        ValidationUtil.validateNoteGetParameters(userId, page, size);
        GenericRestResponse<List<Note>> response = GenericRestResponse
                .generateResponse(noteService.getAllNotes(userId, page, size), ResponseCodes.SUCCESS);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<GenericRestResponse<Note>> createNote(@Validated @RequestBody NoteCreateRequest noteCreateRequest) {
        GenericRestResponse<Note> genericRestResponse;
        ValidationUtil.validateNotePostParameters(noteCreateRequest);
        Optional<Note> noteOptional = noteService.createNote(noteCreateRequest);
        genericRestResponse = noteOptional.map(note ->
                GenericRestResponse.generateResponse(note, ResponseCodes.SUCCESS)).orElseGet(() ->
                GenericRestResponse.generateResponse(null, ResponseCodes.NOTE_COULD_NOT_CREATED));
        return ResponseEntity.ok(genericRestResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<GenericRestResponse<Note>> updateNote(@Validated @RequestBody NoteUpdateRequest noteUpdateRequest) {
        GenericRestResponse<Note> genericRestResponse;
        ValidationUtil.validateNoteUpdateParameters(noteUpdateRequest);
        Optional<Note> noteOptional = noteService.updateNote(noteUpdateRequest);
        genericRestResponse = noteOptional.map(note ->
                GenericRestResponse.generateResponse(note, ResponseCodes.SUCCESS))
                .orElseGet(() -> GenericRestResponse.generateResponse(null, ResponseCodes.NOTE_COULD_NOT_UPDATED));
        return ResponseEntity.ok(genericRestResponse);
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<GenericRestResponse<Object>> deleteNote(@Validated @PathVariable Long userId, @PathVariable Long id) {
        ValidationUtil.validateNoteDeleteParameters(userId, id);
        HashMap<String, Long> data = new HashMap<>();
        if(noteService.deleteNote(userId, id) == Long.MIN_VALUE) {
            return ResponseEntity.ok(GenericRestResponse.generateResponse(null, ResponseCodes.NOTE_COULD_NOT_DELETED));
        }
        data.put("id", id);
        return ResponseEntity.ok(GenericRestResponse.generateResponse(data, ResponseCodes.SUCCESS));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GenericRestResponse<Object>> handleValidationException(ValidationException e) {
        return ResponseEntity.badRequest().body(GenericRestResponse.generateErrorResponse(e.getMessage()));
    }

}
