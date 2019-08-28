/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        This program edits hsa Console to creates it's own console type.
		The Draw class is basically an extention of hsa.Console and acts as a Console would with improvements.
		The improvements include a better way to update the Console using frames.
		Storage of draw methods that draw every single thing in the game.
		Note: The code is made so that other levels could be implemented with time.
*/

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;


public class Draw extends Console
{
    //Instance Variables
    /*
    Name                        Type                    Purpose
    colorArray                  Color[]                 Stores all the non Color class colors we use

    saveFiles                   String[]                Stores all the save files of the players and highscores

    currentPlayer               int                     Stores the current player number; should always have a value of 1 2 or 3
    currentPlayerName           String                  Stores the player name, only used when entering high score
    playerPosition              int[][]                 Stores the previous position of the player and the current position of the player; used for erasing and drawing the player

    gameTime                    float                   Stores the time the user is taking to complete the game                    
    gameFinished                boolean                 Indicated when the game is finished, used to escape userInput ("Play")
    menuChoice                  int                     Stores the current menu choice, used for input with the cursor

    currentMap                  BufferedImage           Stores an BufferedImage of the current map with no block or player
    currentMapName              String                  Stores the name of the current map

    onscreenWindow              Console                 Stores the Console variable for our onscreen window; needs to be instance for use in update
    */

    /*
	colorArray colors
	0 - Light Brown
	1 - Dark Brown
	2 - Redish Brown
	3 - Green
	4 - Orange
	5 - Light Blue
	6 - Orange
	7 - Sandstone
    */
    public Color[] colorArray = {
	new Color (153, 102, 51),
	new Color (77, 51, 25),
	new Color (102, 51, 0),
	new Color (51, 102, 0),
	new Color (255, 153, 0),
	new Color (153, 217, 234),
	new Color (255, 145, 70),
	new Color (212, 202, 167)
	};

    /*
    Save File Structure
    3 saves stores in one file

    First Threee Strings are the player data
    Player data is stored in following order
    currentPlayer
    currentMapName
    gameTime

    The next 20 Strings are the high scores
	name + \n + score for the top 10 scores
	null scores are stored with ---
    */
    public String[] saveFiles = new String [23];

    public int currentPlayer;
    public String currentPlayerName;
    public int[] [] playerPosition = new int [2] [2];

    public float gameTime;
    public boolean gameFinished;
    public int menuChoice;

    public BufferedImage currentMap;
    public String currentMapName;

    Console onscreenWindow = null;

    //Our constructor of Draw
    //Initalizes our two Consoles, nothing more
    public Draw ()
    {
	//Constructs the offscreenConsole
	super (-100000, -100000);
	//Constructs the onscreenConsole
	onscreenWindow = new Console (100, 100, "Adventure of Blocky");
    }


    //Overloaded getChar command, works the same as getChar
    //Used to switch focus when getchar is needed
    //Used to make cursor invisible, just for good measure
    /*
    Name            Type            Description
    userInput       String          Stores userInput and returns it
    */
    public char getChar (boolean tf)
    {
	onscreenWindow.setCursorVisible (false);
	onscreenWindow.screenCanvas.requestFocusInWindow ();
	char userInput = ' ';
	userInput = onscreenWindow.getChar ();
	super.screenCanvas.requestFocusInWindow ();
	return userInput;
    }


    //Rewritten Draw commands, adds an overloaded color option to draw methods; makes sure that the draw commands draw on the offScreenConsole
    //Adds 10 to both coordinates to create a white border around the screen; this is to make up for the part of hsa.Console that cannot be drawn on
    //The oval commands are adjusted to draw the circle from the center
    //We use float values to distinguish from hsa.Console draw commands
    //The font we use throughout the program is Baskerville Old Face
    //No further explaination should be needed

    public void drawLine (float x1, float y1, float x2, float y2)
    {
	super.drawLine ((int) x1 + 10, (int) y1 + 10, (int) x2 + 10, (int) y2 + 10);
    }


