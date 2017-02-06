package com.involves.tests;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import com.involves.Console;
import com.involves.MyException;

public class TestConsole extends TestCase{
	
	@Test
	public void testToSetIncorrectUseDatasetParameters(){
		
		try {			
			
			Console.exec("use");
			
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Número incorreto de parâmetros. Verifique se pelo menos o path do o arquivo foi passado.");
		}
		
	}
		
		
}
