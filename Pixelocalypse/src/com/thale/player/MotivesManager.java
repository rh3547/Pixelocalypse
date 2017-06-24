package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class MotivesManager extends GameAsset
{
	Game game;
	
	public MotivesManager(Game game)
	{
		super(game);
		this.game = game;
	}
	
	public void healthCheck()
	{
		for (int x = 0; x < game.getPlayerManager().getPlayers().size(); x++)
		{
			if (game.getPlayerManager().getPlayers().get(x).getMotives().getHealth() <= 0)
			{
				game.getPlayerManager().killCharacter(game.getPlayerManager().getPlayers().get(x).getName());
			}
		}
	}
}
