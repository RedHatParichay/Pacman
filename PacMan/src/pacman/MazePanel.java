package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private static final int BLOCK_SIZE = 30;	//every block is made of 30 pixels
	private static final int N_OF_BLOCKS = 15;	//there are 15 blocks in each direction
	private static final int SCREEN_SIZE = BLOCK_SIZE*N_OF_BLOCKS;	//screen size is calculated in pixels
	
	
	//declare an array where 0 represents a wall and the other numbers are weights for scores (15x15)
	private final int ScreenData[] = { 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 0, 18, 16, 
									    0,  0,  0,  0,  0, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 
									    0, 16, 20,  0, 18, 16, 25, 24, 24, 24, 28,  0, 17, 16, 16, 
									    0, 16, 16, 16, 16, 20, 16, 18,  0,  0,  0,  0,  0,  0,  0, 
										0, 16, 16, 16, 16, 16, 16, 16, 20, 20, 20, 25, 19, 18, 18, 
										0, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20, 23, 23, 25,
										0, 16, 16, 16, -1, 16, 16, 16, 16, 20,  0,  0,  0,  0, 21,  
										0, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 
										0, 18,  0, 17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 
									   16, 16, 20, 18,  0,  0, 25, 24, 24, 24, 28,  0, 17, 16, 16, 
										0, 16, 16, 16, 16, 20, 0, 18,  0,  0,  0,  0,  0,  0,  0, 
										0, 16, 16, 16, 16, 16, 16, 16, 20, 20, 20, 25, 19, 18, 18, 
									   17, 16, 16, 16, 16, 16, 16, 16, 20, 20, 20, 25, 19, 18, 18, 
									   18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20, 23, 23, 25,
									   17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0, 21  };
	

	Graphics g;				//to draw images, rectangles and strings, we need graphics
	PacMan pacman;			//declare our player pacman
	Ghost[] ghosts;			//declare an array of ghosts
	FoodGrid foodgrid;		//declare food grid which works with a linked list containing food locations
	WallGrid wallgrid;		//declare wall grid which works with a linked list containing wall locations
	Score score;			//declare our player score variable
	Timer timer;			//declare a timer so we know how often to repaint the screen
	boolean GameStarted;	//boolean that will keep track if the player has started the game or not
	boolean InGame;			//boolean that will keep track if the player is in the game or not
    private int DELAY;		//to stop objects from moving too fast, we add a delay
    int lives;				//to store how many lives our player has left	

	int randomizedDelay = 0;
	
    //load images for different directions of pacman and load images for ghosts
    Image pacman_up = new ImageIcon("images/pacman_up.png").getImage();
    Image pacman_down = new ImageIcon("images/pacman_down.png").getImage();
    Image pacman_left = new ImageIcon("images/pacman_left.png").getImage();
    Image pacman_right = new ImageIcon("images/pacman_right.png").getImage();
    Image ghost1_pic = new ImageIcon("images/ghost1.png").getImage();
    Image ghost2_pic = new ImageIcon("images/ghost2.png").getImage();
    Image ghost3_pic = new ImageIcon("images/ghost3.png").getImage();
    Image ghost4_pic = new ImageIcon("images/ghost4.png").getImage();
    
    //constructor to 
    public MazePanel(){
    	GameStarted = false;		// game has not been started
    	InGame = false;				// game is not being played
    	lives=3;					// lives are 3 
    	
    	ghosts = new Ghost[4];		// 4 ghosts
    	pacman = new PacMan(BLOCK_SIZE*4, BLOCK_SIZE*6, BLOCK_SIZE); //one pacman
    	
    	//initialize ghost positions
    	ghosts[0] = new Ghost(0, 0, BLOCK_SIZE);
    	ghosts[1] = new Ghost(BLOCK_SIZE*14, 0, BLOCK_SIZE);
    	ghosts[2] = new Ghost(0, BLOCK_SIZE*14, BLOCK_SIZE);
    	ghosts[3] = new Ghost(BLOCK_SIZE*14, BLOCK_SIZE*14, BLOCK_SIZE);
    	
    	//initialize food and wall grids
    	foodgrid = new FoodGrid();
    	wallgrid = new WallGrid();;

    	//score variable
    	score = new Score();
    	
    	//set images 
    	pacman.setImage(pacman_up);
    	ghosts[0].setImage(ghost1_pic);
    	ghosts[1].setImage(ghost2_pic);
    	ghosts[2].setImage(ghost3_pic);
    	ghosts[3].setImage(ghost4_pic);
    	
    	//set background, add key listener, and size
    	this.setBackground(Color.black);
    	this.addKeyListener(new AL());
    	this.setFocusable(true);
    	this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
    	
    	initGrid();				//call initialize grid    	
    }
    
    //initialize grid by add the walls to wall grid because of a 0 or add food to food grid by iterating through 
    //every x and y
    public void initGrid() {
    	int i = 0;
    	for (int y = 0; y < SCREEN_SIZE; y=y+BLOCK_SIZE) {
            for (int x = 0; x < SCREEN_SIZE; x=x+BLOCK_SIZE) { 
            	if (ScreenData[i]==0) {
            		wallgrid.AddWall(new Wall(x, y, ScreenData[i]));
            	}
            	else if (ScreenData[i]==-1) {
            		
            	}
            	else {
            		foodgrid.AddFood(new Food(x, y, ScreenData[i]));
            	}
            	i++;
            }
    	}    
    }
    
    //start game by initialize boolean playing game and starting timer
    public void startGame() {
    	InGame = true;
    	timer = new Timer(100, this);
    	timer.start();
    }
    
    
    public void paint(Graphics g) {
    	
		super.paint(g);
		
    	Graphics2D g2D = (Graphics2D) g;  //turn graphics to 2d graphics
    	
    	//if game has started
    	if (GameStarted==true) {
        	//if game is being played
    		if (InGame==true) {
        		
        		//print lives
        		g.setColor(Color.red);
            	g.setFont(new Font("Times New Roman", Font.BOLD, 15));
            	g.drawString("Lives: "+lives, BLOCK_SIZE*5, 15);
            	
            	//print score
        		g.setColor(Color.red);
            	g.setFont(new Font("Times New Roman", Font.BOLD, 15));
            	g.drawString("Score: "+score.getScore(), BLOCK_SIZE*7, 15);
            	
            	//draw pacman
        		pacman.drawPacMan(g2D, pacman);
        		//draw ghosts
            	for (int i =0; i<ghosts.length; i++) {
            		ghosts[i].drawGhost(g2D, ghosts[i]);
            	}
            	//draw food grid
            	foodgrid.draw(g2D, BLOCK_SIZE);
            	//draw wall grid
            	wallgrid.draw(g2D, BLOCK_SIZE);	
        	}
    		
        	//if game is finished due to lives =0 go to game over

    		else if (InGame==false){
        		GameOver(g);
        	}
    	}
    	//else print start screen
    	else {
    		g.setColor(Color.black);
    		g.fillRect(0,0,SCREEN_SIZE, SCREEN_SIZE);
        	g.setColor(Color.yellow);
        	g.setFont(new Font("Emulogic", Font.BOLD, 20));
        	g.drawString("PRESS SPACE TO START ", SCREEN_SIZE/15, SCREEN_SIZE/2);
    	}    	
    }
    
    //move method moves pacman and ghosts 
    public void move() {
    	if (DELAY==2) {
			randomizedDelay++;
    		pacman.move(BLOCK_SIZE, wallgrid);
    		DELAY=0;
			if(randomizedDelay == 4){
				randomizedDelay = 0;
				for(int i=0; i<ghosts.length; i++) {
					ghosts[i].randomDirection();			//generate random direction for ghosts
					ghosts[i].move(BLOCK_SIZE, wallgrid);	//move ghosts accordingly
				}
			}
    		
    	}
    	
    }
   
    //method checks if pacman has eaten any food or pacman has hit a wall
    //or within the ghosts array, has any ghost hit a wall
    //or if has pacman hit a ghost, decrement a live
    //or if lives are 0, stop game
    public void checkCollision() {
    	foodgrid.CheckFoodCollision(pacman, score);  
    	pacman.CheckCollision(wallgrid, BLOCK_SIZE);
    	for(int i=0; i<ghosts.length; i++) {
    		ghosts[i].CheckCollision(wallgrid,BLOCK_SIZE);
    		if (ghosts[i].CheckGhostCollision(pacman.x, pacman.y, ghosts[i])==true) {
    			lives-=1;
    			pacman.setPosition(BLOCK_SIZE*4, BLOCK_SIZE*6, BLOCK_SIZE);
    		}
    		else if ((lives==0)||(score.getScore()==3305)) {
    			InGame = false;
    		}
    	}
    }
    
    //game over will print the score and game over information using draw strings method
    public void GameOver(Graphics g) {
    	g.setColor(Color.red);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	g.drawString("Score: "+score.getScore(), SCREEN_SIZE/3, 50);
    	
    	
    	g.setColor(Color.red);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	g.drawString("GAME OVER! ", SCREEN_SIZE/4, SCREEN_SIZE/2);
    }
    
    //key adapter class handles the key pressed using the key pressed method and set the images according to
    //direction
    public class AL extends KeyAdapter {
    	public void keyPressed(KeyEvent e) {
    		pacman.keyPressed(e);
    		if ((e.getKeyCode()==KeyEvent.VK_UP) ||( e.getKeyCode()==KeyEvent.VK_W)) {
    			pacman.setImage(pacman_up);
    		}
    		if ((e.getKeyCode()==KeyEvent.VK_DOWN) ||( e.getKeyCode()==KeyEvent.VK_S)){
    			pacman.setImage(pacman_down);
    		}
    		if ((e.getKeyCode()==KeyEvent.VK_LEFT) || ( e.getKeyCode()==KeyEvent.VK_A)) {
    			pacman.setImage(pacman_left);
    		}
    		if ((e.getKeyCode()==KeyEvent.VK_RIGHT) || ( e.getKeyCode()==KeyEvent.VK_D)) {
    			pacman.setImage(pacman_right);
    		}
    		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
    			GameStarted=true;
    			InGame=true;
    			startGame();
    		}
    
    	}	
    }
    
    //if anything happens on the screen, the following is done,
    //move ghosts and pacman
    //check for any collisions
    //repaint the screen
    //increment delay
    public void actionPerformed(ActionEvent e) {
    	move();
    	checkCollision();
    	repaint();
    	DELAY++;
    }
}

