package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.inventory.InventoryManager;
import com.thale.items.ItemManager;

public class ActionPanel extends GameAsset implements ActionListener
{
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 30;
	private final int BUTTON_SPACE = 10;
	
	private Game game;
	private JPanel gameThread;
	private ItemManager itemMan;
	private InventoryManager invMan;
	private JFrame window;
	private MenuPanel menuPanel;
	private MapGUI mapGUI;
	private InventoryPanel invPanel;
	private SurvivorPanel survivorPanel;
	private CraftBuildPanel craftBuildPanel;
	
	private int windowX;
	private int windowY;
	private boolean menuOpen = false;
	private boolean mapOpen = false;
	private boolean craftBuildOpen = false;
	private boolean survivorsOpen = false;
	private boolean inventoryOpen = false;
	private String currentCharacter;
	
	// Images
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnBaseBig;
	private ImageIcon btnHoverBig;
	private ImageIcon btnBaseMed;
	private ImageIcon btnHoverMed;
	private ImageIcon btnRightBase;
	private ImageIcon btnRightHover;
	private ImageIcon btnLeftBase;
	private ImageIcon btnLeftHover;
	private ImageIcon verticalSeparator;
	
	// Local Components
	private Font titleFont = new Font("Minecraftia", Font.BOLD, 18);
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	private JButton menuBtn = new JButton();
	private JButton mapBtn = new JButton();
	private JButton craftBuildBtn = new JButton();
	private JButton survivorsBtn = new JButton();
	private JLabel vertSep1 = new JLabel();
	private JLabel characterNameLbl = new JLabel();
	private JLabel healthLbl = new JLabel();
	private JButton inventoryBtn = new JButton();
	private JLabel energyLbl = new JLabel();
	private JLabel hungerLbl = new JLabel();
	private JLabel thirstLbl = new JLabel();
	private JLabel levelLbl = new JLabel();
	private JLabel xpLbl = new JLabel();
	private JButton nextCharBtn = new JButton();
	private JButton prevCharBtn = new JButton();
	
