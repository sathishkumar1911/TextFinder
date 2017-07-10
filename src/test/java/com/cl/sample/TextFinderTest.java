package com.cl.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.ValidationException;

/**
 * @author dharmara
 *
 */
public class TextFinderTest {

	public static void main(String[] args) throws ValidationException,
			FileNotFoundException, UnsupportedEncodingException {
		TextFinderTest textFinderTest = new TextFinderTest();
		textFinderTest.deleteTestFolder("TestFolder");
		
		textFinderTest.testOnEmptyInputs();
		textFinderTest.testOnEmptyFolder();
		textFinderTest.testOnInvalidFolder();
		textFinderTest.testOnHitFile();
		textFinderTest.testOnMissFile();
		textFinderTest.testOnSubFolderFile();
		textFinderTest.testOnMultipleFiles();
	}
	
	public void testOnEmptyInputs() throws FileNotFoundException {
		try {
			TextFinder textFinder = new TextFinder();
			textFinder.search("", "");
			throw new AssertionError("testOnEmptyInputs test case failed");
		} catch (ValidationException ve) {
		}
	}

	public void testOnEmptyFolder() throws ValidationException,
			FileNotFoundException {
		File file = new File("TestFolder");
		file.mkdir();

		TextFinder textFinder = new TextFinder();
		List<String> outputList = textFinder.search("TestFolder", "sampletext");
		deleteTestFolder("TestFolder");
		
		if (outputList !=null && outputList.size() != 0) {
			throw new AssertionError("testOnEmptyFolder test case failed");
		}
	}
	
	public void testOnInvalidFolder() throws ValidationException {
		try {
			TextFinder textFinder = new TextFinder();
			textFinder.search("TestFolder", "sample");
			throw new AssertionError("testOnInvalidFolder test case failed");
		} catch (FileNotFoundException ve) {
		}
	}
	
	public void testOnHitFile() throws ValidationException, UnsupportedEncodingException, FileNotFoundException {
		File file = new File("TestFolder");
		file.mkdir();
	    PrintWriter writer = new PrintWriter("TestFolder\\sample.txt", "UTF-8");
	    writer.println("Just a Sample text");
	    writer.close();
		
		TextFinder textFinder = new TextFinder();
		List<String> outputList = textFinder.search("TestFolder", "sample"); // text with case insensitive
		deleteTestFolder("TestFolder");
		if (outputList == null || outputList.size() == 0 || !outputList.get(0).contains("sample.txt:HIT")) {
			throw new AssertionError("testOnHitFile test case failed");
		}
	}
	
	public void testOnMissFile() throws ValidationException, UnsupportedEncodingException, FileNotFoundException {
		File file = new File("TestFolder");
		file.mkdir();
	    PrintWriter writer = new PrintWriter("TestFolder\\sample.txt", "UTF-8");
	    writer.println("Just a Sample text");
	    writer.close();
		
		TextFinder textFinder = new TextFinder();
		List<String> outputList = textFinder.search("TestFolder", "sampletext"); // text with case insensitive
		deleteTestFolder("TestFolder");
		if (outputList == null || outputList.size() == 0 || !outputList.get(0).contains("sample.txt:MISS")) {
			throw new AssertionError("testOnMissFile test case failed");
		}
	}
	
	public void testOnSubFolderFile() throws ValidationException, UnsupportedEncodingException, FileNotFoundException {
		File file = new File("TestFolder\\subFolder");
		file.mkdirs();
	    PrintWriter writer = new PrintWriter("TestFolder\\subFolder\\subsample.txt", "UTF-8");
	    writer.println("Just a Sample text");
	    writer.close();
		
		TextFinder textFinder = new TextFinder();
		List<String> outputList = textFinder.search("TestFolder", "sampletext"); // text with case insensitive
		deleteTestFolder("TestFolder");
		if (outputList == null || outputList.size() == 0 || !outputList.get(0).contains("sample.txt:MISS")) {
			throw new AssertionError("testOnSubFolderFile test case failed");
		}
	}
	
	public void testOnMultipleFiles() throws ValidationException, UnsupportedEncodingException, FileNotFoundException {
		File file = new File("TestFolder");
		file.mkdir();
	    PrintWriter writer = new PrintWriter("TestFolder\\sample.txt", "UTF-8");
	    writer.println("Just a Sample text");
	    writer.close();
	    
	    writer = new PrintWriter("TestFolder\\sampleFile.out", "UTF-8");
	    writer.println("test file");
	    writer.close();
		
		TextFinder textFinder = new TextFinder();
		List<String> outputList = textFinder.search("TestFolder", "sample"); // text with case insensitive
		deleteTestFolder("TestFolder");
		if (outputList == null || outputList.size() == 0 || !outputList.get(0).contains("sample.txt:HIT")) {
			throw new AssertionError("testOnMultipleFiles test case failed");
		}
		
		if (outputList == null || outputList.size() < 2 || !outputList.get(1).contains("sampleFile.out:MISS")) {
			throw new AssertionError("testOnMultipleFiles test case failed");
		}
	}
	

	
	private void deleteTestFolder(String folderName) {
		File folder = new File(folderName);
		if (folder.exists()) {
			File[] arrOfFiles = folder.listFiles();

			for (File file : arrOfFiles) {
				if (file.isFile()) {
					file.delete();
				} else if (file.isDirectory()) {
					deleteTestFolder(file.getAbsolutePath());
				}
			}
		}
		folder.delete();
	}
}
