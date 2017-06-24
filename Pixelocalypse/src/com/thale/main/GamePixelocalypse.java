package com.thale.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.thale.engine.Game;
import com.thale.engine.ResourceLoader;
import com.thale.player.Player;

public class GamePixelocalypse
{
	/********************************************
	* 			   Game Preferences
	********************************************/
	int windowX;
	int windowY;
	int fps;
	
	/********************************************
	* 			  Internal Game Data
	********************************************/
	private Game game;
	
	private String filepath = "C:\\";
	private String respath = "src/res/";
	
	private int health;
	private int maxValue;
	private int maxLevel;
	private int level;
	
	/********************************************
	* 			     File Reading
	********************************************/
	private File prefDir;
	private File prefFile;
	private File saveDir;
	
	private boolean firstLoad;
	
	public GamePixelocalypse()
	{
		init();
	}
	
	public void init()
	{
		prefDir = new File(filepath + "Pixelocalypse\\Options");
		prefFile = new File(filepath + "Pixelocalypse\\Options\\preferences.txt");
		
		// Creates the directory "C:/Pixelocalypse/Options" if it doesn't exist
		if (prefDir.exists() == false)
		{
			prefDir.mkdirs();
		}
		else if (prefDir.exists() == true)
		{
			System.err.format("\nDirectory " + prefDir + " already exists");
		}
		
		// Creates the file "preferences.txt" if it doesn't exist
		Path prefFilePath = Paths.get(filepath + "Pixelocalypse\\Options", "preferences.txt");
		try 
		{ 
		    Files.createFile(prefFilePath);
		} 
		catch (FileAlreadyExistsException x) 
		{
		    System.err.format("\nFile named %s" + " already exists%n", prefFilePath);
		} 
		catch (IOException x) 
		{
		    System.err.format("createFile error: %s%n", x);
		}
		
		saveDir = new File(filepath + "Pixelocalypse\\Saves");
		
		// Creates the directory "C:/Pixelocalypse/Saves" if it doesn't exist
		if (saveDir.exists() == false)
		{
			saveDir.mkdirs();
		}
		else if (saveDir.exists() == true)
		{
			System.err.format("\nDirectory " + saveDir + " already exists");
		}
		
		openInitFile();
		
		if (firstLoad == true)
		{
			windowX = 1280;
			windowY = 720;
			firstLoad = false;
			
			String data;
	        FileWriter writer;
	        PrintWriter outputFile;

	        try
	        {
	           // Open the file
	           writer = new FileWriter(prefFile);
	           outputFile = new PrintWriter(writer);
	              
	           firstLoad = false;
	           // Get the data
	           data = 
	           Boolean.toString(firstLoad) + " " + 
	           Integer.toString(windowX) + " " +
	           Integer.toString(windowY);
	              
	           outputFile.print(data);

	           // Close the file
	           outputFile.close();
	        }
	        catch (IOException i)
	        {
	        	   
	        }
		}
		else if (firstLoad == false)
		{
			openPrefFile();
			startGame();
		}	
	}
	
	public void openInitFile()
	{
		FileReader reader1;
	    BufferedReader inFile1;

	       try
	       {
	          // Open the file
	          reader1 = new FileReader(prefFile);
	          inFile1 = new BufferedReader(reader1);
	          
	          String line1 = inFile1.readLine();
	          
	          if (line1 == null)
	          {
	        	  String data1;
		           FileWriter writer1;
		           PrintWriter outputFile1;

		           try
		           {
		              // Open the file
		              writer1 = new FileWriter(prefFile);
		              outputFile1 = new PrintWriter(writer1);
		              
		              firstLoad = true;
		              // Get the data
		              data1 = 
		              Boolean.toString(firstLoad);
		              
		              outputFile1.print(data1);

		              // Close the file
		              outputFile1.close();
		           }
		           catch (IOException i)
		           {
		        	   
		           }
	          }
	          else 
	          {
	        	  StringTokenizer tokenInit = new StringTokenizer(line1);
	 	         
		          firstLoad = Boolean.parseBoolean(tokenInit.nextToken());
		          
		          inFile1.close(); 
	          }          
	       }
	       catch (IOException e)
	       {
	    	  
	       }
	}
	
	public void openPrefFile()
    {
       FileReader reader;
       BufferedReader inFile;

       try
       {
          // Open the file
          reader = new FileReader(prefFile);
          inFile = new BufferedReader(reader);
          
          String line = inFile.readLine();
			
          /**
          * Loop continues to read the file
          * until it detects that there
          * is no more in the file to read.
          */
          while(line != null)
          {	
             //StringTokenizer detects white spaces
          	 StringTokenizer token = new StringTokenizer(line);
			
             // New Game
          	 firstLoad = Boolean.parseBoolean(token.nextToken());
             windowX = Integer.parseInt(token.nextToken());
             windowY = Integer.parseInt(token.nextToken());
					          
             line = inFile.readLine();
          }
          inFile.close();          
       }
       catch (IOException e)
       {
    	   
       }
    }
	
	public void startGame()
	{
		// Create a new Game "Pixelocalypse"
		game = new Game(windowX, windowY, "Pixelocalypse", 60);
		
		ResourceLoader resLoad = new ResourceLoader(game);
		resLoad.loadFonts();
		resLoad.loadBgImage();
		
		// Show the Loading Screen
		game.getScreenHandler().showLoadingScreen(new ScreenLoading(game.getScreenHandler()));
		
		// Create directories and load game files
		game.getPlayerManager().createCharacterDirectories();
		game.getLocationManager().createLocationDirectories();
		game.getItemManager().createItemDirectories();
		game.getCraftingManager().createCraftingDirectories();
		
		// Create the starting Player with basic attributes
		game.getPlayerManager().createNewCharacter();
		game.setCurrentPlayer(game.getPlayerManager().getPlayers().get(0));
		game.getPlayerManager().createNewCharacter();
		game.getInventoryManager().setDefaultInventory(game.getPlayerManager().getPlayers().get(0).getInventory());
		
		game.setCurrentLocation(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation()));
	}
	
	public static void main(String[] args)
	{
		new GamePixelocalypse();
	}
}
