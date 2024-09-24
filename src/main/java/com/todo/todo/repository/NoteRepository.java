package com.todo.todo.repository;

import com.todo.todo.model.Note;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
}
