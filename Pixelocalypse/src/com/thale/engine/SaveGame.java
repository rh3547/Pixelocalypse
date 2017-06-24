package com.thale.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.thale.inventory.Inventory;
import com.thale.inventory.Slot;
import com.thale.player.Player;

public class SaveGame extends GameAsset 
{
	private final Game game;
	
	private File saveDir;
	private File inventorySavesDir;
	private File characterSavesDir;
	
	private String filename;
	private String saveDirStr;
	
	List<Inventory> inventories;
	List<Player> players;
	
	public SaveGame(Game game) 
	{
		super(game);
		this.game = game;
	}
	
	public void saveGame()
	{
		inventories = game.getInventoryManager().getInventories();
		players = game.getPlayerManager().getPlayers();
		
		/****************************************************************
		 * 					Set Save Location and Name
		 ***************************************************************/
		int chooserStatus;
		JFileChooser chooser = new JFileChooser(game.getFilepath() + "Pixelocalypse\\Saves");
        chooserStatus = chooser.showSaveDialog(null);
        
        if (chooserStatus == JFileChooser.APPROVE_OPTION)
        {
           // Reference the selected file
           File selectedFile = chooser.getSelectedFile();

           // Get the path of the selected file
           filename = selectedFile.getPath();
        }
        
        /****************************************************************
		 * 				  Set the Save Name and saveDir
		 ***************************************************************/
        String saveName = filename.substring(23, filename.length());
        game.setLastSave(saveName);
        
        saveDir = new File(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName);
        saveDirStr = game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName;
		
		// Creates the directory if it doesn't exist
		if (saveDir.exists() == false)
		{
			saveDir.mkdirs();
		}
		else if (saveDir.exists() == true)
		{
			System.err.format("\nDirectory " + saveDir + " already exists");
		}
        System.out.print("\n" + saveName);
        
        inventorySavesDir = new File(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories");
		characterSavesDir = new File(game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters");
		
		// Creates the directory if it doesn't exist
		if (inventorySavesDir.exists() == false)
		{
			inventorySavesDir.mkdirs();
		}
		else if (inventorySavesDir.exists() == true)
		{
			System.err.format("\nDirectory " + inventorySavesDir + " already exists");
		}
		
		// Creates the directory if it doesn't exist
		if (characterSavesDir.exists() == false)
		{
			characterSavesDir.mkdirs();
		}
		else if (characterSavesDir.exists() == true)
		{
			System.err.format("\nDirectory " + characterSavesDir + " already exists");
		}
        
         /****************************************************************
		 * 				  Get the Inventory Save Names
		 ***************************************************************/
        String[] invSaves = new String[inventories.size()];
        
        for (int x = 0; x < inventories.size(); x++)
        {
        	invSaves[x] = saveName + inventories.get(x).getName() + "Save";
        	System.out.print("\n" + invSaves[x]);
        }
        
        /****************************************************************
		 * 				  Get the Character Save Names
		 ***************************************************************/
        String characterSaves = saveName + "CharactersSave";
        
        System.out.print("\n" + characterSaves);
       
        /****************************************************************
		 * 	Write Primary Save File with All Sub-Save File Paths Inside
		 ***************************************************************/
        String data = "";
        String sp = " ";
        FileWriter writer;
        PrintWriter outputFile;
        FileWriter writer2;
        PrintWriter outputFile2;

        try
        {
           // Open the file
           writer = new FileWriter(filename + "File");
           outputFile = new PrintWriter(writer);

           // Write the inventory save paths
           for (int x = 0; x < invSaves.length; x++)
           {
        	   data = data + "Inventory " + invSaves[x] + "\n";
           }
           
           // Write the character save paths
           data = data + "Character " + characterSaves + "\n";
           
           // Write the current character
           data = data + "CurrentCharacter " + game.getCurrentPlayer().getName() + "\n";
           
           outputFile.print(data);
           
           System.out.print("\n\n" + filename);

           // Close the file
           outputFile.close();
        }
        catch (IOException e)
        {
        	
        }
        
        /****************************************************************
		 * 				Write All the Inventory Save Files
		 ***************************************************************/        
        for (int x = 0; x < invSaves.length; x++)
        {
        	data = "";
        	
            try
            {
               // Open the file
               writer = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + invSaves[x]);
               outputFile = new PrintWriter(writer);
               
               Slot[] slots = inventories.get(x).getSlots();
               
               for (int i = 0; i < slots.length; i++)
               {
            	   data = data + 
            	   inventories.get(x).getName() + sp +
            	   Integer.toString(inventories.get(x).getNumSlots()) + sp +
            	   Integer.toString(slots[i].getSlotNum()) + sp +
            	   slots[i].getSlotItem().getName() + sp +
            	   Integer.toString(slots[i].getSlotStack()) + "\n";
               }
               
               outputFile.print(data);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + invSaves[x]);

               // Close the file
               outputFile.close();
            }
            catch (IOException e)
            {
            	
            }
        }
        
        /****************************************************************
		 * 				Write All the Character Save Files
		 ***************************************************************/        
        for (int x = 0; x < 1; x++)
        {
        	data = "";
        	
            try
            {
               // Open the file
               writer = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves);
               outputFile = new PrintWriter(writer);
               
               for (int i = 0; i < players.size(); i++)
               {
            	   data = data + 
            	   players.get(i).getName() + sp +
            	   saveName + players.get(i).getName() + "Stats" + sp +
            	   players.get(i).getLevel() + sp +
            	   players.get(i).getXp() + sp +
            	   players.get(i).getInventory() + sp +
            	   players.get(i).getLocation() + sp +
            	   players.get(i).getUpgradePoints() + sp +
            	   players.get(i).getTimesLooted() + "\n";
            	   
            	   String data2 = "";
               	
                   try
                   {
                      // Open the file
                      writer2 = new FileWriter
                      (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(i).getName() + "Stats");
                      outputFile2 = new PrintWriter(writer2);
                      
                      data2 = data2 +
                    		  players.get(i).getMotives().getHealth() + sp +
                    		  players.get(i).getMotives().getMaxHealth() + sp +
                    		  players.get(i).getMotives().getEnergy() + sp +
                    		  players.get(i).getMotives().getMaxEnergy() + sp +
                    		  players.get(i).getMotives().getHunger() + sp +
                    		  players.get(i).getMotives().getThirst() + sp +
                    		  players.get(i).getMotives().getMorale() + sp +
                    		  players.get(i).getMotives().getMaxMorale() + sp +
                    		  
                    		  players.get(i).getAttributes().getStrength() + sp +
                    		  players.get(i).getAttributes().getConstitution() + sp +
                    		  players.get(i).getAttributes().getAgility() + sp +
                    		  players.get(i).getAttributes().getIntelligence() + sp +
                    		  players.get(i).getAttributes().getCharisma() + sp +
                    		  
                    		  players.get(i).getSkills().getLooting() + sp +
                    		  players.get(i).getLocations()[0] + sp +
                    		  players.get(i).getLocations()[1] + sp +
                    		  players.get(i).getLocations()[2] + sp +
                    		  players.get(i).getLocations()[3];
                      
                      outputFile2.print(data2);
                      
                      System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(i).getName() + "Stats");

                      // Close the file
                      outputFile2.close();
                   }
                   catch (IOException e)
                   {
                   	
                   }
                   
                   data2 = "";
                  	
                   try
                   {
                      // Open the file
                      writer2 = new FileWriter
                      (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(i).getName() + "Scene");
                      outputFile2 = new PrintWriter(writer2);
                      
                      data2 = data2 +
                    		  players.get(i).getScene().getPlayer().getName() + sp +
                    		  players.get(i).getScene().getBgImageStr();
                      
                      for (int j = 0; j < players.get(i).getScene().getSprites().size(); j++)
                      {
                    	  data2 = data2 + "\n" + "Sprite " +
                        		  players.get(i).getScene().getSprites().get(j).getName() + sp +
                        		  players.get(i).getScene().getSprites().get(j).getPosX() + sp +
                        		  players.get(i).getScene().getSprites().get(j).getPosY() + sp +
                        		  players.get(i).getScene().getSprites().get(j).getImageStr();
                      }
                      
                      outputFile2.print(data2);
                      
                      System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(i).getName() + "Scene");

                      // Close the file
                      outputFile2.close();
                   }
                   catch (IOException e)
                   {
                   	
                   }
               }
               
