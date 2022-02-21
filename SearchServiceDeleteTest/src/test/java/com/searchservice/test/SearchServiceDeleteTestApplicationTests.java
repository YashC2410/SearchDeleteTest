package com.searchservice.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.searchservice.test1.service.SearchServiceTest;

//@SpringBootTest(classes = SearchServiceDeleteTestApplication.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SearchServiceDeleteTestApplicationTests {

	//@MockBean
	@InjectMocks
	private SearchServiceTest searchServiceDeleteTest;
	
	public void setMockSuccessCollectionSoftDelete() {
		
		Mockito.when(searchServiceDeleteTest.writeFile(Mockito.any(), Mockito.any())).thenReturn(true);
		Mockito.when(searchServiceDeleteTest.undoFile(Mockito.any())).thenReturn(true);
		Mockito.when(searchServiceDeleteTest.deleteOperation()).thenReturn((int)Math.random());
	}
	
	public void setMockitoFailedCollectionSoftDelete() {
		
		Mockito.when(searchServiceDeleteTest.writeFile(Mockito.any(), Mockito.any())).thenReturn(false);
		Mockito.when(searchServiceDeleteTest.undoFile(Mockito.any())).thenReturn(false);
	}
	@Test
	void insertCollectionDeleteRecordTest() {
		
		//Checking with Valid Inputs
		setMockSuccessCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.writeFile(1, "TestTable"));
		
		//Checking With Client Id as 0
		setMockitoFailedCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.writeFile(0, "TestTable"));
		
		//Checking With Invalid Client Id 
	    setMockitoFailedCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.writeFile(-10, "TestTable"));
				
		//Checking With Table Name as Empty
		setMockitoFailedCollectionSoftDelete();
		Assertions.assertFalse(searchServiceDeleteTest.writeFile(1, ""));
		
		//Checking With Table Name as Null
		setMockitoFailedCollectionSoftDelete();
		Assertions.assertFalse(searchServiceDeleteTest.writeFile(1, null));

	}
	
	@Test
	void undoCollectionDeleteRecordTest() {
		
		//Checking with Valid Inputs
		setMockSuccessCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.undoFile(1));
		
		//Checking With Invalid Client Id 
		setMockitoFailedCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.undoFile(0));
		
		//Checking For Non Existing Client Id
		setMockitoFailedCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.undoFile(-19));
		
	}
	
	@Test
	void checkCollectionDeleteRecord() {
		//Checking with Valid Inputs
		setMockSuccessCollectionSoftDelete();
		Assertions.assertTrue(searchServiceDeleteTest.deleteOperation()>=0);
	}
	}


