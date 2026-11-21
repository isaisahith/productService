package org.sahith.products.controllers;


import org.sahith.products.exceptions.InvalidIdException;
import org.sahith.products.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotfoundException(){
        return new ResponseEntity<>("Product not found", HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidIdException(){
        return new ResponseEntity<>("Id is not provided or Invalid Id", HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> invalidIdException(){
        return new ResponseEntity<>("Id should be >=0", HttpStatusCode.valueOf(400));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(){
//        return new ResponseEntity<>("Something went wrong", HttpStatusCode.valueOf(500));
//    }

}
