package com.involves;

public class MyException extends Exception{

	private static final long serialVersionUID = -6728184266897986533L;
	
	public String message;
	
	public MyException(String msg){
		this.message = msg;
	}
	
    @Override
    public String getMessage(){
        return message;
    }
}
