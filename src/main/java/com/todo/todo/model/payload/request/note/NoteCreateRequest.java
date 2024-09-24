package com.todo.todo.model.payload.request.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.todo.model.enumaration.NoteStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteCreateRequest {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("userId")
    private Long userId;

    @NotNull
    @JsonProperty("desc")
    private String desc;

    @NotNull
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @NotNull
    @JsonProperty("dueDate")
    private LocalDateTime dueDate;

    @NotNull
    @JsonProperty("status")
    private NoteStatus status;
}
