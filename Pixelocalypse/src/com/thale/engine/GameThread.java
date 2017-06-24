package com.thale.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable 
{
	private final Game game;
	private int fps;
	private JFrame window;
	private int windowX;
	private int windowY;
	
	public GameThread(Game game, int fps)
	{
		this.game = game;
		this.fps = fps;
		
		window = game.getWindow();
		
		setLayout(null);
		setFocusable(true);
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				if (game.getScreenHandler().getCurrentScreen() != null)
					game.getScreenHandler().getCurrentScreen().onUpdate();
				
				fps = game.getFps();
				windowX = window.getWidth();
				windowY = window.getHeight();
				this.setBounds(0, 0, windowX, windowY);
				
				Thread.sleep(1000 / fps);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint
		(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (game.getScreenHandler().getCurrentScreen() != null)
			game.getScreenHandler().getCurrentScreen().onDraw(g2d);
			
		repaint();
	}
	
	 @Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(ResourceLoader.getBgImage(), 0, 0, windowX, windowY, null);
	 }
}
