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
import com.thale.inventory.Inventory;
import com.thale.inventory.InventoryManager;
import com.thale.items.Item;
import com.thale.locations.Location;
import com.thale.locations.LocationManager;

public class MapGUI extends GameAsset implements ActionListener
{
	private final int LOOT_WAIT = 0;
	private final int MAX_LOOTING = 5;
	
	private final int EXPLORE_WAIT = 0;
	
	private Game game;
	private JFrame window;
	private JPanel gameThread;
	private LocationManager locMan;
	private InventoryManager invMan;
	
	// Local Variables
	private boolean mapOpen;
	
	private int windowX;
	private int windowY;
	private int panelYPos = -500;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	private Location currentLocation;
	private int currentLocXPos = 0;
	private int feedbackXPos = 0;
	
	private int fadeValue = 255;
	
	private int updateCount = 0;
	
	private Item[] lootedItems;
	private int[] lootAmount;
	private boolean lootDisabled = false;
	private boolean lootCountCaptured = false;
	private int lootCountFinal = 0;
	private int lootCount = 0;
	private int timesLooted = 0;
	
	private boolean exploreDisabled = false;
	private boolean exploreCountCaptured = false;
	private int exploreCountFinal = 0;
	private int exploreCount = 0;
	
	private String location1 = "";
	private String location2 = "";
	private String location3 = "";
	private String location4 = "";
	
	private Inventory defaultInv;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnDisabled;
	private ImageIcon btnBaseMed;
	private ImageIcon btnHoverMed;
	private ImageIcon btnDisabledMed;
	private ImageIcon btnLocHover;
	private ImageIcon btnLocDisabled;
	private ImageIcon btnLoc;
		
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	private JLabel locationLbl = new JLabel();
	private JLabel currentLocLbl = new JLabel();
	private JSeparator topSep = new JSeparator();
	private JLabel feedbackLbl = new JLabel();
	private JSeparator bottomSep = new JSeparator();
	private JButton exploreBtn = new JButton();
	private JLabel actionPaneLbl = new JLabel();
	private JLabel actionLbl = new JLabel();
	private JSeparator actionSep = new JSeparator();
	private JButton lootBtn = new JButton();
	private JButton compoundBtn = new JButton();
	
	private JButton loc1Btn = new JButton();
	private JButton loc2Btn = new JButton();
	private JButton loc3Btn = new JButton();
	private JButton loc4Btn = new JButton();
	private JLabel locLbl1 = new JLabel();
	private JLabel locLbl2 = new JLabel();
	private JLabel locLbl3 = new JLabel();
	private JLabel locLbl4 = new JLabel();
	
	public MapGUI(Game game) 
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
		btnLocHover = new ImageIcon(game.getRespath() + "locations/locationHover.png");
		btnLocDisabled = new ImageIcon(game.getRespath() + "locations/locationDisabled.png");
		btnLoc = new ImageIcon(game.getRespath() + "locations/locationBtn.png");
		
		window = game.getWindow();
		gameThread = game.getGameThread();
		locMan = game.getLocationManager();
		invMan = game.getInventoryManager();
		
