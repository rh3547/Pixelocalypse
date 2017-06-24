package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class SkillsManager extends GameAsset
{
	private Game game;
	
	private String lootingText = "";
	
	public SkillsManager(Game game)
	{
		super(game);
		this.game = game;
	}
	
	public void checkLooting(Player player)
	{
		switch (player.getSkills().getLooting())
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
	
	public String getLootingText()
	{
		return lootingText;
	}
}
