package com.involves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.involves.query.Query;

public class Console {
	
	static Dataset dataset;
	static String sentence;
	
	public static void run() throws IOException{
		
		clear();
		
		do {
			sentence = readSentence();
			
			try {
				exec(sentence);
			} catch (MyException e) {
				System.out.println(e.message);
			}
			
		} while (true);
		
	}
	
	public static String readSentence() throws IOException {
		
		System.out.print("$ ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		return br.readLine().toLowerCase();
		
	}
	
	public static void exec(String sentence) throws MyException, IOException {

		if (sentence.equals("exit")){
			exit();
		} else if(sentence.equals("clear")){
			clear();
		}
		else if(sentence.contains("use")){
			dataset = use(sentence);			
		}
		else if(!sentence.equals("")){
			Dataset results = query(dataset, sentence);
			
			print(results);
		}
		
	}
	
	public static Dataset use(String sentence) throws MyException, IOException {
		
		String[] parts = sentence.split(" ");		

		if(parts.length >= 2){			
				
			if(parts.length == 2){
				return Csv.parseDataset(parts[1].replaceAll("\"", ""));
			} else if(parts.length == 3){
				return Csv.parseDataset(parts[1].replaceAll("\"", ""), parts[2].replaceAll("\"", ""));
			} else if(parts.length == 4){
				return Csv.parseDataset(parts[1].replaceAll("\"", ""), parts[2].replaceAll("\"", ""), parts[3].replaceAll("\"", ""));
			}
			
		}
		else{
			throw new MyException("Número incorreto de parâmetros. Verifique se pelo menos o path do o arquivo foi passado.");			
		}
		
		return null;
	}
	
	public static Dataset query(Dataset dataset, String sentence) throws MyException{
				
		Query query = new Query(dataset);			
		
		Dataset results = query.exec(sentence);
		
		return results;		
	}
	
	public static void print(Dataset results){
		System.out.println(String.join(",", results.getHeader()));
		
		for(List<String> row : results.getBody()){
			System.out.println(String.join(",", row));
		}
		
		System.out.println();
	}
	
	public static void exit(){	
		
		clear();
		
		System.out.print("bye");
		
		System.exit(0);
		
	}

	public static void clear(){		
		
		for (int i = 0; i < 80; ++i){
			System.out.println();
		}
		
	}
}
