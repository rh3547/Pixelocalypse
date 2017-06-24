package com.thale.engine;

import java.awt.Graphics2D;

public abstract class Screen 
{
	private final ScreenHandler screenHandler;
	
	public Screen(ScreenHandler screenHandler)
	{
		this.screenHandler = screenHandler;
	}
	
	public abstract void onCreate();
	
	public abstract void onUpdate();
	
	public abstract void onTick();
	
	public abstract void onDraw(Graphics2D g2d);
	
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}
}
