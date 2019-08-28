/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        This program handles all userInput.
		It also initializes the menuScreens.
		All the FileIO is done in here.
*/

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;


public class Player
{
    //Instance Variables
    /*
    Name            Type                Description
    c               Draw                Constructor for Draw, Grants access to Draw and hsa.Console conmmands and variables
    e               Envirioment         Constructor for Enviroment; grants access to Enviroment's commands and variables
    controls        char[]              Stores all the controls
    menuScreens     BufferedImage[]     Stores all the menu screens, except for InGameMenu which isn't definite
    tempScreen      BufferedImage       Stores a temporary screen when needed
    */
    Draw c = MainGame.c;
    Enviroment e = MainGame.e;
    GameMap g = MainGame.g;
    char[] controls = {'w', 'a', 's', 'd', 'i'};
    BufferedImage[] menuScreens = {null, null, null, null, null, null, null, null, null};
    BufferedImage tempScreen;

    //Player constructor
    //Initializes all the menu screens
    public Player ()
    {
	c.clear ();

	//Screen 1
	c.drawText ("Note:", 415, 150, Color.BLACK, 15);
	c.drawText ("This game will take about 10 minutes to fully complete.", 277, 180, Color.BLACK, 15);
	c.drawText ("The default controls are W and S to move up and down the menu choices respectively.", 170, 210, Color.BLACK, 15);
	c.drawText ("You can change the controls in the menu anytime by going to options.", 230, 240, Color.BLACK, 15);
	c.drawText ("Press any key to continue...", 350, 270, Color.BLACK, 15);
	menuScreens [0] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 2
	c.drawText ("Adventure", 370, 30, Color.BLACK, 34);
	c.drawText ("Of Blocky", 360, 80, Color.BLACK, 38);
	c.drawText ("Load Game", 395, 170, Color.BLACK, 18);
	c.drawText ("New Game", 395, 200, Color.BLACK, 18);
	c.drawText ("High Scores", 395, 230, Color.BLACK, 18);
	c.drawText ("Instructions", 395, 260, Color.BLACK, 18);
	c.drawText ("Options", 405, 290, Color.BLACK, 18);
	c.drawText ("Exit", 420, 320, Color.BLACK, 18);
	menuScreens [1] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 3
	c.drawText ("Instructions:", 390, 120, Color.BLACK, 20);
	c.drawText ("Welcome to Adventure of Blocky!", 325, 160, Color.BLACK, 15);
	c.drawText ("Use " + controls [0] + controls [1] + controls [2] + controls [3] + " to move.", 370, 190, Color.BLACK, 15);
	c.drawText ("Press " + controls [4] + " to access the ingame menu.", 327, 220, Color.BLACK, 15);
	c.drawText ("Try to solve every problem and get to the end!", 285, 250, Color.BLACK, 15);
	c.drawText ("Good luck adventurer, you may need it!", 310, 280, Color.BLACK, 15);
	c.drawText ("Press any key to continue...", 350, 310, Color.BLACK, 15);
	menuScreens [2] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 4
	c.drawText ("Options:", 390, 120, Color.BLACK, 20);
	c.drawText ("Current input keys: " + controls [0] + controls [1] + controls [2] + controls [3] + " to move. " + controls [4] + " to access the ingame menu.", 200, 160, Color.BLACK, 15);
	c.drawText ("Use " + controls [0] + controls [1] + controls [2] + controls [3] + " to move.", 370, 190, Color.BLACK, 15);
	c.drawText ("Pick an option:", 380, 220, Color.BLACK, 15);
	c.drawText ("wasd to move and i to open menu.", 190, 250, Color.BLACK, 15);
	c.drawText ("ijkl to move and e to open menu.", 450, 250, Color.BLACK, 15);
	c.drawText ("Use " + controls [1] + " and " + controls [3] + " to pick an option...", 330, 310, Color.BLACK, 15);
	menuScreens [3] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 5
	c.drawText ("Thanks for Playing!", 370, 120, Color.BLACK, 20);
	c.drawText ("This game was inspired by Legend of Zelda", 310, 160, Color.BLACK, 15);
	c.drawText ("Created by Matthew Mach", 370, 190, Color.BLACK, 15);
	c.drawText ("Press any key to exit...", 380, 220, Color.BLACK, 15);
	menuScreens [4] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 6
	c.drawText ("New Game", 40, 40, Color.BLACK, 20);
	c.drawText ("Choose what save file to overwrite.", 150, 40, Color.BLACK, 15);
	c.drawText ("1", 40, 100, Color.BLACK, 20);
	c.drawRect (38, 80, 700, 50.f);
	c.drawText ("2", 40, 200, Color.BLACK, 20);
	c.drawRect (38, 180, 700, 50.f);
	c.drawText ("3", 40, 300, Color.BLACK, 20);
	c.drawRect (38, 280, 700, 50.f);

	menuScreens [5] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 7
	c.drawText ("Load Game", 40, 40, Color.BLACK, 20);
	c.drawText ("Choose what save file to load.", 150, 40, Color.BLACK, 15);
	c.drawText ("1", 40, 100, Color.BLACK, 20);
	c.drawRect (38, 80, 700, 50.f);
	c.drawText ("2", 40, 200, Color.BLACK, 20);
	c.drawRect (38, 180, 700, 50.f);
	c.drawText ("3", 40, 300, Color.BLACK, 20);
	c.drawRect (38, 280, 700, 50.f);
	menuScreens [6] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 8
	c.drawText ("Congrats!", 40, 40, Color.BLACK, 20);
	c.drawText ("You beat the game!", 40, 100, Color.BLACK, 20);
	c.drawText ("Please enter your name (up to 8 characters):", 40, 200, Color.BLACK, 20);
	menuScreens [7] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();

	//Screen 9
	c.drawText ("Adventure", 370, 30, Color.BLACK, 34);
	c.drawText ("Of Blocky", 360, 80, Color.BLACK, 38);
	c.drawText ("New Game", 395, 200, Color.BLACK, 18);
	c.drawText ("High Scores", 395, 230, Color.BLACK, 18);
	c.drawText ("Instructions", 395, 260, Color.BLACK, 18);
	c.drawText ("Options", 405, 290, Color.BLACK, 18);
	c.drawText ("Exit", 420, 320, Color.BLACK, 18);
	menuScreens [8] = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	c.clear ();
    }


