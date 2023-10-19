package org.pushpa.boot_crud.exception;

public class StudentIdInvalidException extends RuntimeException {
    String message;

    public StudentIdInvalidException(String string) {
        this.message = string;
    }
    public String getMessage() {
        return message;
    }
}
