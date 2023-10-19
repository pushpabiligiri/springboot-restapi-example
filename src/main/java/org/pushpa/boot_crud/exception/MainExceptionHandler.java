package org.pushpa.boot_crud.exception;

import org.pushpa.boot_crud.helper.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(DataShouldNotRepeatException.class)
    public ResponseEntity<ResponseStructure<String>> handle(DataShouldNotRepeatException exception){
        ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage(exception.getMessage());
        structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        structure.setData("Data not saved");

        return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handle(DataNotFoundException exception){
        ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage(exception.getMessage());
        structure.setStatus(HttpStatus.NOT_FOUND.value());
        structure.setData("No Data Present");

        return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
    }
}
