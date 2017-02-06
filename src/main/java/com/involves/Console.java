package com.involves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.involves.query.Query;

public class Console {
	
	static Dataset dataset;
	static String sentence;
	
	public void run() throws IOException{
		
		clear();
		
		do {
			sentence = readSentence();
			
			exec();
			
		} while (true);
		
	}
	
	public static String readSentence() throws IOException{
		
		System.out.print("$ ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		return br.readLine().toLowerCase();
		
	}
	
	public void exec() throws IOException{

		if (sentence.equals("exit")){
			exit();
		} else if(sentence.equals("clear")){
			clear();
		}
		else if(sentence.contains("use")){
			use();
		}
		else if(!sentence.equals("")){
			query();
		}
		
	}
	
	public static void use() throws IOException{
		// use "src/test/resources/cidades.csv" "UTF-8"
		
		String[] parts = sentence.split(" ");		

		if(parts.length >= 2){			
			
			try {
				
				if(parts.length == 2){
					dataset = Csv.parseDataset(parts[1].replaceAll("\"", ""));
				} else if(parts.length == 3){
					dataset = Csv.parseDataset(parts[1].replaceAll("\"", ""), parts[2].replaceAll("\"", ""));
				} else if(parts.length == 4){
					dataset = Csv.parseDataset(parts[1].replaceAll("\"", ""), parts[2].replaceAll("\"", ""), parts[3].replaceAll("\"", ""));
				}
				
			} catch (MyException e){
				System.out.println(e.getMessage());
			}
			
		}
		else{
			System.out.println("Número de parâmetros incorreto.");
		}
	}
	
	public void query(){
		
		if(dataset == null){
			System.out.println("Nenhum dataset encontrado. Utilize o comando use \"/path/to/file.csv\" \"contentType\"");
		}
		else{
			
			Query query = new Query(dataset);			
			
			Dataset results = query.exec(sentence);
			
			System.out.println(String.join(",", results.getHeader()));
			
			for(List<String> row : results.getBody()){
				System.out.println(String.join(",", row));
			}
		}		
		
		System.out.println();
		
	}
	
	public static void clear(){		
		
		for (int i = 0; i < 80; ++i){
			System.out.println();
		}
		
	}
	
	public static void exit(){	
		
		clear();
		
		System.out.print("bye");
		
		System.exit(0);
		
	}
}
