/*
Name:           Matthew Mach
Teacher:        Mrs. Krasteva
Date:           December 15, 2017
Purpose:        This program edits hsa Console to edit the properties of hsa.Console.
				When constructing a Console, we now use this constructor.
				This also inherits all hsa.Console draw commands in which we edit in
				Draw.
*/
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;
import hsa.*;

public class Console extends hsa.Console
{
    //Instance Variables
    /*
    Name                    Type            Purpose
    innerPanel              Panel           Stores and links itself to hsa.Console's consoleCanvasPanelInner Panel, which allows me to edit the properties of the panel, most notabaly, the background color
    screenCanvas            Canvas          Stores and links itself to hsa.Console's graphicsCanvas Canvas, which allows me to edit the properties of it, most notabaly, the background color and visibleility and Focusable properties
    consoleFrame            Frame           Stores and links itself to hsa.Console's window Frame, which allows me to edit the properties of the field, most notabaly, it's location
    */

    public Panel innerPanel;
    public Canvas screenCanvas;
    public Frame consoleFrame;

    //normal constructor, used for offscreenCanvas
    //Class consoleClass uses an extra getSuperclass() method, as when I call super() in Draw, for some reason the constructor is run like the code was ran in Draw, not Console; Unsure why
    /*
    Name                            Type    Description
    x                               int     x coordinate of the Console
    y                               int     y coordinate of the Console

    consoleClass                    Class   Stores the reference to hsa.Parent class; used for easy access
    consoleCanvasPanelInnerField    Field   Used to access the consoleCanvasPanelInner field in hsa.Parent
    consoleFrameField               Field   Used to access the window field in hsa.Parent
    */
    //We call super has we constructor an instance of hsa.Console
    //We try catch our attempt at accessing the hsa.Parent class as it may not exist and throw an exception
    //There may also be an exception when trying to access the fields
    //The code then accesses the fields and links the field to our instance variables
    //We then edit these values now that we have established the links
    public Console (int x, int y)
    {
	super (26, 110); //Calls hsa.Console constructor
	try
	{
	    Class ConsoleCanvasGraphics = null;
	    //We set hsa.Parent to a variable for easy access
	    Class consoleClass = this.getClass ().getSuperclass ().getSuperclass ();

	    //Declaration of fields

	    //Gives access to the consoleCanvasPanelInner Field through the consoleCanvasPanelInnerField variable
	    Field consoleCanvasPanelInnerField = consoleClass.getSuperclass ().getDeclaredField ("consoleCanvasPanelInner");
	    //Sets the Field accessible as the Field is Protected
	    consoleCanvasPanelInnerField.setAccessible (true);

	    //Gives access to the window frame through the variable consoleFrameField
	    Field consoleFrameField = consoleClass.getSuperclass ().getDeclaredField ("window");
	    //Sets the Field accessible as the Field is Protected
	    consoleFrameField.setAccessible (true);

	    //Giving instance variables values using the fields; links the instance variables to the values, allowing me to edit them

	    innerPanel = (Panel) consoleCanvasPanelInnerField.get (this);
	    consoleFrame = (Frame) consoleFrameField.get (this);

	    //We access the Canvas directly here as the variable is not Protected
	    screenCanvas = (Canvas) consoleClass.getDeclaredField ("graphicsCanvas").get (this);
	}
	catch (Exception e)
	{
	}

	//Sets the background of everything to white
	consoleFrame.setBackground (Color.WHITE);
	innerPanel.setBackground (Color.WHITE);
	screenCanvas.setBackground (Color.WHITE);
	//Sets screenCanvas Visible for good measure and allows screen
	screenCanvas.setVisible (true);
	screenCanvas.setFocusable (true);
	consoleFrame.setLocation (x, y);
    }


    //The constructor used for our onscreenConsole
    //This version adds another parameter of the window name
    //The code is the same except for the super constructor which calls an additional parameter being the window name
    /*
    Name                            Type    Description
    x                               int     x coordinate of the Console
    y                               int     y coordinate of the Console
    title                           String  The window name for Console

    consoleClass                    Class   Stores the reference to hsa.Parent class; used for easy access
    consoleCanvasPanelInnerField    Field   Used to access the consoleCanvasPanelInner field in hsa.Parent
    consoleFrameField               Field   Used to access the window field in hsa.Parent
    */
    //Explaination is the same as the constructor above
    //We call super has we constructor an instance of hsa.Console
    //We try catch our attempt at accessing the hsa.Parent class as it may not exist and throw an exception
    //There may also be an exception when trying to access the fields
    //The code then accesses the fields and links the field to our instance variables
    //We then edit these values now that we have established the links
    public Console (int x, int y, final String title)
    {
	super (26, 110, title); //Calls hsa.Console constructor
	try
	{
	    Class ConsoleCanvasGraphics = null;
	    Class consoleClass = this.getClass ().getSuperclass ();

	    //Declaration of fields

	    Field consoleCanvasPanelInnerField = consoleClass.getSuperclass ().getDeclaredField ("consoleCanvasPanelInner");  //Creates a new field consoleCanvasPanelInnerField
	    consoleCanvasPanelInnerField.setAccessible (true);                                                                //turns field to public

	    Field consoleFrameField = consoleClass.getSuperclass ().getDeclaredField ("window");                              //Creates a new field consoleFrameField
	    consoleFrameField.setAccessible (true);                                                                           //turns field to public

	    //Giving instance variables values using the fields

	    innerPanel = (Panel) consoleCanvasPanelInnerField.get (this);
	    screenCanvas = (Canvas) consoleClass.getDeclaredField ("graphicsCanvas").get (this);                           //sets offscreenCanvas to be the canvas draw commands display on
	    consoleFrame = (Frame) consoleFrameField.get (this);
	}
	catch (Exception e)
	{
	}
	consoleFrame.setBackground (Color.WHITE);                                                       //Sets the Color of the Console
	innerPanel.setBackground (Color.WHITE);
	screenCanvas.setBackground (Color.WHITE);
	screenCanvas.setVisible (true);                                                             //Hides the off screen canvas, allows for the painting of the canvas by replacing the visable canvas by this one
	screenCanvas.setFocusable (true);
	consoleFrame.setLocation (x, y);


    }
}



