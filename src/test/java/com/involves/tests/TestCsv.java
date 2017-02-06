package com.involves.tests;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import com.involves.Csv;
import com.involves.Dataset;
import com.involves.MyException;

public class TestCsv extends TestCase{
	
	@Test
	public void testToParseCsvToDataset(){
		
		try {
			Dataset dataset = Csv.parseDataset("src/test/resources/cidades.csv");
			
			assertEquals(dataset.getHeader().size(), 10);
			assertEquals(dataset.getBody().size(), 5565);
			
		} catch (MyException | IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testToParseUnsupportedFileType(){
		
		try {
			Csv.parseDataset("src/test/resources/unsupported_type.xml");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Tipo de arquivo não suportado. Somente arquivos do tipo .csv ou .txt são aceitos.");
		}
		
	}
	
	@Test
	public void testIfFileNotFound(){
		
		try {
			Csv.parseDataset("asrc/test/resources/cidades.csv");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Arquivo não encontrado.");
		}
		
	}
	
	@Test
	public void testToParseDatasetFromEmptyFile(){
		
		try {
			Csv.parseDataset("src/test/resources/empty.csv");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "Não foi possível converter o arquivo. Verifique se ele possui algum conteúdo.");
		}
		
	}	
	
	@Test
	public void testToSetUnsupportedEncodeType(){
		
		try {			
			Csv.parseDataset("src/test/resources/cidades.csv", ",", "utf-xyz");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "EncodeType não suportado.");
		}
		
	}
	
	@Test
	public void testToSetWrongDelimiter(){
		
		try {			
			Csv.parseDataset("src/test/resources/cidades.csv", ";");
		} catch (MyException | IOException e){
			assertEquals(e.getMessage(), "O delimitador ';' não foi encontrado na linha de cabeçalho.");
		}
		
	}
	
	@Test
	public void testToSetHeaderWithSpacesAbove(){
		
		try {
			Dataset dataset = Csv.parseDataset("src/test/resources/header_spaces_above.csv");
			
			assertEquals(dataset.getHeader().size(), 10);
		} catch (MyException | IOException e){
			System.out.println(e.getMessage());			
		}
		
	}	
}
