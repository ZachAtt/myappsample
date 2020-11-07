package JavaDoc;

import org.junit.Test;

import javafx.collections.ObservableList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Unit tests for word occurrences classes
 * @author Zachary Wood
 */
public class TestJunit {
	
	/**
	 * Test WordCount constructor
	 */
	@Test
	public void testWordCountConstructor() {
		Wood_WordCount wordCount = new Wood_WordCount("test", 4);
		Boolean testNotSuccessful = false;
		
		System.out.println("Testing WordCount Constructor ...\n");
		
		try {
			assertEquals("Expected word initialization value \"test\" not found.",
					wordCount.getWord(), "test");
			System.out.println("Found expected word initialization value \"test\".");
		}catch (AssertionError e) {
			System.out.println(e.getMessage());
			testNotSuccessful = true;
		}
		
		try {
			assertTrue("Expected count initialization value 4 not found.",
					wordCount.getCount() == 4);
			System.out.println("Found expected count initialization value 4.");
		}catch (AssertionError e) {
			System.out.println(e.getMessage());
			testNotSuccessful = true;
		}
	}
	
	/**
	 * Test WordCount Setters/Getters
	 */
	@Test
	public void testWordCountSettersGetters() {
		Wood_WordCount wordCount = new Wood_WordCount("test", 4);
		wordCount.setWord("word");
		wordCount.setCount(8);
		
		Boolean testFailed = false;
		
		System.out.println("\nTesting WordCount Setters/Getters ...\n");
		
		try {
			assertEquals("Expected word value \"word\" not set.",
					wordCount.getWord(), "word");
			System.out.println("Found expected word value \"word\".");
		}catch (AssertionError e) {
			System.out.println(e.getMessage());
			testFailed = true;
		}
		
		try {
			assertTrue("Expected count value 8 not set.",
					wordCount.getCount() == 8);
			System.out.println("Found expected count value 8.");
		}catch (AssertionError e) {
			System.out.println(e.getMessage());
			testFailed = true;
		}
	}
	
	/**
	 * Tests ReadandCount method of ReaderCounter class
	 * @exception AssertionError if test fails
	 */
	@Test
	public void testReaderCounter() throws AssertionError {
		java.io.File file = new java.io.File("Words.txt");
		java.io.PrintWriter output;
		try {
			output = new java.io.PrintWriter(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new AssertionError("Could not open file");
		}
		output.println("cat cat cat");
		output.println("dog dog dog dog");
		output.close();
		
		Wood_ReaderCounter readerCounter = new Wood_ReaderCounter();
		
		System.out.println("\nTesting ReaderCounter ...\n");
		
		try {
			readerCounter.ReadAndCount("Words.txt", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObservableList<Wood_WordCount> wordCountList = readerCounter.getWordCountList();
		Boolean test = false;
		
		try {
			test = file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Wood_WordCount cat3 = new Wood_WordCount("cat", 3);
		Wood_WordCount dog4 = new Wood_WordCount("dog", 4);
		Wood_WordCount dog6 = new Wood_WordCount("dog", 6);
		Wood_WordCount bird5 = new Wood_WordCount("bird", 5);
		
		Boolean testSuccessful = true;
		
		try {
			assertTrue("Expected word count (\"cat\", 3) not present in word count list.",
					wordCountList.contains(cat3));
			System.out.println("Expected word count (\"cat\", 3) present in word count list.");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			testSuccessful = false;
		}
		
		try {
			assertTrue("Expected word count (\"dog\", 4) not found in word count list.",
					wordCountList.contains(dog4));
			System.out.println("Expected word count (\"dog\", 4) found in word count list.");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			testSuccessful = false;
		}

		try {
			assertFalse("Unexpected word count (\"dog\", 6) found in word count list.",
					wordCountList.contains(dog6));
			System.out.println("Word count (\"dog\", 6) not found in word count list as expected.");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			testSuccessful = false;
		}

		try {
			assertFalse("Unexpected word count (\"dog\", 6) found in word count list.",
					wordCountList.contains(bird5));
			System.out.println("Word count (\"bird\", 5) not found in word count list as expected.");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			testSuccessful = false;
		}
		
		if(!testSuccessful) {
			throw new AssertionError("Failed ReaderCounter tests");
		}
	}
}