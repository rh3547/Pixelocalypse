package com.thale.engine;

public class ScreenHandler 
{
	private final Game game;
	private Screen screen;
	private boolean gameLoaded = false;
	private boolean gameReady = false;
	
	public ScreenHandler(Game game)
	{
		this.game = game;
	}
	
	public void showLoadingScreen(Screen screen)
	{
		this.screen = screen;
		this.screen.onCreate();
	}
	
	public void showScreen(Screen screen)
	{
		if (gameLoaded == true)
		{
			this.screen = screen;
			this.screen.onCreate();
			gameReady = true;
		}
	}
	
	public Screen getCurrentScreen()
	{
		return screen;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setGameLoaded(boolean gameLoaded)
	{
		this.gameLoaded = gameLoaded;
	}
}
