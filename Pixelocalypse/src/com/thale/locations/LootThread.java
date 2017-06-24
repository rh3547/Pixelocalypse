package com.thale.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.Item;
import com.thale.player.Player;

public class LootThread extends GameAsset implements Runnable
{
	private Game game;
	private Player player;
	private Item[] lootItems;
	private int[] lootAmount;
	private int timesToLoot;
	private int lootWait;
	private int index;
	
	public LootThread(Game game, Player player, int index)
	{
		super(game);
		this.game = game;
		this.player = player;
		this.index = index;
	}

	@Override
	public void run()
	{
		System.out.print("\n\n" + player.getName() + " has started looting.");
		
		if (player.getSkills().getLooting() == 1)
		{
			timesToLoot = 1;
		}
		else if (player.getSkills().getLooting() <= 3)
		{
			timesToLoot = 2;
		}
		else if (player.getSkills().getLooting() <= 5)
		{
			timesToLoot = 3;
		}
		else if (player.getSkills().getLooting() <= 7)
		{
			timesToLoot = 4;
		}
		else if (player.getSkills().getLooting() <= 9)
		{
			timesToLoot = 5;
		}
		else
		{
			timesToLoot = player.getSkills().getLooting() - 4;
		}
		
		switch (player.getSkills().getLooting())
		{
			case 1:
				lootWait = game.getLootWait();
				break;
				
			case 2:
				lootWait = game.getLootWait() - 1;
				break;
				
			case 3:
				lootWait = game.getLootWait() - 3;
				break;
				
			case 4:
				lootWait = game.getLootWait() - 5;
				break;
		
			case 5:
				lootWait = game.getLootWait() - 6;
				break;
				
			case 6:
				lootWait = game.getLootWait() - 8;
				break;
				
			case 7:
				lootWait = game.getLootWait() - 10;
				break;
				
			case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				break;
		}
		
		lootItems  = new Item[timesToLoot];
		lootAmount = new int[timesToLoot];
		
		lootItems();
		lootAmount(lootItems);
		
		try
		{
			Thread.sleep(lootWait * 1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		for (int y = 0; y < lootItems.length; y++)
		{
			game.getInventoryManager().addToInventory(player.getInventory(), lootItems[y], lootAmount[y]);
		}
		
		player.increaseXp(100);
		
		game.getLocationManager().removeFromLootingPlayers(player);
		
		player.getMotives().setEnergy(player.getMotives().getEnergy() - 1);
		
		try
		{
			player.getScene().remove(player.getScene().getSprites().get(index));
			player.getScene().getSprites().remove(index);
		}
		catch (Exception e)
		{
			
		}
		
		System.out.print("\n\n" + player.getName() + " has finished looting.");
	}
	
	public void lootItems()
	{
		String loot;
		Item itemLooted;
		
		for (int x = 0; x < timesToLoot; x++)
		{
			loot = loot();
			itemLooted = game.getItemManager().getLootItems(loot);
			
			lootItems[x] = itemLooted;
			
			System.out.print("\n\nItem Looted: ");
			System.out.print("\n" + itemLooted.getName() + ":");
		}
	}
	
	public int[] lootAmount(Item[] itemsLooted)
	{
		Random randGen = new Random();
		int minLoot = 0;
		int maxLoot = 0;
		
		System.out.print("\n\nAmount Looted: ");
		
		for (int x = 0; x < itemsLooted.length; x++)
		{
			minLoot = itemsLooted[x].getMinLoot();
			maxLoot = itemsLooted[x].getMaxLoot();
			
			if (maxLoot == 1)
			{
				lootAmount[x] = 1;
				
				System.out.print("\n" + itemsLooted[x].getName() + ":");
				System.out.print("\n- " + lootAmount[x]);
			}
			else
			{
				lootAmount[x] = minLoot + randGen.nextInt(maxLoot - minLoot);
				
				System.out.print("\n" + itemsLooted[x].getName() + ":");
				System.out.print("\n- " + lootAmount[x]);
			}
		}
		
		return lootAmount;
	}
	
	public String loot()
	{
		Location currentLoc = game.getCurrentLocation();
		String[] loot = currentLoc.getLoot();
		int[] lootChance = currentLoc.getLootChance();
		Random randGen = new Random();
		List<Integer> randNums = new ArrayList<Integer>();
		List<Integer> randTotalNums = new ArrayList<Integer>();
		int rarityNum = 0;
		boolean newNum = true;
		int randTotalNumsSize = 0;
		List<String> possibleItems = new ArrayList<String>();
		int randItem = 0;
		String chosenItem;
		
		for (int x = 0; x < loot.length; x++)
		{
			if (rarityNum == 0)
			{
				rarityNum = lootChance[x];
				
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
					if (randTotalNums.get(y) == lootChance[x])
					{
						newNum = false;
						break;
					}
				}
				
				if (newNum == true)
				{
					rarityNum = lootChance[x];
					
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
		System.out.print("\nLocation: " + currentLoc.getName());
		System.out.print("\nPossible Rarities: ");
		for (int x = 0; x < randTotalNums.size(); x++)
		{
			System.out.print("\n- " + randTotalNums.get(x));
		}
		
		rarityNum = randNums.get(randGen.nextInt((randNums.size() - 1)));
		
		for (int x = 0; x < loot.length; x++)
		{
			if (lootChance[x] == rarityNum)
			{
				possibleItems.add(loot[x]);
			}
		}
		
		// Test Output 2
		System.out.print("\n");
		System.out.print("\nRarity Chosen: " + rarityNum);
		System.out.print("\nItems With Matching Rarity: ");
		for (int x = 0; x < possibleItems.size(); x++)
		{
			System.out.print("\n- " + possibleItems.get(x));
		}
		
		if (possibleItems.size() == 1)
		{
			chosenItem = possibleItems.get(0);
		}
		else
		{
			randItem = randGen.nextInt((possibleItems.size()));
			chosenItem = possibleItems.get(randItem);
		}
		
		// Test Output 3
		System.out.print("\n");
		System.out.print("\nChosen Item: " + possibleItems.get(randItem));
		
		randNums.clear();
		randTotalNums.clear();
		possibleItems.clear();
		
		return chosenItem;
	}
}
