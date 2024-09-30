package com.todo.todo.service.serviceImpl;

import com.todo.todo.model.User;
import com.todo.todo.model.payload.request.user.UserCreateRequest;
import com.todo.todo.model.payload.request.user.UserLoginRequest;
import com.todo.todo.model.payload.request.user.UserUpdateRequest;
import com.todo.todo.repository.UserRepository;
import com.todo.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Optional<User> createUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .mail(userCreateRequest.getMail())
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .build();
        try {
            userRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            log.info(e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(UserLoginRequest userLoginRequest) {
        Optional<User> user = userRepository.findByUsername(userLoginRequest.getUsername());
        if (user.isPresent()) {
            if(passwordEncoder.matches(userLoginRequest.getPassword(), user.get().getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> updateUser(UserUpdateRequest userUpdateRequest) {
        Optional<User> optionalUser = userRepository.findById(userUpdateRequest.getId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(passwordEncoder.matches(userUpdateRequest.getPassword(), user.getPassword())) {
                try {
                    user.setPassword(passwordEncoder.encode(userUpdateRequest.getNewPassword()));
                    userRepository.save(user);
                    return Optional.of(user);
                } catch (Exception e) {
                    log.info("User could not updated {}", e.getLocalizedMessage());
                }
            }
        } else {
            throw new RuntimeException("Could not found user by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }
}
