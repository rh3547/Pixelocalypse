package com.thale.inventory;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.Item;

public class Slot extends GameAsset
{
	private Game game;
	
	public int slotNum;
	public Item slotItem;
	public int slotStack;
	public int slotMaxStack;
	ImageIcon icon;
	JLabel itemIcon;
	JLabel stackLbl;
	
	private ImageIcon bgII;
	private Image bgImage;
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 12);
	
	public Slot(Game game, int slotNum, Item slotItem, int slotStack, int slotMaxStack, ImageIcon icon, JLabel itemIcon, JLabel stackLbl)
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/slotBg.png");
		bgImage = bgII.getImage();
		
		this.slotNum = slotNum;
		this.slotItem = slotItem;
		this.slotStack = slotStack;
		this.slotMaxStack = slotMaxStack;
		this.icon = icon;
		this.itemIcon = itemIcon;
		this.stackLbl = stackLbl;
		
		this.setLayout(null);
		
		add(getItemIcon());
		getItemIcon().setBounds(8, 4, 40, 40);
		getItemIcon().setIcon(getIcon());
		getItemIcon().setVisible(true);
		
		add(getStackLbl());
		getStackLbl().setBounds(10, 40, 48, 12);
		getStackLbl().setText(Integer.toString(getSlotStack()));
		getStackLbl().setFont(pixelFont);
		getStackLbl().setVisible(true);
	}
	
	public int getSlotNum()
	{
		return slotNum;
	}
	
	public Item getSlotItem()
	{
		return slotItem;
	}
	
	public int getSlotStack()
	{
		return slotStack;
	}
	
	public int getSlotMaxStack()
	{
		return slotMaxStack;
	}
	
	public ImageIcon getIcon()
	{
		return icon;
	}
	
	public JLabel getItemIcon()
	{
		return itemIcon;
	}
	
	public JLabel getStackLbl()
	{
		return stackLbl;
	}
	
	public void setSlotNum(int slotNum)
	{
		this.slotNum = slotNum;
	}
	
	public void setSlotItem(Item slotItem)
	{
		this.slotItem = slotItem;
	}
	
	public void setSlotStack(int slotStack)
	{
		this.slotStack = slotStack;
	}
	
	public void setSlotMaxStack(int slotMaxStack)
	{
		this.slotMaxStack = slotMaxStack;
	}
	
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 48, 56, null);
	 }
}
