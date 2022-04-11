package com.child.box.core.exception;

public class NoAuthorizationException extends Exception {

	private String message;
	
	public NoAuthorizationException(){

    }
	
	public NoAuthorizationException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
