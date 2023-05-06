package pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class Ghost extends PacmanObject{	//inherits from pacman object
	
	private int speed;						//ghost's speed attribute
	private Image image;					//ghost's image attribute
	private boolean up, down, right, left;	//boolean to check in which direction the ghost moves
	
	
	//constructor to set position(x,y) and speed
	public Ghost(int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
	}

	public Image getImage() {				//getter for ghost image
		return this.image;
	}

	public void setImage(Image image) {		//setter for ghost image
		this.image = image;
	}

	public int getSpeed() {					//getter for ghost image
		return speed;
	}

	public void setSpeed(int speed) {		//setter for ghost image
		this.speed = speed;
	}
	
	//method randomizes ghost direction
	public void randomDirection() {			
		int random = new Random().nextInt(4+0);		//generate random number between 0 and 3
		if (random==0) {					//if 0, set direction as up and others as false
			up = true; 
			down = false;
			right = false;
			left = false;
		}
		if (random==1) {					//if 1, set direction as down and others as false
			up = false; 
			down = true;
			right = false;
			left = false;
		}
		if (random==2) {					//if 2, set direction as left and others as false
			up = false; 
			down = false;
			left = true;
			right = false;
		}
		if (random==3) {					//if 3, set direction as right and others as false
			up = false; 
			down = false;
			left = false;
			right = true;
		}
	}
	
	//move method moves by block size and checks position against wall grid
	public void move(int BLOCK_SIZE, WallGrid wallgrid) {
		
		if (up==true) {							//check if direction is set as up, call move up method
			MoveUp(wallgrid, BLOCK_SIZE);
		}
		if (down==true) {						//check if direction is set as down, call move down method
			MoveDown(wallgrid, BLOCK_SIZE);
		}
		if (left==true) {						//check if direction is set as left, call move left method
			MoveLeft(wallgrid, BLOCK_SIZE);
		}
		if (right==true) {						//check if direction is set as up, call move right method
			MoveRight(wallgrid, BLOCK_SIZE);
		}
	
	}
	
	//following move methods check if the ghost position is equal to any x,y position in the wallgrid
	//if yes do not move ghost
	//if no set speed according to direction
	//and set respective direction
	public void MoveUp(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x, y-speed, BLOCK_SIZE)) {
			return;
		}
		this.y = y - speed;
		up = true; 
		down = false;
		right = false;
		left = false;
	}
	
	public void MoveDown(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x, y+speed, BLOCK_SIZE)) {
			return;
		}
		this.y = y + speed;
		up = false; 
		down = true;
		right = false;
		left = false;
	}
	
	public void MoveRight(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x+speed, y, BLOCK_SIZE)) {
			return;
		}
		this.x = x + speed;
		up = false; 
		down = false;
		right = true;
		left = false;
	}

	public void MoveLeft(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x-speed, y, BLOCK_SIZE)) {
			return;
		}
		this.x = x - speed;
		up = false; 
		down = false;
		right = false;
		left = true;
	}
	
	//checks if the ghost has hit a wall or window endpoints by calling their respective methods
	public boolean CheckCollision(WallGrid wallgrid, int BLOCK_SIZE) {
		if (CheckWindowCollision(wallgrid, BLOCK_SIZE) || wallgrid.CheckWallCollision(this.x, this.y, BLOCK_SIZE)) {
			return true;
		}
		return false;
	}
	
	//checks if the ghost is near an window endpoint and act in the opposite direction
	public boolean CheckWindowCollision(WallGrid wallgrid, int BLOCK_SIZE) {
        if (this.x<0) {            //if it moves left and out of window, move ghost right
            this.MoveRight(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.x>BLOCK_SIZE*14) {//if it moves right and out of window, move ghost left
            this.MoveLeft(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.y<0) {            //if it moves up and out of window, move ghost down
            this.MoveDown(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.y>BLOCK_SIZE*14) {//if it moves down and out of window, move ghost up
            this.MoveUp(wallgrid, BLOCK_SIZE);
            return true;
        }
        return false;
    }
	
	//checks if the pacman position is equal to the ghost position
	public boolean CheckGhostCollision(int x, int y, Ghost ghost) {
		if ((ghost.x ==x) && (ghost.y == y)) {
			return true;
		}
		return false;
	}
	
	//draw a ghost 30 by 30 pixels in the relative x and y position
	public void drawGhost(Graphics2D g2D, Ghost ghost) {
		g2D.drawImage(ghost.getImage(), this.x, this.y, null);
	}
}