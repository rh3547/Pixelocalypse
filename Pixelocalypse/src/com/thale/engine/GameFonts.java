package com.thale.engine;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;

public class GameFonts
{
	private static ArrayList<GameFonts> fontList = new ArrayList<GameFonts>();
	
	private static String fontPath;
	
	public GameFonts(String filePath)
	{
		GameFonts.fontPath = filePath;
		registerFont();
	}
	
	private void registerFont()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addFont(GameFonts font)
	{
		fontList.add(font);
	}
}
