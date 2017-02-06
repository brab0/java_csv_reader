package com.involves.query;

import java.util.ArrayList;
import java.util.List;

import com.involves.Dataset;

public class Sentence {
	
	private List<String> properties;
	private List<String> values;
	private List<String> operations;
	
	public Sentence(){		
		this.properties = new ArrayList<String>();
		this.operations = new ArrayList<String>();
		this.values = new ArrayList<String>();
	}
	
	public List<String> getProperties() {
		return properties;
	}
	
	public void setProperties(String sentencePart, Dataset dataset){		
		if((dataset.getHeader().indexOf(sentencePart) > -1) ||
		   (sentencePart.equals("*"))){
			properties.add(sentencePart);
		}
	}

	public List<String> getOperations() {
		return operations;
	}
	
	public void setOperations(String sentencePart, Operation operation){
		if(operation.exists(sentencePart)){
			operations.add(sentencePart);
		}
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(String sentencePart) {
		if((properties.indexOf(sentencePart) == -1) &&
		   (operations.indexOf(sentencePart) == -1)){
			this.values.add(sentencePart);
		}		
	}
}