    //This mehtod handles all user input
    //The first if statement determines what user input it has to deal with
    //We initialize the c.menuChoice value to avoid problems and to set cursor to the first choice
    //I will break the next first comments to explain each code block

    //First we have "play"
    //We start by drawing the player so that the player shows up before the user inputs anything
    //We have to store the previous values for erases, so we do that
    //Then we take userInput and process it using if statements
    //The character moves based off the controls, the in game menu will open if controls[4] is pressed
    //Any other output doesn't do anything.
    //The next if statement exits the input if the player choose to do so in inGameMenu or if the player finishes the game
    //Then we draw the character again and update the screen
    //This process repeats until the user exits/finsihes as stated above

    //Second we have "MainMenuInput"
    //We first draw the cursor then wait for user input
    //The method then erases the previous cursor sets the new co ordinates of the cursor, draws it, and updates
    //This process repeats until the user presses enter to choose their option

    //Third we have "Options"
    //This code is the same as MainMenuInput but with a different cursor mode
    //Forth we have "SaveFileMenu"
    //This code is the same as MainMenuInput but with a different cursor mode

    //Fifth we have InGameMenuInput
    //This code is mostly the same as MainMenuInput but with a different cursor mode
    //Only this one will run other methods and repeat until the user exits
    //In this menu you can configure options, save the file, and exit the game

