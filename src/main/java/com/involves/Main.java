package com.involves;

import java.io.IOException;

public class Main {	
	
	public static void main(String[] args) {
		try {
			Console c = new Console();
			c.run();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}
}
