package com.thale.engine;

import javax.swing.JPanel;

public class GameAsset extends JPanel
{
	private final Game game;
	
	public GameAsset(Game game)
	{
		this.game = game;
	}
	
	public Game getGame()
	{
		return game;
	}
}
