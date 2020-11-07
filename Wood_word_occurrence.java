package JavaDoc;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Read and count the words in a file.
 * Display the words and their frequencies in a table
 * @author Zachary Wood
 */

public class Wood_word_occurrence extends Application {

	/**
	* @param stage    shows contents of gui
	*/
	@Override
	public void start(Stage stage) {
		
		/**
		 * Create instance of ReaderCounter for processing file
		 */
		Wood_ReaderCounter readerCounter = new Wood_ReaderCounter();
		
		/**
		 *  Build GUI components
		 */
		
		/**
		 * Create textfield and label for filename
		 */
		TextField fileName = new TextField();
		fileName.setEditable(true);
		Label text = new Label("Enter File Name");
		
		/**
		 * Create filter checkbox
		 */
		CheckBox htmlFilter = new CheckBox();
		htmlFilter.setSelected(false);
		htmlFilter.setVisible(true);
		htmlFilter.setText("Filter HTML");
		
		/**
		 * Create process file button
		 */
		Button btnProcess = new Button("Process File");
		btnProcess.setFocusTraversable(false);
		btnProcess.setDisable(true);
		btnProcess.setOnAction((ActionEvent e)-> { 
			
			/**
			 * Process file when button is pressed
			 */
			boolean readOk = readerCounter.ReadAndCount(fileName.getText(), htmlFilter.isSelected());
			if(!readOk) {
				JOptionPane.showMessageDialog(null, "Problem reading file: " + fileName.getText());
			}
		});
		
		/**
		 *  Add listener to disable button when textbox is empty
		 */
		fileName.textProperty().addListener(new ChangeListener<String>() {
			/**
			 * 
			 * @param 
			 */
			@Override
			public void changed(ObservableValue<? extends String> observable, String empty, String notEmpty) {
				btnProcess.setDisable(fileName.getText().isEmpty());	
			}
		});
		
		/** 
		 * Use GridPane to arrange checkbox, button, label, and textbox
		 */
		GridPane controlPane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
        col1.setPercentWidth(20);
        col2.setPercentWidth(80);
		controlPane.setHgap(10);
		controlPane.add(htmlFilter, 0, 0);
		controlPane.add(text, 1, 0);
		controlPane.add(btnProcess, 0, 1);
		controlPane.add(fileName, 1, 1);
		controlPane.getColumnConstraints().addAll(col1,col2);
		 
		/**
		 * Create and format word count table
		 */
		TableView<Wood_WordCount> wordCountView = new TableView<Wood_WordCount>();
		ObservableList<Wood_WordCount> wordCountList = readerCounter.getWordCountList();
		SortedList<Wood_WordCount> sortedData = new SortedList<>(wordCountList);
		sortedData.comparatorProperty().bind(wordCountView.comparatorProperty());
		
		wordCountView.setItems(sortedData);		
		TableColumn<Wood_WordCount, String> wordColumn = new TableColumn<Wood_WordCount, String>("Word");
		wordColumn.setCellValueFactory(new PropertyValueFactory("word"));
		wordColumn.setPrefWidth(300);
		TableColumn<Wood_WordCount,Integer> countColumn = new TableColumn<Wood_WordCount,Integer>("Count");
		countColumn.setCellValueFactory(new PropertyValueFactory("count"));
		countColumn.setPrefWidth(200);
		wordCountView.getColumns().setAll(wordColumn, countColumn);
		wordCountView.getSortOrder().addAll(countColumn, wordColumn);
		
		/**
		 * Create BorderPane to hold gui elements
		 */
		BorderPane border = new BorderPane();
		border.setTop(controlPane);
		border.setCenter(wordCountView);
		
		/**
		 * Create scene with borderpane and start gui
		 */
		Scene scene = new Scene(border);
		stage.setScene(scene);	
		stage.setWidth(500.0);
		stage.setHeight(460.0);
		stage.setTitle("Word occurrences");
		
		stage.show();  
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}