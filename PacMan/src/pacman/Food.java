package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

//every food placed on the screen is treated as an object
public class Food extends PacmanObject {	//inherits from pacman object
	
	public int weight; // every food has a weight attached to it
	
	//constructor which needs the x and y positions of the food and what weight it holds
	public Food(int x, int y, int weight) {
		super(x, y);				//call PacManObject class
		this.weight=weight;			//set weight
	}
	
	//draw method will use to 2d graphics to draw a small oval in the center of a block
	public void draw(Graphics2D g2D, int BLOCK_SIZE) {
		g2D.setColor(Color.white);
		g2D.fillOval(this.x+BLOCK_SIZE/2, this.y+BLOCK_SIZE/2, 5, 5);
	}
}
