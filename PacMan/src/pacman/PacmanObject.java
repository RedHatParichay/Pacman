package pacman;

//Base class from which Pacman, Ghosts, Food and Wall inherits
//x and y are coordinates on the grid
public class PacmanObject {
	int x;					//x attribute
	int y;					//y attribute
	
	public PacmanObject(int x, int y) {		//constructor to set x and y positions
		this.x = x;
		this.y = y;
	}
}
