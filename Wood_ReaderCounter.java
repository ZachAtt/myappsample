package JavaDoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Read and count the words from a file and sorts them by their count.
 * Provides access to the sorted list.
 * @author Zachary Wood
 */
public class Wood_ReaderCounter {
	
	protected ObservableList<Wood_WordCount> myWordCountList; 
			
	/**
	 * Constructor
	 */
	public Wood_ReaderCounter() {
		myWordCountList = FXCollections.observableArrayList();
	}
	
	/**
	 * @return the sorted words and their counts as an ObservableList
	 */
	public ObservableList<Wood_WordCount> getWordCountList(){
		return myWordCountList;
	}
	
	/**
	 * Reads the words from a file and count their occurrences.
	 * Stores the words and their counts sorted by count.
	 * @param fileName        file to be read
	 * @param filterHtml      should HTML code be filtered?
	 * @return                true if read and count is successful; otherwise, false
	 */
	public boolean ReadAndCount(String fileName, Boolean filterHtml) {
		
		// Clear out previous word counts
		myWordCountList.clear();
		
		// Create a map of words to their counts
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		
		Wood_word_occurrence wmwo = new Wood_word_occurrence();
		
		// Opens a file to read each line
		FileReader wordFile;
		try {
			wordFile = new FileReader(fileName);
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		// C:\Users\zqwoo\Documents\Seminole State\2020 Fall\Software Development I\Module 3\Macbeth_ Entire Play.html
		BufferedReader wordReader = new BufferedReader(wordFile);
		String fileLine;
		Boolean moreToRead = true;
		
		while(moreToRead) {
			
			try {
				fileLine = wordReader.readLine();
				if(fileLine == null) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			// Convert line to lowercase so that words with different case are the same
			fileLine = fileLine.toLowerCase();
			
			// Separate each line into words
			String[] text;
			if (filterHtml) {
				text = fileLine.split("<[^>]*>|[^a-zA-Z]"); // This excludes html tags
			}
			else {
				text = fileLine.split("[^a-zA-Z]");
			}
			
			// Collect count of each word
			for( String word : text ) {
				if( word.length() == 0 || (word.length() == 1 && word.charAt(0) != 'a' && word.charAt(0) != 'i')) {
					/**
					 * Skip empty values
					 */
					continue; 
				}

				// Get count for this word
				Integer appearances = wordCounts.get(word);
				
				if(appearances == null) {
					// Start count of new word
					appearances = 1;
				}
				else{
					// Increment count for this word
					appearances++;
				}
				
				// Update the map's count for this word
				wordCounts.put(word, appearances);
			}
		}
		
		for (String word : wordCounts.keySet()) {
			myWordCountList.add(new Wood_WordCount(word, wordCounts.get(word)));
		}
		
		// Close file so that it can be deleted after tests in main class
		try {
			wordFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}