    /*
    Name        Type       Description
    keyPress    char       Stores the key press
    */
    public void userInput (String mode)
    {
	char keyPress;
	c.menuChoice = 0;

	//Determine what mode
	if (mode.equals ("play"))
	{
	    //Draws player and update
	    c.drawPlayer ();
	    c.update ();

	    //User input
	    while (true)
	    {
		//Store old position for erase
		c.playerPosition [1] [0] = c.playerPosition [0] [0];
		c.playerPosition [1] [1] = c.playerPosition [0] [1];

		//Gets user input
		keyPress = c.getChar (true);

		//Determine what the key press does
		if (keyPress == controls [0])
		    e.playerMove ("Up");
		else if (keyPress == controls [1])
		    e.playerMove ("Left");
		else if (keyPress == controls [2])
		    e.playerMove ("Down");
		else if (keyPress == controls [3])
		    e.playerMove ("Right");
		else if (keyPress == controls [4])
		    inGameMenu ();

		//Exits the game if the user chooses to exit or the game finishes
		if (c.menuChoice == 3 || c.gameFinished)
		    break;

		//Draws player and updates
		c.drawPlayer ();
		c.update ();
	    }
	}
	else if (mode.equals ("MainMenuInput"))
	{
	    //Draws cursor and updates
	    c.drawCursor ("MainMenu");
	    c.update ();

	    //user input
	    while (true)
	    {
		//Gets user input
		keyPress = c.getChar (true);

		//erases the cursor
		int[] xy = c.returnCursorPosition ("MainMenu");
		BufferedImage erase = menuScreens [1].getSubimage (xy [0] + 10, xy [1] - 3, 16, 14);
		c.drawImage (erase, xy [0] + 10, xy [1] - 3, null);

		//Determines what the key press does
		if (keyPress == controls [0])
		    c.menuChoice -= 1;
		else if (keyPress == controls [2])
		    c.menuChoice += 1;
		else if (keyPress == '\n')
		    break;

		//draws cursor and updates
		c.drawCursor ("MainMenu");
		c.update ();
	    }
	}
	else if (mode.equals ("Options"))
	{
	    //draws cursor and update
	    c.drawCursor ("Options");
	    c.update ();

	    //user input
	    while (true)
	    {
		//Gets the keypress
		keyPress = c.getChar (true);

		//erases the cursor
		int[] xy = c.returnCursorPosition ("Options");
		BufferedImage erase = menuScreens [3].getSubimage (xy [0] + 10, xy [1] - 3, 16, 14);
		c.drawImage (erase, xy [0] + 10, xy [1] - 3, null);

		//Determines what the key press does
		if (keyPress == controls [1])
		    c.menuChoice -= 1;
		else if (keyPress == controls [3])
		    c.menuChoice += 1;
		else if (keyPress == '\n')
		    break;

		//Draws cursor and updates
		c.drawCursor ("Options");
		c.update ();
	    }
	}
	else if (mode.equals ("New/LoadGameMenu"))
	{
	    readSaveFiles ();
	    try
	    {
		c.drawText ((c.saveFiles [0].equals ("---") ? "New Game":
		"Room: Forest " + c.saveFiles [0].charAt (2) + "   Time: " + Integer.toString ((int) Integer.parseInt (c.saveFiles [0].substring (3)) / 60000) + ":" + ((Integer.parseInt (c.saveFiles [0].substring (3)) % 60000) / 1000 < 10 ? Integer.toString ((Integer.parseInt (c.saveFiles [0].substring (3)) % 60000) / 1000) + "0":
		Integer.toString ((Integer.parseInt (c.saveFiles [0].substring (3)) % 60000) / 1000))), 100, 100, Color.BLACK, 15);
		c.drawText ((c.saveFiles [1].equals ("---") ? "New Game":
		"Room: Forest " + c.saveFiles [1].charAt (2) + "   Time: " + Integer.toString ((int) Integer.parseInt (c.saveFiles [1].substring (3)) / 60000) + ":" + ((Integer.parseInt (c.saveFiles [1].substring (3)) % 60000) / 1000 < 10 ? Integer.toString ((Integer.parseInt (c.saveFiles [1].substring (3)) % 60000) / 1000) + "0":
		Integer.toString ((Integer.parseInt (c.saveFiles [1].substring (3)) % 60000) / 1000))), 100, 200, Color.BLACK, 15);
		c.drawText ((c.saveFiles [2].equals ("---") ? "New Game":
		"Room: Forest " + c.saveFiles [2].charAt (2) + "   Time: " + Integer.toString ((int) Integer.parseInt (c.saveFiles [2].substring (3)) / 60000) + ":" + ((Integer.parseInt (c.saveFiles [2].substring (3)) % 60000) / 1000 < 10 ? Integer.toString ((Integer.parseInt (c.saveFiles [2].substring (3)) % 60000) / 1000) + "0":
		Integer.toString ((Integer.parseInt (c.saveFiles [2].substring (3)) % 60000) / 1000))), 100, 300, Color.BLACK, 15);
	    }
	    catch (Exception e)
	    {
	    }



	    c.drawCursor ("Load/SaveGameMenu");
	    c.update ();

	    //User input
	    while (true)
	    {
		//Gets the key press
		keyPress = c.getChar (true);

		//Erases cursor
		int[] xy = c.returnCursorPosition ("Load/SaveGameMenu");
		BufferedImage erase = menuScreens [3].getSubimage (xy [0] + 10, xy [1] - 3, 16, 14);
		c.drawImage (erase, xy [0] + 10, xy [1] - 3, null);

		//Determines what the key press does
		if (keyPress == controls [0])
		    c.menuChoice -= 1;
		else if (keyPress == controls [2])
		    c.menuChoice += 1;
		else if (keyPress == '\n')
		    break;

		//drawCursor and updates
		c.drawCursor ("Load/SaveGameMenu");
		c.update ();
	    }
	}
	else if (mode.equals ("HighScores"))
	{
	    int wordCount = 0;
	    String word = "";
	    while (true)
	    {
		keyPress = c.getChar (true);
		if (keyPress == '\n')
		    break;
		else if (keyPress == (char) 8 && word != "")
		{
		    c.fillRect (400, 180, 400, 20, Color.WHITE);
		    wordCount--;
		    word = word.substring (0, word.length () - 1);
		    c.drawText (word, 400, 200, Color.BLACK, 15);
		}
		else if (wordCount + 1 < 9)
		{
		    wordCount++;
		    word += keyPress;
		    c.drawText (word, 400, 200, Color.BLACK, 15);
		}
		c.update ();
	    }
	    c.currentPlayerName = word;
	}
	else if (mode.equals ("InGameMenuInput"))
	{
	    while (true)
	    {
		c.drawImage (tempScreen, -1, -1, null);
		c.drawCursor ("InGameMenu");
		c.update ();
		while (true)
		{
		    keyPress = c.getChar (true);

		    int[] xy = c.returnCursorPosition ("InGameMenu");
		    BufferedImage erase = tempScreen.getSubimage (xy [0] + 10, xy [1] - 3, 16, 14);
		    c.drawImage (erase, xy [0] + 10, xy [1] - 3, null);

		    if (keyPress == controls [0])
			c.menuChoice -= 1;
		    else if (keyPress == controls [2])
			c.menuChoice += 1;
		    else if (keyPress == '\n')
			break;

		    c.drawCursor ("InGameMenu");
		    c.update ();
		}
		if (c.menuChoice == 0 || c.menuChoice == 3)
		    break;
		else if (c.menuChoice == 1)
		{
		    saveFile ();
		}
		else if (c.menuChoice == 2)
		    configureControls ();
	    }
	}
    }


