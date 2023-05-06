package pacman;

import javax.swing.JFrame;

public class MazeFrame extends JFrame {	

	private static final long serialVersionUID = 1L;
	MazePanel panel;	//declare our panel
	
	public MazeFrame() {								//constructor to initialize our frame
		panel = new MazePanel();						//call constructor to initialize panel
		this.setTitle("Pacman");						//set title of window to pacman
		this.setResizable(false);						//stop user from resizing the window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//when window closed, exit program
		this.add(panel);								//add panel to frame
		this.pack();									//sizes the frame to preferred size				
		this.setLocationRelativeTo(null);				//we don't the window to be somewhere specific	
		this.setVisible(true);							//make frame visible
	}
}
	
