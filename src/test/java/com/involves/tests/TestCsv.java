package com.involves.tests;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import com.involves.Csv;
import com.involves.Dataset;
import com.involves.MyException;

public class TestCsv extends TestCase{
	
	//use "src/test/resources/cidades.csv" "UTF-8"
	
	@SuppressWarnings("unused")
	@Test
	public void testFileNotFound(){
		
		try {
			Dataset dataset = Csv.parseDataset("asrc/test/resources/cidades.csv");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Arquivo não encontrado.");
		}
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testFileEmpty(){
		
		try {
			Dataset dataset = Csv.parseDataset("src/test/resources/empty.csv");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Não foi possível ler o arquivo. Verifique se ele possui algum conteúdo.");
		}
		
	}	
	
	@SuppressWarnings("unused")
	@Test
	public void testEncodeTypeNotSupported(){
		
		try {			
			Dataset dataset = Csv.parseDataset("src/test/resources/cidades.csv", ",", "utf-xyz");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "EncodeType não suportado.");
		}
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testDelimiterNotFound(){
		
		try {			
			Dataset dataset = Csv.parseDataset("src/test/resources/cidades.csv", ";");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "O delimitador ';' não foi encontrado na linha de cabeçalho.");
		}
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testHeaderWithSpacesAbove(){
		
		try {
			Dataset dataset = Csv.parseDataset("src/test/resources/header_spaces_above.csv");
			
			assertEquals(dataset.getHeader().size(), 10);
		} catch (MyException | IOException e){
			System.out.println(e.getMessage());			
		}
		
	}
	
	@Test
	public void testPopulateDataset(){
		
		try {
			Dataset dataset = Csv.parseDataset("src/test/resources/cidades.csv");
			
			assertEquals(dataset.getHeader().size(), 10);
			assertEquals(dataset.getBody().size(), 5565);
			
		} catch (MyException | IOException e){
			System.out.println(e.getMessage());
		}
		
	}
}
