package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.thale.engine.Game;
import com.thale.engine.Screen;
import com.thale.engine.ScreenHandler;
import com.thale.inventory.InventoryManager;
import com.thale.inventory.Slot;
import com.thale.items.ItemManager;
import com.thale.locations.LocationManager;

public class ScreenPixelocalypse extends Screen implements ActionListener
{
	private final int BUTTON_WIDTH = 120;
	private final int BUTTON_HEIGHT = 30;
	
	private int windowX = this.getScreenHandler().getGame().getWindowX();
	private int windowY = this.getScreenHandler().getGame().getWindowY();
	
	private Game game = this.getScreenHandler().getGame();
	private LocationManager locMan = game.getLocationManager();
	private ItemManager itemMan = game.getItemManager();
	private InventoryManager invMan = game.getInventoryManager();
	
	private JFrame window = game.getWindow();
	private JPanel gameThread = game.getGameThread();
	
	/********************************
	 * 		   Local Variables
	 *******************************/
	private int mouseX = 0;
	private int mouseY = 0;
	
	private Slot mouseSlot;
	private JLabel mouseSlotIcon = new JLabel();
	private JLabel mouseSlotLbl = new JLabel();
	
	private int tickCount = 0;
	private boolean menuOpen = false;
	private boolean mapOpen = false;
	private boolean inventoryOpen = false;
	private boolean survivorsOpen = false;
	private boolean craftBuildOpen = false;
	
	private int decrementCount = 0;
	
	/********************************
	 * 		   Player Variables
	 *******************************/
	private int health;
	private int energy;
	private int hunger;
	private int thirst;
	private int xp;
	
	/********************************
	 * 		   Local Objects
	 *******************************/
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	private Font titleFont = new Font("Minecraftia", Font.PLAIN, 18);
	
	private ImageIcon halfHeartII = new ImageIcon(game.getRespath() + "gui/playerStats/halfHeart.png");
	private Image halfHeart = halfHeartII.getImage();
	private ImageIcon fullHeartII = new ImageIcon(game.getRespath() + "gui/playerStats/fullHeart.png");
	private Image fullHeart = fullHeartII.getImage();
	
	private ImageIcon halfEnergyII = new ImageIcon(game.getRespath() + "gui/playerStats/halfEnergy.png");
	private Image halfEnergy = halfEnergyII.getImage();
	private ImageIcon fullEnergyII = new ImageIcon(game.getRespath() + "gui/playerStats/fullEnergy.png");
	private Image fullEnergy = fullEnergyII.getImage();
	
	private ImageIcon halfHungerII = new ImageIcon(game.getRespath() + "gui/playerStats/halfHunger.png");
	private Image halfHunger = halfHungerII.getImage();
	private ImageIcon fullHungerII = new ImageIcon(game.getRespath() + "gui/playerStats/fullHunger.png");
	private Image fullHunger = fullHungerII.getImage();
	
	private ImageIcon halfThirstII = new ImageIcon(game.getRespath() + "gui/playerStats/halfThirst.png");
	private Image halfThirst = halfThirstII.getImage();
	private ImageIcon fullThirstII = new ImageIcon(game.getRespath() + "gui/playerStats/fullThirst.png");
	private Image fullThirst = fullThirstII.getImage();
	
	private ImageIcon emptyXpII = new ImageIcon(game.getRespath() + "gui/playerStats/emptyXp.png");
	private Image emptyXp = emptyXpII.getImage();
	private ImageIcon halfXpII = new ImageIcon(game.getRespath() + "gui/playerStats/halfXp.png");
	private Image halfXp = halfXpII.getImage();
	private ImageIcon fullXpII = new ImageIcon(game.getRespath() + "gui/playerStats/fullXp.png");
	private Image fullXp = fullXpII.getImage();
	
	// MenuPanel
	public MenuPanel menuPanel = new MenuPanel(game);
	
	// MapGUI
	public MapGUI mapGUI = new MapGUI(game);
	
	// InventoryPanel
	public InventoryPanel invPanel = new InventoryPanel(game);
	
	// SurvivorPanel
	public SurvivorPanel survivorPanel = new SurvivorPanel(game);
	
