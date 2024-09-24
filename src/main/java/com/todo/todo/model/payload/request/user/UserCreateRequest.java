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
public class UserCreateRequest {

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

}
