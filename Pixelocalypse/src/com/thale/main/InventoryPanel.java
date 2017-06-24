package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.inventory.InventoryManager;
import com.thale.items.ItemManager;

public class InventoryPanel extends GameAsset implements ActionListener
{
	private Game game;
	private JFrame window;
	private JPanel gameThread;
	private InventoryManager invMan;
	private ItemManager itemMan;
	
	// Local Variables
	private int windowX;
	private int windowY;
	private boolean inventoryPanelOpen;
	private int panelYPos = -500;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnDisabled;
	private ImageIcon btnBaseMed;
	private ImageIcon btnHoverMed;
	private ImageIcon btnDisabledMed;
	
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	private Font pixelFontSmall = new Font("Minecraftia", Font.PLAIN, 13);
	
	private JLabel inventoryLbl = new JLabel();
	private JSeparator topSep = new JSeparator();
	private JButton nextInvBtn = new JButton();
	private JButton prevInvBtn = new JButton();
	private JButton useBtn = new JButton();
	private JButton deleteOneBtn = new JButton();
	private JButton deleteStackBtn = new JButton();
	
	public InventoryPanel(Game game) 
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnDisabled = new ImageIcon(game.getRespath() + "gui/buttons/btnDisabled.png");
		btnBaseMed = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseMed.png");
		btnHoverMed = new ImageIcon(game.getRespath() + "gui/buttons/btnHoverMed.png");
		btnDisabledMed = new ImageIcon(game.getRespath() + "gui/buttons/btnMedDisabled.png");
		
