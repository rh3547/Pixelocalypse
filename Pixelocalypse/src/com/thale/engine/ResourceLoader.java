package com.thale.engine;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.thale.locations.LocationManager;

public class ResourceLoader extends GameAsset
{
	private static Game game;
	public static Image bgImage;
	
	public ResourceLoader(Game game) 
	{
		super(game);
		this.game = game;
	}
	
	public static void loadFonts()
	{
		GameFonts.addFont(new GameFonts(game.getRespath() + "font/Minecraftia.ttf"));
	}
	
	public static void loadBgImage()
	{
		ImageIcon bgII = new ImageIcon(game.getRespath() + "/gui/backgrounds/mainBg.png");
		bgImage = bgII.getImage();
	}
	
	public static void loadResources()
	{
		
	}
	
	public static Image getBgImage()
	{
		return bgImage;
	}
	
	public static void setGame(Game gameset)
	{
		game = gameset;
	}
}
