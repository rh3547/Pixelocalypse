package com.thale.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.player.Player;

public class ExploreThread extends GameAsset implements Runnable
{
	private Game game;
	private Player player;
	private List<Location> locations;
	
	public ExploreThread(Game game, Player player)
	{
		super(game);
		this.game = game;
		this.player = player;
		locations = game.getLocationManager().getLocations();
	}
	
	@Override
	public void run()
	{
		System.out.print("\n\n" + player.getName() + " has started exploring.");
		
		try
		{
			Thread.sleep(game.getExploreWait() * 1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		player.increaseXp(50);
		
		player.getLocations()[0] = explore().getName();
		player.getLocations()[1] = explore().getName();
		player.getLocations()[2] = explore().getName();
		player.getLocations()[3] = explore().getName();
		
		game.getLocationManager().removeFromExploringPlayers(player);
		
		System.out.print("\n\n" + player.getName() + " has finished exploring.");
	}
	
	public Location explore()
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