		this.window = game.getWindow();
		this.gameThread = game.getGameThread();
		this.invMan = game.getInventoryManager();
		this.itemMan = game.getItemManager();
	}
	
	public void createInventoryPanel()
	{	
		nextInvBtn.addActionListener(this);
		prevInvBtn.addActionListener(this);
		useBtn.addActionListener(this);
		deleteOneBtn.addActionListener(this);
		deleteStackBtn.addActionListener(this);
	}
	
	public void showInventoryPanel()
	{	
		panelYPos = -500;
		gameThread.add(this);
		gameThread.setComponentZOrder(this, 1);
		this.setBounds((windowX / 2) - 408, panelYPos, 800, 525);
		this.setLayout(null);
		this.setVisible(true);

		// Inventory label
		this.add(inventoryLbl);
		inventoryLbl.setText(invMan.getShownInventory() + ":");
		inventoryLbl.setFont(pixelFont);
		inventoryLbl.setForeground(Color.white);
		
		// Top separator
		this.add(topSep);
		
		// Next inventory button
		this.add(nextInvBtn);
		nextInvBtn.setBorderPainted(false);
		nextInvBtn.setContentAreaFilled(false);
		nextInvBtn.setFocusPainted(false);
		nextInvBtn.setIcon(btnBase);
		nextInvBtn.setRolloverIcon(btnHover);
		nextInvBtn.setDisabledIcon(btnDisabled);
		nextInvBtn.setText("Next");
		nextInvBtn.setHorizontalTextPosition(JButton.CENTER);
		nextInvBtn.setVerticalTextPosition(JButton.CENTER);
		nextInvBtn.setFont(pixelFont);
		nextInvBtn.setForeground(Color.white);
		nextInvBtn.setToolTipText("Switch to the next inventory.");
		nextInvBtn.setVisible(true);
		
		// Previous inventory button
		this.add(prevInvBtn);
		prevInvBtn.setBorderPainted(false);
		prevInvBtn.setContentAreaFilled(false);
		prevInvBtn.setFocusPainted(false);
		prevInvBtn.setIcon(btnBase);
		prevInvBtn.setRolloverIcon(btnHover);
		prevInvBtn.setDisabledIcon(btnDisabled);
		prevInvBtn.setText("Prev.");
		prevInvBtn.setHorizontalTextPosition(JButton.CENTER);
		prevInvBtn.setVerticalTextPosition(JButton.CENTER);
		prevInvBtn.setFont(pixelFont);
		prevInvBtn.setForeground(Color.white);
		prevInvBtn.setToolTipText("Switch to the previous inventory.");
		prevInvBtn.setVisible(true);
		
		// Use button
		this.add(useBtn);
		useBtn.setBorderPainted(false);
		useBtn.setContentAreaFilled(false);
		useBtn.setFocusPainted(false);
		useBtn.setIcon(btnBase);
		useBtn.setRolloverIcon(btnHover);
		useBtn.setDisabledIcon(btnDisabled);
		useBtn.setText("Use");
		useBtn.setHorizontalTextPosition(JButton.CENTER);
		useBtn.setVerticalTextPosition(JButton.CENTER);
		useBtn.setFont(pixelFont);
		useBtn.setForeground(Color.white);
		useBtn.setToolTipText("Use the selected item.");
		useBtn.setVisible(true);
		
		// Delete one button
		this.add(deleteOneBtn);
		deleteOneBtn.setBorderPainted(false);
		deleteOneBtn.setContentAreaFilled(false);
		deleteOneBtn.setFocusPainted(false);
		deleteOneBtn.setIcon(btnBaseMed);
		deleteOneBtn.setRolloverIcon(btnHoverMed);
		deleteOneBtn.setDisabledIcon(btnDisabledMed);
		deleteOneBtn.setText("Delete One");
		deleteOneBtn.setHorizontalTextPosition(JButton.CENTER);
		deleteOneBtn.setVerticalTextPosition(JButton.CENTER);
		deleteOneBtn.setFont(pixelFontSmall);
		deleteOneBtn.setForeground(Color.white);
		deleteOneBtn.setToolTipText("Delete one of the selected items.");
		deleteOneBtn.setVisible(true);
		
		// Delete stack button
		this.add(deleteStackBtn);
		deleteStackBtn.setBorderPainted(false);
		deleteStackBtn.setContentAreaFilled(false);
		deleteStackBtn.setFocusPainted(false);
		deleteStackBtn.setIcon(btnBaseMed);
		deleteStackBtn.setRolloverIcon(btnHoverMed);
		deleteStackBtn.setDisabledIcon(btnDisabledMed);
		deleteStackBtn.setText("Delete Stack");
		deleteStackBtn.setHorizontalTextPosition(JButton.CENTER);
		deleteStackBtn.setVerticalTextPosition(JButton.CENTER);
		deleteStackBtn.setFont(pixelFontSmall);
		deleteStackBtn.setForeground(Color.white);
		deleteStackBtn.setToolTipText("Delete the entire stack of selected items.");
		deleteStackBtn.setVisible(true);
	}
	
	public void showInventory(String inventory)
	{
		invMan.showInventory(inventory, this, 275, 150);
		invMan.setShownInventory(inventory);
		inventoryLbl.setText(inventory + ":");
	}
	
	public void removeInventoryPanel()
	{	
		game.getInventoryManager().hideInventory(game.getCurrentPlayer().getInventory(), this);
		
		gameThread.remove(this);
		this.setVisible(false);
	}
	
	public void updatePanelClose()
	{
		if (inventoryPanelOpen == true && this.getY() < ((windowY / 2) - 250))
		{
			panelYPos += 30;
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
		}
		
		if (inventoryPanelOpen == true && this.getY() > ((windowY / 2) - 251))
		{
			this.setBounds
			((windowX / 2) - 408, ((windowY / 2) - 250), 800, 525);
		}
		
		if (inventoryPanelOpen == false)
		{
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
			
			panelYPos -= 20;
			
			if (this.getY() <= -501 && this.getY() >= -701)
			{
				removeInventoryPanel();
				
				panelYPos = -1000;
				this.setBounds
				((windowX / 2) - 600, panelYPos, 1200, 525);
			}
		}
	}
	
	public void update()
	{
		inventoryLbl.setText(game.getCurrentPlayer().getInventory() + ":");
		inventoryLbl.setBounds(((800 / 2) - (inventoryLbl.getText().length() * 5)), 40, 250, 30);
		topSep.setBounds((800 / 2) - (300), 80, 600, 5);
		//nextInvBtn.setBounds(395, 110, btnWidth, btnHeight);
		//prevInvBtn.setBounds(275, 110, btnWidth, btnHeight);
		useBtn.setBounds(550, 150, btnWidth, btnHeight);
		deleteOneBtn.setBounds(560, 302, 140, btnHeight);
		deleteStackBtn.setBounds(560, 342, 140, btnHeight);
		
		if (invMan.getFirstSlot() != null)
		{
			if (!invMan.getFirstSlot().getSlotItem().getUse().equals("None"))
			{
				useBtn.setEnabled(true);
			}
			else
			{
				useBtn.setEnabled(false);
			}
		}
		else
		{
			useBtn.setEnabled(false);
		}
		
		if (invMan.getFirstSlot() != null)
		{
			if (!invMan.getFirstSlot().getSlotItem().getName().equals("Null"))
			{
				deleteOneBtn.setEnabled(true);
				deleteStackBtn.setEnabled(true);
			}
		}
		else
		{
			deleteOneBtn.setEnabled(false);
			deleteStackBtn.setEnabled(false);
		}
	}
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
	}
	
	public void setInventoryPanelOpen(boolean inventoryPanelOpen)
	{
		this.inventoryPanelOpen = inventoryPanelOpen;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 800, 525, null);
	 }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == nextInvBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			int invIndex = 0;
			
			for (int x = 0; x < invMan.getInventories().size(); x++)
			{
				if (invMan.getInventories().get(x).getName().equals(invMan.getShownInventory()))
				{
					invIndex = x;
					break;
				}
			}
			
			if ((invIndex + 1) <= (invMan.getInventories().size() - 1))
			{
				invMan.storeSlot();
				
				invMan.hideInventory(invMan.getShownInventory(), this);
				invMan.showInventory
				(invMan.getInventories().get(invIndex + 1).getName(), this, 275, 150);
			}
			else if (invIndex != 0)
			{
				invMan.storeSlot();
				
				invMan.hideInventory(invMan.getShownInventory(), this);
				invMan.showInventory
				(invMan.getInventories().get(0).getName(), this, 275, 150);
			}
			else
			{
				
			}
		}
		
		if (e.getSource() == prevInvBtn)
		{	
			int invIndex = 0;
			
			for (int x = 0; x < invMan.getInventories().size(); x++)
			{
				if (invMan.getInventories().get(x).getName().equals(invMan.getShownInventory()))
				{
					invIndex = x;
					break;
				}
			}
			
			if ((invIndex - 1) >= 0)
			{
				invMan.storeSlot();
				
				invMan.hideInventory(invMan.getShownInventory(), this);
				invMan.showInventory
				(invMan.getInventories().get(invIndex - 1).getName(), this, 275, 150);
			}
			else if (invIndex == 0 && invMan.getInventories().size() > 1)
			{
				invMan.storeSlot();
				
				invMan.hideInventory(invMan.getShownInventory(), this);
				invMan.showInventory
				(invMan.getInventories().get((invMan.getInventories().size() - 1)).getName(), this, 275, 150);
			}
			else
			{
				
			}
			
			game.getAudioHandler().playSound("btnPressed.wav");
		}
		
		if (e.getSource() == useBtn)
		{
			game.getUseManager().useItem(invMan.getFirstSlot().getSlotItem());
			
			invMan.removeFromSlot(game.getCurrentPlayer().getInventory(), 
					invMan.getFirstSlot().getSlotNum(), 
					invMan.getFirstSlot().getSlotItem(), 1);
			
			invMan.resetFirstSlot();
			
			invMan.setFirstSlot(null);
		}
		
		if (e.getSource() == deleteOneBtn)
		{
			invMan.removeFromSlot(game.getCurrentPlayer().getInventory(), 
					invMan.getFirstSlot().getSlotNum(), 
					invMan.getFirstSlot().getSlotItem(), 1);
			
			invMan.resetFirstSlot();
			
			invMan.setFirstSlot(null);
		}
		
		if (e.getSource() == deleteStackBtn)
		{
			invMan.removeFromSlot(game.getCurrentPlayer().getInventory(), 
					invMan.getFirstSlot().getSlotNum(), 
					invMan.getFirstSlot().getSlotItem(), 
					invMan.getFirstSlot().getSlotStack());
			
			invMan.resetFirstSlot();
			
			invMan.setFirstSlot(null);
		}
	}
}
