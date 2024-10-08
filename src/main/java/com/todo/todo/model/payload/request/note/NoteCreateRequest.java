package com.todo.todo.model.payload.request.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("dueDate")
    private LocalDate dueDate;

}
