package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MusicGlobalExceptionHandling {
        @ExceptionHandler(value= TrackAlreadyExistsException.class)
        public ResponseEntity<String> globalException(TrackAlreadyExistsException trackAlreadyExistsException)
        {
            ResponseEntity responseEntity;
            responseEntity=new ResponseEntity<String>(trackAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
            return responseEntity;
        }
        @ExceptionHandler(value= TrackNotFoundException.class)
        public ResponseEntity<String> globalException1(TrackNotFoundException trackNotFoundException)
        {
            ResponseEntity responseEntity;
            responseEntity=new ResponseEntity<String>(trackNotFoundException.getMessage(), HttpStatus.CONFLICT);
            return responseEntity;
        }
}
