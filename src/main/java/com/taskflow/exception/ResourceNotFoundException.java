package com.taskflow.exception;

import com.taskflow.exception.ResourceNotFoundException;
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}