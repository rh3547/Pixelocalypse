package com.thale.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.main.LevelUpPanel;
import com.thale.scene.Scene;

public class PlayerManager extends GameAsset
{
	private final Game game;
	
	private List<Player> players = new ArrayList<Player>();
	
	private List<String> characterNames = new ArrayList<String>();
	private List<String> usedNames = new ArrayList<String>();
	
	private File characterDir;
	private File characterFile;
	private File[] fileArray;
	
	public PlayerManager(Game game)
	{
		super(game);
		
		this.game = game;
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}
	
	public void setPlayers(List<Player> players)
	{
		this.players = players;
	}
	
	public Player getPlayer(String name)
	{
		int index = 0;
		
		for (int count = 0; count < players.size(); count++)
		{
			if (players.get(count).getName().equals(name))
			{
				index = count;
				break;
			}
		}
		
		return players.get(index);
	}
	
	public int getPlayerIndex(String name)
	{
		int index = 0;
		
		for (int count = 0; count < players.size(); count++)
		{
			if (players.get(count).getName().equals(name))
			{
				index = count;
				break;
			}
		}
		
		return index;
	}
	
	public void addPlayer(String name, int level, int xp, String inventory, String location, 
						  Scene scene, Motives motives, Attributes attributes, Skills skills, 
						  int upgradePoints, int timesLooted, String[] locations)
	{
		players.add(new Player(name, level, xp, inventory, location, scene, 
				motives, attributes, skills, upgradePoints, timesLooted, locations));
		
		getPlayer(name).getLocations()[0] = game.getLocationManager().findLocation().getName();
		getPlayer(name).getLocations()[1] = game.getLocationManager().findLocation().getName();
		getPlayer(name).getLocations()[2] = game.getLocationManager().findLocation().getName();
		getPlayer(name).getLocations()[3] = game.getLocationManager().findLocation().getName();
	}
	
	public void removeCharacter(String name)
	{
		players.remove(getPlayerIndex(name));
	}
	
	public Player createNewCharacter()
	{
		String name = game.getPlayerManager().generateName();
		game.getInventoryManager().createNewInventory(name + "'s-Backpack", 20);
		
		addPlayer(name, 1, 0, name + "'s-Backpack", "Forest", null, 
				  new Motives(100, 100, 10, 10, 100, 100, 100, 100), new Attributes(1, 1, 1, 1, 1), 
				  new Skills(1), 2, 0, new String[4]);
		
		game.getSceneManager().createNewPlayerScene(getPlayer(name));
		
		System.out.print("\n\nCharacter Created:");
		System.out.print("\nIndex: " + getPlayerIndex(name));
		System.out.print("\nName: " + getPlayer(name).getName());
		System.out.print("\nLevel: " + getPlayer(name).getLevel());
		System.out.print("\nExperience: " + getPlayer(name).getXp());
		System.out.print("\nInventory: " + getPlayer(name).getInventory());
		System.out.print("\nLocation: " + getPlayer(name).getLocation());
		
		return getPlayer(name);
	}
	
	public void killCharacter(String name)
	{
		String saveName = game.getLastSave();
		
		int index = 0;
		String nextCharacter;
		
		for (int x = 0; x < game.getPlayerManager().getPlayers().size(); x++)
		{
			if (game.getPlayerManager().getPlayers().get(x).getName().equals(name))
			{
				index = x;
				break;
			}
		}
		
		if ((index + 1) <= (game.getPlayerManager().getPlayers().size() - 1))
		{
			nextCharacter = game.getPlayerManager().getPlayers().get(index + 1).getName();
		}
		else
		{
			nextCharacter = game.getPlayerManager().getPlayers().get(0).getName();
		}
		
		game.setCurrentPlayer(game.getPlayerManager().getPlayer(nextCharacter));
		game.getInventoryManager().setDefaultInventory(game.getPlayerManager().getPlayer(nextCharacter).getInventory());
		game.setCurrentLocation(game.getLocationManager().getLocation(game.getPlayerManager().getPlayer(nextCharacter).getLocation()));
		
		if (!saveName.equals(""))
		{
			File filePath = new File(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + name + "Stats");
			Path path = filePath.toPath();
			try 
			{
				Files.delete(path);
			} 
			catch (NoSuchFileException x) 
			{
				System.err.format("\n%s: no such" + " file or directory%n", filePath);
			} 
			catch (DirectoryNotEmptyException x) 
			{
				System.err.format("\n%s not empty%n", filePath);
			} 
			catch (IOException x) 
			{
				System.err.println(x);
			}
			
			filePath = new File(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + "CharactersSave");
			path = filePath.toPath();
			try 
			{
				Files.delete(path);
			} 
			catch (NoSuchFileException x) 
			{
				System.err.format("\n%s: no such" + " file or directory%n", filePath);
			} 
			catch (DirectoryNotEmptyException x) 
			{
				System.err.format("\n%s not empty%n", filePath);
			} 
			catch (IOException x) 
			{
				System.err.println(x);
			}
			
			filePath = new File
			(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + 
			saveName + game.getPlayerManager().getPlayers().get(getPlayerIndex(name)).getInventory() + "Save");
			path = filePath.toPath();
			try 
			{
				Files.delete(path);
			} 
			catch (NoSuchFileException x) 
			{
				System.err.format("\n%s: no such" + " file or directory%n", filePath);
			} 
			catch (DirectoryNotEmptyException x) 
			{
				System.err.format("\n%s not empty%n", filePath);
			} 
			catch (IOException x) 
			{
				System.err.println(x);
			}
			
			filePath = new File
			(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "File");
			path = filePath.toPath();
			try 
			{
				Files.delete(path);
			} 
			catch (NoSuchFileException x) 
			{
				System.err.format("\n%s: no such" + " file or directory%n", filePath);
			} 
			catch (DirectoryNotEmptyException x) 
			{
				System.err.format("\n%s not empty%n", filePath);
			} 
			catch (IOException x) 
			{
				System.err.println(x);
			}
		}
		
		game.getInventoryManager().deleteInventory(players.get(getPlayerIndex(name)).getInventory());
		removeCharacter(name);
		
		if (!saveName.equals(""))
		{
			game.getSaveGame().quickSave();
		}
	}
	
