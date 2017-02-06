package com.involves;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Csv {	
	
	public static Dataset parseDataset(String path) throws MyException, IOException{
		
		return populateDataset(path, ",", "");
		
	}

	public static Dataset parseDataset(String path, String delimiter) throws MyException, IOException{
		
		return populateDataset(path, delimiter, "");
		
	}

	public static Dataset parseDataset(String path, String delimiter, String contentType) throws MyException, IOException{
		
		return populateDataset(path, delimiter, contentType);
		
	}
	
	public static Dataset populateDataset(String path, String delimiter, String contentType)  throws MyException, IOException{
		Dataset dataset = new Dataset();
		
		BufferedReader br = getBufferedReader(path, contentType);		
		
		try {
			String row;				
				
			while ((row = br.readLine()) != null) {
				
				if(dataset.getHeader().size() == 0){
					if(!row.trim().equals("")){					
						if(row.contains(delimiter)){
							dataset.setHeader(Arrays.asList(row.split(delimiter)));
						} else {
							throw new MyException("O delimitador '" + delimiter + "' não foi encontrado na linha de cabeçalho.");
						}
					}
				} else {
					dataset.addBody(Arrays.asList(row.split(delimiter)));
				}				
			}
				
			if((dataset.getHeader().size() == 0) && (dataset.getBody().size() == 0)){				
				throw new MyException("Não foi possível ler o arquivo. Verifique se ele possui algum conteúdo.");
			}			
			
			br.close();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		
		return dataset;		
	}
	
	public static BufferedReader getBufferedReader(String path, String contentType) throws MyException{		 
		
		try {
			FileInputStream file = new FileInputStream(path);			
			
			try {
				Reader reader = setContentType(file, contentType);
				
				return new BufferedReader(reader);
							
			} catch(UnsupportedEncodingException ex){
				throw new MyException("EncodeType não suportado.");
			}
		} catch (IOException e) {
			throw new MyException("Arquivo não encontrado.");
		}
	}
	
	public static Reader setContentType(FileInputStream file, String contentType) throws UnsupportedEncodingException{		
	
		if(contentType == ""){
			return new InputStreamReader(file);				
		} else{
			return new InputStreamReader(file, contentType);
		}
	}
}