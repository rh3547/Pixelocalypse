package com.thale.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.Item;
import com.thale.player.Player;

public class CompoundTravelThread extends GameAsset implements Runnable
{
	private Game game;
	private Player player;
	
	public CompoundTravelThread(Game game, Player player)
	{
		super(game);
		this.game = game;
		this.player = player;
	}

	@Override
	public void run()
	{	
		try
		{
			Thread.sleep(game.getExploreWait() * 1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		player.setLocation("Compound");
		game.setCurrentLocation(game.getLocationManager().getLocation("Compound"));
		player.getScene().setBgImageStr("src/res/scenes/" + player.getLocation() + "Scene.png");
		player.getScene().resetBgImage();
		game.getSceneManager().regenerateScene(player);
		game.getLocationManager().removeFromTravellingPlayers(player);
	}
}
