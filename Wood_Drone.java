package Module1;

// Maintains and control the drone's location and heading
public class Wood_Drone {
	
	public enum Direction{NORTH, SOUTH, WEST, EAST}
	
	Direction myHeading;

	int myX, myY, myZ;

	public Wood_Drone() {
		reset();
	}
	
	// Provides drone's location and heading
	
	public String location() {
		return myX + ", " + myY + ", " + myZ;
	}
	
	public String heading() {
		return myHeading.toString();
	}
	
	public int x() {
		return myX;
	}
	
	public int y() {
		return myY;
	}
	
	public int z() {
		return myZ;
	}
	
	// Resets location and heading to initial values
	public void reset() {
		myX = 0;
		myY = 0;
		myZ = 0;
		myHeading = Direction.NORTH;
	}
	
	// Provides control of the drone's heading and movements
	
	public void turnLeft() {
		switch(myHeading) {
		case NORTH:
			myHeading = Direction.WEST;
			break;
		case SOUTH:
			myHeading = Direction.EAST;
			break;
		case WEST:
			myHeading = Direction.SOUTH;
			break;
		case EAST:
			myHeading = Direction.NORTH;
			break;
		default:
			break;
		}
	}
	
	public void turnRight() {
		switch(myHeading) {
		case NORTH:
			myHeading = Direction.EAST;
			break;
		case SOUTH:
			myHeading = Direction.WEST;
			break;
		case WEST:
			myHeading = Direction.NORTH;
			break;
		case EAST:
			myHeading = Direction.SOUTH;
			break;
		default:
			break;
		}
	}

	public void moveForward() {
		switch(myHeading) {
		case NORTH:
			increaseY();
			break;
		case SOUTH:
			decreaseY();
			break;
		case WEST:
			decreaseX();
			break;
		case EAST:
			increaseX();
			break;
		default:
			break;
		}
	
	}

	public void moveBackward() {
		switch(myHeading) {
		case NORTH:
			decreaseY();
			break;
		case SOUTH:
			increaseY();
			break;
		case WEST:
			increaseX();
			break;
		case EAST:
			decreaseX();
			break;
		default:
			break;
		}
	}

	public void moveUp() {
		increaseZ();
	}

	public void moveDown() {
		decreaseZ();
	}
	
	// Modify coordinate values
	// Could add a different increment here
	
	protected void increaseX() {
		++myX;
	}
	
	protected void decreaseX() {
		--myX;
	}
	
	protected void increaseY() {
		++myY;
	}
	
	protected void decreaseY() {
		--myY;
	}
	
	protected void increaseZ() {
		++myZ;
	}
	
	protected void decreaseZ() {
		--myZ;
	}
}