    //Handles the in game menu for the game
    //I have to draw the image everytime because the picture isn't definite as I use a fade out effect
    //The if statement is there to redraw the current room and blocks
    /*
    Name        Type        Description
    alpha       float       Stores float values for a faded out effect
    */
    public void inGameMenu ()
    {
	float alpha = (float) 0.5;
	c.fillRect (0, 0, c.maxx () + 1, c.maxy () + 1, new Color (0, 0, 0, alpha));
	c.drawText ("Back to Game", 385, 200, Color.BLACK, 18);
	c.drawText ("Save Game", 395, 230, Color.BLACK, 18);
	c.drawText ("Options", 405, 260, Color.BLACK, 18);
	c.drawText ("Exit to Main Menu", 367, 290, Color.BLACK, 18);
	c.update ();
	tempScreen = c.getClip (1, 1, c.maxx () + 2, c.maxy () + 2);
	userInput ("InGameMenuInput");
	if (c.menuChoice == 0)
	{
	    c.drawImage (c.currentMap, -1, -1, null);
	    for (int x = 0 ; x < 29 ; x++)
		for (int y = 0 ; y < 17 ; y++)
		    if (g.currentRoom [x] [y].equals ("block"))
			c.drawTile (x, y, "block");
	}
	c.update ();
    }


