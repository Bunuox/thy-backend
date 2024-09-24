package com.todo.todo.model;

import com.todo.todo.converter.NoteStatusConverter;
import com.todo.todo.model.enumaration.NoteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TBL_NOTE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    //Generate will be added
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "desc")
    private String desc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status")
    @Convert(converter = NoteStatusConverter.class)
    private NoteStatus status;
}
