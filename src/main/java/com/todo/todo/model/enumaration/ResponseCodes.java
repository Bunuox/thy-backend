package com.todo.todo.model.enumaration;

public enum ResponseCodes {

    SUCCESS(0, "Success"),
    USER_NOT_FOUND(1, "User could not found."),
    USER_COULD_NOT_CREATED(3, "User could not created."),
    NOTE_COULD_NOT_CREATED(4, "Note could not created."),
    NOTE_COULD_NOT_UPDATED(5, "Note could not updated."),
    NOTE_COULD_NOT_DELETED(6, "Note could not deleted."),
    USER_COULD_NOT_UPDATED(7, "User could not updated.");

    private final int code;
    private final String message;

    ResponseCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
