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
import com.thale.player.PlayerManager;

public class SurvivorPanel extends GameAsset implements ActionListener
{
	private int BUTTON_SPACE = 10;
	
	private Game game;
	private JFrame window;
	private JPanel gameThread;
	private InventoryManager invMan;
	private ItemManager itemMan;
	private PlayerManager playerMan;
	
	// Local Variables
	private int windowX;
	private int windowY;
	private boolean survivorPanelOpen;
	private int panelYPos = -500;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	private String currentCharacter = "John Doe";
	
	private boolean basicInfoTabOpen = true;
	private boolean attributesTabOpen = false;
	private boolean skillsTabOpen = false;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon paneII;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnDisabled;
	private ImageIcon btnBaseMed;
	private ImageIcon btnHoverMed;
	private ImageIcon btnRightBase;
	private ImageIcon btnRightHover;
	private ImageIcon btnLeftBase;
	private ImageIcon btnLeftHover;
	private ImageIcon btnTabMed;
	private ImageIcon btnTabHoverMed;
	private ImageIcon btnPlusBase;
	private ImageIcon btnPlusHover;
	
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	private JLabel characterLbl = new JLabel();
	private JSeparator topSep = new JSeparator();
	private JButton nextCharBtn = new JButton();
	private JButton prevCharBtn = new JButton();
	
	private JLabel titleLbl = new JLabel();
	private JSeparator topSep2 = new JSeparator();
	private JLabel paneLbl = new JLabel();
	
	private JButton basicInfoTab = new JButton();
	private JLabel locationLbl = new JLabel();
	private JLabel actionLbl = new JLabel();
	
	private JButton attributesTab = new JButton();
	private JLabel upgradePointsLbl = new JLabel();
	private JLabel strengthLbl = new JLabel();
	private JButton strengthBtn = new JButton();
	private JLabel constitutionLbl = new JLabel();
	private JButton constitutionBtn = new JButton();
	private JLabel agilityLbl = new JLabel();
	private JButton agilityBtn = new JButton();
	private JLabel intelligenceLbl = new JLabel();
	private JButton intelligenceBtn = new JButton();
	private JLabel charismaLbl = new JLabel();
	private JButton charismaBtn = new JButton();
	
	private JButton skillsTab = new JButton();
	private JLabel lootingLbl = new JLabel();
	private JButton lootingBtn = new JButton();
	
	public SurvivorPanel(Game game)
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		paneII = new ImageIcon(game.getRespath() + "gui/backgrounds/pane.png");
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnDisabled = new ImageIcon(game.getRespath() + "gui/buttons/btnDisabled.png");
		btnBaseMed = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseMed.png");
		btnHoverMed = new ImageIcon(game.getRespath() + "gui/buttons/btnHoverMed.png");
		btnRightBase = new ImageIcon(game.getRespath() + "gui/buttons/rightBase.png");
		btnRightHover = new ImageIcon(game.getRespath() + "gui/buttons/rightHover.png");
		btnLeftBase = new ImageIcon(game.getRespath() + "gui/buttons/leftBase.png");
		btnLeftHover = new ImageIcon(game.getRespath() + "gui/buttons/leftHover.png");
		btnTabMed = new ImageIcon(game.getRespath() + "gui/buttons/tabMed.png");
		btnTabHoverMed = new ImageIcon(game.getRespath() + "gui/buttons/tabHoverMed.png");
		btnPlusBase  = new ImageIcon(game.getRespath() + "gui/buttons/plusBase.png");
		btnPlusHover  = new ImageIcon(game.getRespath() + "gui/buttons/plusHover.png");
		
		this.window = game.getWindow();
		this.gameThread = game.getGameThread();
		this.invMan = game.getInventoryManager();
		this.itemMan = game.getItemManager();
		this.playerMan = game.getPlayerManager();
		