               outputFile.print(data);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves);

               // Close the file
               outputFile.close();
            }
            catch (IOException e)
            {
            	
            }
        }
	}
	
	public void quickSave()
	{
		String saveName = game.getLastSave();
		filename = game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName;
		
		inventories = game.getInventoryManager().getInventories();
		players = game.getPlayerManager().getPlayers();
		
		 /****************************************************************
		 * 				  Get the Inventory Save Names
		 ***************************************************************/
        String[] invSaves = new String[inventories.size()];
        
        for (int x = 0; x < inventories.size(); x++)
        {
        	invSaves[x] = saveName + inventories.get(x).getName() + "Save";
        	System.out.print("\n" + invSaves[x]);
        }
        
        /****************************************************************
		 * 				  Get the Character Save Names
		 ***************************************************************/
        String characterSaves = saveName + "CharactersSave";
        
        System.out.print("\n" + characterSaves);
        
        /****************************************************************
		 * 	Write Primary Save File with All Sub-Save File Paths Inside
		 ***************************************************************/
        String data = "";
        String sp = " ";
        FileWriter writer;
        PrintWriter outputFile;
        FileWriter writer2;
        PrintWriter outputFile2;

        try
        {
           // Open the file
           writer = new FileWriter(filename + "File");
           outputFile = new PrintWriter(writer);

           // Write the inventory save paths
           for (int x = 0; x < invSaves.length; x++)
           {
        	   data = data + "Inventory " + invSaves[x] + "\n";
           }
           
           // Write the character save paths
           data = data + "Character " + characterSaves + "\n";
           
           // Write the current character
           data = data + "CurrentCharacter " + game.getCurrentPlayer().getName() + "\n";
           
           outputFile.print(data);
           
           System.out.print("\n\n" + filename);

           // Close the file
           outputFile.close();
        }
        catch (IOException e)
        {
        	
        }
        
        /****************************************************************
		 * 				Write All the Inventory Save Files
		 ***************************************************************/        
        for (int x = 0; x < invSaves.length; x++)
        {
        	data = "";
        	
            try
            {
               // Open the file
               writer = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + invSaves[x]);
               outputFile = new PrintWriter(writer);
               
               Slot[] slots = inventories.get(x).getSlots();
               
               for (int i = 0; i < slots.length; i++)
               {
            	   data = data + 
            	   inventories.get(x).getName() + sp +
            	   Integer.toString(inventories.get(x).getNumSlots()) + sp +
            	   Integer.toString(slots[i].getSlotNum()) + sp +
            	   slots[i].getSlotItem().getName() + sp +
            	   Integer.toString(slots[i].getSlotStack()) + "\n";
               }
               
               outputFile.print(data);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Inventories\\" + invSaves[x]);

               // Close the file
               outputFile.close();
            }
            catch (IOException e)
            {
            	
            }
        }
        
        /****************************************************************
		 * 				Write All the Character Save Files
		 ***************************************************************/        
        for (int x = 0; x < 1; x++)
        {
        	data = "";
        	
            try
            {
               // Open the file
               writer = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves);
               outputFile = new PrintWriter(writer);
               
               for (int i = 0; i < players.size(); i++)
               {
            	   data = data + 
            	   players.get(i).getName() + sp +
            	   saveName + players.get(i).getName() + "Stats" + sp +
            	   players.get(i).getLevel() + sp +
            	   players.get(i).getXp() + sp +
            	   players.get(i).getInventory() + sp +
            	   players.get(i).getLocation() + sp +
            	   players.get(i).getUpgradePoints() + sp +
            	   players.get(i).getTimesLooted() + "\n";
               }
               
               outputFile.print(data);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + characterSaves);

               // Close the file
               outputFile.close();
            }
            catch (IOException e)
            {
            	
            }
        }
        
        for (int y = 0; y < players.size(); y++)
        {
        	String data2 = "";
           	
            try
            {
               // Open the file
               writer2 = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(y).getName() + "Stats");
               outputFile2 = new PrintWriter(writer2);
               
               data2 = data2 +
             		  players.get(y).getMotives().getHealth() + sp +
             		  players.get(y).getMotives().getMaxHealth() + sp +
             		  players.get(y).getMotives().getEnergy() + sp +
             		  players.get(y).getMotives().getMaxEnergy() + sp +
             		  players.get(y).getMotives().getHunger() + sp +
             		  players.get(y).getMotives().getThirst() + sp +
             		  players.get(y).getMotives().getMorale() + sp +
             		  players.get(y).getMotives().getMaxMorale() + sp +
             		  
             		  players.get(y).getAttributes().getStrength() + sp +
             		  players.get(y).getAttributes().getConstitution() + sp +
             		  players.get(y).getAttributes().getAgility() + sp +
             		  players.get(y).getAttributes().getIntelligence() + sp +
             		  players.get(y).getAttributes().getCharisma() + sp +
             		  
             		  players.get(y).getSkills().getLooting() + sp +
            		  players.get(y).getLocations()[0] + sp +
            		  players.get(y).getLocations()[1] + sp +
            		  players.get(y).getLocations()[2] + sp +
            		  players.get(y).getLocations()[3];
               
               outputFile2.print(data2);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(y).getName() + "Stats");

               // Close the file
               outputFile2.close();
            }
            catch (IOException e)
            {
            	
            }
            
            data2 = "";
          	
            try
            {
               // Open the file
               writer2 = new FileWriter
               (game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(y).getName() + "Scene");
               outputFile2 = new PrintWriter(writer2);
               
               data2 = data2 +
             		  players.get(y).getScene().getPlayer().getName() + sp +
             		  players.get(y).getScene().getBgImageStr();
               
               for (int j = 0; j < players.get(y).getScene().getSprites().size(); j++)
               {
            	   data2 = data2 + "\n" + "Sprite " +
                 		  players.get(y).getScene().getSprites().get(j).getName() + sp +
                 		  players.get(y).getScene().getSprites().get(j).getPosX() + sp +
                 		  players.get(y).getScene().getSprites().get(j).getPosY() + sp +
                 		  players.get(y).getScene().getSprites().get(j).getImageStr();
               }
               
               outputFile2.print(data2);
               
               System.out.print("\n\n" + game.getFilepath() + "Pixelocalypse\\Saves\\" + saveName + "\\" + "Characters\\" + saveName + players.get(y).getName() + "Scene");

               // Close the file
               outputFile2.close();
            }
            catch (IOException e)
            {
            	
            }
        }
	}
}
