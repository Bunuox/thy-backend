package com.todo.todo.service;

import com.todo.todo.model.User;
import com.todo.todo.model.payload.request.user.UserCreateRequest;
import com.todo.todo.model.payload.request.user.UserLoginRequest;
import com.todo.todo.model.payload.request.user.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<User> createUser(UserCreateRequest userCreateRequest);

    List<User> getAllUsers();

    Optional<User> getUserByUsername(UserLoginRequest userLoginRequest);

    Optional<User> getUserByUsername(String username);

    Optional<User> updateUser(UserUpdateRequest userUpdateRequest);

    Optional<User> getUserByMail(String mail);
}
