package com.todo.todo.service.serviceImpl;

import com.todo.todo.model.payload.request.user.UserCreateRequest;
import com.todo.todo.repository.UserRepository;
import com.todo.todo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Boolean createUser(UserCreateRequest userCreateRequest) {
        return null;
    }
}
