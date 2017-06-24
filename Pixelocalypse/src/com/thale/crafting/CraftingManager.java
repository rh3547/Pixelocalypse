package com.thale.crafting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.Item;
import com.thale.main.ScreenMainMenu;

public class CraftingManager extends GameAsset
{
	Game game;
	
	public List<Recipe> recipes = new ArrayList<Recipe>();
	
	private File craftingDir;
	private File craftingFile;
	private File[] fileArray;
	
	public CraftingManager(Game game)
	{
		super(game);
		this.game = game;
	}
	
	public void createCraftingDirectories()
	{
		craftingDir = new File(game.getFilepath() + "Pixelocalypse\\Crafting");
		
		// Creates the directory if it doesn't exist
		if (craftingDir.exists() == false)
		{
			craftingDir.mkdirs();
		}
		else if (craftingDir.exists() == true)
		{
			System.err.format("\nDirectory " + craftingDir + " already exists");
		}
		
		loadRecipes();
	}
	
	public void loadRecipes()
	{	
		String name = "";
		Item item;
		int amount;
		
		List<Item> inputItems = new ArrayList<Item>();
		Item[] itemInput;
		List<Integer> inputAmounts = new ArrayList<Integer>();
		int[] amountInput;
		
		List<Item> outputItems = new ArrayList<Item>();
		Item[] itemOutput;
		List<Integer> outputAmounts = new ArrayList<Integer>();
		int[] amountOutput;
		
		String readToken;
		
		int itemIndex = -1;	// Increment variable for creating the Item list 

		fileArray = craftingDir.listFiles();	// Reads the directory and adds the file path of every file in it to the array
		for (int x = 0; x < fileArray.length; x++)	// Control loop repeats as many times as there are files
		{
			System.out.println("\n\nNew Recipe File:");
		   
			itemIndex++;
			System.out.print("Index: " + itemIndex);
		   
			craftingFile = new File(fileArray[x].toString());	// Sets what file from the array is to be read
			System.out.print("\nFile Path: " + craftingFile);
		    
			// Handles the reading of the item file
			FileReader reader;
			BufferedReader inFile;

			try
			{
				// Open the file
			    reader = new FileReader(craftingFile);
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
			        readToken = token.nextToken();
			        
			        if (readToken.equals("Name:"))
			        {
			        	name = token.nextToken();
			        }
			        else if (readToken.equals("Input:"))
			        {
			        	readToken = token.nextToken();
			        	
			        	while (!readToken.equals("EndInput"))
			        	{
			        		item = game.getItemManager().getItem(readToken);
			        		amount = Integer.parseInt(token.nextToken());
			        		inputItems.add(item);
			        		inputAmounts.add(amount);
			        		
			        		readToken = token.nextToken();
			        	}
			        }
			        else if (readToken.equals("Output:"))
			        {
			        	readToken = token.nextToken();
			        	
			        	while (!readToken.equals("EndOutput"))
			        	{
			        		item = game.getItemManager().getItem(readToken);
			        		amount = Integer.parseInt(token.nextToken());
			        		outputItems.add(item);
			        		outputAmounts.add(amount);
			        		
			        		readToken = token.nextToken();
			        	}
			        }
								          
			        line = inFile.readLine();	// Reads the next line in the file
			    }
			    inFile.close();	// Closes the file        
			}
			catch (IOException e)
			{
			    	   
			}
			itemInput = new Item[inputItems.size()];
			for (int i = 0; i < inputItems.size(); i++)
			{
				itemInput[i] = inputItems.get(i);
			}
			amountInput = new int[inputAmounts.size()];
			for (int i = 0; i < inputAmounts.size(); i++)
			{
				amountInput[i] = inputAmounts.get(i);
			}
			
			itemOutput = new Item[outputItems.size()];
			for (int i = 0; i < outputItems.size(); i++)
			{
				itemOutput[i] = outputItems.get(i);
			}
			amountOutput = new int[outputAmounts.size()];
			for (int i = 0; i < outputAmounts.size(); i++)
			{
				amountOutput[i] = outputAmounts.get(i);
			}
			
			recipes.add(new Recipe(name, itemInput, amountInput, itemOutput, amountOutput));
			
			System.out.print("\nRecipe Name: " + name);
			
			inputItems.clear();
			inputAmounts.clear();
			outputItems.clear();
			outputAmounts.clear();

		   }// End "main for loop"
		 
