package pacman;

import java.awt.Graphics;

public class Score {			//class to work with scores
	
	int score;					//only attribute score
	
	public Score() {			//constructor initializes score to zero
		this.score = 0;
	}
	public int getScore() {		//score getter
		return this.score;
	}
	
	public void UpdateScore(int weight) {		//updates the total score by adding the weight of the food eaten
		this.score += weight;
	}
	
	//draw method writes the score string in a specific x and y position by converting from integer to string 
	public void draw(Graphics g, int x, int y) {		
		g.drawString(Integer.toString(this.score), x, y);
	}
}
