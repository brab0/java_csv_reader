package com.involves.query;

import com.involves.Dataset;
import com.involves.MyException;

public class Query {
	
	private Dataset dataset;
	private Sentence sentence;
	private Operation operation;	
	
	public Query(Dataset dataset) throws MyException{
		
		setDataset(dataset);
		
		this.sentence = new Sentence();
		this.operation = new Operation();
	}	
	
	public void setDataset(Dataset dataset) throws MyException{
		if(dataset != null){
			this.dataset = dataset.copy();
		} else{
			throw new MyException("Nenhum dataset definido. Antes de começar a executar queries, utilize o comando: use \"/path/to/file.csv\" [\"delimiter\" \"contentType\"]");			
		}
	}
	
	public Dataset getDataset() {
		return dataset;
	}

	public Sentence getSentence() {
		return sentence;
	}	
	
	public void setSentence(String sentenceStr){

		String parts[] = sentenceStr.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		
		for(String part : parts){			
			sentence.setProperties(part.trim(), dataset);			
			sentence.setOperations(part.trim(), operation);
			sentence.setValues(part.trim().replaceAll("\"", ""));
		}
		
		int indexCount = sentence.getOperations().indexOf("count");
		
		if(indexCount > -1){
			sentence.setOperations(sentence.getOperations().get(indexCount), operation);
			sentence.getOperations().remove(indexCount);
		}
	}
	
	public Dataset exec(String sentenceStr) throws MyException {
		
		setSentence(sentenceStr);
		
		if(sentence.getOperations().size() > 0){
			
			if(sentence.getProperties().size() > 0){
				
				for (String op : sentence.getOperations()) {			
					this.dataset = operation.getItem(op).exec(dataset, sentence);			
				}
				
				return dataset;
				
			} else{
				throw new MyException("Nenhuma propriedade válida encontrada na sentença.");
			}
			
		} else{
			throw new MyException("Nenhuma operação válida encontrada na sentença.");
		}		
	}	
}