	public ActionPanel(Game game, MenuPanel menuPanel, MapGUI mapGUI, InventoryPanel invPanel, SurvivorPanel survivorPanel, CraftBuildPanel craftBuildPanel) 
	{
		super(game);
		this.game = game;
		
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/topPanelBg.png");
		bgImage = bgII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnBaseBig = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseBig.png");
		btnHoverBig = new ImageIcon(game.getRespath() + "/gui/buttons/btnHoverBig.png");
		btnBaseMed = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseMed.png");
		btnHoverMed = new ImageIcon(game.getRespath() + "gui/buttons/btnHoverMed.png");
		btnRightBase = new ImageIcon(game.getRespath() + "gui/buttons/rightBase.png");
		btnRightHover = new ImageIcon(game.getRespath() + "gui/buttons/rightHover.png");
		btnLeftBase = new ImageIcon(game.getRespath() + "gui/buttons/leftBase.png");
		btnLeftHover = new ImageIcon(game.getRespath() + "gui/buttons/leftHover.png");
		verticalSeparator = new ImageIcon(game.getRespath() + "gui/other/verticalSeparator.png");
		
		gameThread = game.getGameThread();
		itemMan = game.getItemManager();
		invMan = game.getInventoryManager();
		window = game.getWindow();
		this.menuPanel = menuPanel;
		this.mapGUI = mapGUI;
		this.invPanel = invPanel;
		this.survivorPanel = survivorPanel;
		this.craftBuildPanel = craftBuildPanel;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, windowX, 75, null);
	 }
	
	public void createPanel()
	{
		/*
		 * 	Create top panel
		 */
		gameThread.add(this);
		this.setLayout(null);
		this.setVisible(true);
		
		// Menu button
		this.add(menuBtn);
		menuBtn.addActionListener(this);
		menuBtn.setBorderPainted(false);
		menuBtn.setContentAreaFilled(false);
		menuBtn.setFocusPainted(false);
		menuBtn.setIcon(btnBase);
		menuBtn.setRolloverIcon(btnHover);
		menuBtn.setText("Menu");
		menuBtn.setHorizontalTextPosition(JButton.CENTER);
		menuBtn.setVerticalTextPosition(JButton.CENTER);
		menuBtn.setFont(pixelFont);
		menuBtn.setForeground(Color.white);
		menuBtn.setToolTipText("Save/Load, change options, or exit the game.");
		menuBtn.setVisible(true);
		
		// Map button
		this.add(mapBtn);
		mapBtn.addActionListener(this);
		mapBtn.setBorderPainted(false);
		mapBtn.setContentAreaFilled(false);
		mapBtn.setFocusPainted(false);
		mapBtn.setIcon(btnBase);
		mapBtn.setRolloverIcon(btnHover);
		mapBtn.setText("Map");
		mapBtn.setHorizontalTextPosition(JButton.CENTER);
		mapBtn.setVerticalTextPosition(JButton.CENTER);
		mapBtn.setFont(pixelFont);
		mapBtn.setForeground(Color.white);
		mapBtn.setToolTipText("Explore different locations and search for loot.");
		mapBtn.setVisible(true);
		
		// Craft and build button
		this.add(craftBuildBtn);
		craftBuildBtn.addActionListener(this);
		craftBuildBtn.setBorderPainted(false);
		craftBuildBtn.setContentAreaFilled(false);
		craftBuildBtn.setFocusPainted(false);
		craftBuildBtn.setIcon(btnBaseBig);
		craftBuildBtn.setRolloverIcon(btnHoverBig);
		craftBuildBtn.setText("Crafting & Building");
		craftBuildBtn.setHorizontalTextPosition(JButton.CENTER);
		craftBuildBtn.setVerticalTextPosition(JButton.CENTER);
		craftBuildBtn.setFont(pixelFont);
		craftBuildBtn.setForeground(Color.white);
		craftBuildBtn.setToolTipText("Use your resources to craft different items and build structures.");
		craftBuildBtn.setVisible(true);
		
		// Survivors button
		this.add(survivorsBtn);
		survivorsBtn.addActionListener(this);
		survivorsBtn.setBorderPainted(false);
		survivorsBtn.setContentAreaFilled(false);
		survivorsBtn.setFocusPainted(false);
		survivorsBtn.setIcon(btnBaseMed);
		survivorsBtn.setRolloverIcon(btnHoverMed);
		survivorsBtn.setText("Survivors");
		survivorsBtn.setHorizontalTextPosition(JButton.CENTER);
		survivorsBtn.setVerticalTextPosition(JButton.CENTER);
		survivorsBtn.setFont(pixelFont);
		survivorsBtn.setForeground(Color.white);
		survivorsBtn.setToolTipText("Manage your survivors and assign them different tasks to perform");
		survivorsBtn.setVisible(true);
		
		// Next survivor button
		this.add(nextCharBtn);
		nextCharBtn.addActionListener(this);
		nextCharBtn.setBorderPainted(false);
		nextCharBtn.setContentAreaFilled(false);
		nextCharBtn.setFocusPainted(false);
		nextCharBtn.setIcon(btnRightBase);
		nextCharBtn.setRolloverIcon(btnRightHover);
		nextCharBtn.setHorizontalTextPosition(JButton.CENTER);
		nextCharBtn.setVerticalTextPosition(JButton.CENTER);
		nextCharBtn.setFont(pixelFont);
		nextCharBtn.setForeground(Color.white);
		nextCharBtn.setToolTipText("Switch to the next survivor");
		nextCharBtn.setVisible(true);
		
		// Previous survivors button
		this.add(prevCharBtn);
		prevCharBtn.addActionListener(this);
		prevCharBtn.setBorderPainted(false);
		prevCharBtn.setContentAreaFilled(false);
		prevCharBtn.setFocusPainted(false);
		prevCharBtn.setIcon(btnLeftBase);
		prevCharBtn.setRolloverIcon(btnLeftHover);
		prevCharBtn.setHorizontalTextPosition(JButton.CENTER);
		prevCharBtn.setVerticalTextPosition(JButton.CENTER);
		prevCharBtn.setFont(pixelFont);
		prevCharBtn.setForeground(Color.white);
		prevCharBtn.setToolTipText("Switch to the previous survivor");
		prevCharBtn.setVisible(true);
		
		// Vertical separator 1
		this.add(vertSep1);
		vertSep1.setIcon(verticalSeparator);
		vertSep1.setText("");
		vertSep1.setVisible(true);
		
		// Character name label
		this.add(characterNameLbl);
		characterNameLbl.setText(game.getCurrentPlayer().getName());
		characterNameLbl.setFont(pixelFont);
		characterNameLbl.setForeground(Color.white);
		
		// Health label
		this.add(healthLbl);
		healthLbl.setText("Health:");
		healthLbl.setFont(pixelFont);
		healthLbl.setForeground(Color.white);
		healthLbl.setToolTipText("Health:" + "");
		
		// Inventory button
		this.add(inventoryBtn);
		inventoryBtn.addActionListener(this);
		inventoryBtn.setBorderPainted(false);
		inventoryBtn.setContentAreaFilled(false);
		inventoryBtn.setFocusPainted(false);
		inventoryBtn.setIcon(btnBaseMed);
		inventoryBtn.setRolloverIcon(btnHoverMed);
		inventoryBtn.setText("Inventory");
		inventoryBtn.setHorizontalTextPosition(JButton.CENTER);
		inventoryBtn.setVerticalTextPosition(JButton.CENTER);
		inventoryBtn.setFont(pixelFont);
		inventoryBtn.setForeground(Color.white);
		inventoryBtn.setToolTipText("View and manage the items you've collected.");
		inventoryBtn.setVisible(true);
		
		// Energy label
		this.add(energyLbl);
		energyLbl.setText("Energy:");
		energyLbl.setFont(pixelFont);
		energyLbl.setForeground(Color.white);
		energyLbl.setToolTipText("Energy:" + "");
		
		// Hunger label
		this.add(hungerLbl);
		hungerLbl.setText("Hunger:");
		hungerLbl.setFont(pixelFont);
		hungerLbl.setForeground(Color.white);
		hungerLbl.setToolTipText("Hunger:" + "");
		
		// Thirst label
		this.add(thirstLbl);
		thirstLbl.setText("Thirst:");
		thirstLbl.setFont(pixelFont);
		thirstLbl.setForeground(Color.white);
		thirstLbl.setToolTipText("Thirst:" + "");
		
		// Level label
		this.add(levelLbl);
		levelLbl.setText("Lvl: ");
		levelLbl.setFont(pixelFont);
		levelLbl.setForeground(Color.white);
		levelLbl.setToolTipText("Level:" + "");
		
		// XP label
		this.add(xpLbl);
		xpLbl.setText("XP:");
		xpLbl.setFont(pixelFont);
		xpLbl.setForeground(Color.white);
		xpLbl.setToolTipText("XP:" + "");
		
		window.repaint();
	}
	
	public void removePanel()
	{
		
	}
	
	public void updatePosition()
	{
		this.setBounds(0, 0, windowX, 75);
		menuBtn.setBounds(BUTTON_SPACE, 15, BUTTON_WIDTH, BUTTON_HEIGHT);
		mapBtn.setBounds((BUTTON_SPACE * 2) + BUTTON_WIDTH, 15, BUTTON_WIDTH, BUTTON_HEIGHT);
		craftBuildBtn.setBounds((BUTTON_SPACE * 3) + (BUTTON_WIDTH * 2), 15, 240, BUTTON_HEIGHT);
		survivorsBtn.setBounds((BUTTON_SPACE * 4) + (BUTTON_WIDTH * 2) + (240), 15, 140, BUTTON_HEIGHT);
		//nextCharBtn.setBounds((BUTTON_SPACE * 4) + (BUTTON_WIDTH * 2) + (310), 40, 32, 32);
		//prevCharBtn.setBounds((BUTTON_SPACE * 4) + (BUTTON_WIDTH * 2) + (280), 40, 32, 32);
		vertSep1.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + (380), -4, 5, 75);
		characterNameLbl.setBounds
		((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + (400), 2, (15) * (game.getCurrentPlayer().getName().length()), 30);
		inventoryBtn.setBounds
		((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + (420 + ((15) * (game.getCurrentPlayer().getName().length()))), 2, 140, BUTTON_HEIGHT);
		healthLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(650 + ((15) * (game.getCurrentPlayer().getName().length()))), 2, 140, BUTTON_HEIGHT);
		energyLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(820 + ((15) * (game.getCurrentPlayer().getName().length()))), 2, 140, BUTTON_HEIGHT);
		hungerLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(650 + ((15) * (game.getCurrentPlayer().getName().length()))), 35, 140, 30);
		thirstLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(820 + ((15) * (game.getCurrentPlayer().getName().length()))), 35, 140, 30);
		levelLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(400), 35, 75, 30);
		xpLbl.setBounds((BUTTON_SPACE * 5) + (BUTTON_WIDTH * 2) + 
		(485), 35, 160, 30);
	}
	
	public void updateData(int x, int y)
	{
		this.windowX = x;
		this.windowY = y;
		window.repaint();
		
		healthLbl.setToolTipText("Health: " + game.getCurrentPlayer().getMotives().getHealth() + 
								 "   Max Health: " + game.getCurrentPlayer().getMotives().getMaxHealth());
		energyLbl.setToolTipText("Energy: " + game.getCurrentPlayer().getMotives().getEnergy() + 
								 "   Max Energy: " + game.getCurrentPlayer().getMotives().getMaxEnergy());
		hungerLbl.setToolTipText("Hunger: " + game.getCurrentPlayer().getMotives().getHunger());
		thirstLbl.setToolTipText("Thirst: " + game.getCurrentPlayer().getMotives().getThirst());
		
		levelLbl.setText("Lvl: " + game.getCurrentPlayer().getLevel());
		levelLbl.setToolTipText("Level: " + game.getCurrentPlayer().getLevel());
		xpLbl.setText("XP: " + game.getCurrentPlayer().getXp());
		xpLbl.setToolTipText((1000 - game.getCurrentPlayer().getXp()) + " xp until level " + (game.getCurrentPlayer().getLevel() + 1));
		
		characterNameLbl.setText(game.getCurrentPlayer().getName());
	}
	
	public boolean isMapOpen()
	{
		return mapOpen;
		
	}
	
	public boolean isInventoryOpen()
	{
		return inventoryOpen;
	}
	
	public boolean isSurvivorsOpen()
	{
		return survivorsOpen;
	}
	
	public boolean isCraftBuildOpen()
	{
		return craftBuildOpen;
	}
	
	public void setMenuOpen(boolean menuOpen)
	{
		this.menuOpen = menuOpen;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == menuBtn)
		{
			if (menuOpen == false)	// Opens the menu
			{
				game.getAudioHandler().playSound("btnPressed.wav");
				menuPanel.showMenuPanel();
			}
			else
			{
				game.getAudioHandler().playSound("btnPressed2.wav");
				menuPanel.setMenuOpen(false);
				menuPanel.updatePosition();
			}
		}
		
		if (e.getSource() == mapBtn)
		{
			if (mapOpen == false)
			{
				game.getAudioHandler().playSound("btnPressed.wav");
				mapGUI.showMapGUI();
				mapGUI.updateLocation();
				mapOpen = true;
				
				inventoryOpen = false;
				invPanel.update();
				
				survivorsOpen = false;
				survivorPanel.update();
				
				craftBuildOpen = false;
			}
			else
			{
				game.getAudioHandler().playSound("btnPressed2.wav");
				mapOpen = false;
				mapGUI.update();
			}
		}
		
		if (e.getSource() == craftBuildBtn)
		{
			if (craftBuildOpen == false)
			{
				game.getAudioHandler().playSound("btnPressed.wav");	
				craftBuildPanel.showCraftBuildPanel();
				
				craftBuildOpen = true;
				
				inventoryOpen = false;
				
				survivorsOpen = false;
				survivorPanel.update();
				
				mapOpen = false;
				mapGUI.update();
				
				game.getInventoryManager().setShownInventory(game.getCurrentPlayer().getInventory());
			}
			else
			{
				game.getAudioHandler().playSound("btnPressed2.wav");
				craftBuildOpen = false;
			}
		}
		
		if (e.getSource() == survivorsBtn)
		{
			if (survivorsOpen == false)
			{
				game.getAudioHandler().playSound("btnPressed.wav");
				survivorPanel.setCurrentCharacter(game.getCurrentPlayer().getName());
				survivorPanel.showSurvivorPanel();
				survivorsOpen = true;
				
				mapOpen = false;
				mapGUI.update();
				
				inventoryOpen = false;
				invPanel.update();
				
				craftBuildOpen = false;
			}
			else
			{
				game.getAudioHandler().playSound("btnPressed2.wav");
				survivorsOpen = false;
			}
		}
		
		if (e.getSource() == inventoryBtn)
		{
			if (inventoryOpen == false)
			{
				game.getAudioHandler().playSound("openBag.wav");
				invPanel.showInventoryPanel();
				invPanel.showInventory(game.getCurrentPlayer().getInventory());
				inventoryOpen = true;
				
				mapOpen = false;
				mapGUI.update();
				
				survivorsOpen = false;
				survivorPanel.update();
				
				craftBuildOpen = false;
			}
			else
			{
				game.getAudioHandler().playSound("closeBag.wav");
				inventoryOpen = false;
			}
		}
		
		if (e.getSource() == nextCharBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			int index = 0;
			
			for (int x = 0; x < game.getPlayerManager().getPlayers().size(); x++)
			{
				if (game.getPlayerManager().getPlayers().get(x).getName().equals(currentCharacter))
				{
					index = x;
					break;
				}
			}
			
			if ((index + 1) <= (game.getPlayerManager().getPlayers().size() - 1))
			{
				currentCharacter = game.getPlayerManager().getPlayers().get(index + 1).getName();
			}
			else
			{
				currentCharacter = game.getPlayerManager().getPlayers().get(0).getName();
			}
			
			game.setCurrentPlayer(game.getPlayerManager().getPlayer(currentCharacter));
			game.getInventoryManager().setDefaultInventory(game.getPlayerManager().getPlayer(currentCharacter).getInventory());
			game.setCurrentLocation(game.getLocationManager().getLocation(game.getPlayerManager().getPlayer(currentCharacter).getLocation()));
			
			invMan.storeSlot();
			
			invPanel.removeInventoryPanel();
			invPanel.showInventoryPanel();
			
			craftBuildPanel.removeCraftBuildPanel();
			craftBuildPanel.showCraftBuildPanel();
		}
		
		if (e.getSource() == prevCharBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			int index = 0;
			
			for (int x = 0; x < game.getPlayerManager().getPlayers().size(); x++)
			{
				if (game.getPlayerManager().getPlayers().get(x).getName().equals(currentCharacter))
				{
					index = x;
					break;
				}
			}
			
			if ((index - 1) >= 0)
			{
				currentCharacter = game.getPlayerManager().getPlayers().get(index - 1).getName();
			}
			else
			{
				currentCharacter = game.getPlayerManager().getPlayers().get(game.getPlayerManager().getPlayers().size() - 1).getName();
			}
			
			game.setCurrentPlayer(game.getPlayerManager().getPlayer(currentCharacter));
			game.getInventoryManager().setDefaultInventory(game.getPlayerManager().getPlayer(currentCharacter).getInventory());
			game.setCurrentLocation(game.getLocationManager().getLocation(game.getPlayerManager().getPlayer(currentCharacter).getLocation()));
			
			invMan.storeSlot();
			
			invPanel.removeInventoryPanel();
			invPanel.showInventoryPanel();
			
			craftBuildPanel.removeCraftBuildPanel();
			craftBuildPanel.showCraftBuildPanel();
		}
	}
}
