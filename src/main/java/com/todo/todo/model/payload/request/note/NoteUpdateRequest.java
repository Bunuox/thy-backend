package com.todo.todo.model.payload.request.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.todo.model.enumaration.NoteStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteUpdateRequest {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("dueDate")
    private LocalDate dueDate;

    @JsonProperty("status")
    private NoteStatus status;
}
