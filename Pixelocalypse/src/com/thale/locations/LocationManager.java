package com.thale.locations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.ItemManager;
import com.thale.player.Player;

public class LocationManager extends GameAsset 
{
	private Game game;
	private ItemManager itemMan;
	
	public LocationManager(Game game) 
	{
		super(game);
		this.game = game;
		itemMan = game.getItemManager();
	}

	public List<Location> locations = new ArrayList<Location>();
	public List<Player> exploringPlayers = new ArrayList<Player>();
	public List<Player> lootingPlayers = new ArrayList<Player>();
	public List<Player> travellingPlayers = new ArrayList<Player>();
	public List<Player> lootLockedPlayers = new ArrayList<Player>();
	
	private File locationsDir;
	private File locationsFile;
	private File lootDir;
	private File[] fileArray;
	
	public void createLocationDirectories()
	{
		locationsDir = new File(game.getFilepath() + "Pixelocalypse\\Locations");
		
		// Creates the directory if it doesn't exist
		if (locationsDir.exists() == false)
		{
			locationsDir.mkdirs();
		}
		else if (locationsDir.exists() == true)
		{
			System.err.format("\nDirectory " + locationsDir + " already exists");
		}
		
		lootDir = new File(game.getFilepath() + "Pixelocalypse\\Loot");
		
		// Creates the directory if it doesn't exist
		if (lootDir.exists() == false)
		{
			lootDir.mkdirs();
		}
		else if (lootDir.exists() == true)
		{
			System.err.format("\nDirectory " + lootDir + " already exists");
		}
		
		loadLocations();
	}
	
	public void loadLocations()
	{	
		String name = "";
		int rarity = 0;
		List<String> loot = new ArrayList<String>();
		List<Integer> lootChance = new ArrayList<Integer>();
		ImageIcon icon;
		int danger = 0;
		int level = 0;
		
		String lootFilePath = "";
		File lootFile;
		String iconPath = "";
		int lootArrayCount = 0;	// Increment variable for creating the loot arrays
		int locationIndex = -1;	// Increment variable for creating the Location list 

		fileArray = locationsDir.listFiles();	// Reads the directory and adds the file path of every file in it to the array
		for (int x = 0; x < fileArray.length; x++)	// Control loop repeats as many times as there are files
		{
			System.out.println("\n\nNew Location File:");
		   
			locationIndex++;
			System.out.print("Index: " + locationIndex);
		   
			locationsFile = new File(fileArray[x].toString());	// Sets what file from the array is to be read
			System.out.print("\nFile Path: " + locationsFile);
		    
			// Handles the reading of the location file
			FileReader reader;
			BufferedReader inFile;

			try
			{
				// Open the file
			    reader = new FileReader(locationsFile);
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
			        
			        // Reads the values from the current line and sets them equal to variables
			        name = token.nextToken();
			        rarity = Integer.parseInt(token.nextToken());
			        lootFilePath = token.nextToken();
			        iconPath = token.nextToken();
			        danger = Integer.parseInt(token.nextToken());
			        level = Integer.parseInt(token.nextToken());
								          
			        line = inFile.readLine();	// Reads the next line in the file
			    }
			    inFile.close();	// Closes the file        
			}
			catch (IOException e)
			{
			    	   
			}
			System.out.print("\nLocation Name: " + name);
			       
			lootFile = new File(game.getFilepath() + "Pixelocalypse\\Loot\\" + lootFilePath);	// Sets the file location for the relevant loot table
			icon = new ImageIcon(game.getRespath() + "locations/" + iconPath);	// Finds the image for the relevant location
			
			// Handles the reading of the loot file
			FileReader reader2;
			BufferedReader inFile2;

			try
			{
				// Open the file
			    reader2 = new FileReader(lootFile);
			    inFile2 = new BufferedReader(reader2);
			          
			    String line2 = inFile2.readLine();	// Reads the first line of the file
						
			    /**
			    * Loop continues to read the file
			    * until it detects that there
			    * is no more in the file to read.
			    */
			    while(line2 != null)
			    {	
			    	//StringTokenizer detects whitespace and different values on a single line
			        StringTokenizer token2 = new StringTokenizer(line2);
			        
			        // Reads the values from the current line and adds them to the proper array
			        loot.add(lootArrayCount, token2.nextToken());
					lootChance.add(lootArrayCount, Integer.parseInt(token2.nextToken()));
						 
					lootArrayCount++;	// Increment the array location
			          	 
			        line2 = inFile2.readLine();	// Reads the next line of the file
			    }
			    inFile2.close();	// Closes the file        
			}
			catch (IOException e)
			{
			    	   
			}
			lootArrayCount = 0;	// Resets the loot array increment for the next location file
			
			System.out.print("\nNew Loot File:");
			System.out.print("\nFile Path: " + lootFile);
			System.out.print("\nLoot Sample: " + loot.get(0) + " " + lootChance.get(0));
			
			String[] lootArray = new String[loot.size()];
			loot.toArray(lootArray);
			
			int[] lootChanceArray = new int[lootChance.size()];
			
			 for (int i = 0; i < lootChanceArray.length; i++) 
			 {
				 lootChanceArray[i] = lootChance.get(i);
			 }
			
			// Uses all the collected data to create a new Location object and store it into the list
			locations.add(new Location(name, rarity, lootArray, lootChanceArray, icon, danger, level));
			
			loot.clear();
			lootChance.clear();
			
		   }// End "main for loop"
		 
