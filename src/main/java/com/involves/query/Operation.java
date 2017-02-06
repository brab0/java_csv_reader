package com.involves.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.involves.Dataset;

public class Operation {
	
	private static Hashtable<String, OperationInterface> items;	
	
	interface OperationInterface {
		public Dataset exec(Dataset dataset, Sentence sentence);
    }
	
	public Operation() {
		items = new Hashtable<String, OperationInterface>();
		
    	items.put("count", new OperationInterface() { public Dataset exec(Dataset dataset, Sentence sentence) { return count(dataset, sentence); } });
    	items.put("distinct", new OperationInterface() { public Dataset exec(Dataset dataset, Sentence sentence) { return distinct(dataset, sentence); } });
    	items.put("filter", new OperationInterface() { public Dataset exec(Dataset dataset, Sentence sentence) { return filter(dataset, sentence); } });    	
    }
	
	public OperationInterface getItem(String item){
		return items.get(item);
	}
	
	public boolean exists(String cmd){			
		
		if (items.get(cmd) != null) {
			return true;
		}
		
		return false;
	}
	
	private Dataset applyFilterIfNecessary(Dataset dataset, Sentence sentence){		
		if((sentence.getValues().size() > 0) && (sentence.getOperations().indexOf("filter") == -1)){			
			return filter(dataset, sentence);
		}
		
		return dataset;
	}
	
	private Dataset count(Dataset dataset, Sentence sentence) {
		List<String> newHeader = new ArrayList<String>();
		List<String> newRow = new ArrayList<String>();
		
		Dataset result = applyFilterIfNecessary(dataset, sentence);		
		
		for(String property : sentence.getProperties()){
			newHeader.add(property);
			
			newRow.add(String.valueOf(result.getBody().size()));
		}
		
		result.setHeader(newHeader);
		result.setBody(Arrays.asList(newRow));
		
		return result;
	}	
	
	private Dataset distinct(Dataset dataset, Sentence sentence) {
		
		Dataset result = new Dataset();
		
		result.setHeader(dataset.getHeader());	
		
		applyFilterIfNecessary(dataset, sentence);
		
		for(List<String> row : dataset.getBody()){
			
			Boolean unique = true;
			
			for(List<String> newRow : result.getBody()){
				for(String property : sentence.getProperties()){
					int indexCol = dataset.getHeader().indexOf(property);
					
					if(row.get(indexCol).equals(newRow.get(indexCol))){
						unique = false;
					}
				}
			}
			
			if((result.getBody().size() == 0) || (unique)){
				result.addBody(row);
			}
		}
		
		return result;
	}
	
	private Dataset filter(Dataset dataset, Sentence sentence) {
		
		Dataset result = new Dataset();
		
		result.setHeader(dataset.getHeader());
		
		for(List<String> row : dataset.getBody()){
			
			int andCount = 0;			
			
			for(String property : sentence.getProperties()){
				int indexCol = dataset.getHeader().indexOf(property);				
				Boolean found = false;
				
				for(String value : sentence.getValues()){					
					
					if(row.get(indexCol).toLowerCase().equals(value.toLowerCase())){
						found = true;
					}
				}
				
				if(found){
					andCount++;
				}				
			}
			
			if(andCount == sentence.getProperties().size()){
				result.addBody(row);
			}
		}
		
		return result;
	}
}
