package Module1;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

//Author Name: Zachary Wood
//Date: 8/31/2020
//Program Name: Wood_DroneControl
//Purpose: Simulation using button, drone movement in x, y,z location

public class Wood_DroneControl extends Application {

  public static void main(String[] args)
  {
	launch(args);
  }
	
  public void start(Stage stage)
  {
	// Create the drone
	Wood_Drone drone = new Wood_Drone();
	
	// Create labels to display the coordinates and heading
	Label coordinates = new Label(drone.location());
	Label heading = new Label(drone.heading());

	// Create buttons with actions to control the drone
	Button btnLeft = new Button("LEFT");
	btnLeft.setOnAction((ActionEvent e)-> {
		drone.turnLeft();
		heading.setText(drone.heading());
	});
	
	Button btnRight = new Button("RIGHT");
	btnRight.setOnAction((ActionEvent e)-> {
		drone.turnRight();
		heading.setText(drone.heading());
	});
	
	Button btnForward = new Button("FORWARD");
	btnForward.setOnAction((ActionEvent e)-> {
		drone.moveForward();
		coordinates.setText(drone.location());
	});
	
	Button btnBackward = new Button("BACKWARD");
	btnBackward.setOnAction((ActionEvent e)-> {
		drone.moveBackward();
		coordinates.setText(drone.location());
	});
	
	Button btnUp = new Button("UP");
	btnUp.setOnAction((ActionEvent e)-> {
		drone.moveUp();
		coordinates.setText(drone.location());
	});
	
	Button btnDown = new Button("DOWN");
	btnDown.setOnAction((ActionEvent e)-> {
		drone.moveDown();
		coordinates.setText(drone.location());
	});
	
	Button btnReset = new Button("Reset");
	btnReset.setOnAction((ActionEvent e)-> {
		drone.reset();
		coordinates.setText(drone.location());
		heading.setText(drone.heading());
	});
	
	// Construct the UI control panel
	GridPane gp = new GridPane();
	gp.setHgap(10);
	
	gp.add(btnUp, 0, 0);
	gp.add(coordinates, 4, 0);
	gp.add(btnDown, 0, 4);
	gp.add(btnForward, 2, 1);
	gp.add(btnBackward, 2, 3);
	gp.add(btnLeft, 1, 2);
	gp.add(btnRight, 3, 2);
	gp.add(heading, 4, 4);
	gp.add(btnReset, 3, 0);
	
	Scene scene = new Scene(gp);
	
	stage.setScene(scene);	
	stage.setWidth(500.0);
	stage.setHeight(460.0);
	
	// Show the control panel
	stage.show();  
  }
}