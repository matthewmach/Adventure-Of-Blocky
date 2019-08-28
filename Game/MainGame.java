/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        Combines all the other files to run.
		Contains splashScreen and high scores.
		Contains most menus.
*/
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;

public class MainGame
{
    //Instance variables
    /*
    Name            Type            Descriptions
    c               Draw            Constructs Draw, grants access to the commands and variables
    g               GameMap         Constructs GameMap, grants access to the commands and variables
    e               Enviroment      Constructs Enviroment, grants access to the commands and variables
    p               Player          Constructs Player, grants access to the commands and variables
    time            long            Stores the time that you store this
    gameIsPlaying   boolean         States if the game is playing or not
    */
    public static Draw c = new Draw ();
    public static GameMap g = new GameMap ();
    public static Enviroment e = new Enviroment ();
    public static Player p = new Player ();
    public static long time = (long) 0;
    public static boolean gameIsPlaying = false;

    //Displays a note
    public void note ()
    {
	for (float x = (float) 1.0 ; x >= (float) 0.0 ; x -= (float) 0.02)
	{
	    c.drawImage (p.menuScreens [0], 0, 0, null);
	    c.fillRect (0, 0, c.maxx () + 1, c.maxy () + 1, new Color (0, 0, 0, x));
	    c.update ();
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.drawImage (p.menuScreens [0], 0, 0, null);
	c.update ();
	char userInput = c.getChar (true);
	for (float x = (float) 0.02 ; x < (float) 1.01 ; x += (float) 0.02)
	{
	    c.fillRect (0, 0, c.maxx () + 1, c.maxy () + 1, new Color (0, 0, 0, x));
	    c.update ();
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    //Our splash screen animation
    //It is run as a thread
    //The for loops makes animation by using delays and for loops
    public void splashScreen ()
    {
	c.currentMap = c.getClip (0, 0, c.maxx (), c.maxy ());
	new Thread ()
	{
	    public void run ()
	    {
		//Draws the block beforehand
		c.drawBlock (813, 100);
		c.drawBlock (75, 131);
		c.drawBlock (105, 429);
		c.drawBlock (754, 398);

		//Sets player positions
		c.playerPosition [0] [0] = 845;
		c.playerPosition [0] [1] = 100;
		c.playerPosition [1] [0] = 845;
		c.playerPosition [1] [1] = 100;
		for (int x = 0 ; x < 770 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.playerPosition [0] [0] -= 1;
		    c.drawRect (813 - x + 1, 100, 30, 30, Color.WHITE);
		    c.drawBlock (813 - x, 100);
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.drawText ("Adventure", 370, 200, Color.BLACK, 34);
		for (int x = 0 ; x < 6 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.playerPosition [0] [1] += 1;
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		for (int x = 0 ; x < 330 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.drawRect (75, 131 + x - 1, 30, 30, Color.WHITE);
		    c.drawBlock (75, 131 + x);
		    c.playerPosition [0] [1] += 1;
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.drawText ("Of", 360, 250, Color.BLACK, 38);
		for (int x = 0 ; x < 5 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.playerPosition [0] [0] += 1;
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		for (int x = 0 ; x < 680 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.drawRect (105 + x - 1, 429, 30, 30, Color.WHITE);
		    c.drawBlock (105 + x, 429);
		    c.playerPosition [0] [0] += 1;
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		c.drawText ("Blocky", 420, 250, Color.BLACK, 38);
		c.update ();
		for (int x = 0 ; x < 7 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.playerPosition [0] [1] -= 1;
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
		for (int x = 0 ; x < 380 ; x++)
		{
		    c.playerPosition [1] [0] = c.playerPosition [0] [0];
		    c.playerPosition [1] [1] = c.playerPosition [0] [1];
		    c.playerPosition [0] [1] -= 1;
		    c.drawRect (754, 398 - x + 1, 30, 30, Color.WHITE);
		    c.drawBlock (754, 398 - x);
		    c.drawPlayer ();
		    c.update ();
		    try
		    {
			Thread.sleep (1);
		    }
		    catch (Exception e)
		    {
		    }
		}
	    }
	}
	.start ();
    }


    //Draws the mainMenu and calls userInput
    public void mainMenu ()
    {
	c.gameFinished = false;
	c.clear ();
	p.readSaveFiles();
	if (p.doesFileExist () && !(c.saveFiles [0] == "---" && c.saveFiles [1] == "---" && c.saveFiles [2] == "---"))
	    c.drawImage (p.menuScreens [1], 0, 0, null);
	else
	    c.drawImage (p.menuScreens [8], 0, 0, null);
	c.update ();
	p.userInput ("MainMenuInput");
    }


    //displays high scores when user finishes the game
    //We first get useriNput for the name of the player
    //We then transfer the values for save files into a temp variable highScores
    //We then bubble sort highScores
    //We check if the lowest score is lower than the current score and replace the score with the current one
    //We then bubble sort again
    //Then the program displays the high scores and updates saveFiles
    /*
    Name        Type        Description
    highScores  int[][]     Stores the high scores from least to greatest, stores the order of the scores
    temp        int         Stores a temporary value for our bubble sort
    */
    public void displayHighScores ()
    {
	if (!p.doesFileExist ())
	{
	    c.currentPlayer = 0;
	    for (int x = 0 ; x < 23 ; x++)
		c.saveFiles [x] = "---";
	    p.saveFile ();
	}
	p.readSaveFiles ();
	int[] [] highScores = new int [10] [2];
	for (int x = 4 ; x < 23 ; x += 2)
	{
	    if (!c.saveFiles [x].equals ("---"))
		highScores [x / 2 - 2] [0] = Integer.parseInt (c.saveFiles [x]);
	    else
		highScores [x / 2 - 2] [0] = 0;
	    highScores [x / 2 - 2] [1] = x - 1;
	}
	int temp;
	for (int x = 0 ; x < 10 ; x++)
	    for (int y = 1 ; y < 10 - x ; y++)
		if (highScores [y - 1] [0] > highScores [y] [0])
		{
		    for (int e = 0 ; e < 2 ; e++)
		    {
			temp = highScores [y - 1] [e];
			highScores [y - 1] [e] = highScores [y] [e];
			highScores [y] [e] = temp;
		    }
		}
	if (highScores [0] [0] < c.gameTime)
	{
	    highScores [0] [0] = (int) c.gameTime;
	    c.saveFiles [highScores [0] [1]] = c.currentPlayerName;
	}
	for (int y = 1 ; y < 10 ; y++)
	    if (highScores [y - 1] [0] > highScores [y] [0])
	    {
		for (int e = 0 ; e < 2 ; e++)
		{
		    temp = highScores [y - 1] [e];
		    highScores [y - 1] [e] = highScores [y] [e];
		    highScores [y] [e] = temp;
		}
	    }
	c.clear ();
	//Printing the high scores
	c.drawText ("High Scores", 10, 10, Color.BLACK, 20);
	c.drawText ("Name", 10, 40, Color.BLACK, 15);
	c.drawText ("Score", 100, 40, Color.BLACK, 15);
	for (int x = 0 ; x < 10 ; x++)
	{
	    c.drawText (c.saveFiles [highScores [x] [1]], 10, (20 - x) * 20-160, Color.BLACK, 15);
	    c.drawText (Integer.toString ((int) highScores [x] [0] / 60000) + ":" + ((highScores [x] [0] % 60000) / 1000 < 10 ? Integer.toString ((highScores [x] [0] % 60000) / 1000) + "0":
	    Integer.toString ((highScores [x] [0] % 60000) / 1000)), 100, (20 - x) * 20-160, Color.BLACK, 15);
	}
	c.update ();
	String[] tempSaveFile = new String [20];
	for (int x = 0 ; x < 10 ; x++)
	{
	    tempSaveFile [x * 2] = c.saveFiles [highScores [9 - x] [1]];
	    tempSaveFile [x * 2 + 1] = Integer.toString (highScores [9 - x] [0]);
	}
	for (int x = 3 ; x < 23 ; x++)
	{
	    c.saveFiles [x] = tempSaveFile [x - 3];
	}
	if (c.currentPlayer != 0)
	    c.saveFiles [c.currentPlayer - 1] = "---";
	p.saveFile ();

	c.drawText ("Press any key to continue or 's' to clear the high scores...", 10, 300, Color.BLACK, 15);
	c.update();
	char userInput = c.getChar (true);
	if (userInput == 's')
	    for (int x=3; x < 23; x++)
		c.saveFiles[x] = "---";
	p.saveFile();
    }


    //plays the game based off the information given
    public void playGame ()
    {
	gameIsPlaying = true;
	time = System.currentTimeMillis ();
	p.userInput ("play");
	time = System.currentTimeMillis () - time;
	c.gameTime += time;
	gameIsPlaying = false;
	if (c.gameFinished)
	{
	    c.clear ();
	    c.drawImage (p.menuScreens [7], 0, 0, null);
	    c.update ();
	    p.userInput ("HighScores");
	    displayHighScores ();
	}
    }


    //display the load game menu
    //allows user to start from their save
    public void loadGame ()
    {
	//Clears the screen
	c.clear ();
	//Displays the image
	c.drawImage (p.menuScreens [6], 0, 0, null);
	//Updates
	c.update ();
	//Starts userInput
	p.userInput ("New/LoadGameMenu");
	if (c.menuChoice == 0)
	{
	    c.currentPlayer = 1;
	    c.currentMapName = c.saveFiles [0].substring (1, 2);
	    c.gameTime = (float) Integer.parseInt (c.saveFiles [0].substring (3));
	}
	else if (c.menuChoice == 1)
	{
	    c.currentPlayer = 2;
	    c.currentMapName = c.saveFiles [1].substring (1, 2);
	    c.gameTime = (float) Integer.parseInt (c.saveFiles [1].substring (3));
	}
	else if (c.menuChoice == 2)
	{
	    c.currentPlayer = 3;
	    c.currentMapName = c.saveFiles [2].substring (1, 2);
	    c.gameTime = (float) Integer.parseInt (c.saveFiles [2].substring (3));
	}
	if (c.currentMapName.equals ("f1"))
	{
	    c.playerPosition [0] [0] = 420;
	    c.playerPosition [0] [1] = 390;
	    c.playerPosition [1] [0] = 420;
	    c.playerPosition [1] [1] = 390;
	    c.currentMap = g.map [0] [0];
	    g.setCurrentRoom ();
	    c.drawImage (g.map [0] [0], -1, -1, null);
	    g.setBlocks ();
	}
	else if (c.currentMapName.equals ("f2"))
	{
	    c.playerPosition [0] [0] = 420;
	    c.playerPosition [0] [1] = 486;
	    c.playerPosition [1] [0] = 420;
	    c.playerPosition [1] [1] = 486;
	    c.currentMap = g.map [0] [1];
	    g.setCurrentRoom ();
	    c.drawImage (g.map [0] [1], -1, -1, null);
	    g.setBlocks ();
	}
	else if (c.currentMapName.equals ("f3"))
	{
	    c.playerPosition [0] [0] = 420;
	    c.playerPosition [0] [1] = 486;
	    c.playerPosition [1] [0] = 420;
	    c.playerPosition [1] [1] = 486;
	    c.currentMap = g.map [0] [2];
	    g.setCurrentRoom ();
	    c.drawImage (g.map [0] [2], -1, -1, null);
	    g.setBlocks ();
	}
	playGame ();
    }


    //creates a new save
    //Sets varibles to starting position and runs the game
    public void newGame ()
    {
	if (!p.doesFileExist ())
	{
	    c.currentPlayer = 0;
	    for (int x = 0 ; x < 23 ; x++)
		c.saveFiles [x] = "---";
	    p.saveFile ();
	}
	//Clears the screen
	c.clear ();
	//Displays the image
	c.drawImage (p.menuScreens [5], 0, 0, null);
	//Updates
	c.update ();

	//Starts userInput
	p.userInput ("New/LoadGameMenu");
	if (c.menuChoice == 0)
	    c.currentPlayer = 1;
	else if (c.menuChoice == 1)
	    c.currentPlayer = 2;
	else if (c.menuChoice == 2)
	    c.currentPlayer = 3;
	//Sets varibles to starting position
	c.playerPosition [0] [0] = 420;
	c.playerPosition [0] [1] = 390;
	c.playerPosition [1] [0] = 420;
	c.playerPosition [1] [1] = 390;
	c.currentMapName = "f1";
	c.gameTime = 0;

	p.saveFile ();

	c.currentMap = g.map [0] [0];
	g.setCurrentRoom ();
	c.drawImage (g.map [0] [0], -1, -1, null);
	g.setBlocks ();

	playGame ();
    }


    //displays instructions and wait for user input
    public void instructions ()
    {
	//Clears the screen
	c.clear ();
	//Displays the image
	c.drawImage (p.menuScreens [2], 0, 0, null);
	//Updates
	c.update ();
	//Waits for user to press any key
	char userInput = c.getChar (true);
    }


    //displays options and tell user to configure controls
    public void options ()
    {
	//Runs the options used in both menus
	p.configureControls ();
    }


    //displays goodbye message
    //waits for user input
    //then exits the game
    public void goodbye ()
    {
	//Clears Screen
	c.clear ();
	//Draws Screen
	c.drawImage (p.menuScreens [4], 0, 0, null);
	//Updates
	c.update ();
	//Waits for user to press any key
	char userInput = c.getChar (true);
	//Exits system
	System.exit (0);
    }


    //main program
    //The try statement stops the thread until the splash screen animation ends
    //The while loop loops the program until the user chooses the exit
    //The if statements use the menu input to determine what to do
    public static void main (String[] args)
    {
	MainGame a = new MainGame ();
	a.splashScreen ();
	try
	{
	    Thread.sleep (5000);
	}
	catch (Exception e)
	{
	}
	if (!p.doesFileExist ())
	    a.note ();
	else
	    p.readSaveFiles();
	while (true)
	{
	    a.mainMenu ();
	    if (p.doesFileExist () && !(c.saveFiles [0] == "---" && c.saveFiles [1] == "---" && c.saveFiles [2] == "---"))
	    {
		if (c.menuChoice == 0)
		    a.loadGame ();
		else if (c.menuChoice == 1)
		    a.newGame ();
		else if (c.menuChoice == 2)
		    a.displayHighScores();
		else if (c.menuChoice == 3)
		    a.instructions ();
		else if (c.menuChoice == 4)
		    a.options ();
		else if (c.menuChoice == 5)
		    break;
	    }
	    else
	    {
		if (c.menuChoice == 0)
		    a.newGame ();
		else if (c.menuChoice == 1)
		    a.displayHighScores();
		else if (c.menuChoice == 2)
		    a.instructions ();
		else if (c.menuChoice == 3)
		    a.options ();
		else if (c.menuChoice == 4)
		    break;
	    }
	}
	a.goodbye ();
    }
}