		// Array print test
		System.out.print("\n\nLocations Loaded:");
		for (int x = 0; x < locations.size(); x++)
		{
			System.out.print("\n- " + locations.get(x).getName());
		}
	}
	
	public Location getLocation(String name)
	{
		int index = 0;
		
		for (int count = 0; count < locations.size(); count++)
		{
			if (locations.get(count).getName().equals(name))
			{
				index = count;
			}
		}
		
		return locations.get(index);
	}
	
	public List<Location> getLocations()
	{
		return locations;
	}
	
	public void returnToCompound(Player player)
	{
		new Thread(new CompoundTravelThread(game, player)).start();
	}
	
	public void travel(Player player, String location)
	{
		new Thread(new TravelThread(game, player, location)).start();
	}
	
	public void explore(Player player)
	{
		new Thread(new ExploreThread(game, player)).start();
	}
	
	public List<Player> getExploringPlayers()
	{
		return exploringPlayers;
	}
	
	public void addToExploringPlayers(Player player)
	{
		exploringPlayers.add(player);
	}
	
	public void removeFromExploringPlayers(Player player)
	{
		for (int x = 0; x < exploringPlayers.size(); x++)
		{
			if (exploringPlayers.get(x).getName().equals(player.getName()))
			{
				exploringPlayers.remove(x);
				break;
			}
		}
	}
	
	public boolean isPlayerExploring(Player player)
	{
		boolean playerExploring = false;
		
		for (int x = 0; x < exploringPlayers.size(); x++)
		{
			if (exploringPlayers.get(x).getName().equals(player.getName()))
			{
				playerExploring = true;
				break;
			}
		}
		
		return playerExploring;
	}
	
	public void loot(Player player, int index)
	{
		new Thread(new LootThread(game, player, index)).start();
	}
	
	public List<Player> getLootingPlayers()
	{
		return lootingPlayers;
	}
	
	public void addToLootingPlayers(Player player)
	{
		lootingPlayers.add(player);
	}
	
	public void removeFromLootingPlayers(Player player)
	{
		for (int x = 0; x < lootingPlayers.size(); x++)
		{
			if (lootingPlayers.get(x).getName().equals(player.getName()))
			{
				lootingPlayers.remove(x);
				break;
			}
		}
	}
	
	public boolean isPlayerLooting(Player player)
	{
		boolean playerLooting = false;
		
		for (int x = 0; x < lootingPlayers.size(); x++)
		{
			if (lootingPlayers.get(x).getName().equals(player.getName()))
			{
				playerLooting = true;
				break;
			}
		}
		
		return playerLooting;
	}
	
	public List<Player> getTravellingPlayers()
	{
		return travellingPlayers;
	}
	
	public void addToTravellingPlayers(Player player)
	{
		travellingPlayers.add(player);
	}
	
	public void removeFromTravellingPlayers(Player player)
	{
		for (int x = 0; x < travellingPlayers.size(); x++)
		{
			if (travellingPlayers.get(x).getName().equals(player.getName()))
			{
				travellingPlayers.remove(x);
				break;
			}
		}
	}
	
	public boolean isPlayerTravelling(Player player)
	{
		boolean playerTravelling = false;
		
		for (int x = 0; x < travellingPlayers.size(); x++)
		{
			if (travellingPlayers.get(x).getName().equals(player.getName()))
			{
				playerTravelling = true;
				break;
			}
		}
		
		return playerTravelling;
	}
	
	public List<Player> getLootLockedPlayers()
	{
		return lootLockedPlayers;
	}
	
	public void addToLootLockedPlayers(Player player)
	{
		lootLockedPlayers.add(player);
	}
	
	public void removeFromLootLockedPlayers(Player player)
	{
		for (int x = 0; x < lootLockedPlayers.size(); x++)
		{
			if (lootLockedPlayers.get(x).getName().equals(player.getName()))
			{
				lootLockedPlayers.remove(x);
				break;
			}
		}
	}
	
	public boolean isPlayerLootLocked(Player player)
	{
		boolean playerLootLocked = false;
		
		for (int x = 0; x < lootLockedPlayers.size(); x++)
		{
			if (lootLockedPlayers.get(x).getName().equals(player.getName()))
			{
				playerLootLocked = true;
				break;
			}
		}
		
		return playerLootLocked;
	}
	
	public Location findLocation()
	{
		Random randGen = new Random();
		List<Integer> randNums = new ArrayList<Integer>();
		List<Integer> randTotalNums = new ArrayList<Integer>();
		int rarityNum = 0;
		boolean newNum = true;
		int randTotalNumsSize = 0;
		List<Location> possibleLocations = new ArrayList<Location>();
		int randLoc = 0;
		Location chosenLoc;
		
		for (int x = 0; x < locations.size(); x++)
		{
			if (rarityNum == 0)
			{
				rarityNum = locations.get(x).getRarity();
				
				for (int y = 0; y < rarityNum; y++)
				{
					randNums.add(rarityNum);
				}
				
				randTotalNums.add(rarityNum);
			}
			else
			{
				randTotalNumsSize = randTotalNums.size();
				
				for (int y = 0; y < randTotalNumsSize; y++)
				{
					if (randTotalNums.get(y) == locations.get(x).getRarity())
					{
						newNum = false;
						break;
					}
				}
				
				if (newNum == true)
				{
					rarityNum = locations.get(x).getRarity();
					
					for (int y = 0; y < rarityNum; y++)
					{
						randNums.add(rarityNum);
					}
					
					randTotalNums.add(rarityNum);
				}
				
				newNum = true;
			}
		}
		
		// Test Output
		System.out.print("\n");
		System.out.print("\nPossible Rarities: ");
		for (int x = 0; x < randTotalNums.size(); x++)
		{
			System.out.print("\n- " + randTotalNums.get(x));
		}
		
		rarityNum = randNums.get(randGen.nextInt((randNums.size() - 1)));
		
		for (int x = 0; x < locations.size(); x++)
		{
			if (locations.get(x).getRarity() == rarityNum)
			{
				possibleLocations.add(locations.get(x));
			}
		}
		
		// Test Output 2
		System.out.print("\n");
		System.out.print("\nRarity Chosen: " + rarityNum);
		System.out.print("\nLocations With Matching Rarity: ");
		for (int x = 0; x < possibleLocations.size(); x++)
		{
			System.out.print("\n- " + possibleLocations.get(x).getName());
		}
		
		if (possibleLocations.size() == 1)
		{
			chosenLoc = possibleLocations.get(0);
		}
		else
		{
			randLoc = randGen.nextInt((possibleLocations.size()));
			chosenLoc = possibleLocations.get(randLoc);
		}
		
		// Test Output 3
		System.out.print("\n");
		System.out.print("\nChosen Location: " + possibleLocations.get(randLoc).getName());
		
		randNums.clear();
		randTotalNums.clear();
		possibleLocations.clear();
		
		return game.getLocationManager().getLocation(chosenLoc.getName());
	}
}
