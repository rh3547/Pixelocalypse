package com.thale.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

import com.thale.inventory.Inventory;
import com.thale.player.Attributes;
import com.thale.player.Motives;
import com.thale.player.Player;
import com.thale.player.Skills;
import com.thale.scene.Scene;
import com.thale.sprite.Sprite;

public class LoadGame extends GameAsset
{
	private final Game game;
	
	private String filename = "";
	private String saveName = "";
	private String line;
	private List<String> inventorySaves = new ArrayList<String>();
	private List<String> characterSaves = new ArrayList<String>();
	
	private List<Inventory> inventories = new ArrayList<Inventory>();
	private String invName;
	private int invNumSlots;
	private int slotNum;
	private String itemName;
	private int slotStack;
	private boolean invAdded = false;
	
	private List<Player> players = new ArrayList<Player>();
	private String currentCharacterName;
	private int playerCount = 0;
	private String name;
	private String statsPath;
	private int level;
	private int xp;
	private String inventory;
	private String location;
	private int upgradePoints;
	private int timesLooted;
	
	private int health;
	private int maxHealth;
	private int energy;
	private int maxEnergy;
	private int hunger;
	private int thirst;
	private int morale;
	private int maxMorale;
	
	private int strength;
	private int constitution;
	private int agility;
	private int intelligence;
	private int charisma;
	
	private int looting;
	
	private String loc1 = "";
	private String loc2 = "";
	private String loc3 = "";
	private String loc4 = "";
	
	private Scene scene;
	private Player scenePlayer;
	private String bgImageStr = "";
	private String spriteName;
	private int spritePosX;
	private int spritePosY;
	private String imageStr;
	
	public LoadGame(Game game) 
	{
		super(game);
		this.game = game;
	}
	
