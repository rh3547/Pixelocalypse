package com.thale.engine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.thale.crafting.CraftingManager;
import com.thale.inventory.Inventory;
import com.thale.inventory.InventoryManager;
import com.thale.items.ItemManager;
import com.thale.items.UseManager;
import com.thale.locations.Location;
import com.thale.locations.LocationManager;
import com.thale.player.AttributesManager;
import com.thale.player.MotivesManager;
import com.thale.player.Player;
import com.thale.player.PlayerManager;
import com.thale.player.SkillsManager;
import com.thale.scene.SceneManager;

/**
 * Game: 
 *
 * 	The main driver class that "bridges" most of the
 * 	other classes and determines the basic components
 * 	of the game.  All of a games important data is 
 *  accessed through this class.
 * 
 * @author Ryan Hochmuth
 */

public class Game 
{
	private final JFrame window = new JFrame();	// The main game window
	private final ScreenHandler screenHandler;	// The class that handles screen changes
	private final KeyboardListener keyboardListener;	// Handles keyboard input
	private final GameThread gameThread;	// The master game clock.  Updates at 60 fps
	private final GameTick gameTick;	// A secondary game clock.  Updates once a second
	private final GameAsset gameAsset;	// A general class used when something needs to access this class
	private final LocationManager locMan;	// Handles all location management
	private final ItemManager itemMan;	// Handles all item management
	private final InventoryManager invMan;	// Handles all inventory management
	private final PlayerManager playMan;	// Handles all player management
	private final MotivesManager motMan;	// Handles all motive management
	private final SkillsManager skillMan;	// Handles all skill management
	private final AttributesManager attMan;	// Handles all attribute management
	private final CraftingManager craftMan;	// Handles all crafting management
	private final SceneManager sceneMan;	// Handles all scene management
	private final AudioHandler audioHandler;	// Processes all audio
	private final UseManager useMan;	// Handles item usage
	private final SaveGame saveGame;	// Handles game saving
	private final LoadGame loadGame;	// Handles game loading
	
	private final String respath = "D:\\Projects\\Programming\\Java\\Pixelocalypse\\Pixelocalypse\\res\\";	// The path to the games resources (images, audio, etc.)
	private final String filepath = "C:\\";	// The root path to the game install location
	
	private int fps;	// The games frames per second (handled by GameThread)
	private int windowX;	// The current window width
	private int windowY;	// The current window height
	
	private boolean gameLoaded = false;	// Whether the game has finished loading resources
	private String lastSave = "";	// The name of the last saved game. (used for quick save)
	
	private int lootWait = 15;	// The time (in seconds) it takes to loot
	private int exploreWait = 15;	// The time (in seconds) it takes to explore
	
	public Thread threadGame;	// The thread that GameThread runs on
	public Thread threadTick;	// The thread that GameTick runs on
	
	public Location location;	// The currently viewed location
	public Inventory defaultInv;	// The default inventory to open
	
	// Player data
	public Player currentPlayer;	// The player currently being played
	
	/**
	 * This starts a completely new game.  Should only be created once.
	 * Once created, all the necessary components will be created
	 * and the game clocks will begin.
	 * 
	 * @param windowX
	 * @param windowY
	 * @param title
	 * @param fps
	 */
	public Game(int windowX, int windowY, String title, int fps)
	{
		this.fps = fps;
		this.windowX = windowX;
		this.windowY = windowY;
		
		window.setSize(windowX, windowY);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		
		screenHandler = new ScreenHandler(this);
		gameAsset = new GameAsset(this);
		locMan = new LocationManager(this);
		itemMan = new ItemManager(this);
		invMan = new InventoryManager(this);
		playMan = new PlayerManager(this);
		motMan = new MotivesManager(this);
		attMan = new AttributesManager(this);
		skillMan = new SkillsManager(this);
		craftMan = new CraftingManager(this);
		sceneMan = new SceneManager(this);
		audioHandler = new AudioHandler();
		keyboardListener = new KeyboardListener();
		saveGame = new SaveGame(this);
		loadGame = new LoadGame(this);
		useMan = new UseManager(this);
		
		gameThread = new GameThread(this, fps);
		window.add(gameThread);
		
		gameTick = new GameTick(this);
		
		threadGame = new Thread(gameThread);
		threadGame.start();
		
		threadTick = new Thread(gameTick);
		threadTick.start();
		
		window.repaint();
	}
	
	/**
	 * @return is the game loaded
	 */
	public boolean isGameLoaded()
	{
		return gameLoaded;
	}
	
	/**
	 * @param gameLoaded : Set whether or not the game is loaded.
	 * @param screen : The screen to show once the game is loaded.
	 */
	public void setGameLoaded(boolean gameLoaded, Screen screen)
	{
		this.gameLoaded = gameLoaded;
		
		if (gameLoaded == true)
		{
			screenHandler.setGameLoaded(gameLoaded);
			screenHandler.showScreen(screen);
		}
	}
	