    public void drawLine (float x1, float y1, float x2, float y2, Color color)
    {
	super.setColor (color);
	super.drawLine ((int) x1 + 10, (int) y1 + 10, (int) x2 + 10, (int) y2 + 10);
    }


    public void drawRect (float x1, float y1, float width, float length)
    {
	super.drawRect ((int) x1 + 10, (int) y1 + 10, (int) width, (int) length);
    }


    public void drawRect (float x1, float y1, float width, float length, Color color)
    {
	super.setColor (color);
	super.drawRect ((int) x1 + 10, (int) y1 + 10, (int) width, (int) length);
    }


    public void fillRect (float x1, float y1, float width, float length)
    {
	super.fillRect ((int) x1 + 10, (int) y1 + 10, (int) width, (int) length);
    }


    public void fillRect (float x1, float y1, float width, float length, Color color)
    {
	super.setColor (color);
	super.fillRect ((int) x1 + 10, (int) y1 + 10, (int) width, (int) length);
    }


    //drawOval methods that draw the circle from the center
    public void drawOval (float x, float y, float xd, float yd)
    {
	super.drawOval ((int) (x) - ((int) (xd)) / 2 + 10, (int) (y) - (int) (yd) / 2 + 10, (int) xd, (int) yd);
    }


    public void drawOval (float x, float y, float xd, float yd, Color color)
    {
	super.setColor (color);
	super.drawOval ((int) x - (int) (xd) / 2 + 10, (int) y - (int) (yd) / 2 + 10, (int) xd, (int) yd);
    }


    public void fillOval (float x, float y, float xd, float yd)
    {
	super.fillOval ((int) x - (int) (xd) / 2 + 10, (int) y - (int) (yd) / 2 + 10, (int) xd, (int) yd);
    }


    public void fillOval (float x, float y, float xd, float yd, Color color)
    {
	super.setColor (color);
	super.fillOval ((int) x - (int) (xd) / 2 + 10, (int) y - (int) (yd) / 2 + 10, (int) xd, (int) yd);
    }


    //drawArc methods that draw the arc from the center
    public void drawArc (float x, float y, float xd, float yd, float startAngle, float arcAngle)
    {
	super.drawArc ((int) x - (int) (xd) / 2 + 10, (int) y - (int) (yd) / 2 + 10, (int) xd, (int) yd, (int) startAngle, (int) arcAngle);
    }


    public void drawText (String string, float x, float y, Color color, float fontSize)
    {
	super.setColor (color);
	super.setFont (new Font ("Baskerville Old Face", 0, (int) fontSize));
	super.drawString (string, (int) x + 10, (int) y + 10);
    }


    public void drawPolygon (int[] xPoints, int[] yPoints, float nPoints)
    {
	int[] temp1 = new int [(int) nPoints], temp2 = new int [(int) nPoints];
	for (int x = 0 ; x < xPoints.length ; x++)
	    temp1 [x] = xPoints [x] + 10;
	for (int x = 0 ; x < yPoints.length ; x++)
	    temp2 [x] = yPoints [x] + 10;
	super.drawPolygon (temp1, temp2, (int) nPoints);
    }


    public void drawPolygon (int[] xPoints, int[] yPoints, float nPoints, Color color)
    {
	int[] temp1 = new int [(int) nPoints], temp2 = new int [(int) nPoints];
	super.setColor (color);
	for (int x = 0 ; x < xPoints.length ; x++)
	    temp1 [x] = xPoints [x] + 10;
	for (int x = 0 ; x < yPoints.length ; x++)
	    temp2 [x] = yPoints [x] + 10;
	super.drawPolygon (temp1, temp2, (int) nPoints);
    }


