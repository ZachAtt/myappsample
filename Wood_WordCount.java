package JavaDoc;

import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Store the word and count data and implement methods to support Observable
 * @author Zachary Wood
 */
public class Wood_WordCount extends Observable{

	private StringProperty myWord;
	private IntegerProperty myCount;
	
	/**
	 * Constructor
	 * @param word      word value
	 * @param count     count value
	 */
		public Wood_WordCount(String word, Integer count) {
			setWord(word);
			setCount(count);
		}
	
	/**
	 * Sets the word value
	 * @param value     word value
	 */
	public void setWord(String value) { wordProperty().set(value); }
	
	/**
	 * @return    current word value
	 */
	public String getWord() { return wordProperty().get(); }

	/**
	 * Sets the count value
	 * @param value     count value
	 */
	public void setCount(Integer value) { countProperty().set(value); }
	
	/**
	 * @return    current count value
	 */
	public Integer getCount() { return countProperty().get(); }
	
	/**
	 * Property needed for Observable
	 * @return    current word property
	 */
	public StringProperty wordProperty() { 
		if (myWord == null) myWord = new SimpleStringProperty(this, "word");
		return myWord;
	}
	
	/**
	 * Property needed for Observable
	 * @return    current count property
	 */
	public IntegerProperty countProperty() { 
		if (myCount == null) myCount = new SimpleIntegerProperty(this, "count");
		return myCount; 
	}
	
	/**
	 * HashCode needed for observable
	 * @return       the word as a hashcode value
	 */
	@Override
    public int hashCode() {
        return myWord.hashCode();
    }
	
	
	/**
	 * Compare this word and count with the word and count found in the given object
	 * @param obj    the word and count being compared
	 * @return       true when the words and counts are equal; otherwise, false
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wood_WordCount other = (Wood_WordCount)obj;
        return getWord().compareTo(other.getWord()) == 0 && getCount() == other.getCount();
    }
}