		// Array print test
		System.out.print("\n\nRecipes Loaded:");
		for (int x = 0; x < recipes.size(); x++)
		{
			System.out.print("\n\n- " + recipes.get(x).getName());
			System.out.print("\nInput:");
			
			for (int y = 0; y < recipes.get(x).getRecipeInput().length; y++)
			{
				System.out.print(" " + recipes.get(x).getRecipeInput()[y].getName() + " " +  recipes.get(x).getInputAmounts()[y]);
			}
			
			System.out.print("\nOutput:");
			
			for (int y = 0; y < recipes.get(x).getRecipeOutput().length; y++)
			{
				System.out.print(" " + recipes.get(x).getRecipeOutput()[y].getName() + " " + recipes.get(x).getOutputAmounts()[y]);
			}
		}
		createCraftingInventories();
		
		game.setGameLoaded(true, new ScreenMainMenu(game.getScreenHandler()));
	}
	
	public void createCraftingInventories()
	{
		game.getInventoryManager().createNewInventory("Crafting-Input", 25);
		game.getInventoryManager().createNewInventory("Crafting-Output", 25);
	}
	
	public void checkRecipe()
	{
		System.out.print("\n\nStart Craft Check");
		
		int recipeNum = -1;
		int inputItemNum = -1;
		int recipeItemNum = -1;
		
		boolean craftSuccess = false;
		
		List<Item> inputItems = new ArrayList<Item>();
		List<Integer> inputAmounts = new ArrayList<Integer>();
		List<Item> matchedItems = new ArrayList<Item>();
		List<Integer> matchedAmounts = new ArrayList<Integer>();
		
		for (int x = 0; x < game.getInventoryManager().getInventory("Crafting-Input").getNumSlots(); x++)
		{
			if (!game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem().getName().equals("Null"))
			{
				inputItems.add(game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem());
				inputAmounts.add(game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotStack());
				
				System.out.print(" " + inputAmounts.get(x) + " " + inputItems.get(x).getName());
			}
		}
		
		while (recipeNum < recipes.size() - 1)
		{
			recipeNum++;
			
			System.out.print("\nChecking Recipe: " + recipes.get(recipeNum).getName());
			
			matchedItems.clear();
			matchedAmounts.clear();
			inputItemNum = -1;
			while (inputItemNum < inputItems.size() - 1)
			{
				inputItemNum++;
				
				System.out.print("\nLooking For: " + inputAmounts.get(inputItemNum) + " " + inputItems.get(inputItemNum).getName());
				
				recipeItemNum = -1;
				while (recipeItemNum < recipes.get(recipeNum).getRecipeInput().length - 1)
				{
					recipeItemNum++;
					
					System.out.print("\nFound: " + recipes.get(recipeNum).getInputAmounts()[recipeItemNum] + " " + 
									 recipes.get(recipeNum).getRecipeInput()[recipeItemNum].getName());
					
					if (recipes.get(recipeNum).getRecipeInput()[recipeItemNum].getName().equals(inputItems.get(inputItemNum).getName()))
					{
						if (recipes.get(recipeNum).getInputAmounts()[recipeItemNum] == inputAmounts.get(inputItemNum))
						{
							System.out.print("\nItems Match");
							
							matchedItems.add(recipes.get(recipeNum).getRecipeInput()[recipeItemNum]);
							matchedAmounts.add(recipes.get(recipeNum).getInputAmounts()[recipeItemNum]);
							
							break;
						}
					}
					else
					{
						System.out.print("\nItems Don't Match");
					}
				}
				
				if (matchedItems.size() == recipes.get(recipeNum).getRecipeInput().length || matchedItems.size() == 0)
				{
					break;
				}
			}
			
			if (matchedItems.size() == recipes.get(recipeNum).getRecipeInput().length)
			{
				craftSuccess = true;
				break;
			}
		}
		
		if (craftSuccess == true)
		{
			System.out.print("\nSuccess");
			
			game.getInventoryManager().clearInventory("Crafting-Output");
			
			System.out.print("\nOutput:");
			
			for (int x = 0; x < recipes.get(recipeNum).getRecipeOutput().length; x++)
			{
				System.out.print("\n" + recipes.get(recipeNum).getOutputAmounts()[x] + " " + recipes.get(recipeNum).getRecipeOutput()[x].getName());
				
				game.getInventoryManager().addToInventory("Crafting-Output", recipes.get(recipeNum).getRecipeOutput()[x], 
														  recipes.get(recipeNum).getOutputAmounts()[x]);
			}
		}
		else
		{
			System.out.print("\nFailure");
			
			game.getInventoryManager().clearInventory("Crafting-Output");
		}
		
		inputItems.clear();
		inputAmounts.clear();
		matchedItems.clear();
		matchedAmounts.clear();
		
		System.out.print("\nEnd Craft Check");
	}
}