	public void hurtCharacter(Player player, int damage)
	{		
		player.getMotives().setHealth(player.getMotives().getHealth() - damage);
		
		System.out.print("\n\n" + game.getCurrentPlayer().getName());
		System.out.print("\n" + game.getCurrentPlayer().getMotives().getHealth());
	}
	
	public void createCharacterDirectories()
	{
		characterDir = new File(game.getFilepath() + "Pixelocalypse\\Characters");
		
		// Creates the directory if it doesn't exist
		if (characterDir.exists() == false)
		{
			characterDir.mkdirs();
		}
		else if (characterDir.exists() == true)
		{
			System.err.format("\nDirectory " + characterDir + " already exists");
		}
		
		loadCharacterNames();
	}
	
	public void loadCharacterNames()
	{
		int itemIndex = -1;
		
		fileArray = characterDir.listFiles();	// Reads the directory and adds the file path of every file in it to the array
		for (int x = 0; x < fileArray.length; x++)	// Control loop repeats as many times as there are files
		{
			System.out.println("\n\nNew Character Name File:");
		   
			itemIndex++;
			System.out.print("Index: " + itemIndex);
		   
			characterDir = new File(fileArray[x].toString());	// Sets what file from the array is to be read
			System.out.print("\nFile Path: " + characterDir);
		    
			// Handles the reading of the attribute file
			FileReader reader;
			BufferedReader inFile;

			try
			{
				// Open the file
			    reader = new FileReader(characterDir);
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
			        
			        // Reads the values from the current line and adds them to the list
			        characterNames.add(token.nextToken());
								          
			        line = inFile.readLine();	// Reads the next line in the file
			    }
			    inFile.close();	// Closes the file        
			}
			catch (IOException e)
			{
			    	   
			}
		}// End "main for loop"
		 
		// Array print test
		System.out.print("\n\nNames Loaded:");
		for (int x = 0; x < characterNames.size(); x++)
		{
			System.out.print("\n- " + characterNames.get(x));
		}
	}
	
	public void clearUsedNames()
	{
		usedNames.clear();
	}
	
	public void addToUsedNames(String name)
	{
		usedNames.add(name);
	}
	
	public void removeFromUsedNames(String name)
	{
		int index = 0;
		
		for (int x = 0; x < usedNames.size(); x++)
		{
			if (usedNames.get(x).equals(name))
			{
				index = x;
				break;
			}
		}
		
		usedNames.remove(index);
	}
	
	public String generateName()
	{
		Random randGen = new Random();
		int randNum = 0;
		String generatedName = "";
		boolean nameGenerated = false;
		boolean nameMatched = false;
		
		while (nameGenerated == false)
		{
			randNum = randGen.nextInt(characterNames.size() - 1);
			nameMatched = false;
			
			if (usedNames.size() == 0)
			{
				generatedName = characterNames.get(randNum);
				usedNames.add(generatedName);
				nameGenerated = true;
			}
			else
			{
				for (int x = 0; x < usedNames.size(); x++)
				{
					if (characterNames.get(randNum).equals(usedNames.get(x)))
					{
						nameMatched = true;
						break;
					}
				}
				
				if (nameMatched == false)
				{
					generatedName = characterNames.get(randNum);
					usedNames.add(generatedName);
					nameGenerated = true;
				}
			}
		}
		
		return generatedName;
	}
	
	public void xpCheck()
	{
		for (int x = 0; x < getPlayers().size(); x++)
		{
			if (getPlayers().get(x).getXp() >= 1000)
			{
				levelUp(getPlayers().get(x));
			}
		}
	}
	
	public void levelUp(Player player)
	{
		player.setXp(0);
		player.setLevel(player.getLevel() + 1);
		player.setUpgradePoints(player.getUpgradePoints() + 2);
		player.getMotives().setHealth(player.getMotives().getMaxHealth());
		player.getMotives().setEnergy(player.getMotives().getMaxEnergy());
		
		game.getAudioHandler().playSound("levelUp.wav");
		new LevelUpPanel(game).showLevelUpPanel(player);
	}
}
