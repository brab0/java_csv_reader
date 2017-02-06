package com.involves;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	
	private List<String> header;
	private List<List<String>> body;
	
	public Dataset(){
		header = new ArrayList<String>();
		body = new ArrayList<List<String>>();
	}
	
	public List<String> getHeader() {
		return header;
	}
	
	public void setHeader(List<String> row) {		
		this.header = row;
	}
	
	public List<List<String>> getBody() {
		return body;
	}
	
	public void addBody(List<String> row) {
		this.body.add(row);
	}
	
	public void setBody(List<List<String>> body) {
		this.body = body;
	}
}