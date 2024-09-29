package com.todo.todo.model.payload.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @JsonProperty("password")
    private String password;

    @JsonProperty("newPassword")
    private String newPassword;

    @JsonProperty("confirmedPassword")
    private String confirmedPassword;
}