		game.getAttributesManager().checkStrength(game.getCurrentPlayer());
		game.getAttributesManager().checkConstitution(game.getCurrentPlayer());
		game.getAttributesManager().checkAgility(game.getCurrentPlayer());
		game.getAttributesManager().checkIntelligence(game.getCurrentPlayer());
		game.getAttributesManager().checkCharisma(game.getCurrentPlayer());
		
		game.getSkillsManager().checkLooting(game.getCurrentPlayer());
	}
	
	public void createSurvivorPanel()
	{	
		nextCharBtn.addActionListener(this);
		prevCharBtn.addActionListener(this);
		
		basicInfoTab.addActionListener(this);
		attributesTab.addActionListener(this);
		skillsTab.addActionListener(this);
		
		strengthBtn.addActionListener(this);
		constitutionBtn.addActionListener(this);
		agilityBtn.addActionListener(this);
		intelligenceBtn.addActionListener(this);
		charismaBtn.addActionListener(this);
		
		lootingBtn.addActionListener(this);
	}
	
	public void showSurvivorPanel()
	{	
		panelYPos = -500;
		gameThread.add(this);
		gameThread.setComponentZOrder(this, 1);
		this.setBounds((windowX / 2) - 408, panelYPos, 800, 525);
		this.setLayout(null);
		this.setVisible(true);
		
		// Character label
		this.add(characterLbl);
		characterLbl.setText(currentCharacter);
		characterLbl.setFont(pixelFont);
		characterLbl.setForeground(Color.white);
		
		// Top separator
		this.add(topSep);
		
		// Next character button
		this.add(nextCharBtn);
		nextCharBtn.setBorderPainted(false);
		nextCharBtn.setContentAreaFilled(false);
		nextCharBtn.setFocusPainted(false);
		nextCharBtn.setIcon(btnBase);
		nextCharBtn.setRolloverIcon(btnHover);
		nextCharBtn.setDisabledIcon(btnDisabled);
		nextCharBtn.setText("Next");
		nextCharBtn.setHorizontalTextPosition(JButton.CENTER);
		nextCharBtn.setVerticalTextPosition(JButton.CENTER);
		nextCharBtn.setFont(pixelFont);
		nextCharBtn.setForeground(Color.white);
		nextCharBtn.setToolTipText("Switch to the next survivor.");
		nextCharBtn.setVisible(true);
		
		// Previous character button
		this.add(prevCharBtn);
		prevCharBtn.setBorderPainted(false);
		prevCharBtn.setContentAreaFilled(false);
		prevCharBtn.setFocusPainted(false);
		prevCharBtn.setIcon(btnBase);
		prevCharBtn.setRolloverIcon(btnHover);
		prevCharBtn.setDisabledIcon(btnDisabled);
		prevCharBtn.setText("Prev.");
		prevCharBtn.setHorizontalTextPosition(JButton.CENTER);
		prevCharBtn.setVerticalTextPosition(JButton.CENTER);
		prevCharBtn.setFont(pixelFont);
		prevCharBtn.setForeground(Color.white);
		prevCharBtn.setToolTipText("Switch to the previous survivor.");
		prevCharBtn.setVisible(true);
		
		// Pane label
		this.add(paneLbl);
		paneLbl.setIcon(paneII);
		paneLbl.setFont(pixelFont);
		paneLbl.setForeground(Color.white);
		
		// Basic info tab button
		this.add(basicInfoTab);
		basicInfoTab.setBorderPainted(false);
		basicInfoTab.setContentAreaFilled(false);
		basicInfoTab.setFocusPainted(false);
		basicInfoTab.setIcon(btnTabMed);
		basicInfoTab.setRolloverIcon(btnTabHoverMed);
		basicInfoTab.setText("Basic Info");
		basicInfoTab.setHorizontalTextPosition(JButton.CENTER);
		basicInfoTab.setVerticalTextPosition(JButton.CENTER);
		basicInfoTab.setFont(pixelFont);
		basicInfoTab.setForeground(Color.white);
		basicInfoTab.setToolTipText("Check this character's basic info.");
		basicInfoTab.setVisible(true);
		
		// Attributes tab button
		this.add(attributesTab);
		attributesTab.setBorderPainted(false);
		attributesTab.setContentAreaFilled(false);
		attributesTab.setFocusPainted(false);
		attributesTab.setIcon(btnTabMed);
		attributesTab.setRolloverIcon(btnTabHoverMed);
		attributesTab.setText("Attributes");
		attributesTab.setHorizontalTextPosition(JButton.CENTER);
		attributesTab.setVerticalTextPosition(JButton.CENTER);
		attributesTab.setFont(pixelFont);
		attributesTab.setForeground(Color.white);
		attributesTab.setToolTipText("Check this character's attributes.");
		attributesTab.setVisible(true);
		
		// Skills tab button
		this.add(skillsTab);
		skillsTab.setBorderPainted(false);
		skillsTab.setContentAreaFilled(false);
		skillsTab.setFocusPainted(false);
		skillsTab.setIcon(btnTabMed);
		skillsTab.setRolloverIcon(btnTabHoverMed);
		skillsTab.setText("Skills");
		skillsTab.setHorizontalTextPosition(JButton.CENTER);
		skillsTab.setVerticalTextPosition(JButton.CENTER);
		skillsTab.setFont(pixelFont);
		skillsTab.setForeground(Color.white);
		skillsTab.setToolTipText("Check this character's skills.");
		skillsTab.setVisible(true);
		
		// Title label
		this.add(titleLbl);
		titleLbl.setText("Basic Info:");
		titleLbl.setFont(pixelFont);
		titleLbl.setForeground(Color.white);
				
		// Top separator 2
		this.add(topSep2);
		
		// Location label
		this.add(locationLbl);
		locationLbl.setText("Location: " + game.getCurrentPlayer().getLocation());
		locationLbl.setFont(pixelFont);
		locationLbl.setForeground(Color.white);
		
		// Action label
		this.add(actionLbl);
		actionLbl.setText("Current Action: ");
		actionLbl.setFont(pixelFont);
		actionLbl.setForeground(Color.white);
		
		this.setComponentZOrder(titleLbl, 1);
		this.setComponentZOrder(topSep2, 1);
		this.setComponentZOrder(locationLbl, 1);
		this.setComponentZOrder(actionLbl, 1);
	}
	
	public void removeSurvivorPanel()
	{		
		this.removeAll();
		gameThread.remove(this);
		this.setVisible(false);
	}
	
	public void updatePanelClose()
	{
		if (survivorPanelOpen == true && this.getY() < ((windowY / 2) - 250))
		{
			panelYPos += 30;
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
		}
		
		if (survivorPanelOpen == true && this.getY() > ((windowY / 2) - 251))
		{
			this.setBounds
			((windowX / 2) - 408, ((windowY / 2) - 250), 800, 525);
		}
		
		if (survivorPanelOpen == false)
		{
			this.setBounds
			((windowX / 2) - 408, panelYPos, 800, 525);
			
			panelYPos -= 20;
			
			if (this.getY() <= -501)
			{
				removeSurvivorPanel();
			}
		}
	}
	
	public void update()
	{
		this.setBounds((windowX / 2) - 408, panelYPos, 800, 525);
		
		characterLbl.setText(currentCharacter);
		characterLbl.setBounds(((800 / 2) - (characterLbl.getText().length() * 5)), 40, 250, 30);
		topSep.setBounds((800 / 2) - (300), 80, 600, 5);
		
		nextCharBtn.setBounds(620, 40, btnWidth, btnHeight);
		prevCharBtn.setBounds(60, 40, btnWidth, btnHeight);
		
		paneLbl.setBounds(35, 130, 730, 370);
		
		basicInfoTab.setBounds(60, 100, 140, btnHeight);
		attributesTab.setBounds((800 / 2) - 70, 100, 140, btnHeight);
		skillsTab.setBounds((800 / 2) + 205, 100, 140, btnHeight);
		
		titleLbl.setBounds(((800 / 2) - (titleLbl.getText().length() * 5)), 150, 250, 30);
		topSep2.setBounds((800 / 2) - (300), 180, 600, 5);
		
		// Info tab
		locationLbl.setText("Location: " + game.getCurrentPlayer().getLocation());
		locationLbl.setBounds(80, 190, 350, 30);
		
		if (game.getLocationManager().isPlayerExploring(game.getCurrentPlayer()) == true)
		{
			actionLbl.setText("Current Action: Exploring new locations");
		}
		else if (game.getLocationManager().isPlayerTravelling(game.getCurrentPlayer()) == true)
		{
			actionLbl.setText("Current Action: Travelling to a location");
		}
		else if (game.getLocationManager().isPlayerLooting(game.getCurrentPlayer()) == true)
		{
			actionLbl.setText("Current Action: Looting at a " + game.getCurrentPlayer().getLocation());
		}
		else
		{
			actionLbl.setText("Current Action: None");
		}
		actionLbl.setBounds(80, 230, 500, 30);
		
		// Attributes tab
		upgradePointsLbl.setBounds(((800 / 2) - (upgradePointsLbl.getText().length() * 5)), 200, 250, 30);
		strengthLbl.setBounds(80, 240, 200, 30);
		strengthLbl.setToolTipText("Strength determines character melee damage and inventory size." +
								   "\n  Next Upgrade: " + game.getAttributesManager().getStrengthText());
		strengthBtn.setBounds(250, 240, 32, 32);
		constitutionLbl.setBounds(80, 270, 200, 30);
		constitutionLbl.setToolTipText("Constitution determines character max health." + 
									   "\n  Next Upgrade: " + game.getAttributesManager().getConstitutionText());
		constitutionBtn.setBounds(250, 270, 32, 32);
		agilityLbl.setBounds(80, 300, 200, 30);
		agilityLbl.setToolTipText("Agility determines character movement speed and a character's chance to dodge attacks." + 
				   				  "\n  Next Upgrade: " + game.getAttributesManager().getAgilityText());
		agilityBtn.setBounds(250, 300, 32, 32);
		intelligenceLbl.setBounds(80, 330, 200, 30);
		intelligenceLbl.setToolTipText("Intelligence determines character max energy." + 
				   					   "\n  Next Upgrade: " + game.getAttributesManager().getIntelligenceText());
		intelligenceBtn.setBounds(250, 330, 32, 32);
		charismaLbl.setBounds(80, 360, 200, 30);
		charismaLbl.setToolTipText("Charisma determines character interactions and trading." + 
				   				   "\n  Next Upgrade: " + game.getAttributesManager().getCharismaText());
		charismaBtn.setBounds(250, 360, 32, 32);
		
		// Skills tab
		lootingLbl.setBounds(80, 240, 200, 30);
		lootingLbl.setToolTipText("The looting skill determines how much loot you recieve each time you loot as well as how long it takes to loot." + 
				   				  "\n  Next Upgrade: " + game.getSkillsManager().getLootingText());
		lootingBtn.setBounds(250, 240, 32, 32);
	}
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
	}
	
	public void updateTabs()
	{
		if (basicInfoTabOpen == true)
		{
			this.remove(upgradePointsLbl);
			this.remove(strengthLbl);
			this.remove(strengthBtn);
			this.remove(constitutionLbl);
			this.remove(constitutionBtn);
			this.remove(agilityLbl);
			this.remove(agilityBtn);
			this.remove(intelligenceLbl);
			this.remove(intelligenceBtn);
			this.remove(charismaLbl);
			this.remove(charismaBtn);
			this.remove(lootingLbl);
			this.remove(lootingBtn);
			
			// Location label
			this.add(locationLbl);
			locationLbl.setText("Location: " + game.getCurrentPlayer().getLocation());
			locationLbl.setFont(pixelFont);
			locationLbl.setForeground(Color.white);
			this.setComponentZOrder(locationLbl, 1);
			
			// Action label
			this.add(actionLbl);
			actionLbl.setText("Current Action: ");
			actionLbl.setFont(pixelFont);
			actionLbl.setForeground(Color.white);
			this.setComponentZOrder(actionLbl, 1);
			
			titleLbl.setText("Basic Info:");
		}
		else if (attributesTabOpen == true)
		{
			this.remove(locationLbl);
			this.remove(actionLbl);
			this.remove(lootingLbl);
			this.remove(lootingBtn);
			
			// Upgrade points label
			this.add(upgradePointsLbl);
			upgradePointsLbl.setText("Upgrade Points: " + game.getCurrentPlayer().getUpgradePoints());
			upgradePointsLbl.setToolTipText("Upgrade points are used to upgrade character attributes and skills.");
			upgradePointsLbl.setFont(pixelFont);
			upgradePointsLbl.setForeground(Color.white);
			this.setComponentZOrder(upgradePointsLbl, 1);
			
			// Strength label
			this.add(strengthLbl);
			strengthLbl.setText("Strength: " + game.getCurrentPlayer().getAttributes().getStrength());
			strengthLbl.setFont(pixelFont);
			strengthLbl.setForeground(Color.white);
			this.setComponentZOrder(strengthLbl, 1);
			
			// Strength button
			this.add(strengthBtn);
			strengthBtn.setBorderPainted(false);
			strengthBtn.setContentAreaFilled(false);
			strengthBtn.setFocusPainted(false);
			strengthBtn.setIcon(btnPlusBase);
			strengthBtn.setRolloverIcon(btnPlusHover);
			strengthBtn.setHorizontalTextPosition(JButton.CENTER);
			strengthBtn.setVerticalTextPosition(JButton.CENTER);
			strengthBtn.setFont(pixelFont);
			strengthBtn.setForeground(Color.white);
			strengthBtn.setToolTipText("Add one point to strength.");
			this.setComponentZOrder(strengthBtn, 1);
			strengthBtn.setVisible(true);
			
			// Constitution label
			this.add(constitutionLbl);
			constitutionLbl.setText("Constitution: " + game.getCurrentPlayer().getAttributes().getConstitution());
			constitutionLbl.setFont(pixelFont);
			constitutionLbl.setForeground(Color.white);
			this.setComponentZOrder(constitutionLbl, 1);
			
			// Constitution button
			this.add(constitutionBtn);
			constitutionBtn.setBorderPainted(false);
			constitutionBtn.setContentAreaFilled(false);
			constitutionBtn.setFocusPainted(false);
			constitutionBtn.setIcon(btnPlusBase);
			constitutionBtn.setRolloverIcon(btnPlusHover);
			constitutionBtn.setHorizontalTextPosition(JButton.CENTER);
			constitutionBtn.setVerticalTextPosition(JButton.CENTER);
			constitutionBtn.setFont(pixelFont);
			constitutionBtn.setForeground(Color.white);
			constitutionBtn.setToolTipText("Add one point to constitution.");
			this.setComponentZOrder(constitutionBtn, 1);
			constitutionBtn.setVisible(true);
			
			// Agility label
			this.add(agilityLbl);
			agilityLbl.setText("Agility: " + game.getCurrentPlayer().getAttributes().getAgility());
			agilityLbl.setFont(pixelFont);
			agilityLbl.setForeground(Color.white);
			this.setComponentZOrder(agilityLbl, 1);
			
			// Agility button
			this.add(agilityBtn);
			agilityBtn.setBorderPainted(false);
			agilityBtn.setContentAreaFilled(false);
			agilityBtn.setFocusPainted(false);
			agilityBtn.setIcon(btnPlusBase);
			agilityBtn.setRolloverIcon(btnPlusHover);
			agilityBtn.setHorizontalTextPosition(JButton.CENTER);
			agilityBtn.setVerticalTextPosition(JButton.CENTER);
			agilityBtn.setFont(pixelFont);
			agilityBtn.setForeground(Color.white);
			agilityBtn.setToolTipText("Add one point to agility.");
			this.setComponentZOrder(agilityBtn, 1);
			agilityBtn.setVisible(true);
			
			// Intelligence label
			this.add(intelligenceLbl);
			intelligenceLbl.setText("Intelligence: " + game.getCurrentPlayer().getAttributes().getIntelligence());
			intelligenceLbl.setFont(pixelFont);
			intelligenceLbl.setForeground(Color.white);
			this.setComponentZOrder(intelligenceLbl, 1);
			
			// Intelligence button
			this.add(intelligenceBtn);
			intelligenceBtn.setBorderPainted(false);
			intelligenceBtn.setContentAreaFilled(false);
			intelligenceBtn.setFocusPainted(false);
			intelligenceBtn.setIcon(btnPlusBase);
			intelligenceBtn.setRolloverIcon(btnPlusHover);
			intelligenceBtn.setHorizontalTextPosition(JButton.CENTER);
			intelligenceBtn.setVerticalTextPosition(JButton.CENTER);
			intelligenceBtn.setFont(pixelFont);
			intelligenceBtn.setForeground(Color.white);
			intelligenceBtn.setToolTipText("Add one point to intelligence.");
			this.setComponentZOrder(intelligenceBtn, 1);
			intelligenceBtn.setVisible(true);
			
			// Charisma label
			this.add(charismaLbl);
			charismaLbl.setText("Charisma: " + game.getCurrentPlayer().getAttributes().getCharisma());
			charismaLbl.setFont(pixelFont);
			charismaLbl.setForeground(Color.white);
			this.setComponentZOrder(charismaLbl, 1);
			
			// Charisma button
			this.add(charismaBtn);
			charismaBtn.setBorderPainted(false);
			charismaBtn.setContentAreaFilled(false);
			charismaBtn.setFocusPainted(false);
			charismaBtn.setIcon(btnPlusBase);
			charismaBtn.setRolloverIcon(btnPlusHover);
			charismaBtn.setHorizontalTextPosition(JButton.CENTER);
			charismaBtn.setVerticalTextPosition(JButton.CENTER);
			charismaBtn.setFont(pixelFont);
			charismaBtn.setForeground(Color.white);
			charismaBtn.setToolTipText("Add one point to charisma.");
			this.setComponentZOrder(charismaBtn, 1);
			charismaBtn.setVisible(true);
			
			titleLbl.setText("Attributes:");
		}
		else if (skillsTabOpen == true)
		{
			this.remove(locationLbl);
			this.remove(actionLbl);
			this.remove(strengthLbl);
			this.remove(strengthBtn);
			this.remove(constitutionLbl);
			this.remove(constitutionBtn);
			this.remove(agilityLbl);
			this.remove(agilityBtn);
			this.remove(intelligenceLbl);
			this.remove(intelligenceBtn);
			this.remove(charismaLbl);
			this.remove(charismaBtn);
			
			// Upgrade points label
			this.add(upgradePointsLbl);
			upgradePointsLbl.setText("Upgrade Points: " + game.getCurrentPlayer().getUpgradePoints());
			upgradePointsLbl.setToolTipText("Upgrade points are used to upgrade character attributes and skills.");
			upgradePointsLbl.setFont(pixelFont);
			upgradePointsLbl.setForeground(Color.white);
			this.setComponentZOrder(upgradePointsLbl, 1);
			
			// Looting label
			this.add(lootingLbl);
			lootingLbl.setText("Looting: " + game.getCurrentPlayer().getSkills().getLooting());
			lootingLbl.setFont(pixelFont);
			lootingLbl.setForeground(Color.white);
			this.setComponentZOrder(lootingLbl, 1);
			
			// Looting button
			this.add(lootingBtn);
			lootingBtn.setBorderPainted(false);
			lootingBtn.setContentAreaFilled(false);
			lootingBtn.setFocusPainted(false);
			lootingBtn.setIcon(btnPlusBase);
			lootingBtn.setRolloverIcon(btnPlusHover);
			lootingBtn.setHorizontalTextPosition(JButton.CENTER);
			lootingBtn.setVerticalTextPosition(JButton.CENTER);
			lootingBtn.setFont(pixelFont);
			lootingBtn.setForeground(Color.white);
			lootingBtn.setToolTipText("Add one point to looting.");
			this.setComponentZOrder(lootingBtn, 1);
			lootingBtn.setVisible(true);
			
			titleLbl.setText("Skills:");
		}
	}
	
	public void setSurvivorPanelOpen(boolean survivorPanelOpen)
	{
		this.survivorPanelOpen = survivorPanelOpen;
	}
	
	public void setCurrentCharacter(String name)
	{
		this.currentCharacter = name;
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
		if (e.getSource() == nextCharBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			game.getSceneManager().hideScene(game.getCurrentPlayer());
			
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
			
			game.getSceneManager().showScene(game.getCurrentPlayer());
			
			invMan.storeSlot();
		}
		else if (e.getSource() == prevCharBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			game.getSceneManager().hideScene(game.getCurrentPlayer());
			
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
			
			game.getSceneManager().showScene(game.getCurrentPlayer());
			
			invMan.storeSlot();
		}
		
		if (e.getSource() == basicInfoTab)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			basicInfoTabOpen = true;
			attributesTabOpen = false;
			skillsTabOpen = false;
			
			updateTabs();
		}
		else if (e.getSource() == attributesTab)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			attributesTabOpen = true;
			basicInfoTabOpen = false;
			skillsTabOpen = false;
			
			updateTabs();
		}
		else if (e.getSource() == skillsTab)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			skillsTabOpen = true;
			basicInfoTabOpen = false;
			attributesTabOpen = false;
			
			updateTabs();
		}
		
		if (e.getSource() == strengthBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getAttributes().setStrength(game.getCurrentPlayer().getAttributes().getStrength() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getAttributesManager().checkStrength(game.getCurrentPlayer());
		}
		else if (e.getSource() == constitutionBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getAttributes().setConstitution(game.getCurrentPlayer().getAttributes().getConstitution() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getAttributesManager().checkConstitution(game.getCurrentPlayer());
		}
		else if (e.getSource() == agilityBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getAttributes().setAgility(game.getCurrentPlayer().getAttributes().getAgility() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getAttributesManager().checkAgility(game.getCurrentPlayer());
		}
		else if (e.getSource() == intelligenceBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getAttributes().setIntelligence(game.getCurrentPlayer().getAttributes().getIntelligence() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getAttributesManager().checkIntelligence(game.getCurrentPlayer());
		}
		else if (e.getSource() == charismaBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getAttributes().setCharisma(game.getCurrentPlayer().getAttributes().getCharisma() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getAttributesManager().checkCharisma(game.getCurrentPlayer());
		}
		
		if (e.getSource() == lootingBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			if (game.getCurrentPlayer().getUpgradePoints() >= 1)
			{
				game.getCurrentPlayer().getSkills().setLooting(game.getCurrentPlayer().getSkills().getLooting() + 1);
				game.getCurrentPlayer().setUpgradePoints(game.getCurrentPlayer().getUpgradePoints() - 1);
			}
			
			game.getSkillsManager().checkLooting(game.getCurrentPlayer());
		}
		
		upgradePointsLbl.setText("Upgrade Points: " + game.getCurrentPlayer().getUpgradePoints());
		strengthLbl.setText("Strength: " + game.getCurrentPlayer().getAttributes().getStrength());
		constitutionLbl.setText("Constitution: " + game.getCurrentPlayer().getAttributes().getConstitution());
		agilityLbl.setText("Agility: " + game.getCurrentPlayer().getAttributes().getAgility());
		intelligenceLbl.setText("Intelligence: " + game.getCurrentPlayer().getAttributes().getIntelligence());
		charismaLbl.setText("Charisma: " + game.getCurrentPlayer().getAttributes().getCharisma());
		
		lootingLbl.setText("Looting: " + game.getCurrentPlayer().getSkills().getLooting());
	}
}
