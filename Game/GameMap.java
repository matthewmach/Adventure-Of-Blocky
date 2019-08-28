/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        This program stores all the maps of the program and sets up the currentRoom.
		currentRoom stores the values of block used in interactions and player
		movement.
*/

import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;


public class GameMap
{
    //Instance Variables
    /*
    Name                Type                Description
    c                   Draw                Constructor for Draw, same as MainGame.c; grants access to all the Draw and hsa.Console commands and variables
    map                 BufferedImage[][]   This stores all the game maps
    currentRoom         String[][]          Stores the x y values of tiles; the first index is x, the second index is y
    */
    Draw c = MainGame.c;
    BufferedImage[] [] map = new BufferedImage [3] [3];
    String[] [] currentRoom = new String [29] [17]; //Stores the String name of the tiles in the room

    //GameMap constructor
    //Initializes all the map images
    public GameMap ()
    {
	//Map 1
	c.currentMapName = "f1";
	setCurrentRoom ();
	c.drawGround ("forest");
	for (int x = 0 ; x < 29 ; x++)
	    for (int y = 0 ; y < 17 ; y++)
		c.drawTile (x, y, currentRoom [x] [y]);
	map [0] [0] = c.getClip (1, 1, c.maxx () + 3, c.maxy () + 3);
	c.clear ();
	//Map 2
	c.currentMapName = "f2";
	setCurrentRoom ();
	c.drawGround ("forest");
	for (int x = 0 ; x < 29 ; x++)
	    for (int y = 0 ; y < 17 ; y++)
		c.drawTile (x, y, currentRoom [x] [y]);
	map [0] [1] = c.getClip (1, 1, c.maxx () + 3, c.maxy () + 3);
	c.clear ();
	//Map 3
	c.currentMapName = "f3";
	setCurrentRoom ();
	c.drawGround ("forest");
	for (int x = 0 ; x < 29 ; x++)
	    for (int y = 0 ; y < 17 ; y++)
		c.drawTile (x, y, currentRoom [x] [y]);
	map [0] [2] = c.getClip (1, 1, c.maxx () + 3, c.maxy () + 3);
	c.clear ();
    }


    //This sets up currentRoom for the currentMapName
    //The first nested for loops initializes everything to none; we don't want any NullPointerExceptions
    //Then the if statment uses c.currentMapName and sets up currentRoom accordingly
    public void setCurrentRoom ()
    {
	for (int x = 0 ; x < 29 ; x++)
	    for (int y = 0 ; y < 17 ; y++)
		currentRoom [x] [y] = "none";
	if (c.currentMapName.equals ("f1"))
	{
	    for (int y = 0 ; y < 17 ; y += 16)
		for (int x = 0 ; x < 29 ; x += 1 + (x == 13 && y == 0 ? 1:
		0))
		    currentRoom [x] [y] = "tree";
	    for (int y = 0 ; y < 17 ; y++)
		for (int x = 0 ; x < 29 ; x += 28)
		    currentRoom [x] [y] = "tree";
	}
	else if (c.currentMapName.equals ("f2"))
	{
	    for (int x = 0 ; x < 29 ; x += 1 + (x == 13 ? 1:
	    0))
		for (int y = 0 ; y < 17 ; y += 16)
		    currentRoom [x] [y] = "tree";
	    for (int x = 0 ; x < 29 ; x += 28)
		for (int y = 0 ; y < 17 ; y++)
		    currentRoom [x] [y] = "tree";
	}
	else if (c.currentMapName.equals ("f3"))
	{
	    for (int y = 0 ; y < 17 ; y += 16)
		for (int x = 0 ; x < 29 ; x += 1 + (x == 13 ? 1:
		0))
		    currentRoom [x] [y] = "tree";
	    for (int y = 0 ; y < 16 ; y++)
		for (int x = 0 ; x < 29 ; x += 1 + (x == 11 ? 5:
		0))
		    currentRoom [x] [y] = "tree";
	}
    }


    //This sets up currentRoom with blocks
    //We don't want blocks in currentRoom when drawing the images for map
    //This would mess up erases
    //Thus we only draw them when we need it
    //So this method draws and sets values in currentRoom
    public void setBlocks ()
    {
	//Sets the blocks
	if (c.currentMapName.equals ("f1"))
	{
	    for (int x = 1 ; x < 29 ; x += 2)
		for (int y = 1 ; y < 4 ; y += 2)
		    currentRoom [x] [y] = "block";
	    for (int x = 2 ; x < 27 ; x += 2)
		currentRoom [x] [2] = "block";
	}
	else if (c.currentMapName.equals ("f2"))
	{
	    for (int x = 1 ; x < 5 ; x++)
		currentRoom [9 + x] [x] = "block";
	    for (int x = 5 ; x > 0 ; x--)
		currentRoom [19 - x] [x] = "block";
	}
	else if (c.currentMapName.equals ("f3"))
	{
	    for (int x = 12 ; x < 17 ; x += 4)
		for (int y = 1 ; y < 6 ; y += 2)
		    currentRoom [x] [y] = "block";
	    for (int x = 13 ; x < 16 ; x += 1)
		for (int y = 2 ; y < 5 ; y += 2)
		    currentRoom [x] [y] = "block";
	}
	//Draws the blocks
	for (int x = 0 ; x < 29 ; x++)
	    for (int y = 0 ; y < 17 ; y++)
		if (currentRoom [x] [y].equals ("block"))
		    c.drawTile (x, y, "block");
    }
}
