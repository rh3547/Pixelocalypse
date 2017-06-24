package com.thale.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener implements KeyListener
{
	private boolean[] keys = new boolean[256];
	
	@Override
	public void keyPressed(KeyEvent event) 
	{
		keys[event.getKeyCode()] = true;
		System.out.print("\n\n" + event.getKeyCode() + " was pressed.");
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{	
		keys[event.getKeyCode()] = false;
		System.out.print("\n\n" + event.getKeyCode() + " was released.");
	}

	@Override
	public void keyTyped(KeyEvent event) 
	{

	}
	
	public boolean isKeyPressed(int key)
	{
		return keys[key];
	}
	
	public boolean isKeyReleased(int key)
	{
		return !keys[key];
	}
}