	/**
	 * @return the file path
	 */
	public String getFilepath()
	{
		return filepath;
	}
	
	/**
	 * @return the resource path
	 */
	public String getRespath()
	{
		return respath;
	}
	
	/**
	 * @return the window (main JFrame)
	 */
	public JFrame getWindow()
	{
		return window;
	}
	
	/**
	 * @return the GameThread (main JPanel)
	 */
	public JPanel getGameThread()
	{
		return gameThread;
	}
	
	/**
	 * @return the fps
	 */
	public int getFps()
	{
		return fps;
	}
	
	/**
	 * @param fps, the fps to set
	 */
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	/**
	 * @return the ScreenHandler
	 */
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}
	
	/**
	 * @return the KeyboardListener
	 */
	public KeyboardListener getKeyboardListener()
	{
		return keyboardListener;
	}
	
	/**
	 * @return the AudioHandler
	 */
	public AudioHandler getAudioHandler()
	{
		return audioHandler;
	}
	
	/**
	 * @return the SaveGame
	 */
	public SaveGame getSaveGame()
	{
		return saveGame;
	}
	
	/**
	 * @return the LoadGame
	 */
	public LoadGame getLoadGame()
	{
		return loadGame;
	}
	
	/**
	 * @return the window X size
	 */
	public int getWindowX()
	{
		return windowX;
	}

	/**
	 * @param windowX, the window X size to set
	 */
	public void setWindowX(int windowX)
	{
		this.windowX = windowX;
		window.setSize(this.windowX, windowY);
		window.setLocationRelativeTo(null);
	}

	/**
	 * @return the window Y size
	 */
	public int getWindowY()
	{
		return windowY;
	}

	/**
	 * @param windowY, the window Y size to set
	 */
	public void setWindowY(int windowY)
	{
		this.windowY = windowY;
		window.setSize(this.windowX, windowY);
		window.setLocationRelativeTo(null);
	}

	/**
	 * @return the current location
	 */
	public Location getCurrentLocation()
	{
		return location;
	}

	/**
	 * @param location, the current location to set
	 */
	public void setCurrentLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * @return the default inventory
	 */
	public Inventory getDefaultInventory()
	{
		defaultInv = invMan.getDefaultInventory();
		
		return invMan.getDefaultInventory();
	}

	/**
	 * @param defaultInv, the default inventory to set
	 */
	public void setDefaultInventory(Inventory defaultInv)
	{
		this.defaultInv = defaultInv;
	}
	
	/**
	 * @return the last save
	 */
	public String getLastSave()
	{
		return lastSave;
	}

	/**
	 * @param lastSave, the last save to set
	 */
	public void setLastSave(String lastSave)
	{
		this.lastSave = lastSave;
	}

	/**
	 * @return the LocationManager
	 */
	public LocationManager getLocationManager()
	{
		return locMan;
	}

	/**
	 * @return the ItemManager
	 */
	public ItemManager getItemManager()
	{
		return itemMan;
	}

	/**
	 * @return the InventoryManager
	 */
	public InventoryManager getInventoryManager()
	{
		return invMan;
	}

	/**
	 * @return the PlayerManager
	 */
	public PlayerManager getPlayerManager()
	{
		return playMan;
	}
	
	/**
	 * @return the MotivesManager
	 */
	public MotivesManager getMotivesManager()
	{
		return motMan;
	}
	
	/**
	 * @return the AttributesManager
	 */
	public AttributesManager getAttributesManager()
	{
		return attMan;
	}
	
	/**
	 * @return the SkillsManager
	 */
	public SkillsManager getSkillsManager()
	{
		return skillMan;
	}
	
	/**
	 * @return the CraftingManager
	 */
	public CraftingManager getCraftingManager()
	{
		return craftMan;
	}
	
	/**
	 * @return the sceneMan
	 */
	public SceneManager getSceneManager()
	{
		return sceneMan;
	}
	
	/**
	 * @return the Use Manager
	 */
	public UseManager getUseManager()
	{
		return useMan;
	}

	/**
	 * @return The current Player
	 */
	public Player getCurrentPlayer()
	{	
		return currentPlayer;
	}

	/**
	 * @param currentPlayer, The current Player to set
	 */
	public void setCurrentPlayer(Player currentPlayer)
	{
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * @return The explore wait time in seconds
	 */
	public int getExploreWait()
	{	
		return exploreWait;
	}

	/**
	 * @param exploreWait, The explore wait time in seconds to set
	 */
	public void setExploreWait(int exploreWait)
	{
		this.exploreWait = exploreWait;
	}
	
	/**
	 * @return The loot wait time in seconds
	 */
	public int getLootWait()
	{	
		return lootWait;
	}

	/**
	 * @param lootWait, The loot wait time in seconds to set
	 */
	public void setLootWait(int lootWait)
	{
		this.lootWait = lootWait;
	}

	public void explore(Player player)
	{
		locMan.explore(player);
	}
}
