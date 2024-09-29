package com.todo.todo.model.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String authToken;
    private Long userId;
    private String username;
}
