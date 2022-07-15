package com.dev.introducaospring.exception;

public class ResourceNotFoundException extends Exception {
	 
    public ResourceNotFoundException() {
    }
 
    public ResourceNotFoundException(String msg) {
        super(msg);
    }    
}