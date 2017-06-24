package com.thale.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.player.Player;
import com.thale.sprite.Sprite;

public class Scene extends GameAsset
{
	Game game;
	Player player;
	String bgImageStr;
	List<Sprite> sprites;
	
	ImageIcon bgII;
	Image bgImage;
	
	/**
	 * @return the player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Scene(Game game, Player player, String bgImageStr, List<Sprite> sprites)
	{
		super(game);
		this.game = game;
		this.player = player;
		this.bgImageStr = bgImageStr;
		this.sprites = sprites;
		
		bgII = new ImageIcon(bgImageStr);
		bgImage = bgII.getImage();
		
		this.setLayout(null);
	}

	/**
	 * @return the backgroundStr
	 */
	public String getBgImageStr()
	{
		return bgImageStr;
	}

	/**
	 * @param bgImageStr the backgroundStr to set
	 */
	public void setBgImageStr(String bgImageStr)
	{
		this.bgImageStr = bgImageStr;
	}
	
	/**
	 * @return the background
	 */
	public Image getBgImage()
	{
		return bgImage;
	}
	
	public void resetBgImage()
	{
		bgII = new ImageIcon(bgImageStr);
		bgImage = bgII.getImage();
	}

	/**
	 * @return the tiles
	 */
	public List<Sprite> getSprites()
	{
		return sprites;
	}
	
	public void addToSprites(Sprite sprite)
	{
		this.sprites.add(sprite);
		this.add(sprite);
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setSprites(List<Sprite> sprites)
	{
		this.sprites = sprites;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 1480, 745, null);
	 }
}
