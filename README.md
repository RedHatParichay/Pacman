# Pacman

This project is one of those that I'm proud of a lot because it is something that works visually.  
A huge shoutout to @M4mbo for the great help he's been for this project, I kid you not, he's one of the most humblest persons to ask 
for help should you ever need to.


It primarily is based on Swing GUI and relies heavily on Java classes. It works in the following way, the PacManGame class calls the 
MazeFrame to initialize the frame which in turn calls the MazePanel class. To create walls, ghosts, food, and the Pacman, they have 
to inherit the basic attribute x and y coordinates from the superclass PacmanObject. To keep track of where the walls and food are 
placed, I used the built-in Java Linked List structure. I also created a score class which deals with updating and writing the score 
on the screen. The ghosts and Pacman move with a combination of which direction to move in(a boolean) and a speed. I had to create 
functions that stop the ghosts and Pacman from going outside the windows and prevent passing through the walls. Finally, the 
MazePanel runs everything. 

