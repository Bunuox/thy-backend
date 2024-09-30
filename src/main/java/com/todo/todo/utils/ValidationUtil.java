package com.todo.todo.utils;

import com.todo.todo.exception.ValidationException;
import com.todo.todo.model.payload.request.note.NoteCreateRequest;
import com.todo.todo.model.payload.request.note.NoteUpdateRequest;
import com.todo.todo.model.payload.request.user.UserCreateRequest;
import com.todo.todo.model.payload.request.user.UserLoginRequest;
import com.todo.todo.model.payload.request.user.UserUpdateRequest;

import java.time.LocalDate;

public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateNoteGetParameters(Long userId, int page, int size) {
        if (userId == null || userId <= 0) {
            throw new ValidationException("Invalid user ID");
        }

        if (page < 0) {
            throw new ValidationException("Invalid page number");
        }

        if (size <= 0) {
            throw new ValidationException("Invalid size");
        }
    }

    public static void validateNotePostParameters(NoteCreateRequest noteCreateRequest) {
        if (noteCreateRequest == null) {
            throw new ValidationException("Invalid noteCreateRequest");
        }
        if (noteCreateRequest.getTitle() == null || noteCreateRequest.getTitle().isEmpty()) {
            throw new ValidationException("Invalid title");
        }
        if(noteCreateRequest.getTitle().length() > 250) {
            throw new ValidationException("Invalid title");
        }
        if(noteCreateRequest.getUserId() == null || noteCreateRequest.getUserId() <= 0) {
            throw new ValidationException("Invalid user ID");
        }
    }

    public static void validateNoteUpdateParameters(NoteUpdateRequest noteUpdateRequest) {
        if (noteUpdateRequest == null) {
            throw new ValidationException("Invalid noteUpdateRequest");
        }

        if(noteUpdateRequest.getUserId() <= 0) {
            throw new ValidationException("Invalid user ID");
        }

        if (noteUpdateRequest.getTitle() != null && !noteUpdateRequest.getTitle().isEmpty()) {
            if (noteUpdateRequest.getTitle().length() > 250) {
                throw new ValidationException("Invalid title");
            }
        }

        if (noteUpdateRequest.getDueDate() != null && noteUpdateRequest.getDueDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Due date cannot be in the past");
        }
    }

    public static void validateNoteDeleteParameters(Long userId ,Long noteId) {
        if (noteId <= 0) {
            throw new ValidationException("Invalid noteId");
        }
        if (userId <= 0) {
            throw new ValidationException("Invalid user ID");
        }
    }

    public static void validateUserCreateParameters(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null) {
            throw new ValidationException("Invalid userCreateRequest");
        }
        if (userCreateRequest.getUsername().isEmpty()) {
            throw new ValidationException("Username cannot be null or empty");
        }
        if (userCreateRequest.getMail().isEmpty() || !isValidEmail(userCreateRequest.getMail())) {
            throw new ValidationException("Invalid email");
        }
        if (userCreateRequest.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be null or empty");
        }
    }

    public static void validateUserUpdateParameters(UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null) {
            throw new ValidationException("Invalid userUpdateRequest");
        }
        if(userUpdateRequest.getId() == null || userUpdateRequest.getId() <= 0) {
            throw new ValidationException("Invalid user ID");
        }

        if(userUpdateRequest.getPassword() == null || userUpdateRequest.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be null or empty");
        }
        if(userUpdateRequest.getNewPassword() == null || userUpdateRequest.getNewPassword().isEmpty()) {
            throw new ValidationException("New password cannot be null or empty");
        }

        if(userUpdateRequest.getConfirmedPassword() == null || userUpdateRequest.getConfirmedPassword().isEmpty()) {
            throw new ValidationException("Confirmed password cannot be null or empty");
        }

        if(!userUpdateRequest.getNewPassword().equals(userUpdateRequest.getConfirmedPassword())) {
            throw new ValidationException("New passwords do not match");
        }
    }

    public static void validateUserLoginParameters(UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new ValidationException("Invalid userLoginRequest");
        }
        if(userLoginRequest.getUsername() == null || userLoginRequest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if(userLoginRequest.getPassword() == null || userLoginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
