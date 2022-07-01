package com.dev.crudv2.exception;

public class ResourceAlreadyExistsException extends Exception {
	 
    public ResourceAlreadyExistsException() {
    }
 
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}