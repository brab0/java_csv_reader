package com.involves.query;

import com.involves.Dataset;

public class Query {
	
	private Dataset dataset;
	private Sentence sentence;
	private Operation operation;	
	
	public Query(Dataset dataset){
		//sem copiar referencia em memória
		copyDataset(dataset);
		
		this.sentence = new Sentence();
		this.operation = new Operation();
	}
	
	public void copyDataset(Dataset dataset){
		this.dataset = new Dataset();
		this.dataset.setHeader(dataset.getHeader());
		this.dataset.setBody(dataset.getBody());
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
	
	public Dataset exec(String sentenceStr){
		
		setSentence(sentenceStr);
		
		for (String op : sentence.getOperations()) {			
			this.dataset = operation.getItem(op).exec(dataset, sentence);			
		}
		
		return dataset;
	}	
}
