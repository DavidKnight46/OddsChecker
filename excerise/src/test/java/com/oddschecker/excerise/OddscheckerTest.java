package com.oddschecker.excerise;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.oddschecker.controller.OddscheckerController;

public class OddscheckerTest {
	
	final String VALID_FILE_PATH = "C:\\Users\\New\\Desktop\\Oddschecker.txt";
	final String VALID_FILE_MULTI_PATH = "C:\\Users\\New\\Desktop\\OddscheckerMulti.txt";
	final String INVALID_FILE_PATH = "C:\\Users\\New\\Desktop\\OddscheckerFalse.txt";
	
	final String SINGLE_LINE_RESULT = "O draconian devil! Oh lame saint!";
	
	final String MULTI_LINE_RESULT = "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, "
			+ "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam "
			+ "quaerat voluptatem.";
	
	OddscheckerController testController;
	
	@Before
	public void before() {
		testController = new OddscheckerController();
	}

	/**
	 * Test that single line of text is reassembled correctly
	 * 
	 */
	@Test
	public void textAssembleWithValidPathSingleLineTest() throws IOException{
		testController.assemble(VALID_FILE_PATH);
		
		String reAssembledText = testController.assembledText.toString();
		
		assertTrue(reAssembledText.contains(SINGLE_LINE_RESULT));
		
	}
	
	/**
	 * Test that multiply lines of text is reassembled correctly
	 * 
	 * @throws IOException
	 */
	@Test
	public void textAssembleWithValidPathMultiLineTest() throws IOException {
        testController.assemble(VALID_FILE_MULTI_PATH);
		
		String reAssembledText = testController.assembledText.toString();
		
		assertTrue(reAssembledText.contains(MULTI_LINE_RESULT));
	}
	
	/**
	 * That expected expectation is thrown with invalid file is provided
	 * 
	 * @throws IOException 
	 * 
	 */
	@Test( expected=IOException.class )
	public void textAssembleInValidPath() throws IOException {
		testController.assemble(INVALID_FILE_PATH);

	}
	

}
