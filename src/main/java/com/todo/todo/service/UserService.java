package com.todo.todo.service;

import com.todo.todo.model.payload.request.user.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean createUser(UserCreateRequest userCreateRequest);
}
