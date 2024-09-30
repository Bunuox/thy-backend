package com.todo.todo.model;

import com.todo.todo.converter.NoteStatusConverter;
import com.todo.todo.model.enumaration.NoteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_NOTE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_notes_seq")
    @SequenceGenerator(name = "t_notes_seq", sequenceName = "T_NOTES_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "desc")
    private String desc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "status")
    @Convert(converter = NoteStatusConverter.class)
    private NoteStatus status;
}
