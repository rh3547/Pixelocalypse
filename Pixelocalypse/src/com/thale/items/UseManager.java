package com.thale.items;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class UseManager extends GameAsset
{
	Game game;
	
	public UseManager(Game game)
	{
		super(game);
		
		this.game = game;
	}
	
	public void useItem(Item item)
	{
		String use = item.getUse();
		int useAmt = item.getUseAmt();
		
		if (use.equals("HealthUp"))
		{
			if ((game.getCurrentPlayer().getMotives().getHealth() + useAmt) <= game.getCurrentPlayer().getMotives().getMaxHealth())
			{
				game.getCurrentPlayer().getMotives()
				.setHealth(game.getCurrentPlayer().getMotives().getHealth() + useAmt);
			}
			else
			{
				game.getCurrentPlayer().getMotives()
				.setHealth(game.getCurrentPlayer().getMotives().getMaxHealth());
			}
		}
		else if (use.equals("HungerUp"))
		{
			if ((game.getCurrentPlayer().getMotives().getHunger() + useAmt) <= 100)
			{
				game.getCurrentPlayer().getMotives()
				.setHunger(game.getCurrentPlayer().getMotives().getHunger() + useAmt);
			}
			else
			{
				game.getCurrentPlayer().getMotives().setHunger(100);
			}
		}
		else if (use.equals("ThirstUp"))
		{
			if ((game.getCurrentPlayer().getMotives().getThirst() + useAmt) <= 100)
			{
				game.getCurrentPlayer().getMotives()
				.setThirst(game.getCurrentPlayer().getMotives().getThirst() + useAmt);
			}
			else
			{
				game.getCurrentPlayer().getMotives().setThirst(100);
			}
		}
	}
}