    public void fillPolygon (int[] xPoints, int[] yPoints, float nPoints)
    {
	int[] temp1 = new int [(int) nPoints], temp2 = new int [(int) nPoints];
	for (int x = 0 ; x < xPoints.length ; x++)
	    temp1 [x] = xPoints [x] + 10;
	for (int x = 0 ; x < yPoints.length ; x++)
	    temp2 [x] = yPoints [x] + 10;
	super.fillPolygon (temp1, temp2, (int) nPoints);
    }


    public void fillPolygon (int[] xPoints, int[] yPoints, float nPoints, Color color)
    {
	int[] temp1 = new int [(int) nPoints], temp2 = new int [(int) nPoints];
	super.setColor (color);
	for (int x = 0 ; x < xPoints.length ; x++)
	    temp1 [x] = xPoints [x] + 10;
	for (int x = 0 ; x < yPoints.length ; x++)
	    temp2 [x] = yPoints [x] + 10;
	super.fillPolygon (temp1, temp2, (int) nPoints);
    }


    //Below are the custom draw commands I use; all my custom art is in here

    //drawPlayer of course draws our player sprite
    //Draws our player from values of playerPosition
    //Our erase here is the exact same size as our player and erases perfectly.
    /*
    Name        Type                    Description
    erase       BufferedImage           Stores the erase for the player, gets a subimage from the currentMap to erase instead of making custom erases for each background and situation
    */
    public void drawPlayer ()
    {
	BufferedImage erase = currentMap.getSubimage (playerPosition [1] [0] + 10, playerPosition [1] [1] + 10, 24, 24);
	drawImage (erase, playerPosition [1] [0] + 10, playerPosition [1] [1] + 10, null);
	//Body
	fillRect (playerPosition [0] [0], playerPosition [0] [1], 24, 24, Color.BLUE);
	//Bandana
	fillRect (playerPosition [0] [0], playerPosition [0] [1] + 2, 24, 4, colorArray [6]);
	//Face
	fillRect (playerPosition [0] [0] + 2, playerPosition [0] [1] + 6, 20, 8, colorArray [5]);
	fillRect (playerPosition [0] [0] + 4, playerPosition [0] [1] + 14, 16, 2, colorArray [5]);
	//Eyes
	fillRect (playerPosition [0] [0] + 4, playerPosition [0] [1] + 6, 4, 4, Color.BLUE);
	fillRect (playerPosition [0] [0] + 16, playerPosition [0] [1] + 6, 4, 4, Color.BLUE);
	fillRect (playerPosition [0] [0] + 6, playerPosition [0] [1] + 6, 2, 2, Color.WHITE);
	fillRect (playerPosition [0] [0] + 16, playerPosition [0] [1] + 6, 2, 2, Color.WHITE);
	//Nose
	fillRect (playerPosition [0] [0] + 10, playerPosition [0] [1] + 8, 4, 2, Color.BLUE);
	fillRect (playerPosition [0] [0] + 8, playerPosition [0] [1] + 10, 8, 2, Color.BLUE);
	//Belly
	fillRect (playerPosition [0] [0] + 8, playerPosition [0] [1] + 18, 8, 2, colorArray [5]);
	fillRect (playerPosition [0] [0] + 6, playerPosition [0] [1] + 20, 12, 4, colorArray [5]);
    }


    //I wanted to distinguish the drawBlock from drawTile for a few unique uses
    /*
    Name                Type    Description
    x                   int     Stores the x co-ordinate of the block
    y                   int     Stores the y co-ordinate of the block
    */
    public void drawBlock (int x, int y)
    {
	fillRect (x, y, 30, 30, colorArray [7]);
	drawRect (x, y, 30, 30, Color.BLACK);
	drawRect (x + 7, y + 7, 16, 16, Color.BLACK);
	drawLine (x, y, x + 7, y + 7.f);
	drawLine (x + 30, y, x + 23, y + 7.f);
	drawLine (x, y + 30, x + 7, y + 23.f);
	drawLine (x + 30, y + 30, x + 23, y + 23.f);
    }