	// CraftBuildPanel
	public CraftBuildPanel craftBuildPanel = new CraftBuildPanel(game);
		
	// ActionPanel
	public ActionPanel actionPanel = new ActionPanel(game, menuPanel, mapGUI, invPanel, survivorPanel, craftBuildPanel);
	
	// DevPanel
	public DevPanel devPanel = new DevPanel(game);
	
	public ScreenPixelocalypse(ScreenHandler screenHandler) 
	{
		super(screenHandler);
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
	      
	    window.setIconImage(img);
	    
		window.repaint();
		
		/*
		 * 	Edit window settings
		 */
		window.setLayout(null);
		
		actionPanel.createPanel();
		
		menuPanel.createMenuPanel();
		menuPanel.removeMenuPanel();
		
		mapGUI.createMapGUI();
		mapGUI.removeMapGUI();
		
		invPanel.createInventoryPanel();
		
		survivorPanel.createSurvivorPanel();
		
		craftBuildPanel.createCraftBuildPanel();
		craftBuildPanel.removeCraftBuildPanel();
		
		devPanel.createDevPanel();
		
		game.getSceneManager().showScene(game.getCurrentPlayer());
		
		 //mouseSlot = new Slot(game, 0, itemMan.getItem("Null"), 0, 0, 
				 //null, 
				 //mouseSlotIcon, 
				 //mouseSlotLbl);
		 //gameThread.add(mouseSlot);
		 //gameThread.setComponentZOrder(mouseSlot, 1);
		 //mouseSlot.setBounds(0, 0, 48, 56);
		
		Action openDevPanel = new OpenDevPanel();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('`'), "openDevPanel");
		gameThread.getActionMap().put("openDevPanel", openDevPanel);
		
