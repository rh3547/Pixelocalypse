package com.thale.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.locations.Location;
import com.thale.main.ScreenMainMenu;
import com.thale.main.ScreenPixelocalypse;

public class ItemManager extends GameAsset
{
	private Game game;
	
	public List<Item> items = new ArrayList<Item>();
	
	private File itemsDir;
	private File itemFile;
	private File[] fileArray;
	
	public ItemManager(Game game) 
	{
		super(game);
		this.game = game;
	}
	
	public void createItemDirectories()
	{
		itemsDir = new File(game.getFilepath() + "Pixelocalypse\\Items");
		
		// Creates the directory if it doesn't exist
		if (itemsDir.exists() == false)
		{
			itemsDir.mkdirs();
		}
		else if (itemsDir.exists() == true)
		{
			System.err.format("\nDirectory " + itemsDir + " already exists");
		}
		
		loadItems();
	}
	
	public void loadItems()
	{	
		String name = "";
		int minLoot = 0;
		int maxLoot = 0;
		int value = 0;
		int stackSize = 0;
		ImageIcon icon;
		String use = "";
		int useAmt = 0;
		int mod = 0;
		String desc;
		String currentToken;
		
		String iconPath = "";
		int itemIndex = -1;	// Increment variable for creating the Item list 

		fileArray = itemsDir.listFiles();	// Reads the directory and adds the file path of every file in it to the array
		for (int x = 0; x < fileArray.length; x++)	// Control loop repeats as many times as there are files
		{
			System.out.println("\n\nNew Item File:");
		   
			itemIndex++;
			System.out.print("Index: " + itemIndex);
		   
			itemFile = new File(fileArray[x].toString());	// Sets what file from the array is to be read
			System.out.print("\nFile Path: " + itemFile);
		    
			// Handles the reading of the item file
			FileReader reader;
			BufferedReader inFile;
			
			desc = "";
			currentToken = "";

			try
			{
				// Open the file
			    reader = new FileReader(itemFile);
			    inFile = new BufferedReader(reader);
			          
			    String line = inFile.readLine();	// Reads the first line of the file
						
			    /**
			    * Loop continues to read the file
			    * until it detects that there
			    * is no more in the file to read.
			    */
			    while(line != null)
			    {	
			    	//StringTokenizer detects whitespace and different values on a single line
			        StringTokenizer token = new StringTokenizer(line);
			        
			        try
         			{
         				currentToken = token.nextToken();
         			}
         			catch(Exception ex)
         			{
         				ex.printStackTrace();
         				break;
         			}
			        
			        if (currentToken.equals("Info"))
			        {
			        	// Reads the values from the current line and sets them equal to variables
				        name = token.nextToken();
				        minLoot = Integer.parseInt(token.nextToken());
				        maxLoot = Integer.parseInt(token.nextToken());
				        value = Integer.parseInt(token.nextToken());
				        stackSize = Integer.parseInt(token.nextToken());
				        iconPath = token.nextToken();
				        use = token.nextToken();
				        useAmt = Integer.parseInt(token.nextToken());
				        mod = Integer.parseInt(token.nextToken());
			        }
			        else if (currentToken.equals("Desc"))
			        {
			        	try
	         			{
	         				currentToken = token.nextToken();
	         			}
	         			catch(Exception ex)
	         			{
	         				ex.printStackTrace();
	         				break;
	         			}
			        	
			        	while (!currentToken.equals("EndDesc"))
			        	{
			        		desc = desc + currentToken + " ";
			        		
			        		try
		         			{
		         				currentToken = token.nextToken();
		         			}
		         			catch(Exception ex)
		         			{
		         				ex.printStackTrace();
		         				break;
		         			}
			        	}
			        }
								          
			        line = inFile.readLine();	// Reads the next line in the file
			    }
			    inFile.close();	// Closes the file        
			}
			catch (IOException e)
			{
			    	   
			}
			System.out.print("\nItem Name: " + name);
			
			icon = new ImageIcon(game.getRespath() + "items/" + iconPath);	// Finds the image for the relevant item
			
			// Uses all the collected data to create a new Item object and store it into the list
			items.add(new Item(name, minLoot, maxLoot, value, stackSize, icon, desc, use, useAmt, mod));
		   }// End "main for loop"
		 
		// Array print test
		System.out.print("\n\nItems Loaded:");
		for (int x = 0; x < items.size(); x++)
		{
			System.out.print("\n- " + items.get(x).getName());
		}
	}
	
	public Item getItem(String name)
	{
		int index = 0;
		
		for (int count = 0; count < items.size(); count++)
		{
			if (items.get(count).getName().equals(name))
			{
				index = count;
				break;
			}
		}
		
		return items.get(index);
	}
	
	public Item getLootItems(String name)
	{
		int index = 0;
		
		for (int count = 0; count < items.size(); count++)
		{
			if (items.get(count).getName().equals(name))
			{
				index = count;
				break;
			}
		}
		
		return items.get(index);
	}
}
