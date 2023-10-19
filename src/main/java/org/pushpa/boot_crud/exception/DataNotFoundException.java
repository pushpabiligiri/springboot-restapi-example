package org.pushpa.boot_crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException{

    String message="Data not found";

}