    //Our universal drawTile command
    //The x and y are unique here as they draw from the grid x and y co-ordinate instead of the traditional x and y co-ordinates
    //Note that each tile is 30 by 30
    //The if statement determies what tile to draw, either "tree" or "block"
    /*
    Name                Type    Description
    x                   int     Stores the grid x co-ordinate of the tile
    y                   int     Stores the grid y co-ordinate of the tile
    tileName            String  Stores the anme of tile used in dictating what tile to draw
    */
    public void drawTile (int x, int y, String tileName)
    {
	if (tileName == "tree")
	{
	    fillRect (13 + x * 30, 22 + y * 30, 5, 8, colorArray [2]);
	    fillOval (10 + x * 30, 17 + y * 30, 15, 15, colorArray [3]);
	    fillOval (20 + x * 30, 17 + y * 30, 15, 15, colorArray [3]);
	    fillOval (15 + x * 30, 8 + y * 30, 15, 15, colorArray [3]);
	}
	else if (tileName == "block")
	{
	    drawBlock (x * 30, y * 30);
	}
    }


    //Draws the ground, basically sets the background
    //The if statement is there in case of other levels
    public void drawGround (String name)
    {
	if (name.equals ("forest"))
	    fillRect (0, 0, 871, 511, colorArray [0]);
    }


    //The key part in making our cursor work, returnCursorPosition
    //Returns an array of x and y co-ordinate for the cursor to be drawn at
    //A lot of if statements but can be explained very generically
    //The first if statement determines what set of co-ordinates it needs to take from
    //The cursor is built from menuChoice, which is an int, so different menus have different cusor positions for the same menuChoice value
    //Now InGameMenu and Options have the same amount of if structures so I'll go over them together
    //The first if structure adjusts the value of menuChoice, this allows for smoother menus as the player can press down at the very bottom to get straight to the top, or vice versa
    //The second if structure assigns the x and y co ordinates, which is returned
    //Frankly, the xy variable does not need to exist as I could return a set, but is there for convience; adds a lot of extra lines but easier on the eyes
    //The MainMenu mode is different as it has to check for existing saveFile so it can give the option to load them. Otherwise the rest of the code is the same
    /*
    Name    Type    Description
    xy      int[]   Stores the x and y co ordinates to return
    */
    public int[] returnCursorPosition (String mode)
    {
	int[] xy = new int [2];
	if (mode.equals ("MainMenu"))
	{
	    if (Player.doesFileExist ())
	    {
		if (menuChoice == -1)
		    menuChoice = 5;
		else if (menuChoice == 6)
		    menuChoice = 0;

		if (menuChoice == 0)
		{
		    xy [0] = 330;
		    xy [1] = 170;
		}
		else if (menuChoice == 1)
		{
		    xy [0] = 330;
		    xy [1] = 200;
		}
		else if (menuChoice == 2)
		{
		    xy [0] = 330;
		    xy [1] = 230;
		}
		else if (menuChoice == 3)
		{
		    xy [0] = 330;
		    xy [1] = 260;
		}
		else if (menuChoice == 4)
		{
		    xy [0] = 330;
		    xy [1] = 290;
		}
		else if (menuChoice == 5)
		{
		    xy [0] = 330;
		    xy [1] = 320;
		}
	    }
	    else
	    {
		if (menuChoice == -1)
		    menuChoice = 4;
		else if (menuChoice == 5)
		    menuChoice = 0;

		if (menuChoice == 0)
		{
		    xy [0] = 330;
		    xy [1] = 200;
		}
		else if (menuChoice == 1)
		{
		    xy [0] = 330;
		    xy [1] = 230;
		}
		else if (menuChoice == 2)
		{
		    xy [0] = 330;
		    xy [1] = 260;
		}
		else if (menuChoice == 3)
		{
		    xy [0] = 330;
		    xy [1] = 290;
		}
		else if (menuChoice == 4)
		{
		    xy [0] = 330;
		    xy [1] = 320;
		}
	    }
	}
	else if (mode.equals ("InGameMenu"))
	{
	    if (menuChoice == -1)
		menuChoice = 3;
	    else if (menuChoice == 4)
		menuChoice = 0;

	    if (menuChoice == 0)
	    {
		xy [0] = 330;
		xy [1] = 200;
	    }
	    else if (menuChoice == 1)
	    {
		xy [0] = 340;
		xy [1] = 230;
	    }
	    else if (menuChoice == 2)
	    {
		xy [0] = 350;
		xy [1] = 260;
	    }
	    else if (menuChoice == 3)
	    {
		xy [0] = 320;
		xy [1] = 290;
	    }
	}
	else if (mode.equals ("Options"))
	{
	    if (menuChoice == -1)
		menuChoice = 1;
	    else if (menuChoice == 2)
		menuChoice = 0;

	    if (menuChoice == 0)
	    {
		xy [0] = 150;
		xy [1] = 254;
	    }
	    else if (menuChoice == 1)
	    {
		xy [0] = 420;
		xy [1] = 254;
	    }
	}
	else if (mode.equals("Load/SaveGameMenu"))
	{
	    if (menuChoice == -1)
		menuChoice = 2;
	    else if (menuChoice == 3)
		menuChoice = 0;

	    if (menuChoice == 0)
	    {
		xy [0] = 70;
		xy [1] = 100;
	    }
	    else if (menuChoice == 1)
	    {
		xy [0] = 70;
		xy [1] = 200;
	    }
	    else if (menuChoice == 2)
	    {
		xy [0] = 70;
		xy [1] = 300;
	    }
	}
	return xy;
    }