		defaultInv = game.getInventoryManager().getDefaultInventory();
	}
	
	public void createMapGUI()
	{		
		currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
		currentLocXPos = (800 / 2) - (36) - ((currentLocation.getName().length() * 8) / 2);
		
		panelYPos = -500;
		this.setBounds
		((windowX / 2) - 408, panelYPos, 800, 525);
		gameThread.add(this);
		this.setLayout(null);
		this.setVisible(true);
		
		// Location label
		this.add(locationLbl);
		locationLbl.setText("Current Location:");
		locationLbl.setFont(pixelFont);
		locationLbl.setForeground(Color.white);
		
		// Current location label
		this.add(currentLocLbl);
		currentLocLbl.setText(currentLocation.getName());
		currentLocLbl.setFont(pixelFont);
		currentLocLbl.setForeground(Color.white);
		currentLocLbl.setIcon(currentLocation.getIcon());
		
		// Top separator
		this.add(topSep);
		
		// feedback label
		this.add(feedbackLbl);
		feedbackLbl.setText("You've travelled to a " +  currentLocation.getName() + ".");
		feedbackLbl.setFont(pixelFont);
		feedbackLbl.setForeground(new Color(255, 255, 255, fadeValue));
		feedbackXPos = (800 / 2) - (36) - ((feedbackLbl.getText().length() * 8) / 2);
		
		// Bottom separator
		this.add(bottomSep);
				
		// Explore button
		this.add(exploreBtn);
		exploreBtn.addActionListener(this);
		exploreBtn.setBorderPainted(false);
		exploreBtn.setContentAreaFilled(false);
		exploreBtn.setFocusPainted(false);
		exploreBtn.setIcon(btnBase);
		exploreBtn.setRolloverIcon(btnHover);
		exploreBtn.setDisabledIcon(btnDisabled);
		exploreBtn.setText("Explore");
		exploreBtn.setHorizontalTextPosition(JButton.CENTER);
		exploreBtn.setVerticalTextPosition(JButton.CENTER);
		exploreBtn.setFont(pixelFont);
		exploreBtn.setForeground(Color.white);
		exploreBtn.setToolTipText("Explore the world to find new locations.");
		exploreBtn.setVisible(true);
		
		// Action Pane
		this.add(actionPaneLbl);
		actionPaneLbl.setIcon(bgII);
		actionPaneLbl.setText("");
		actionPaneLbl.setVisible(true);
		
		// Action label
		this.add(actionLbl);
		actionLbl.setText("Actions:");
		actionLbl.setFont(pixelFont);
		actionLbl.setForeground(Color.white);
		
		// Action separator
		this.add(actionSep);
		
		// Loot button
		this.add(lootBtn);
		lootBtn.addActionListener(this);
		lootBtn.setBorderPainted(false);
		lootBtn.setContentAreaFilled(false);
		lootBtn.setFocusPainted(false);
		lootBtn.setIcon(btnBase);
		lootBtn.setRolloverIcon(btnHover);
		lootBtn.setDisabledIcon(btnDisabled);
		lootBtn.setText("Loot");
		lootBtn.setHorizontalTextPosition(JButton.CENTER);
		lootBtn.setVerticalTextPosition(JButton.CENTER);
		lootBtn.setFont(pixelFont);
		lootBtn.setForeground(Color.white);
		lootBtn.setToolTipText("Loot the area to get random items.");
		lootBtn.setVisible(true);
		
		compoundBtn.addActionListener(this);
		
		this.setComponentZOrder(lootBtn, 1);
		this.setComponentZOrder(actionLbl, 1);
		this.setComponentZOrder(actionSep, 1);
		
		loc1Btn.addActionListener(this);
		loc2Btn.addActionListener(this);
		loc3Btn.addActionListener(this);
		loc4Btn.addActionListener(this);
	}
	
	public void showMapGUI()
	{	
		currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
		currentLocXPos = (800 / 2) - (36) - ((currentLocation.getName().length() * 8) / 2);
		
		panelYPos = -500;
		this.setBounds
		((windowX / 2) - 408, panelYPos, 800, 525);
		gameThread.add(this);
		gameThread.setComponentZOrder(this, 1);
		this.setLayout(null);
		this.setVisible(true);
		
		// Location label
		this.add(locationLbl);
		locationLbl.setText("Current Location:");
		locationLbl.setFont(pixelFont);
		locationLbl.setForeground(Color.white);
		
		// Current location label
		this.add(currentLocLbl);
		currentLocLbl.setText(currentLocation.getName());
		currentLocLbl.setFont(pixelFont);
		currentLocLbl.setForeground(Color.white);
		currentLocLbl.setIcon(currentLocation.getIcon());
		
		// Top separator
		this.add(topSep);
		
		// Current location label
		this.add(feedbackLbl);
		feedbackLbl.setText("You've travelled to a " +  currentLocation.getName() + ".");
		feedbackLbl.setFont(pixelFont);
		feedbackLbl.setForeground(new Color(255, 255, 255, fadeValue));
		feedbackXPos = (800 / 2) - (36) - ((feedbackLbl.getText().length() * 8) / 2);
		
		// Bottom separator
		this.add(bottomSep);
				
		// Explore button
		this.add(exploreBtn);
		exploreBtn.setBorderPainted(false);
		exploreBtn.setContentAreaFilled(false);
		exploreBtn.setFocusPainted(false);
		exploreBtn.setIcon(btnBase);
		exploreBtn.setRolloverIcon(btnHover);
		exploreBtn.setDisabledIcon(btnDisabled);
		exploreBtn.setText("Explore");
		exploreBtn.setHorizontalTextPosition(JButton.CENTER);
		exploreBtn.setVerticalTextPosition(JButton.CENTER);
		exploreBtn.setFont(pixelFont);
		exploreBtn.setForeground(Color.white);
		exploreBtn.setToolTipText("Explore the world to find new locations.");
		exploreBtn.setVisible(true);
		
		// Action Pane
		this.add(actionPaneLbl);
		actionPaneLbl.setIcon(bgII);
		actionPaneLbl.setText("");
		actionPaneLbl.setVisible(true);
		
		// Action label
		this.add(actionLbl);
		actionLbl.setText("Actions:");
		actionLbl.setFont(pixelFont);
		actionLbl.setForeground(Color.white);
		
		// Action separator
		this.add(actionSep);
		
		// Loot button
		this.add(lootBtn);
		lootBtn.setBorderPainted(false);
		lootBtn.setContentAreaFilled(false);
		lootBtn.setFocusPainted(false);
		lootBtn.setIcon(btnBaseMed);
		lootBtn.setRolloverIcon(btnHoverMed);
		lootBtn.setDisabledIcon(btnDisabledMed);
		lootBtn.setText("Loot");
		lootBtn.setHorizontalTextPosition(JButton.CENTER);
		lootBtn.setVerticalTextPosition(JButton.CENTER);
		lootBtn.setFont(pixelFont);
		lootBtn.setForeground(Color.white);
		lootBtn.setToolTipText("Loot the area to get random items.");
		lootBtn.setVisible(true);
		
		// Compound button
		this.add(compoundBtn);
		compoundBtn.setBorderPainted(false);
		compoundBtn.setContentAreaFilled(false);
		compoundBtn.setFocusPainted(false);
		compoundBtn.setIcon(btnBaseMed);
		compoundBtn.setRolloverIcon(btnHoverMed);
		compoundBtn.setDisabledIcon(btnDisabledMed);
		compoundBtn.setText("Compound");
		compoundBtn.setHorizontalTextPosition(JButton.CENTER);
		compoundBtn.setVerticalTextPosition(JButton.CENTER);
		compoundBtn.setFont(pixelFont);
		compoundBtn.setForeground(Color.white);
		compoundBtn.setToolTipText("Return to the survivor compound.");
		compoundBtn.setVisible(true);
		
		// Location 1 label
		this.add(locLbl1);
		locLbl1.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[0]).getIcon());
		locLbl1.setVisible(true);
		
		// Location 2 label
		this.add(locLbl2);
		locLbl2.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[1]).getIcon());
		locLbl2.setVisible(true);
		
		// Location 3 label
		this.add(locLbl3);
		locLbl3.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[2]).getIcon());
		locLbl3.setVisible(true);
		
		// Location 4 label
		this.add(locLbl4);
		locLbl4.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[3]).getIcon());
		locLbl4.setVisible(true);
		
		// Location 1 button
		this.add(loc1Btn);
		loc1Btn.setBorderPainted(false);
		loc1Btn.setContentAreaFilled(false);
		loc1Btn.setFocusPainted(false);
		loc1Btn.setIcon(btnLoc);
		loc1Btn.setRolloverIcon(btnLocHover);
		loc1Btn.setDisabledIcon(btnLocDisabled);
		loc1Btn.setVisible(true);
		loc1Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[0]);
		this.setComponentZOrder(loc1Btn, 1);
		
		// Location 2 button
		this.add(loc2Btn);
		loc2Btn.setBorderPainted(false);
		loc2Btn.setContentAreaFilled(false);
		loc2Btn.setFocusPainted(false);
		loc2Btn.setIcon(btnLoc);
		loc2Btn.setRolloverIcon(btnLocHover);
		loc2Btn.setDisabledIcon(btnLocDisabled);
		loc2Btn.setVisible(true);
		loc2Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[1]);
		this.setComponentZOrder(loc2Btn, 1);
		
		// Location 3 button
		this.add(loc3Btn);
		loc3Btn.setBorderPainted(false);
		loc3Btn.setContentAreaFilled(false);
		loc3Btn.setFocusPainted(false);
		loc3Btn.setIcon(btnLoc);
		loc3Btn.setRolloverIcon(btnLocHover);
		loc3Btn.setDisabledIcon(btnLocDisabled);
		loc3Btn.setVisible(true);
		loc3Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[2]);
		this.setComponentZOrder(loc3Btn, 1);
		
		// Location 4 button
		this.add(loc4Btn);
		loc4Btn.setBorderPainted(false);
		loc4Btn.setContentAreaFilled(false);
		loc4Btn.setFocusPainted(false);
		loc4Btn.setIcon(btnLoc);
		loc4Btn.setRolloverIcon(btnLocHover);
		loc4Btn.setDisabledIcon(btnLocDisabled);
		loc4Btn.setVisible(true);
		loc4Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[3]);
		this.setComponentZOrder(loc4Btn, 1);
		
		this.setComponentZOrder(lootBtn, 1);
		this.setComponentZOrder(compoundBtn, 1);
		this.setComponentZOrder(actionLbl, 1);
		this.setComponentZOrder(actionSep, 1);
	}
	
	public void removeMapGUI()
	{
		this.removeAll();
		gameThread.remove(this);
		
		this.setVisible(false);
		
		this.updateData(windowX, windowY, btnHeight, btnHeight);
	}
	
	public void updatePanelClose()
	{
		if (mapOpen == true && this.getY() < ((windowY / 2) - 250))
		{
			panelYPos += 30;
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
		}
		
		if (mapOpen == true && this.getY() > ((windowY / 2) - 251))
		{
			this.setBounds
			((windowX / 2) - 408, ((windowY / 2) - 250), 800, 525);
		}
		
		if (mapOpen == false)
		{
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
			
			panelYPos -= 20;
			
			if (this.getY() == -501)
			{
				removeMapGUI();
			}
		}
	}
	
	public void update()
	{	
		currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
		currentLocLbl.setIcon(currentLocation.getIcon());
		currentLocLbl.setText(currentLocation.getName());
		
		locationLbl.setBounds((800 / 2) - 90, 10, 180, 30);
		currentLocLbl.setBounds(currentLocXPos, 40, 250, 30);
		topSep.setBounds((800 / 2) - (300), 80, btnWidth * 5, 5);
		feedbackLbl.setBounds(feedbackXPos, (425), 500, 30);
		bottomSep.setBounds((800 / 2) - (300), (465), btnWidth * 5, 5);
		exploreBtn.setBounds((800 / 2) - (50), (475), btnWidth, btnHeight);
		actionPaneLbl.setBounds(50, (70), 200, 400);
		actionLbl.setBounds(90, (130), 80, 20);
		actionSep.setBounds(62, (160), 123, 5);
		//lootBtn.setBounds(55, (220), 140, btnHeight);
		compoundBtn.setBounds(55, (170), 140, btnHeight);
		
		locLbl1.setBounds(380, 150, 32, 32);
		locLbl2.setBounds(450, 220, 32, 32);
		locLbl3.setBounds(380, 290, 32, 32);
		locLbl4.setBounds(310, 220, 32, 32);
		
		loc1Btn.setBounds(380, 150, 32, 32);
		loc2Btn.setBounds(450, 220, 32, 32);
		loc3Btn.setBounds(380, 290, 32, 32);
		loc4Btn.setBounds(310, 220, 32, 32);
	
		explore();
		travel();
		loot();
		
		defaultInv = game.getInventoryManager().getDefaultInventory();	
	}
	
	public void slowUpdate()
	{
		updateCount++;
		if (updateCount == 10)
		{
			updateCount = 0;
		}
		
		if (fadeValue > 15)
		{
			fadeValue -= 15;
		}
		else
		{
			fadeValue = 0;
		}
		
		feedbackLbl.setForeground(new Color(255, 255, 255, fadeValue));
		
		this.repaint();
	}
	
	public void explore()
	{
		if (game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
			game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
			game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
		{
			locLbl1.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[0]).getIcon());
			locLbl2.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[1]).getIcon());
			locLbl3.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[2]).getIcon());
			locLbl4.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[3]).getIcon());
			
			loc1Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[0]);
			loc2Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[1]);
			loc3Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[2]);
			loc4Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[3]);
			
			if (game.getCurrentPlayer().getMotives().getEnergy() != 0)
			{
				exploreBtn.setText("Explore");
				exploreBtn.setEnabled(true);
				exploreBtn.setToolTipText("Explore the world to find new locations.");
				
				loc1Btn.setEnabled(true);
				loc2Btn.setEnabled(true);
				loc3Btn.setEnabled(true);
				loc4Btn.setEnabled(true);
				
				if (!game.getCurrentPlayer().getLocation().equals("Compound"))
				{
					compoundBtn.setEnabled(true);
				}
				
				if (game.getCurrentPlayer().getTimesLooted() == MAX_LOOTING)
				{
					lootBtn.setText("Looted");
					lootBtn.setToolTipText("This area has no more loot, go somewhere else to loot again.");
					lootBtn.setEnabled(false);
				}
				else
				{
					lootBtn.setText("Loot");
					lootBtn.setEnabled(true);
					lootBtn.setToolTipText("Loot the area to get random items.");
				}
			}
			else
			{
				lootBtn.setText("Fatigued");
				lootBtn.setToolTipText("You cannot loot while fatigued.");
				lootBtn.setEnabled(false);
				
				exploreBtn.setText("Fatigued");
				exploreBtn.setToolTipText("You cannot explore while fatigued.");
				exploreBtn.setEnabled(false);
				
				compoundBtn.setText("Fatigued");
				compoundBtn.setToolTipText("You cannot travel while fatigued.");
				compoundBtn.setEnabled(false);
				
				loc1Btn.setEnabled(false);
				loc2Btn.setEnabled(false);
				loc3Btn.setEnabled(false);
				loc4Btn.setEnabled(false);
			}
			
			currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
			game.setCurrentLocation(currentLocation);
			
			currentLocLbl.setText(currentLocation.getName());
			currentLocLbl.setIcon(currentLocation.getIcon());
			
			currentLocXPos = (800 / 2) - (36) - ((currentLocation.getName().length() * 8) / 2);
		}
		
		if (game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == true)
		{
			exploreBtn.setText("Wait...");
			exploreBtn.setEnabled(false);
			exploreBtn.setToolTipText("You are looking for a new area.");
			
			compoundBtn.setEnabled(false);
			
			lootBtn.setText("Wait...");
			lootBtn.setEnabled(false);
			lootBtn.setToolTipText("You can't loot while exploring.");
			
			loc1Btn.setEnabled(false);
			loc2Btn.setEnabled(false);
			loc3Btn.setEnabled(false);
			loc4Btn.setEnabled(false);
		}
	}
	
	public void travel()
	{
		if (game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
			game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
			game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
		{
			locLbl1.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[0]).getIcon());
			locLbl2.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[1]).getIcon());
			locLbl3.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[2]).getIcon());
			locLbl4.setIcon(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocations()[3]).getIcon());
			
			loc1Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[0]);
			loc2Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[1]);
			loc3Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[2]);
			loc4Btn.setToolTipText("Travel to a " + game.getCurrentPlayer().getLocations()[3]);
			
			if (game.getCurrentPlayer().getMotives().getEnergy() != 0)
			{
				exploreBtn.setText("Explore");
				exploreBtn.setEnabled(true);
				exploreBtn.setToolTipText("Explore the world to find new locations.");
				
				loc1Btn.setEnabled(true);
				loc2Btn.setEnabled(true);
				loc3Btn.setEnabled(true);
				loc4Btn.setEnabled(true);
				
				if (!game.getCurrentPlayer().getLocation().equals("Compound"))
				{
					compoundBtn.setEnabled(true);
				}
				
				if (game.getCurrentPlayer().getTimesLooted() == MAX_LOOTING)
				{
					lootBtn.setText("Looted");
					lootBtn.setToolTipText("This area has no more loot, go somewhere else to loot again.");
					lootBtn.setEnabled(false);
				}
				else
				{
					lootBtn.setText("Loot");
					lootBtn.setEnabled(true);
					lootBtn.setToolTipText("Loot the area to get random items.");
				}
			}
			else
			{
				lootBtn.setText("Fatigued");
				lootBtn.setToolTipText("You cannot loot while fatigued.");
				lootBtn.setEnabled(false);
				
				exploreBtn.setText("Fatigued");
				exploreBtn.setToolTipText("You cannot explore while fatigued.");
				exploreBtn.setEnabled(false);
				
				compoundBtn.setText("Fatigued");
				compoundBtn.setToolTipText("You cannot travel while fatigued.");
				compoundBtn.setEnabled(false);
				
				loc1Btn.setEnabled(false);
				loc2Btn.setEnabled(false);
				loc3Btn.setEnabled(false);
				loc4Btn.setEnabled(false);
			}
			
			currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
			game.setCurrentLocation(currentLocation);
			
			currentLocLbl.setText(currentLocation.getName());
			currentLocLbl.setIcon(currentLocation.getIcon());
			
			currentLocXPos = (800 / 2) - (36) - ((currentLocation.getName().length() * 8) / 2);
		}
		
		if (game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == true)
		{
			exploreBtn.setText("Wait...");
			exploreBtn.setEnabled(false);
			exploreBtn.setToolTipText("You are currently travelling to a destination.");
			
			compoundBtn.setEnabled(false);
			
			lootBtn.setText("Wait...");
			lootBtn.setEnabled(false);
			lootBtn.setToolTipText("You can't loot while travelling.");
			
			loc1Btn.setEnabled(false);
			loc2Btn.setEnabled(false);
			loc3Btn.setEnabled(false);
			loc4Btn.setEnabled(false);
		}
	}
	
	public void loot()
	{
		if (game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false && 
			game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
			game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
		{
			if (game.getCurrentPlayer().getMotives().getEnergy() != 0)
			{
				exploreBtn.setText("Explore");
				exploreBtn.setEnabled(true);
				exploreBtn.setToolTipText("Explore the world to find new locations.");
				
				loc1Btn.setEnabled(true);
				loc2Btn.setEnabled(true);
				loc3Btn.setEnabled(true);
				loc4Btn.setEnabled(true);
				
				if (!game.getCurrentPlayer().getLocation().equals("Compound"))
				{
					compoundBtn.setEnabled(true);
				}
				
				if (game.getCurrentPlayer().getTimesLooted() == MAX_LOOTING)
				{
					lootBtn.setText("Looted");
					lootBtn.setToolTipText("This area has no more loot, go somewhere else to loot again.");
					lootBtn.setEnabled(false);
				}
				else
				{
					lootBtn.setText("Loot");
					lootBtn.setEnabled(true);
					lootBtn.setToolTipText("Loot the area to get random items.");
				}
			}
			else
			{
				lootBtn.setText("Fatigued");
				lootBtn.setToolTipText("You cannot loot while fatigued.");
				lootBtn.setEnabled(false);
				
				exploreBtn.setText("Fatigued");
				exploreBtn.setToolTipText("You cannot explore while fatigued.");
				exploreBtn.setEnabled(false);
				
				compoundBtn.setText("Fatigued");
				compoundBtn.setToolTipText("You cannot travel while fatigued.");
				compoundBtn.setEnabled(false);
				
				loc1Btn.setEnabled(false);
				loc2Btn.setEnabled(false);
				loc3Btn.setEnabled(false);
				loc4Btn.setEnabled(false);
			}
		}
		
		if (game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == true)
		{
			exploreBtn.setText("Wait...");
			exploreBtn.setEnabled(false);
			exploreBtn.setToolTipText("You cannot explore while looting.");
			
			compoundBtn.setEnabled(false);
			
			lootBtn.setText("Wait...");
			lootBtn.setEnabled(false);
			lootBtn.setToolTipText("You are searching for loot.");
			
			loc1Btn.setEnabled(false);
			loc2Btn.setEnabled(false);
			loc3Btn.setEnabled(false);
			loc4Btn.setEnabled(false);
		}
	}
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
	}
	
	public void updateLocation()
	{
		currentLocation = game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation());
		
		currentLocLbl.setText(currentLocation.getName());
		currentLocLbl.setIcon(currentLocation.getIcon());
		
		currentLocXPos = (800 / 2) - (36) - ((currentLocation.getName().length() * 8) / 2);
	}
	
	public void setMapOpen(boolean mapOpen)
	{
		this.mapOpen = mapOpen;
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
		if (e.getSource() == exploreBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToExploringPlayers(game.getCurrentPlayer());
			game.explore(game.getCurrentPlayer());
			game.getCurrentPlayer().setTimesLooted(0);
		}
		
		if (e.getSource() == lootBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToLootingPlayers(game.getCurrentPlayer());
			game.getLocationManager().loot(game.getCurrentPlayer(), 100);
			game.getCurrentPlayer().setTimesLooted(game.getCurrentPlayer().getTimesLooted() + 1);
		}
		
		if (e.getSource() == compoundBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToTravellingPlayers(game.getCurrentPlayer());
			game.getCurrentPlayer().setTimesLooted(5);
			game.getLocationManager().returnToCompound(game.getCurrentPlayer());
		}
		
		if (e.getSource() == loc1Btn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToTravellingPlayers(game.getCurrentPlayer());
			game.getLocationManager().travel(game.getCurrentPlayer(), game.getCurrentPlayer().getLocations()[0]);
		}
		else if (e.getSource() == loc2Btn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToTravellingPlayers(game.getCurrentPlayer());
			game.getLocationManager().travel(game.getCurrentPlayer(), game.getCurrentPlayer().getLocations()[1]);
		}
		else if (e.getSource() == loc3Btn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToTravellingPlayers(game.getCurrentPlayer());
			game.getLocationManager().travel(game.getCurrentPlayer(), game.getCurrentPlayer().getLocations()[2]);
		}
		else if (e.getSource() == loc4Btn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			game.getLocationManager().addToTravellingPlayers(game.getCurrentPlayer());
			game.getLocationManager().travel(game.getCurrentPlayer(), game.getCurrentPlayer().getLocations()[3]);
		}
	}
}
