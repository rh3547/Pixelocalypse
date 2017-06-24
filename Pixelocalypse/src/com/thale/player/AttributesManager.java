package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class AttributesManager extends GameAsset
{
	private Game game;
	
	private String strengthText = "";
	private String constitutionText = "";
	private String agilityText = "";
	private String intelligenceText = "";
	private String charismaText = "";
	
	public AttributesManager(Game game)
	{
		super(game);
		this.game = game;
	}
	
	public void checkStrength(Player player)
	{
		switch (player.getAttributes().getStrength())
		{
			case 1:
				strengthText = "Increased melee damage.";
				break;
				
			case 2:
				strengthText = "Increased melee damage & +2 inventory slots.";
				break;
				
			case 3:
				strengthText = "Increased melee damage.";
				game.getInventoryManager().updateInventorySize(game.getInventoryManager().getInventory(player.getInventory()), 22);
				break;
				
			case 4:
				strengthText = "Increased melee damage & +3 inventory slots.";
				break;
		
			case 5:
				strengthText = "Increased melee damage.";
				game.getInventoryManager().updateInventorySize(game.getInventoryManager().getInventory(player.getInventory()), 25);
				break;
				
			case 6:
				strengthText = "Increased melee damage.";
				break;
				
			case 7:
				strengthText = "Increased melee damage.";
				break;
				
			case 8:
				strengthText = "Increased melee damage.";
				break;
				
			case 9:
				strengthText = "Increased melee damage.";
				break;
				
			case 10:
				strengthText = "Increased melee damage.";
				break;
		}
	}
	
	public String getStrengthText()
	{
		return strengthText;
	}
	
	public void checkConstitution(Player player)
	{
		switch (player.getAttributes().getConstitution())
		{
			case 1:
				player.getMotives().setMaxHealth(100);
				break;
				
			case 2:
				player.getMotives().setMaxHealth(125);
				break;
				
			case 3:
				player.getMotives().setMaxHealth(150);
				break;
				
			case 4:
				player.getMotives().setMaxHealth(175);
				break;
		
			case 5:
				player.getMotives().setMaxHealth(200);
				break;
				
			case 6:
				break;
				
			case 7:
				break;
				
			case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				break;
		}
	}
	
	public String getConstitutionText()
	{
		return constitutionText;
	}
	
	public void checkAgility(Player player)
	{
		switch (player.getAttributes().getAgility())
		{
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
		
			case 5:
				break;
				
			case 6:
				break;
				
			case 7:
				break;
				
			case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				break;
		}
	}
	
	public String getAgilityText()
	{
		return agilityText;
	}
	
	public void checkIntelligence(Player player)
	{
		switch (player.getAttributes().getIntelligence())
		{
			case 1:
				player.getMotives().setMaxEnergy(10);
				break;
				
			case 2:
				player.getMotives().setMaxEnergy(12);
				break;
				
			case 3:
				player.getMotives().setMaxEnergy(15);
				break;
				
			case 4:
				player.getMotives().setMaxEnergy(17);
				break;
		
			case 5:
				player.getMotives().setMaxEnergy(20);
				break;
				
			case 6:
				break;
				
			case 7:
				break;
				
			case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				break;
		}
	}
	
	public String getIntelligenceText()
	{
		return intelligenceText;
	}
	
	public void checkCharisma(Player player)
	{
		switch (player.getAttributes().getCharisma())
		{
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
		
			case 5:
				break;
				
			case 6:
				break;
				
			case 7:
				break;
				
			case 8:
				break;
				
			case 9:
				break;
				
			case 10:
				break;
		}
	}
	
	public String getCharismaText()
	{
		return charismaText;
	}
}