	public void loadGame()
	{
		/****************************************************************
		 * 					Select Which Game to Load
		 ***************************************************************/
		int chooserStatus;
        JFileChooser chooser = new JFileChooser(game.getFilepath() + "Pixelocalypse\\Saves");
        chooserStatus = chooser.showOpenDialog(null);
        if (chooserStatus == JFileChooser.APPROVE_OPTION)
        {
           // Reference the selected file
           File selectedFile = chooser.getSelectedFile();

           // Get the path of the selected file
           filename = selectedFile.getPath();
           
           game.getPlayerManager().getPlayers().clear();
   		   game.getPlayerManager().clearUsedNames();
   		   game.getInventoryManager().getInventories().clear();
   		   
   		   saveName = filename.substring(23, (filename.length() - 4));
   		   game.setLastSave(saveName);
   		   System.out.print("\n" + saveName);
        }
        
        if (!filename.equals(""))
        {
        /****************************************************************
		 * 				Open and Read the Primary Save File
		 ***************************************************************/
        game.getSceneManager().hideScene(game.getCurrentPlayer());	
        	
        String inputLine;
        FileReader reader;
        BufferedReader inFile;
        String currentToken;

        try
        {
           // Open the file
           reader = new FileReader(filename);
           inFile = new BufferedReader(reader);
           
           line = inFile.readLine();
			
           /**
           * Loop continues to read the file
           * until it detects that there
           * is no more in the file to read.
           */
           while(line != null)
           {	
              //StringTokenizer detects white spaces
           	  StringTokenizer token = new StringTokenizer(line);
           	  
           	  currentToken = token.nextToken();
           	  
           	  if (currentToken.equals("Inventory"))
           	  {
           		  inventorySaves.add(token.nextToken());
                
           		  for (int x = 0; x < inventorySaves.size(); x++)
           		  {
           			  System.out.print("\n" + inventorySaves.get(x));
           		  } 
           	  }
           	  else if (currentToken.equals("Character"))
           	  {
           		  characterSaves.add(token.nextToken());
                
           		  for (int x = 0; x < characterSaves.size(); x++)
           		  {
           			  System.out.print("\n" + characterSaves.get(x));
           		  }  
           	  }
           	  else if (currentToken.equals("CurrentCharacter"))
           	  {
           		  currentCharacterName = token.nextToken();
           	  }
                     
              line = inFile.readLine();
           }
           
           inFile.close();         
        }
        catch (IOException e)
        {
        	
        }
        
        /****************************************************************
		 * 			Open and Read All the Inventory Save Files
		 ***************************************************************/
        for (int x = 0; x < inventorySaves.size(); x++)
        {
        	try
            {
               // Open the file
               reader = new FileReader(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + inventorySaves.get(x));
               inFile = new BufferedReader(reader);
               
               System.out.print("\n" + game.getFilepath() +  "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + inventorySaves.get(x));
               
               line = inFile.readLine();
    			
               /**
               * Loop continues to read the file
               * until it detects that there
               * is no more in the file to read.
               */
               while(line != null)
               {	
                  //StringTokenizer detects white spaces
               	  StringTokenizer token = new StringTokenizer(line);
    			
               	  invName = token.nextToken();
               	  invNumSlots = Integer.parseInt(token.nextToken());
               	  
               	  slotNum = Integer.parseInt(token.nextToken());
               	  itemName =  token.nextToken();
               	  slotStack = Integer.parseInt(token.nextToken());
               	  
               	  if (invAdded == false)
               	  {
               		  game.getInventoryManager().createNewInventory(invName, invNumSlots);
               		  System.out.print("\n" + "Added Inventory: " + invName); 
               		
               		  invAdded = true;
               	  }
               
               	  game.getInventoryManager().addToSlot
               	  (invName, slotNum, game.getItemManager().getItem(itemName), slotStack);
                         
                  line = inFile.readLine();
               }
               
               inFile.close(); 
               System.out.print("\n" + "Finished Loading: " + inventorySaves.get(x));
               
               invAdded = false;
            }
            catch (IOException e)
            {
            	
            }
        }
        
        /****************************************************************
		 * 			Open and Read All the Character Save Files
		 ***************************************************************/
        game.getPlayerManager().getPlayers().clear();
        
        for (int x = 0; x < characterSaves.size(); x++)
        {
        	playerCount = 0;
        	
        	try
            {
               // Open the file
               reader = new FileReader(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves.get(x));
               inFile = new BufferedReader(reader);
               
               System.out.print("\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves.get(x));
               
               line = inFile.readLine();
    			
               /**
               * Loop continues to read the file
               * until it detects that there
               * is no more in the file to read.
               */
               while(line != null)
               {	
                  //StringTokenizer detects white spaces
               	  StringTokenizer token = new StringTokenizer(line);
    			
               	  name = token.nextToken();
               	  statsPath = token.nextToken();
               	  level = Integer.parseInt(token.nextToken());
               	  xp = Integer.parseInt(token.nextToken());
               	  inventory = token.nextToken();
               	  location = token.nextToken();
               	  upgradePoints = Integer.parseInt(token.nextToken());
               	  timesLooted = Integer.parseInt(token.nextToken());
               	  
               	String inputLine2;
                FileReader reader2;
                BufferedReader inFile2;
                String line2;

                try
                {
                   // Open the file
                   reader2 = new FileReader(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + statsPath);
                   inFile2 = new BufferedReader(reader2);
                   
                   line2 = inFile2.readLine();
        			
                   /**
                   * Loop continues to read the file
                   * until it detects that there
                   * is no more in the file to read.
                   */
                   while(line2 != null)
                   {	
                      //StringTokenizer detects white spaces
                   	  StringTokenizer token2 = new StringTokenizer(line2);
                   	        
                   	  health = Integer.parseInt(token2.nextToken());
                   	  maxHealth = Integer.parseInt(token2.nextToken());
                   	  energy = Integer.parseInt(token2.nextToken());
                   	  maxEnergy = Integer.parseInt(token2.nextToken());
                   	  hunger = Integer.parseInt(token2.nextToken());
                   	  thirst = Integer.parseInt(token2.nextToken());
                   	  morale = Integer.parseInt(token2.nextToken());
                   	  maxMorale = Integer.parseInt(token2.nextToken());
                   	  
                   	  strength = Integer.parseInt(token2.nextToken());
                   	  constitution = Integer.parseInt(token2.nextToken());
                   	  agility = Integer.parseInt(token2.nextToken());
                   	  intelligence = Integer.parseInt(token2.nextToken());
                   	  charisma = Integer.parseInt(token2.nextToken());
                   	  
                   	  looting = Integer.parseInt(token2.nextToken());
                   	  
                   	  loc1 = token2.nextToken();
                   	  loc2 = token2.nextToken();
                   	  loc3 = token2.nextToken();
                   	  loc4 = token2.nextToken();
                             
                      line2 = inFile2.readLine();
                   }
                   
                   inFile2.close();         
                }
                catch (IOException e)
                {
                	
                }
                
                game.getPlayerManager().getPlayers().add(new Player(name, level, xp, inventory, location, 
                		new Scene(game, null, "src/res/scenes/" + location + "Scene.png", new ArrayList<Sprite>()),
            			new Motives(health, maxHealth, energy, maxEnergy, hunger, thirst, morale, maxMorale), 
            			new Attributes(strength, constitution, agility, intelligence, charisma), 
            			new Skills(looting), upgradePoints, timesLooted, new String[4]));
                
                game.getPlayerManager().getPlayer(name).getLocations()[0] = loc1;
                game.getPlayerManager().getPlayer(name).getLocations()[1] = loc2;
                game.getPlayerManager().getPlayer(name).getLocations()[2] = loc3;
                game.getPlayerManager().getPlayer(name).getLocations()[3] = loc4;
                
                try
                {
                   // Open the file
                   reader2 = new FileReader(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + name + "Scene");
                   inFile2 = new BufferedReader(reader2);
                   String currentToken2;
                   
                   line2 = inFile2.readLine();
        			
                   /**
                   * Loop continues to read the file
                   * until it detects that there
                   * is no more in the file to read.
                   */
                   while(line2 != null)
                   {	
                      //StringTokenizer detects white spaces
                   	  StringTokenizer token2 = new StringTokenizer(line2);
                   	   
                   	  currentToken2 = token2.nextToken();
                   	  
                   	  if (currentToken2.equals("Sprite"))
                   	  {
                   		  spriteName = token2.nextToken();
                   		  spritePosX = Integer.parseInt(token2.nextToken());
                   		  spritePosY = Integer.parseInt(token2.nextToken());
                   		  imageStr = token2.nextToken();
                   		  
                   		  game.getPlayerManager().getPlayer(name).getScene().addToSprites(new Sprite(spriteName, spritePosX, spritePosY, imageStr));
                   	  }
                   	  else
                   	  {
                   		 bgImageStr = token2.nextToken();
                   	  }
                             
                      line2 = inFile2.readLine();
                   }
                   
                   inFile2.close();         
                }
                catch (IOException e)
                {
                	
                }
                
                game.getPlayerManager().getPlayer(name).getScene().setPlayer(game.getPlayerManager().getPlayer(name));
                
                line = inFile.readLine();
               }
               
               inFile.close();
               
               game.setCurrentPlayer(game.getPlayerManager().getPlayer(currentCharacterName));
               game.getSceneManager().showScene(game.getCurrentPlayer());
               game.getCurrentPlayer().getScene().resetBgImage();
               
               game.getPlayerManager().clearUsedNames();
               for (int k = 0; k < game.getPlayerManager().getPlayers().size(); k++)
               {
            	   game.getPlayerManager().addToUsedNames(game.getPlayerManager().getPlayers().get(k).getName()); 
               }
               
               System.out.print("\n" + "Finished Loading: " + characterSaves.get(x));
            }
            catch (IOException e)
            {
            	
            }
        }
	}
	}
}
