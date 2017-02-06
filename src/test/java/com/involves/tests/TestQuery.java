package com.involves.tests;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import com.involves.Console;
import com.involves.Dataset;
import com.involves.MyException;
import com.involves.query.Query;

public class TestQuery extends TestCase{
	
	@Test
	public void testToInstantianteQueryKeepingOriginalDatasetIntact() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);
		
		assertNotSame(query.getDataset(), dataset);
		
	}
	
	@Test
	@SuppressWarnings("unused")
	public void testToQueryInUndefinedDataset(){
		
		Dataset ds = null;
		
		try {			
			
			Query query = new Query(ds);			
						
			Dataset results = query.exec("count *");
			
		} catch (MyException e){
			
			assertEquals(e.getMessage(), "Nenhum dataset definido. Antes de começar a executar queries, utilize o comando: use \"/path/to/file.csv\" [\"delimiter\" \"contentType\"]");
			
		}
		
	}
	
	@Test
	@SuppressWarnings("unused")
	public void testUnsupportedOperation(){		
		
		try {
			
			Dataset dataset = Console.use("use src/test/resources/cidades.csv");
			
			Query query = new Query(dataset);

			Dataset results = query.exec("invalid_op uf");
			
		} catch (MyException | IOException e){
			
			assertEquals(e.getMessage(), "Nenhuma operação válida encontrada na sentença.");
			
		}		
		
	}
	
	@Test
	@SuppressWarnings("unused")
	public void testPropertyNotFound() throws MyException, IOException{
		
		try {
			
			Dataset dataset = Console.use("use src/test/resources/cidades.csv");
			
			Query query = new Query(dataset);

			Dataset results = query.exec("filter invalid_prop");
			
		} catch (MyException | IOException e){
			
			assertEquals(e.getMessage(), "Nenhuma propriedade válida encontrada na sentença.");
			
		}
		
	}
	
	@Test
	public void testOperationCountAll() throws MyException, IOException{		
			
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("count *");
		
		assertEquals(results.getHeader().get(0), "*");
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "5565");
		
	}
	
	@Test
	public void testOperationCountEspecificProperty() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("count uf");
		
		assertEquals(results.getHeader().get(0), "uf");
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "5565");		
		
	}
	
	@Test
	public void testOperationCountManyProperties() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("count uf name");
		
		assertEquals(results.getHeader().get(0), "uf");
		
		assertEquals(results.getHeader().get(1), "name");
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "5565");
		
		assertEquals(results.getBody().get(0).get(1), "5565");
		
	}
	
	@Test
	public void testOperationCountFiltering() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("count uf pa");
		
		assertEquals(results.getHeader().get(0), "uf");
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "143");
		
	}
	
	@Test
	public void testOperationFilter() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);
		
		Dataset results = query.exec("filter name belém");
		
		assertEquals(results.getHeader().size(), 10);
		
		assertEquals(results.getBody().size(), 3);
		
	}
	
	@Test
	@SuppressWarnings("unused")
	public void testOperationFilterWithNoValue() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		try {			
			
			Query query = new Query(dataset);			
									
			Dataset results = query.exec("count *");
			
		} catch (MyException e){
			
			assertEquals(e.getMessage(), "Nenhum valor encontrado na sentença.");
			
		}
		
	}
	
	@Test
	public void testOperationDistinct() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("distinct uf");
		
		assertEquals(results.getHeader().size(), 10);
		
		assertEquals(results.getBody().size(), 27);
		
	}
	
	@Test
	public void testQueryOperationMix() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("count distinct filter uf pa");
		
		assertEquals(results.getHeader().size(), 1);
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "1");
		
		query = new Query(dataset);

		results = query.exec("distinct filter name florianópolis sc");
		
		assertEquals(results.getHeader().size(), 10);
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(0), "4205407");
		
	}
	
	@Test
	public void testQueryOperationMixSemanticWhatever() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("filter uf and distinct by value rj");
		
		assertEquals(results.getHeader().size(), 10);
		
		assertEquals(results.getBody().size(), 1);
		
		assertEquals(results.getBody().get(0).get(1), "RJ");
		
	}
	
	@Test
	public void testQueryCaseInsensitive() throws MyException, IOException{
		
		Dataset dataset = Console.use("use src/test/resources/cidades.csv");
		
		Query query = new Query(dataset);

		Dataset results = query.exec("filter uf rj");
		
		assertEquals(results.getBody().get(0).get(1), "RJ");
		
	}
		
}