    //Draws our cursor
    //Erases are not done inside here as returnCursorPostion does not reutrn old coordinates; can be done but adds more code
    /*
    Name                Type    Description
    xy                  int[]   Stores the values returned by returnCusorPosition
    triangleX           int[]   Stores the x values for the triangle; needed to drawPolygon
    triangleY           int[]   Stores the y values for the triangle; needed to drawPolygon
    */
    public void drawCursor (String mode)
    {
	//Co-odinates of the top right corner of the cursor
	int[] xy = returnCursorPosition (mode);
	//Initalizes the triangle xy values
	int[] triangleX = {xy [0] + 3, xy [0], xy [0] + 15, xy [0] };
	int[] triangleY = {xy [1] - 7, xy [1], xy [1] - 7, xy [1] - 13};
	//Draw the inside
	fillPolygon (triangleX, triangleY, 4.f, colorArray [4]);
	//Draw the border
	drawPolygon (triangleX, triangleY, 4.f, Color.BLACK);
    }


    //getClip is used to clip a part of the screen
    //Basically creates a smaller image in the form of img and shrinks it furthur using getSubImage
    //getSubImage could be used everytime instead of this but this is overall easier
    //Don't call with offscreen points
    /*
    Name        Type                    Description
    img         BufferedImage           Stores a part of the onscreenCanvas
    */
    public BufferedImage getClip (int x1, int y1, int x2, int y2)
    {
	BufferedImage img = (BufferedImage) super.screenCanvas.createImage (x2, y2);
	super.screenCanvas.paint (img.getGraphics ());
	return img.getSubimage (x1, y1, x2 - x1, y2 - y1);
    }


    //Actually the main part of updated hsa.Console
    //Makes hsa.Console almost the same as Turing's offscreenmode;; smoother and absolute no flashing in java
    //Access the canvas of the offscreenConsole and paints it onto the onscreenConsole
    //The two console will almost always have the same image on them
    //Prevents flashing as the onscreenCanvas is only updated when update() is called
    //Normally, hsa.Console's buffer is really weird and you can draw faster than it updates causing flashes
    public void update ()
    {
	super.screenCanvas.paint (onscreenWindow.screenCanvas.getGraphics ());
    }
}
