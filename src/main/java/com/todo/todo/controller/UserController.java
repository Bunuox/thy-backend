package com.todo.todo.controller;

import com.todo.todo.exception.ValidationException;
import com.todo.todo.model.User;
import com.todo.todo.model.enumaration.ResponseCodes;
import com.todo.todo.model.payload.request.user.UserCreateRequest;
import com.todo.todo.model.payload.request.user.UserLoginRequest;
import com.todo.todo.model.payload.request.user.UserUpdateRequest;
import com.todo.todo.model.payload.response.GenericRestResponse;
import com.todo.todo.model.payload.response.LoginResponse;
import com.todo.todo.security.JwtTokenProvider;
import com.todo.todo.service.UserService;
import com.todo.todo.utils.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider ){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<GenericRestResponse<LoginResponse>> getUser(@Validated  @RequestBody  UserLoginRequest userLoginRequest) {
        GenericRestResponse<LoginResponse> genericRestResponse;
        ValidationUtil.validateUserLoginParameters(userLoginRequest);
        Optional<User> user = userService.getUserByUsername(userLoginRequest);
        LoginResponse loginResponse = new LoginResponse();

        if(user.isPresent()) {
            loginResponse.setUserId(user.get().getId());
            loginResponse.setUsername(user.get().getUsername());
            loginResponse.setAuthToken(jwtTokenProvider.generateToken(user.get().getId()));
            genericRestResponse = GenericRestResponse.generateResponse(loginResponse, ResponseCodes.SUCCESS);

        } else {
            genericRestResponse = GenericRestResponse.generateResponse(null, ResponseCodes.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(genericRestResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericRestResponse<Long>> register(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        ValidationUtil.validateUserCreateParameters(userCreateRequest);
        if(userService.getUserByUsername(userCreateRequest.getUsername()).isPresent()
                || userService.getUserByMail(userCreateRequest.getMail()).isPresent()) {
            return ResponseEntity.badRequest().body(GenericRestResponse.generateErrorResponse("User Already exists"));
        }
        GenericRestResponse<Long> genericRestResponse;
        Optional<User> user = userService.createUser(userCreateRequest);

        genericRestResponse = user.map(value ->
                GenericRestResponse.generateResponse(value.getId(), ResponseCodes.SUCCESS))
                .orElseGet(() -> GenericRestResponse.generateResponse(null, ResponseCodes.USER_COULD_NOT_CREATED));
        return ResponseEntity.ok(genericRestResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<GenericRestResponse<Long>> update(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        ValidationUtil.validateUserUpdateParameters(userUpdateRequest);
        GenericRestResponse<Long> genericRestResponse;
        Optional<User> user = userService.updateUser(userUpdateRequest);
        genericRestResponse = user.map(value ->
                GenericRestResponse.generateResponse(value.getId(), ResponseCodes.SUCCESS))
                .orElseGet(() -> GenericRestResponse.generateResponse(null, ResponseCodes.USER_COULD_NOT_UPDATED));
        return ResponseEntity.ok(genericRestResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GenericRestResponse<Object>> handleValidationException(ValidationException e) {
        return ResponseEntity.badRequest().body(GenericRestResponse.generateErrorResponse(e.getMessage()));
    }
}
