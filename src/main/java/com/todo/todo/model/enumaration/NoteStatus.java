package com.todo.todo.model.enumaration;

public enum NoteStatus {

    ON_GOING("G"),
    ON_HOLD("H"),
    DONE("D");


    private final String code;

    NoteStatus(String code) {
        this.code = code;
    }

    public String getCode() {return code;}

    public static NoteStatus getByCode(String code) {
        for (NoteStatus noteStatus : NoteStatus.values()) {
            if (noteStatus.getCode().equals(code)) {
                return noteStatus;
            }
        }
        throw new IllegalArgumentException("Note status not found");
    }
}
