package com.todo.todo.repository;

import com.todo.todo.model.Note;
import com.todo.todo.model.enumaration.NoteStatus;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE (" +
            "LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
            "OR LOWER(n.desc) LIKE LOWER(CONCAT('%', :desc, '%'))) " +
            "AND n.userId = :userId AND n.status = :status")
    Page<Note> findByTitleOrDescAndUserIdAndStatus(
            @Param("title") String title,
            @Param("desc") String desc,
            @Param("userId") Long userId,
            @Param("status") NoteStatus status,
            Pageable pageable);

    Page<Note> findByTitleContainingOrDescContainingAndUserId(String titleKeyword, String descKeyword, Long userId, Pageable pageable);

}
