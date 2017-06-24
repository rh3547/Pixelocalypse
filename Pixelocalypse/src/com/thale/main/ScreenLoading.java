package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.thale.engine.Game;
import com.thale.engine.Screen;
import com.thale.engine.ScreenHandler;

public class ScreenLoading extends Screen
{
	private Game game;
	
	private ImageIcon bgII;
	private Image bgImage;
	
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 32);
	
	private int count = 0;
	
	public ScreenLoading(ScreenHandler screenHandler)
	{
		super(screenHandler);
		game = screenHandler.getGame();
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
	}

	@Override
	public void onCreate()
	{
		BufferedImage img = null;
	    try 
	    {
	       img = ImageIO.read(new File(game.getRespath() + "undead.png"));
	    } 
	    catch (IOException e) 
	    {
	       System.out.println("\nImage not found");
	    }
	      
	    game.getWindow().setIconImage(img);
	    
		game.getWindow().repaint();
	}

	@Override
	public void onUpdate()
	{
		count++;
	}

	@Override
	public void onTick()
	{
		
	}

	@Override
	public void onDraw(Graphics2D g2d)
	{
		if (count >= 75)
		{
			count = 0;
		}
		
		g2d.drawImage(bgImage, (game.getWindowX() / 2) - 150, (game.getWindowY() / 2) - 40, 300, 80, null);
		
		if (count < 25)
		{
			g2d.setFont(pixelFont);
			g2d.setColor(Color.white);
			g2d.drawString("Loading.", (game.getWindowX() / 2) - 80, (game.getWindowY() / 2) + 15);
		}
		else if (count > 25 && count < 50)
		{
			g2d.setFont(pixelFont);
			g2d.setColor(Color.white);
			g2d.drawString("Loading..", (game.getWindowX() / 2) - 80, (game.getWindowY() / 2) + 15);
		}
		else if (count > 50)
		{
			g2d.setFont(pixelFont);
			g2d.setColor(Color.white);
			g2d.drawString("Loading...", (game.getWindowX() / 2) - 80, (game.getWindowY() / 2) + 15);
		}
	}
}
