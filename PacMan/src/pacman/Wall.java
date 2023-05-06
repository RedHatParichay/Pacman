package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends PacmanObject{	//inherits from pacman object class
	public int weight;					//only new attribute is weight
	
	public Wall(int x, int y, int weight) {		//constructor to set wall at this (x,y) has weight 0
		super(x, y);
		this.weight=weight;
	}
	
	//draw method draw a blue wall with width and height of block size in the x and y position
	public void draw(Graphics2D g2D, int BLOCK_SIZE) {		
		g2D.setColor(Color.blue);
    	g2D.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}
}
