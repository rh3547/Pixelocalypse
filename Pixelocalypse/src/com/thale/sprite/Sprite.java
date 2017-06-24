package com.thale.sprite;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.thale.engine.Game;

public class Sprite extends JLabel
{
	String name;
	int posX;
	int posY;
	String imageStr;
	
	ImageIcon imageII;
	Image image;
	
	public Sprite(String name, int posX, int posY, String imageStr)
	{
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.imageStr = imageStr;
		
		imageII = new ImageIcon(imageStr);
		image = imageII.getImage();
		
		this.setBounds(posX, posY, 128, 128);
		this.setIcon(imageII);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the posX
	 */
	public int getPosX()
	{
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX)
	{
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY()
	{
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	
	public void updatePosition()
	{
		this.setBounds(posX, posY, 128, 128);
	}

	/**
	 * @return the imageStr
	 */
	public String getImageStr()
	{
		return imageStr;
	}

	/**
	 * @param imageStr the imageStr to set
	 */
	public void setImageStr(String imageStr)
	{
		this.imageStr = imageStr;
	}
	
	/**
	 * @return the imageS
	 */
	public Image getImage()
	{
		return image;
	}
	
	public void resetImage()
	{
		imageII = new ImageIcon(imageStr);
		image = imageII.getImage();
		
		this.setIcon(imageII);
	}
}
