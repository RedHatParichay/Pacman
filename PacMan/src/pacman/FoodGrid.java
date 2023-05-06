package pacman;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class FoodGrid {		//grid of foods class
	
	private LinkedList<Food> FoodMaze = new LinkedList<Food>();		//declare linked list of type food
	private Food TempFood;					//declare a temp wall attribute of type food for iteration
	
	public FoodGrid() {						//food grid constructor
		
	}
	
	//draw method iterates through linked list and draw the food of height and width block size 
	//by calling the draw method of food class
	public void draw(Graphics2D g2D, int BLOCK_SIZE) {
		for(int i=0; i<FoodMaze.size(); i++) {
			TempFood = FoodMaze.get(i);
			TempFood.draw(g2D, BLOCK_SIZE);
		}
	}
	//add food to linked list
	public void AddFood(Food food) {
		FoodMaze.add(food);
	}
	//remove food from linked list
	public void RemoveFood(int i) {
		FoodMaze.remove(i);
	}
	
	//check if pacman has eaten the food by iterating through the linked list and check if pacman's position
	//is equal to a food position in the linked list
	//if yes, remove the food from the linked list and update score by adding weight using update score method
	//of score class
	public void CheckFoodCollision(PacMan pacman, Score score) {
		for(int i=0; i<FoodMaze.size(); i++) {
			TempFood = FoodMaze.get(i);
			if ((pacman.x==TempFood.x) && (pacman.y==TempFood.y)) {
				RemoveFood(i);
				score.UpdateScore(TempFood.weight);
			}
		}
	}

}
