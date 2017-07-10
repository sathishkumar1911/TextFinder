package com.cl.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

/**
 * @author dharmaraj
 *
 */
public class TextFinder {
	
	public static void main(String[] args) {
		String directoryPath = "";
		String textToBeSearched = "";

		if (args.length == 2) {
			directoryPath = args[0];
			textToBeSearched = args[1];
		} else {
			System.out.println("Arguments not matched with requirement");
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter the directory path : ");
			directoryPath = scanner.next();

			System.out.print("Enter the text need to be searched : ");
			textToBeSearched = scanner.next();

			scanner.close();
		}

		try {
			TextFinder app = new TextFinder();
			app.search(directoryPath, textToBeSearched);
		} catch (ValidationException ve) {
			System.out.println(ve.getMessage());
		} catch (FileNotFoundException fne) {
			System.out.println(fne.getMessage());
		}
	}

	/**
	 * Method to search a string in a given Directory Path
	 * 
	 * @param directoryPath			String
	 * @param textToBeSearched		String
	 * @return List<String>
	 * @throws ValidationException
	 * @throws FileNotFoundException
	 */
	public List<String> search(String directoryPath, String textToBeSearched)
			throws ValidationException, FileNotFoundException {
		validateInputs(directoryPath, textToBeSearched);
		List<String> outputList = new ArrayList<String> (); // List of strings to support test cases
		searchFiles(directoryPath, textToBeSearched, outputList);
		
		return outputList;
	}

	/**
	 * Method to search files. It search files in sub directories too
	 *
	 */
	private void searchFiles(String directoryPath, String textToBeSearched, List<String> outputList)
			throws FileNotFoundException {
		File folder = new File(directoryPath);

		if (folder.exists()) {
			File[] arrOfFiles = folder.listFiles();

			for (File file : arrOfFiles) {
				if (file.isFile()) {
					searchText(file.getAbsolutePath(), file, textToBeSearched, outputList);
				} else if (file.isDirectory()) {
					searchFiles(file.getAbsolutePath(), textToBeSearched, outputList);
				}
			}
		} else {
			throw new FileNotFoundException("Given directory not found : ["
					+ directoryPath + "]");
		}
	}

	/*
	 * Method to search a text in a file
	 */
	private void searchText(String fileDirectoryPath, File file,
			String textToBeSearched, List<String> outputList) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);

		boolean isTextFound = false;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			line = line.toLowerCase();
			if (line.contains(textToBeSearched.toLowerCase())) {
				isTextFound = true;
				break;
			}
		}
		scanner.close();
		String result = isTextFound ? "HIT" : "MISS";
		System.out.println(fileDirectoryPath + ":" + result);
		outputList.add(fileDirectoryPath + ":" + result);
	}

	/*
	 * Method to validate user inputs
	 */
	private void validateInputs(String directoryPath, String textToBeSearched)
			throws ValidationException {
		if (Utils.isEmptyStringWithoutSpace(directoryPath)) {
			throw new ValidationException("Got empty string as directory path");
		}

		if (Utils.isEmptyString(textToBeSearched)) {
			throw new ValidationException("Got empty text to search");
		}
	}
}
