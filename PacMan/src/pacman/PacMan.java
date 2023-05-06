package pacman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

//PacMan inherits from PacmanObject
public class PacMan extends PacmanObject {
	
	int speed;
	private Image image;
	private boolean up, down, right, left;
	
	//constructor to set position(x,y), speed and directions
	public PacMan(int x, int y, int speed) {
		super(x, y);
		up = false;
		down = false;
		right = false;
		left = false;
		this.speed = speed;
	}
	
	public void setPosition(int x, int y, int speed) {
		this.x	= x;
		this.y = y;
		up = false;
		down = false;
		right = false;
		left = false;
		this.speed = speed;
	}
	
	public Image getImage() { 				//image getter
		return this.image;
	}
	
	public void setImage(Image image) {		//image setter
		this.image = image;
	}
		
	public int getSpeed() {					//pacman speed getter
		return this.speed;
	}
	
	public void setSpeed(int speed) {		//pacman speed setter
		this.speed = speed;
	}
	
	//move method moves by block size and checks position against wall grid
	public void move(int BLOCK_SIZE, WallGrid wallgrid) {
		
			if (up==true) {								//check if direction is set as up, call move up method
				MoveUp(wallgrid, BLOCK_SIZE);
			}	
			if (down==true) {							//check if direction is set as down, call move down method
				MoveDown(wallgrid, BLOCK_SIZE);
			}
			if (left==true) {							//check if direction is set as left, call move left method
				MoveLeft(wallgrid, BLOCK_SIZE);
			}	
			if (right==true) {							//check if direction is set as up, call move right method
				MoveRight(wallgrid, BLOCK_SIZE);
			}
		
	}

	//following move methods check if the pacman position is equal to any x,y position in the wallgrid
		//if yes do not move ghost
		//if no set speed according to direction
		//and set respective direction
	public void MoveUp(WallGrid wallgrid, int BLOCK_SIZE) {
		
		if(wallgrid.CheckWallCollision(x, y-speed, BLOCK_SIZE)==true) {
			return;
		}
		this.y = y - speed;
	}
	
	public void MoveDown(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x, y+speed, BLOCK_SIZE)==true) {
			return;
		}
		this.y = y + speed;
	}
	
	public void MoveRight(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x+speed, y, BLOCK_SIZE)==true) {
			return;
		}
		this.x = x + speed;
	}

	public void MoveLeft(WallGrid wallgrid, int BLOCK_SIZE) {
		if(wallgrid.CheckWallCollision(x-speed, y, BLOCK_SIZE)==true) {
			return;
		}
		this.x = x - speed;
	}
	//checks if the pacman has hit a wall or window endpoints by calling their respective methods
	public boolean CheckCollision(WallGrid wallgrid, int BLOCK_SIZE) {
		if (CheckWindowCollision(wallgrid, BLOCK_SIZE) || wallgrid.CheckWallCollision(this.x, this.y, BLOCK_SIZE)) {
			return true;
		}
		return false;
	}
	
	//checks if the pacman is near an window endpoint and act in the opposite direction
	public boolean CheckWindowCollision(WallGrid wallgrid, int BLOCK_SIZE) {
        if (this.x<0) {            //if it moves left and out of window, move pacman right
            this.MoveRight(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.x>BLOCK_SIZE*14) {//if it moves right and out of window, move pacman left
            this.MoveLeft(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.y<0) {            //if it moves up and out of window, move pacman down
            this.MoveDown(wallgrid, BLOCK_SIZE);
            return true;
        }
        if (this.y>BLOCK_SIZE*14) {//if it moves down and out of window, move pacman up
            this.MoveUp(wallgrid, BLOCK_SIZE);
            return true;
        }
        return false;
    }
	
	// key handler to see what key was pressed and set directions according to it 
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode()==KeyEvent.VK_UP) ||( e.getKeyCode()==KeyEvent.VK_W)) {
			up = true; 				//up key so pacman direction is up and others false
			down = false;
			right = false;
			left = false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_DOWN) ||( e.getKeyCode()==KeyEvent.VK_S)) {
			up = false; 
			down = true;			//down key so pacman direction is up and others false
			right = false;
			left = false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_LEFT) ||( e.getKeyCode()==KeyEvent.VK_A)) {
			up = false; 
			down = false;
			left = true;			//left key so pacman direction is up and others false
			right = false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_RIGHT) ||( e.getKeyCode()==KeyEvent.VK_D)) {
			up = false; 
			down = false;
			left = false;
			right = true;			//right key so pacman direction is up and others false
		}
	}
	
	
	//draw method draw the pacman image in the relative x and y position
	public void drawPacMan(Graphics2D g2d, PacMan pacman) {
		g2d.drawImage(pacman.getImage(), this.x, this.y, null);
	}
}
