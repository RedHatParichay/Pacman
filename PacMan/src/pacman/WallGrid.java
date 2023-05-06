package pacman;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class WallGrid {	// grid of walls class
	
	private LinkedList<Wall> WallMaze = new LinkedList<Wall>();		//declare linked list of type wall
	private Wall TempWall;						//declare a temp wall attribute of type wall for iteration
	
	public WallGrid() {					//wall grid constructor 
		
	}
	
	//draw method iterates through linked list and draw the wall of height and width block size 
	//by calling the draw method of wall class
	public void draw(Graphics2D g2D, int BLOCK_SIZE) {
		for(int i=0; i<WallMaze.size(); i++) {
			TempWall = WallMaze.get(i);
			TempWall.draw(g2D, BLOCK_SIZE);
		}
	}
	//add wall to linked list
	public void AddWall(Wall wall) {
		WallMaze.add(wall);
	}
	
	//check if pacman/ghost has hit a wall by iterating through the linked list and check if pacman/ghost's 
	//position
	//is equal to a wall position in the linked list
	//if yes, return true, else, return false
	public boolean CheckWallCollision(int x, int y, int BLOCK_SIZE) {
		for(int i=0; i<WallMaze.size(); i++) {
			TempWall = WallMaze.get(i);
			if ((x==TempWall.x) && (y== TempWall.y)) {
				return true;
			}
			
		}
		return false;
	}
}


