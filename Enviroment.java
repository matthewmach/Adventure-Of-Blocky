/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        This program holds all the inteactions between the user and objects, mostly blocks.
		Also moves the player by editing playerPosition.
*/

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;


public class Enviroment
{
    //Instance Variables
    /*
    Name                    Type            Description
    c                       Draw            Grants access to all of Draw's commands and variables as well as hsa.Consoles; basically edited hsa.Console; draws on MainGame's Draw
    g                       GameMap         Grants access to all of GameMaps's commands and variables; used for changing values of currentRoom and room transitions; we also need access to the map images to erase

    listOfImpassableBlocks  String[]        Stores a list of block that the user cannot pass through, makes it so that the user cannot pahse through objects

    counter                 int[]           Stores both the current mode of the counter and the state of the counter; used for when pushing blocks
    tempX                   int             Stores the temporary x coordinate value of the block, used to prevent linking
    tempY                   int             Stores the temporary y coordinate valve of the block, use to prevent linking
    */
    Draw c = MainGame.c;
    GameMap g = MainGame.g;

    String[] listOfImpassableBlocks = {"block", "tree"};

    int[] counter = {0, 0};

    int tempX, tempY;

    //isMovenment valid is basically our collision mechanics
    //Controls whether the player can move in the given direction or not
    //The first statement basically prevents ArrayOutOfBounds Exceptions and automatically allows movement
    //The movement is then controlled by playerMove
    //The next if statement checks for the direction in which the user is moving.
    //Used to determine whether the block in said direct is impassable or not
    //Realistically the next two if statements could be put into one, but that would be pain to read (trust me I tried, wasn't a bright idea, had a hard time figuring out what was happening)
    //The first if statement checks for border movement by the xy coordinate
    //The second if statement is special as it covers an interesting case.
    //playerPosition (the one in this method) gives the x and y coordinate of the grid, not the actualy x and y coordinates
    //Good enough for borders, not when the block can be accessed from all sides
    //The coordinates are determined by the top right corner
    //That means the player could be colliding with a Impassable block but the method wont pick it up as it think the player is in another block
    //This second if statement essentually checks using ranges, can be edited to chek for values as there is an expected value, but is too inconvienent for me
    //If it determine the block to be impassable, the method returns false
    //If the block is passable, the method returns true
    /*
    Name                Type            Description
    playerPosition      int[]           Stores the current x and y grid coordinates of the player
    */
    public boolean isMovementValid (String direction)
    {
	int[] playerPosition = { (int) (c.playerPosition [0] [0] / 30), (int) (c.playerPosition [0] [1] / 30) };

	//Preventing ArrayOutOfBounds Exception
	if ((playerPosition [1] - 1 < 0 && direction.equals ("Up")) || (playerPosition [0] - 1 < 0 && direction.equals ("Left")) || (playerPosition [1] + 1 > 16 && direction.equals ("Down")) || (playerPosition [0] + 1 > 28 && direction.equals ("Right")))
	    return true;

	if (direction.equals ("Up")) //Check the direction
	{
	    //Check each block
	    for (int x = 0 ; x < listOfImpassableBlocks.length ; x++)
	    {
		//Check if the player in only one block
		if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0]] [playerPosition [1] - 1]) && (((double) (c.playerPosition [0] [1] / 30.0)) - ((int) (c.playerPosition [0] [1] / 30)) == 0.0))
		    return false;
		else if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] + 1] [playerPosition [1] - 1]) && ((playerPosition [0] + 1) * 30 < c.playerPosition [0] [0] + 24) && (c.playerPosition [0] [0] + 24 < (playerPosition [0] + 2) * 30) && (playerPosition [1] * 30 == c.playerPosition [0] [1]))
		    return false;
		//^ Check if the player is in two blocks
		//Same logic of code for the next 3
	    }
	}
	else if (direction.equals ("Down"))
	{
	    for (int x = 0 ; x < listOfImpassableBlocks.length ; x++)
	    {
		if ((listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0]] [playerPosition [1] + 1])) && (Double.toString (((double) (c.playerPosition [0] [1] / 30.0)) - ((int) (c.playerPosition [0] [1] / 30))).substring (0, 3).equals ("0.1")))
		    return false;
		else if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] + 1] [playerPosition [1] + 1]) && ((playerPosition [0] + 1) * 30 < c.playerPosition [0] [0] + 24) && (c.playerPosition [0] [0] + 24 < (playerPosition [0] + 2) * 30) && ((playerPosition [1] + 1) * 30 - 24 == c.playerPosition [0] [1]))
		    return false;
	    }
	}
	else if (direction.equals ("Right"))
	{
	    for (int x = 0 ; x < listOfImpassableBlocks.length ; x++)
	    {
		if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] + 1] [playerPosition [1]]) && (Double.toString (((double) (c.playerPosition [0] [0] / 30.0)) - ((int) (c.playerPosition [0] [0] / 30))).substring (0, 3).equals ("0.1")))
		    return false;
		else if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] + 1] [playerPosition [1] + 1]) && ((playerPosition [1] + 1) * 30 < c.playerPosition [0] [1] + 24) && (c.playerPosition [0] [1] + 24 < (playerPosition [1] + 2) * 30) && ((playerPosition [0] + 1) * 30 - 24 == c.playerPosition [0] [0]))
		    return false;
	    }
	}
	else if (direction.equals ("Left"))
	{
	    for (int x = 0 ; x < listOfImpassableBlocks.length ; x++)
	    {
		if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] - 1] [playerPosition [1]]) && (((double) (c.playerPosition [0] [0] / 30.0)) - ((int) (c.playerPosition [0] [0] / 30)) == 0.0))
		    return false;
		else if (listOfImpassableBlocks [x].equals (g.currentRoom [playerPosition [0] - 1] [playerPosition [1] + 1]) && ((playerPosition [1] + 1) * 30 < c.playerPosition [0] [1] + 24) && (c.playerPosition [0] [1] + 24 < (playerPosition [1] + 2) * 30) && (playerPosition [0] * 30 == c.playerPosition [0] [0]))
		    return false;
	    }
	}
	return true;
    }


    //gets the tile depending on the direction; used only for playerMove
    /*
    Name                        Type    Description
    playerPosition  int[]       Stores the xy grid co-ordinates
    */
    public String getTile (String direction)
    {
	int[] playerPosition = { (int) (c.playerPosition [0] [0] / 30), (int) (c.playerPosition [0] [1] / 30) };

	if (direction.equals ("Up")) //Check direction
	    return g.currentRoom [playerPosition [0]] [playerPosition [1] - 1]; //Return proper block
	else if (direction.equals ("Down"))
	    return g.currentRoom [playerPosition [0]] [playerPosition [1] + 1];
	else if (direction.equals ("Right"))
	    return g.currentRoom [playerPosition [0] + 1] [playerPosition [1]];
	else if (direction.equals ("Left"))
	    return g.currentRoom [playerPosition [0] - 1] [playerPosition [1]];
	return "None";
    }


    //Moves the player!
    //The first if statement checks for the direction the player want to move to.
    //The second if statement check if the movement is valid
    //If it is it moves the player accordingly
    //If not it then checks if the the said impasssable tile is a block
    //If it is it adds to the counter.
    //When adding to the counter, it check what mode the counter is, each direction has a different mode
    //Then it checks if the counter is at 5 so that it can move the block accordingly
    //For movement "Up" and "Down", we have to check for room Transistions
    //We use an if statement for that
    //We also have to check when the user completes all levels, thus the if statement
    //Realistically playerPosition isn't needed but is there for convience.
    /*
    Name                Type            Description
    playerPosition      int[]           Stores the grid x y coordinates
    playerXYPosition    double[]        Stores the grid x y co ordinate as doubles; Needed for more precise if statements when accessing the play's co ordinates
    */
    public void playerMove (String direction)
    {
	int[] playerPosition = { (int) (c.playerPosition [0] [0] / 30), (int) (c.playerPosition [0] [1] / 30) };
	double[] playerXYPosition = { ((double) c.playerPosition [0] [0]) / (30.0), ((double) c.playerPosition [0] [1]) / (30.0) };

	//Check the direction
	if (direction.equals ("Up"))
	{
	    //Check if movement is valid
	    if (!isMovementValid ("Up"))
	    {
		//Check if the tile is a block
		if (getTile ("Up").equals ("block"))
		{
		    //Add one to the count or change counter if not the right mode
		    if (counter [0] == 0)
			counter [1]++;
		    else
		    {
			counter [0] = 0;
			counter [1] = 1;
		    }

		    //Once the counter reachs 5, the block moves
		    if (counter [1] == 5)
		    {
			moveBlock ("Up", playerPosition [0], playerPosition [1]);
			counter [1] = 0;
		    }
		}
	    }
	    else
	    {
		//Check for room transisitons, makes up for preventing ArrayOutOfBounds Exceptions
		if (playerXYPosition [1] == 0.0)
		{
		    //Checks to see if game is finished
		    if (c.currentMapName.equals ("f3"))
			c.gameFinished = true;
		    else //if not transistion room
			roomTransition ("Up");
		}
		else
		{
		    //Moves the player
		    c.playerPosition [0] [1] -= 6;
		    //Resets the counter
		    counter [1] = 0;

		    //the rest of the next 3 code is practially the same
		}
	    }
	}
	else if (direction.equals ("Right"))
	{
	    if (!isMovementValid ("Right"))
	    {
		if (getTile ("Right").equals ("block"))
		{
		    if (counter [0] != 1)
		    {
			counter [0] = 1;
			counter [1] = 1;
		    }
		    else
			counter [1]++;

		    if (counter [1] == 5)
		    {
			moveBlock ("Right", playerPosition [0], playerPosition [1]);
			counter [1] = 0;
		    }
		}
	    }
	    else
	    {
		c.playerPosition [0] [0] += 6;
		counter [1] = 0;
	    }
	}
	else if (direction.equals ("Down"))
	{
	    if (!isMovementValid ("Down"))
	    {
		if (getTile ("Down").equals ("block"))
		{
		    if (counter [0] != 2)
		    {
			counter [0] = 2;
			counter [1] = 1;
		    }
		    else
			counter [1]++;

		    if (counter [1] == 5)
		    {
			moveBlock ("Down", playerPosition [0], playerPosition [1]);
			counter [1] = 0;
		    }
		}
	    }
	    else
	    {
		if (playerXYPosition [1] == 16.2)
		    roomTransition ("Down");
		else
		{
		    c.playerPosition [0] [1] += 6;
		    counter [1] = 0;
		}
	    }
	}
	else if (direction.equals ("Left"))
	{
	    if (!isMovementValid ("Left"))
	    {
		if (getTile ("Left").equals ("block"))
		{
		    if (counter [0] != 3)
		    {
			counter [0] = 3;
			counter [1] = 1;
		    }
		    else
			counter [1]++;

		    if (counter [1] == 5)
		    {
			moveBlock ("Left", playerPosition [0], playerPosition [1]);
			counter [1] = 0;
		    }
		}
	    }
	    else
	    {
		c.playerPosition [0] [0] -= 6;
		counter [1] = 0;
	    }
	}
    }


    //Our code that animate the movement of the block! This is an overloaded method!
    //This will be run in a thread
    //The one if statement we use here jsut determines where the block moves
    //Notes this is just the animation, all changes to variables are done in moveBlock
    /*
    Name            Type            Description
    direction       String          Stores the direction in which the block is moving
    x               int             Stores the current x grid co ordinate of the block that is about to be moved
    y               int             Stores the current y grid co ordinate of the block that is about to be moved
    tf              boolean         Placeholder; serves no purpose other than indicating the overloaded method
    */
    public void moveBlock (String direction, int x, int y, boolean tf)
    {
	x *= 30;
	y *= 30;
	for (int a = 1 ; a < 31 ; a++)
	{
	    if (direction.equals ("Up"))
	    {
		c.drawRect (x, y - a + 1, 30, 30, new Color (153, 102, 51));
		c.drawBlock (x, y - a);
	    }
	    else if (direction.equals ("Down"))
	    {
		c.drawRect (x, y + a - 1, 30, 30, new Color (153, 102, 51));
		c.drawBlock (x, y + a);
	    }
	    else if (direction.equals ("Right"))
	    {
		c.drawRect (x + a - 1, y, 30, 30, new Color (153, 102, 51));
		c.drawBlock (x + a, y);
	    }
	    else if (direction.equals ("Left"))
	    {
		c.drawRect (x - a + 1, y, 30, 30, new Color (153, 102, 51));
		c.drawBlock (x - a, y);
	    }
	    c.update ();
	    try
	    {
		Thread.sleep (0);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    //This method manages all the variable changes when moving the block
    //Also runs the animation to move the block.
    //test starts as true and may may false after goingt hrough some if stateement which are our "tests"
    //The first if statement check for the direction.
    //The second if statement then checks for an ArrayOutOfBound exception, which fails the test if it is.
    //Note: this test canno fail as a block cannot be in a position to fail the test.
    //Then if check to see if the tile that the block would go onto is an impassable block, if it is the test fails it
    //Then we check if the tile in the position that the block would be moved to is a border tile, if it is, the test fails
    //Finally if none of the tests are failed, the methods changes some values in the variables and runs the animation
    /*
    Name                Type            Description
    direction           String          Stores the direction in which the block is going to move
    x                   int             Stores the x grid coordinate of the block
    y                   int             Stores the y grid coordinate of the block
    test                boolean         Stores whether the block is able to move in the space the player is trying to move it
    */
    public void moveBlock (String direction, int x, int y)
    {
	boolean test = true; //Sets it to true so that it only turns false if it fails the test
	tempX = x;
	tempY = y;

	//Checks what direction the block is going to move
	if (direction.equals ("Left"))
	{
	    //Check for out of bounds exception
	    if (x - 2 > -1)
	    {
		//Tests if the block in the expected position is an impassable block
		for (int a = 0 ; a < listOfImpassableBlocks.length ; a++)
		    if (g.currentRoom [x - 2] [y].equals (listOfImpassableBlocks [a]))
			test = false;
		//Checks if the tile in the excpeted if the position is a border block
		if (x - 2 == 0)
		    test = false;
	    }

	    //If all the tests pass, this runs
	    if (test)
	    {
		//The current position of the block is set to "none"
		g.currentRoom [x - 1] [y] = "none";
		//The expected posotion of the block is set to "block"
		g.currentRoom [x - 2] [y] = "block";
		//the animation is run
		new Thread ()
		{
		    public void run ()
		    {
			moveBlock ("Left", tempX - 1, tempY, true);
		    }
		}
		.start ();
	    }
	    //The structure of the code for the next 3 is the same
	}
	else if (direction.equals ("Right"))
	{
	    if (x + 2 < 29)
	    {
		for (int a = 0 ; a < listOfImpassableBlocks.length ; a++)
		    if (g.currentRoom [x + 2] [y].equals (listOfImpassableBlocks [a]))
			test = false;
		if (x + 2 == 28)
		    test = false;
	    }

	    if (test)
	    {
		g.currentRoom [x + 1] [y] = "none";
		g.currentRoom [x + 2] [y] = "block";
		new Thread ()
		{
		    public void run ()
		    {
			moveBlock ("Right", tempX + 1, tempY, true);
		    }
		}
		.start ();
	    }
	}
	else if (direction.equals ("Up"))
	{
	    if (y - 2 > -1)
	    {
		for (int a = 0 ; a < listOfImpassableBlocks.length ; a++)
		    if (g.currentRoom [x] [y - 2].equals (listOfImpassableBlocks [a]))
			test = false;
		if (y - 2 == 0)
		    test = false;
	    }

	    if (test)
	    {
		g.currentRoom [x] [y - 1] = "none";
		g.currentRoom [x] [y - 2] = "block";
		new Thread ()
		{
		    public void run ()
		    {
			moveBlock ("Up", tempX, tempY - 1, true);
		    }
		}
		.start ();
	    }
	}
	else if (direction.equals ("Down"))
	{
	    if (y + 2 < 17)
	    {
		for (int a = 0 ; a < listOfImpassableBlocks.length ; a++)
		    if (g.currentRoom [x] [y + 2].equals (listOfImpassableBlocks [a]))
			test = false;
		if (y + 2 == 16)
		    test = false;
	    }

	    if (test)
	    {
		g.currentRoom [x] [y + 1] = "none";
		g.currentRoom [x] [y + 2] = "block";
		new Thread ()
		{
		    public void run ()
		    {
			moveBlock ("Down", tempX, tempY + 1, true);
		    }
		}
		.start ();
	    }
	}
    }


    //This handles all transitioning of rooms.
    //There are only two directions the player can transition, Up and Down
    //if first have to obtain the names of the maps and their indexes in the array g.map
    //We do that by looking at the current room and direction and looking at the currentroom's second character and adding 1 to that number
    //Then we use a loop to determine the index for both rooms
    //Then we start the animation; note that we have to constantly redraw my fake top border, I don't think this si considered an erase as I'm trying t omimic the bottom un drawable part of the console
    //We then readjust the player's position, currentMapName, and set up the currentRoom
    /*
    Name            Type            Description
    direction       String          This stores the direction in which the room is transitioning
    tempMapNames    String[]        This stores the names of the two maps that are ebing transitioned
    area            int[]           This stores the array indexs for the maps
    rooms           int[]           This stores the array indexs for the maps
    */
    public void roomTransition (String direction)
    {
	String[] tempMapNames = new String [2];
	int[] areas = new int [2], rooms = new int [2];

	if (direction.equals ("Up"))
	{

	    tempMapNames [0] = c.currentMapName;
	    tempMapNames [1] = c.currentMapName.charAt (0) + Integer.toString (Character.getNumericValue (c.currentMapName.charAt (1)) + 1);
	    for (int x = 0 ; x < 2 ; x++)
	    {
		if (tempMapNames [x].charAt (0) == 'f')
		    areas [x] = 0;
		else if (tempMapNames [x].charAt (0) == 'c')
		    areas [x] = 1;
		else
		    areas [x] = 2;

		if (tempMapNames [x].charAt (1) == '1')
		    rooms [x] = 0;
		else if (tempMapNames [x].charAt (1) == '2')
		    rooms [x] = 1;
		else
		    rooms [x] = 2;
	    }

	    for (int y = 1 ; y < 509 ; y++)
	    {
		c.drawImage (g.map [areas [0]] [rooms [0]], -1, y, null);
		c.drawImage (g.map [areas [1]] [rooms [1]], -1, y - 510, null);
		c.currentMap = c.getClip (0, 0, c.maxx () + 2, c.maxy () + 2);
		c.fillRect (-10, -10, 970, 10.f, Color.WHITE);
		c.update ();
		try {Thread.sleep (0);} catch (Exception e){}
	    }
	    c.playerPosition [0] [0] = 420;
	    c.playerPosition [0] [1] = 486;
	    c.playerPosition [1] [0] = 420;
	    c.playerPosition [1] [1] = 486;
	    c.currentMapName = tempMapNames [1];
	    c.drawImage (c.currentMap = g.map [areas [1]] [rooms [1]], -1, -1, null);
	    g.setCurrentRoom ();
	    g.setBlocks ();
	}
	else if (direction.equals ("Down"))
	{
	    tempMapNames [0] = c.currentMapName;
	    tempMapNames [1] = c.currentMapName.charAt (0) + Integer.toString (Character.getNumericValue (c.currentMapName.charAt (1)) - 1);
	    for (int x = 0 ; x < 2 ; x++)
	    {
		if (tempMapNames [x].charAt (0) == 'f')
		    areas [x] = 0;
		else if (tempMapNames [x].charAt (0) == 'c')
		    areas [x] = 1;
		else
		    areas [x] = 2;

		if (tempMapNames [x].charAt (1) == '1')
		    rooms [x] = 0;
		else if (tempMapNames [x].charAt (1) == '2')
		    rooms [x] = 1;
		else
		    rooms [x] = 2;
	    }
	    for (int y = 1 ; y < 509 ; y++)
	    {
		c.drawImage (g.map [areas [1]] [rooms [1]], -1, 508 - y, null);
		c.drawImage (g.map [areas [0]] [rooms [0]], -1, -1 - y, null);
		c.currentMap = c.getClip (0, 0, c.maxx () + 2, c.maxy () + 2);
		c.fillRect (-10, -10, 970, 10.f, Color.WHITE);
		c.update ();
		try {Thread.sleep (0);} catch (Exception e){}
	    }
	    c.playerPosition [0] [0] = 420;
	    c.playerPosition [0] [1] = 0;
	    c.playerPosition [1] [0] = 420;
	    c.playerPosition [1] [1] = 0;
	    c.currentMapName = tempMapNames [1];
	    c.drawImage (c.currentMap = g.map [areas [1]] [rooms [1]], -1, -1, null);
	    g.setCurrentRoom ();
	    g.setBlocks ();
	}
    }
}