		Action moveRight = new MoveRight();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "moveRight");
		gameThread.getActionMap().put("moveRight", moveRight);
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		gameThread.getActionMap().put("moveRight", moveRight);
		
		Action moveDown = new MoveDown();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "moveDown");
		gameThread.getActionMap().put("moveDown", moveDown);
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
		gameThread.getActionMap().put("moveDown", moveDown);
		
		Action moveLeft = new MoveLeft();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "moveLeft");
		gameThread.getActionMap().put("moveLeft", moveLeft);
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		gameThread.getActionMap().put("moveLeft", moveLeft);
		
		Action moveUp = new MoveUp();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "moveUp");
		gameThread.getActionMap().put("moveUp", moveUp);
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "moveUp");
		gameThread.getActionMap().put("moveUp", moveUp);
		
		Action characterAction = new CharacterAction();
		gameThread.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('e'), "characterAction");
		gameThread.getActionMap().put("characterAction", characterAction);
	}

	@Override
	public void onUpdate() 
	{
		// Update window sizes
		windowX = window.getWidth();
		windowY = window.getHeight();
				
		devPanel.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);
		devPanel.update();
		
		// Player data updates
		game.getMotivesManager().healthCheck();
		game.getPlayerManager().xpCheck();
		
		health = game.getCurrentPlayer().getMotives().getHealth();
		energy = game.getCurrentPlayer().getMotives().getEnergy();
		hunger = game.getCurrentPlayer().getMotives().getHunger();
		thirst = game.getCurrentPlayer().getMotives().getThirst();
		xp = game.getCurrentPlayer().getXp();
		
		/*
		 * 	Update Component Sizes, data, and locations
		 */
		// ActionPanel size/position updates
		actionPanel.updateData(windowX, windowY);
		actionPanel.updatePosition();
		
		mapOpen = actionPanel.isMapOpen();
		mapGUI.setMapOpen(mapOpen);
		craftBuildOpen = actionPanel.isCraftBuildOpen();
		craftBuildPanel.setCraftBuildPanelOpen(craftBuildOpen);
		inventoryOpen = actionPanel.isInventoryOpen();
		invPanel.setInventoryPanelOpen(inventoryOpen);
		survivorsOpen = actionPanel.isSurvivorsOpen();
		survivorPanel.setSurvivorPanelOpen(survivorsOpen);
		
		mapGUI.updatePanelClose();
		craftBuildPanel.updatePanelClose();
		invPanel.updatePanelClose();
		survivorPanel.updatePanelClose();
		
		// MapGUI updates
		if (mapOpen == true)
		{
			mapGUI.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);
			mapGUI.update();
		}
		
		// CraftBuildPanel updates
		if (craftBuildOpen == true)
		{
			craftBuildPanel.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);	
			craftBuildPanel.update();
		}
		
		// InventoryPanel updates
		if (inventoryOpen == true)
		{
			invPanel.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);	
			invPanel.update();
		}
		
		// SurvivorPanel updates
		if (survivorsOpen == true)
		{
			survivorPanel.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);	
			survivorPanel.update();
		}
		
		// MenuPanel updates
		menuOpen = menuPanel.isMenuOpen();
		
		// MenuPanel updates
		if (menuOpen == true || menuPanel.getY() > -150)
		{
			menuPanel.updateData(windowX, windowY, BUTTON_WIDTH, BUTTON_HEIGHT);
			menuPanel.updatePosition();
		}
		
		// ActionPanel menu update
		actionPanel.setMenuOpen(menuOpen);
	}

	@Override
	public void onTick() 
	{
		tickCount++;
		
		mapGUI.slowUpdate();
		
		decrementCount++;
		
		if (decrementCount == 5)
		{
			for (int x = 0; x < game.getPlayerManager().getPlayers().size(); x++)
			{
				if (game.getPlayerManager().getPlayers().get(x).getMotives().getHunger() > 0)
				{
					game.getPlayerManager().getPlayers().get(x).getMotives()
					.setHunger(game.getPlayerManager().getPlayers().get(x).getMotives().getHunger() - 1);
				}
				
				if (game.getPlayerManager().getPlayers().get(x).getMotives().getThirst() > 0)
				{
					game.getPlayerManager().getPlayers().get(x).getMotives()
					.setThirst(game.getPlayerManager().getPlayers().get(x).getMotives().getThirst() - 1);
				}
				
				if (game.getPlayerManager().getPlayers().get(x).getMotives().getHunger() <= 0)
				{
					game.getPlayerManager().getPlayers().get(x).getMotives()
					.setHealth(game.getPlayerManager().getPlayers().get(x).getMotives().getHealth() - 1);
				}
				
				if (game.getPlayerManager().getPlayers().get(x).getMotives().getThirst() <= 0)
				{
					game.getPlayerManager().getPlayers().get(x).getMotives()
					.setHealth(game.getPlayerManager().getPlayers().get(x).getMotives().getHealth() - 1);
				}
			}
			
			decrementCount = 0;
		}
	}

	@Override
	public void onDraw(Graphics2D g2d) 
	{	
		if (game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == true)
		{
			g2d.setFont(titleFont);
			g2d.setColor(Color.white);
			g2d.drawString("Looting...", (windowX /2) - (10 * 5), (windowY / 2) + 350);
		}
		
		switch (health / 10)
		{
			case 10: 	g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 4), 10, 16, 16, null);
						break;
						
			case 9:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 10, 16, 16, null);
						g2d.drawImage(halfHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 4), 10, 16, 16, null);
						break;
						
			case 8:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 10, 16, 16, null);
						break;
						
			case 7:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						g2d.drawImage(halfHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 10, 16, 16, null);
						break;
						
			case 6:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						break;
						
			case 5:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						g2d.drawImage(halfHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 10, 16, 16, null);
						break;
						
			case 4:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						break;
						
			case 3:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(halfHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 10, 16, 16, null);
						break;
						
			case 2:		g2d.drawImage(fullHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						break;
						
			case 1:		g2d.drawImage(halfHeart, (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						break;
						
			case 0:		g2d.setFont(pixelFont);
						g2d.setColor(Color.red);
						g2d.drawString("Dead", (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10);
						break;
						
			default:	g2d.setFont(pixelFont);
						g2d.setColor(Color.red);
						g2d.drawString("Dead", (975 + ((15) * (game.getCurrentPlayer().getName().length()))), 10);
						break;
		}
		
		switch (energy)
		{
			case 10: 	g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 5), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 6), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 7), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 8), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 9), 10, 16, 16, null);
						break;
						
			case 9:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 5), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 6), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 7), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 8), 10, 16, 16, null);
						break;
						
			case 8:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 5), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 6), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 7), 10, 16, 16, null);
						break;
						
			case 7:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 5), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 6), 10, 16, 16, null);
						break;
						
			case 6:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 5), 10, 16, 16, null);
						break;
						
			case 5:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 4), 10, 16, 16, null);
						break;
						
			case 4:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 3), 10, 16, 16, null);
						break;
						
			case 3:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 2), 10, 16, 16, null);
						break;
						
			case 2:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))) + (10 * 1), 10, 16, 16, null);
						break;
						
			case 1:		g2d.drawImage(fullEnergy, (1150 + ((15) * (game.getCurrentPlayer().getName().length()))), 10, 16, 16, null);
						break;
						
			case 0:		g2d.setFont(pixelFont);
						g2d.setColor(Color.yellow);
						g2d.drawString("Fatigued", (1155 + ((15) * (game.getCurrentPlayer().getName().length()))), 25);
						break;
		}
		
		switch (hunger / 10)
		{
			case 10: 	g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 4), 42, 16, 16, null);
						break;
						
			case 9:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 42, 16, 16, null);
						g2d.drawImage(halfHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 4), 42, 16, 16, null);
						break;
						
			case 8:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 42, 16, 16, null);
						break;
						
			case 7:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						g2d.drawImage(halfHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 3), 42, 16, 16, null);
						break;
						
			case 6:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						break;
						
			case 5:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						g2d.drawImage(halfHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 2), 42, 16, 16, null);
						break;
						
			case 4:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						break;
						
			case 3:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						g2d.drawImage(halfHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))) + (15 * 1), 42, 16, 16, null);
						break;
						
			case 2:		g2d.drawImage(fullHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						break;
						
			case 1:		g2d.drawImage(halfHunger, (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 42, 16, 16, null);
						break;
						
			case 0:		g2d.setFont(pixelFont);
						g2d.setColor(Color.orange);
						g2d.drawString("Starving", (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 58);
						break;
						
			default:	g2d.setFont(pixelFont);
						g2d.setColor(Color.orange);
						g2d.drawString("Starving", (980 + ((15) * (game.getCurrentPlayer().getName().length()))), 58);
						break;
		}
		
		switch (thirst / 10)
		{
			case 10: 	g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 3), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 4), 44, 16, 16, null);
						break;
						
			case 9:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 3), 44, 16, 16, null);
						g2d.drawImage(halfThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 4), 44, 16, 16, null);
						break;
						
			case 8:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 3), 44, 16, 16, null);
						break;
						
			case 7:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						g2d.drawImage(halfThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 3), 44, 16, 16, null);
						break;
						
			case 6:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						break;
						
			case 5:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						g2d.drawImage(halfThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 2), 44, 16, 16, null);
						break;
						
			case 4:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						break;
						
			case 3:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						g2d.drawImage(halfThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))) + (12 * 1), 44, 16, 16, null);
						break;
						
			case 2:		g2d.drawImage(fullThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						break;
						
			case 1:		g2d.drawImage(halfThirst, (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 44, 16, 16, null);
						break;
						
			case 0:		g2d.setFont(pixelFont);
						g2d.setColor(Color.blue);
						g2d.drawString("Dehydrated", (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 58);
						break;
						
			default:	g2d.setFont(pixelFont);
						g2d.setColor(Color.blue);
						g2d.drawString("Dehydrated", (1140 + ((15) * (game.getCurrentPlayer().getName().length()))), 58);
						break;		
		}
		
		if (xp >= 0 && xp < 100)
		{
			g2d.drawImage(halfXp, (830), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 100 && xp < 200)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 200 && xp < 300)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(halfXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 300 && xp < 400)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 400 && xp < 500)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(halfXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 500 && xp < 600)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 600 && xp < 700)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(halfXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 700 && xp < 800)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(emptyXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 800 && xp < 1000)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(halfXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
		if (xp >= 1000)
		{
			g2d.drawImage(fullXp, (830), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 1)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 2)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 3)), 44, 16, 16, null);
			g2d.drawImage(fullXp, (830 + (16 * 4)), 44, 16, 16, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	class OpenDevPanel extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.print("\n\nDeveloper Panel Opened");
			devPanel.showDevPanel();
		}
	}
	
	class MoveRight extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int index = 0;
			
			for (int x = 0; x < game.getCurrentPlayer().getScene().getSprites().size(); x++)
			{
				if (game.getCurrentPlayer().getScene().getSprites().get(x).getName().equals("Character"))
				{
					index = x;
					break;
				}
			}
			
			if (game.getCurrentPlayer().getMotives().getEnergy() > 0 && game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
			{
				game.getCurrentPlayer().getScene().getSprites().get(index).setImageStr(game.getRespath() + "scenes/characterRight.png");
				game.getCurrentPlayer().getScene().getSprites().get(index).resetImage();
				
				game.getCurrentPlayer().getScene().getSprites().get(index).setPosX(
				game.getCurrentPlayer().getScene().getSprites().get(index).getPosX() + 128);
				
				game.getCurrentPlayer().getScene().getSprites().get(index).updatePosition();
			}
		}
	}
	
	class MoveDown extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int index = 0;
			
			for (int x = 0; x < game.getCurrentPlayer().getScene().getSprites().size(); x++)
			{
				if (game.getCurrentPlayer().getScene().getSprites().get(x).getName().equals("Character"))
				{
					index = x;
					break;
				}
			}
			
			if (game.getCurrentPlayer().getMotives().getEnergy() > 0 && game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
			{
				game.getCurrentPlayer().getScene().getSprites().get(index).setImageStr(game.getRespath() + "scenes/characterDown.png");
				game.getCurrentPlayer().getScene().getSprites().get(index).resetImage();
				
				game.getCurrentPlayer().getScene().getSprites().get(index).setPosY(
				game.getCurrentPlayer().getScene().getSprites().get(index).getPosY() + 128);
				
				game.getCurrentPlayer().getScene().getSprites().get(index).updatePosition();
			}
		}
	}
	
	class MoveLeft extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int index = 0;
			
			for (int x = 0; x < game.getCurrentPlayer().getScene().getSprites().size(); x++)
			{
				if (game.getCurrentPlayer().getScene().getSprites().get(x).getName().equals("Character"))
				{
					index = x;
					break;
				}
			}
			
			if (game.getCurrentPlayer().getMotives().getEnergy() > 0 && game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
			{
				game.getCurrentPlayer().getScene().getSprites().get(index).setImageStr(game.getRespath() + "scenes/characterLeft.png");
				game.getCurrentPlayer().getScene().getSprites().get(index).resetImage();
				
				game.getCurrentPlayer().getScene().getSprites().get(index).setPosX(
				game.getCurrentPlayer().getScene().getSprites().get(index).getPosX() - 128);
				
				game.getCurrentPlayer().getScene().getSprites().get(index).updatePosition();
			}
		}
	}
	
	class MoveUp extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int index = 0;
			
			for (int x = 0; x < game.getCurrentPlayer().getScene().getSprites().size(); x++)
			{
				if (game.getCurrentPlayer().getScene().getSprites().get(x).getName().equals("Character"))
				{
					index = x;
					break;
				}
			}
			
			if (game.getCurrentPlayer().getMotives().getEnergy() > 0 && game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
			{
				game.getCurrentPlayer().getScene().getSprites().get(index).setImageStr(game.getRespath() + "scenes/characterUp.png");
				game.getCurrentPlayer().getScene().getSprites().get(index).resetImage();
				
				game.getCurrentPlayer().getScene().getSprites().get(index).setPosY(
				game.getCurrentPlayer().getScene().getSprites().get(index).getPosY() - 128);
				
				game.getCurrentPlayer().getScene().getSprites().get(index).updatePosition();
			}
		}
	}
	
	class CharacterAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (game.getCurrentPlayer().getMotives().getEnergy() > 0 && game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == false &&
					game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == false)
			{
				game.getSceneManager().checkPosition(game.getCurrentPlayer());
			}
		}
	}
}