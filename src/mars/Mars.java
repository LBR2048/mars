package mars;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class Mars implements IMars {
	
	// Terrain limits
	int xMin = 0;
	int xMax = 4;
	int yMin = 0;
	int yMax = 4;

	// Rover position and direction
	int xPosition = 0;
	int yPosition = 0;
	char direction = 'N';
	
	/**
	 * Move and turn the rover according to the instructions given
	 * M moves the rover forward
	 * L turns the rover counterclockwise
	 * R turns the rover clockwise
	 */
	@Override
	public String move(String instructions) throws WebApplicationException {
		for (int i = 0; i < instructions.length(); i++){
		    char c = instructions.charAt(i);   
		    switch (c) {
				case 'M':
					move();
					break;
				case 'L':
					left();
					break;
				case 'R':
					right();
					break;
				default:
					System.out.println("Error");
					throw new WebApplicationException(Response.Status.BAD_REQUEST);						
			}
		}
		return "(" + xPosition + ", " + yPosition + ", " + direction + ")\n";
	}
	
	/**
	 * Moves the rover one step forward taking into account its current
	 * direction if there is space available on the terrain
	 */
	private void move() {
		switch (direction) {
			case 'N':
				if (yPosition + 1 <= yMax)
					yPosition++;
				else
					throw new WebApplicationException(Response.Status.BAD_REQUEST);						
				break;
			case 'S':
				if (yPosition - 1 >= yMin)
					yPosition--;
				else
					throw new WebApplicationException(Response.Status.BAD_REQUEST);						
				break;
			case 'E':
				if (xPosition + 1 <= xMax)
					xPosition++;
				else
					throw new WebApplicationException(Response.Status.BAD_REQUEST);						
				break;
			case 'W':
				if (xPosition - 1 >= xMin)
					xPosition--;
				else
					throw new WebApplicationException(Response.Status.BAD_REQUEST);						
				break;
			default:
				break;
		}
	}
	
	/**
	 * Turns the rover counterclockwise and returns its new direction
	 */
	private void left() {
		switch (direction){
			case 'N':
				direction = 'W';
				break;
			case 'S':
				direction = 'E';
				break;
			case 'E':
				direction = 'N';
				break;
			case 'W':
				direction = 'S';
				break;
			default:
				break;
		}
	}

	/**
	 * Turns the rover clockwise and returns its new direction
	 */
	private void right() {
		switch (direction){
			case 'N':
				direction = 'E';
				break;
			case 'S':
				direction = 'W';
				break;
			case 'E':
				direction = 'S';
				break;
			case 'W':
				direction = 'N';
				break;
			default:
				break;
		}
	}

}