    //return if a save file exists or not
    /*
    Name            Type                Description
    tryForFile      BufferedReader      Is the reader for the file
    */
    public static boolean doesFileExist ()
    {
	BufferedReader tryForFile;
	try
	{
	    tryForFile = new BufferedReader (new FileReader ("saveFiles.sav"));
	    tryForFile.close ();
	}
	catch (Exception e)
	{
	    return false;
	}
	return true;
    }


    //read the save file onto the s.saveFiles variable
    //The program reads the save file onto a temp file and and sets c.saveFiles to tempSaveFiles
    /*
    Name        Type        Description
    */
    public void readSaveFiles ()
    {
	BufferedReader readSaveFile;
	String[] tempSaveFiles = new String [23];
	try
	{
	    readSaveFile = new BufferedReader (new FileReader ("saveFile.sav"));
	    for (int x = 0 ; x < 23 ; x++)
		tempSaveFiles [x] = readSaveFile.readLine ();
	    readSaveFile.close ();
	}
	catch (Exception e)
	{
	}
	for (int x = 0 ; x < 23 ; x++)
	    c.saveFiles [x] = tempSaveFiles [x];
    }


    //Saves the file, not much more to say
    //The try catch is for any error that appears when wrtiing the file
    /*
    Name            Type            Description
    writeSaveFile   PrintWriter     Is the writer that writes the file
    */
    public void saveFile ()
    {
	PrintWriter writeSaveFile;
	if (MainGame.gameIsPlaying)
	{
	    MainGame.time = System.currentTimeMillis () - MainGame.time;
	    c.gameTime += MainGame.time;
	    MainGame.time = System.currentTimeMillis ();
	}
	try
	{
	    writeSaveFile = new PrintWriter (new BufferedWriter (new FileWriter ("saveFile.sav")));
	    if (c.currentPlayer == 1)
	    {
		writeSaveFile.println ("1" + c.currentMapName + c.gameTime);
		for (int x = 1 ; x < 23 ; x++)
		    writeSaveFile.println (c.saveFiles [x]);
	    }
	    else if (c.currentPlayer == 2)
	    {
		writeSaveFile.println (c.saveFiles [0]);
		writeSaveFile.println ("2" + c.currentMapName + c.gameTime);
		for (int x = 2 ; x < 23 ; x++)
		    writeSaveFile.println (c.saveFiles [x]);
	    }
	    else if (c.currentPlayer == 3)
	    {
		writeSaveFile.println (c.saveFiles [0]);
		writeSaveFile.println (c.saveFiles [1]);
		writeSaveFile.println ("3" + c.currentMapName + c.gameTime);
		for (int x = 3 ; x < 23 ; x++)
		    writeSaveFile.println (c.saveFiles [x]);
	    }
	    else
	    {
		for (int x = 0 ; x < 23 ; x++)
		    writeSaveFile.println (c.saveFiles [x]);
	    }
	    writeSaveFile.close ();
	}
	catch (Exception e)
	{
	}
	readSaveFiles ();
    }


    //draws the options menu; configures the controls
    //the if statement sets the controls based off of the user input
    public void configureControls ()
    {
	c.clear ();
	c.drawImage (menuScreens [3], 0, 0, null);
	c.update ();
	userInput ("Options");
	if (c.menuChoice == 0)
	{
	    controls [0] = 'w';
	    controls [1] = 'a';
	    controls [2] = 's';
	    controls [3] = 'd';
	    controls [4] = 'i';
	}
	else
	{
	    controls [0] = 'i';
	    controls [1] = 'j';
	    controls [2] = 'k';
	    controls [3] = 'l';
	    controls [4] = 'e';
	}
    }
}
