package org.pushpa.boot_crud.helper;

// import org.pushpa.boot_crud.dto.Student;

import lombok.Data;

@Data
public class ResponseStructure<T> {
    String message;
    int status;
    T data;
}
