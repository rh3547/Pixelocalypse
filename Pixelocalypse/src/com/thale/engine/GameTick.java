package com.thale.engine;

public class GameTick implements Runnable
{
	private final Game game;
	
	public GameTick(Game game)
	{
		this.game = game;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				if (game.getScreenHandler().getCurrentScreen() != null)
					game.getScreenHandler().getCurrentScreen().onTick();
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
