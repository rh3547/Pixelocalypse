package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class CraftBuildPanel extends GameAsset implements ActionListener, MouseListener
{
	private Game game;
	private JFrame window;
	private JPanel gameThread;
	private InventoryManager invMan;
	private ItemManager itemMan;
	
	// Local Variables
	private int windowX;
	private int windowY;
	private boolean craftBuildPanelOpen;
	private int panelYPos = -500;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	private boolean panelCreated = false;
	private boolean craftTabOpen = true;
	private boolean buildTabOpen = false;
	
	private String invToShow;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnDisabled;
	
	private ImageIcon verticalSeparator;
	private Image vertSep;
	
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	private JButton craftTab = new JButton();
	private JButton buildTab = new JButton();
	
	private JLabel titleLbl = new JLabel();
	private JSeparator topSep = new JSeparator();
	private JLabel invLbl = new JLabel();
	private JSeparator invSep = new JSeparator();
	private JLabel inputLbl = new JLabel();
	private JLabel outputLbl = new JLabel();
	private JButton clearBtn = new JButton();
	private JButton craftBtn = new JButton();
	
	private JButton inputPane = new JButton();
	private JButton outputPane = new JButton();
	private JButton invPane = new JButton();
	
	public CraftBuildPanel(Game game)
	{
		super(game);
		this.game = game;
		
		invToShow = game.getCurrentPlayer().getInventory();
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnDisabled = new ImageIcon(game.getRespath() + "gui/buttons/btnDisabled.png");
		
		verticalSeparator = new ImageIcon(game.getRespath() + "gui/other/verticalSeparator.png");
		vertSep = verticalSeparator.getImage();
		
		this.window = game.getWindow();
		this.gameThread = game.getGameThread();
		this.invMan = game.getInventoryManager();
		this.itemMan = game.getItemManager();
	}
	
	public void createCraftBuildPanel()
	{	
		craftTab.addActionListener(this);
		buildTab.addActionListener(this);
		clearBtn.addActionListener(this);
		craftBtn.addActionListener(this);
		
		inputPane.addMouseListener(this);
		outputPane.addMouseListener(this);
		invPane.addMouseListener(this);
	}
	
	public void showCraftBuildPanel()
	{	
		panelYPos = -500;
		gameThread.add(this);
		gameThread.setComponentZOrder(this, 1);
		this.setBounds((windowX / 2) - 600, panelYPos, 1200, 525);
		this.setLayout(null);
		this.setVisible(true);
		
		// Crafting tab button
		this.add(craftTab);
		craftTab.setBorderPainted(false);
		craftTab.setContentAreaFilled(false);
		craftTab.setFocusPainted(false);
		craftTab.setIcon(btnBase);
		craftTab.setRolloverIcon(btnHover);
		craftTab.setDisabledIcon(btnDisabled);
		craftTab.setText("Crafting");
		craftTab.setHorizontalTextPosition(JButton.CENTER);
		craftTab.setVerticalTextPosition(JButton.CENTER);
		craftTab.setFont(pixelFont);
		craftTab.setForeground(Color.white);
		craftTab.setToolTipText("Craft items.");
		craftTab.setVisible(true);
		
		// Building tab button
		this.add(buildTab);
		buildTab.setBorderPainted(false);
		buildTab.setContentAreaFilled(false);
		buildTab.setFocusPainted(false);
		buildTab.setIcon(btnBase);
		buildTab.setRolloverIcon(btnHover);
		buildTab.setDisabledIcon(btnDisabled);
		buildTab.setText("Building");
		buildTab.setHorizontalTextPosition(JButton.CENTER);
		buildTab.setVerticalTextPosition(JButton.CENTER);
		buildTab.setFont(pixelFont);
		buildTab.setForeground(Color.white);
		buildTab.setToolTipText("Build Structures.");
		buildTab.setVisible(true);
		
		// Title label
		this.add(titleLbl);
		titleLbl.setText("Crafting:");
		titleLbl.setFont(pixelFont);
		titleLbl.setForeground(Color.white);
		
		// Top separator
		this.add(topSep);
		
		// Inventory label
		this.add(invLbl);
		invLbl.setText(game.getCurrentPlayer().getInventory());
		invLbl.setFont(pixelFont);
		invLbl.setForeground(Color.white);
		
		// Inventory separator
		this.add(invSep);
		
		// Input label
		this.add(inputLbl);
		inputLbl.setText("Input:");
		inputLbl.setFont(pixelFont);
		inputLbl.setForeground(Color.white);
		
		// Output label
		this.add(outputLbl);
		outputLbl.setText("Output:");
		outputLbl.setFont(pixelFont);
		outputLbl.setForeground(Color.white);
		
		// Clear button
		this.add(clearBtn);
		clearBtn.setBorderPainted(false);
		clearBtn.setContentAreaFilled(false);
		clearBtn.setFocusPainted(false);
		clearBtn.setIcon(btnBase);
		clearBtn.setRolloverIcon(btnHover);
		clearBtn.setDisabledIcon(btnDisabled);
		clearBtn.setText("Clear");
		clearBtn.setHorizontalTextPosition(JButton.CENTER);
		clearBtn.setVerticalTextPosition(JButton.CENTER);
		clearBtn.setFont(pixelFont);
		clearBtn.setForeground(Color.white);
		clearBtn.setToolTipText("Clear the input grid and return the items back to your inventory.");
		clearBtn.setVisible(true);
		
		// Craft button
		this.add(craftBtn);
		craftBtn.setBorderPainted(false);
		craftBtn.setContentAreaFilled(false);
		craftBtn.setFocusPainted(false);
		craftBtn.setIcon(btnBase);
		craftBtn.setRolloverIcon(btnHover);
		craftBtn.setDisabledIcon(btnDisabled);
		craftBtn.setText("Craft");
		craftBtn.setHorizontalTextPosition(JButton.CENTER);
		craftBtn.setVerticalTextPosition(JButton.CENTER);
		craftBtn.setFont(pixelFont);
		craftBtn.setForeground(Color.white);
		craftBtn.setToolTipText("Craft the current items displayed in the output grid.");
		craftBtn.setVisible(true);
		
		// Input pane label
		gameThread.add(inputPane);
		
		// Output pane label
		gameThread.add(outputPane);
		
		// Inventory pane label
		gameThread.add(invPane);
		
		panelCreated = true;
	}
	
	public void updateTabs()
	{
		if (craftTabOpen == true)
		{
			// Inventory label
			this.add(invLbl);
			invLbl.setText(game.getCurrentPlayer().getInventory());
			invLbl.setFont(pixelFont);
			invLbl.setForeground(Color.white);
			
			// Inventory separator
			this.add(invSep);
			
			// Input label
			this.add(inputLbl);
			inputLbl.setText("Input:");
			inputLbl.setFont(pixelFont);
			inputLbl.setForeground(Color.white);
			
			// Output label
			this.add(outputLbl);
			outputLbl.setText("Output:");
			outputLbl.setFont(pixelFont);
			outputLbl.setForeground(Color.white);
			
			// Clear button
			this.add(clearBtn);
			clearBtn.setBorderPainted(false);
			clearBtn.setContentAreaFilled(false);
			clearBtn.setFocusPainted(false);
			clearBtn.setIcon(btnBase);
			clearBtn.setRolloverIcon(btnHover);
			clearBtn.setDisabledIcon(btnDisabled);
			clearBtn.setText("Clear");
			clearBtn.setHorizontalTextPosition(JButton.CENTER);
			clearBtn.setVerticalTextPosition(JButton.CENTER);
			clearBtn.setFont(pixelFont);
			clearBtn.setForeground(Color.white);
			clearBtn.setToolTipText("Clear the input grid and return the items back to your inventory.");
			clearBtn.setVisible(true);
			
			// Craft button
			this.add(craftBtn);
			craftBtn.setBorderPainted(false);
			craftBtn.setContentAreaFilled(false);
			craftBtn.setFocusPainted(false);
			craftBtn.setIcon(btnBase);
			craftBtn.setRolloverIcon(btnHover);
			craftBtn.setDisabledIcon(btnDisabled);
			craftBtn.setText("Craft");
			craftBtn.setHorizontalTextPosition(JButton.CENTER);
			craftBtn.setVerticalTextPosition(JButton.CENTER);
			craftBtn.setFont(pixelFont);
			craftBtn.setForeground(Color.white);
			craftBtn.setToolTipText("Craft the current items displayed in the output grid.");
			craftBtn.setVisible(true);
			
			// Input pane label
			gameThread.add(inputPane);
			gameThread.setComponentZOrder(inputPane, 1);
			inputPane.setBorderPainted(false);
			inputPane.setContentAreaFilled(false);
			inputPane.setFocusPainted(false);
			
			// Output pane label
			gameThread.add(outputPane);
			gameThread.setComponentZOrder(outputPane, 1);
			outputPane.setBorderPainted(false);
			outputPane.setContentAreaFilled(false);
			outputPane.setFocusPainted(false);
			
			// Inventory pane label
			gameThread.add(invPane);
			gameThread.setComponentZOrder(invPane, 1);
			invPane.setBorderPainted(false);
			invPane.setContentAreaFilled(false);
			invPane.setFocusPainted(false);
			
			titleLbl.setText("Crafting:");
			
			invMan.showInventory("Crafting-Input", this, 55, 150);
			invMan.showInventory("Crafting-Output", this, 500, 150);
			
			invMan.showInventory(game.getCurrentPlayer().getInventory(), this, 860, 170);
			
			invMan.setShownInventory(game.getCurrentPlayer().getInventory());
			invMan.setCraftMode(true);
		}
		else if (buildTabOpen == true)
		{
			invMan.hideInventory("Crafting-Input", this);
			invMan.hideInventory("Crafting-Output", this);
			invMan.hideInventory(game.getCurrentPlayer().getInventory(), this);
			
			this.remove(invLbl);
			this.remove(invSep);
			this.remove(inputLbl);
			this.remove(outputLbl);
			this.remove(clearBtn);
			this.remove(craftBtn);
			gameThread.remove(inputPane);
			gameThread.remove(outputPane);
			gameThread.remove(invPane);
			
			titleLbl.setText("Building:");
			
			invMan.setCraftMode(false);
		}
	}
	
	public void removeCraftBuildPanel()
	{	
		for (int x = 0; x < game.getInventoryManager().getInventory("Crafting-Input").getNumSlots(); x++)
		{
			if (!game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem().getName().equals("Null"))
			{
				game.getInventoryManager().addToInventory(game.getCurrentPlayer().getInventory(), 
														  game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem(), 
														  game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotStack());
			}
		}
		
		game.getInventoryManager().clearInventory("Crafting-Input");
		game.getInventoryManager().clearInventory("Crafting-Output");
		
		game.getInventoryManager().hideInventory(game.getCurrentPlayer().getInventory(), this);
		invMan.setCraftMode(false);
		
		gameThread.remove(inputPane);
		gameThread.remove(outputPane);
		gameThread.remove(invPane);
		
		gameThread.remove(this);
		this.setVisible(false);
	}
	
	public void updatePanelClose()
	{
		if (craftBuildPanelOpen == true && this.getY() < ((windowY / 2) - 250))
		{
			panelYPos += 30;
			this.setBounds
			((windowX / 2) - 600, panelYPos, 1200, 525);
		}
		
		if (craftBuildPanelOpen == true && this.getY() > ((windowY / 2) - 251))
		{
			this.setBounds
			((windowX / 2) - 600, ((windowY / 2) - 250), 1200, 525);
			
			if (panelCreated == true)
			{
				updateTabs();
				panelCreated = false;
			}
		}
		
		if (craftBuildPanelOpen == false)
		{
			this.setBounds
			((windowX / 2) - 600, panelYPos, 1200, 525);
			
			panelYPos -= 20;
			
			if (this.getY() <= -501 && this.getY() >= -701)
			{
				removeCraftBuildPanel();
				
				panelYPos = -1000;
				this.setBounds
				((windowX / 2) - 600, panelYPos, 1200, 525);
			}
		}
	}
	
	public void update()
	{
		craftTab.setBounds(40, 15, btnWidth, btnHeight);
		buildTab.setBounds(btnWidth + 35, 15, btnWidth, btnHeight);
		
		titleLbl.setBounds(((800 / 2) - (titleLbl.getText().length() * 5)), 60, 250, 30);
		topSep.setBounds((800 / 2) - (300), 100, 600, 5);
		invLbl.setText(game.getCurrentPlayer().getInventory());
		invLbl.setBounds((935 - (titleLbl.getText().length() * 5)), 60, 250, 30);
		invSep.setBounds(830, 100, 300, 5);
		inputLbl.setBounds(150, 115, 250, 30);
		outputLbl.setBounds(585, 115, 250, 30);
		clearBtn.setBounds(115, 450, btnWidth, btnHeight);
		craftBtn.setBounds(560, 450, btnWidth, btnHeight);
		
		inputPane.setBounds(185, 300, 260, 300);
		
		outputPane.setBounds(630, 300, 260, 300);
		
		invPane.setBounds(990, 320, 260, 244);
		
		invMan.setShownInventory(invToShow);
	}
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
	}
	
	public void setCraftBuildPanelOpen(boolean craftBuildPanelOpen)
	{
		this.craftBuildPanelOpen = craftBuildPanelOpen;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 1200, 525, null);
	     g.drawImage(vertSep, 780, 7, 25, 510, null);
	 }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == craftTab)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			craftTabOpen = true;
			buildTabOpen = false;
			
			updateTabs();
		}
		
		if (e.getSource() == buildTab)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			buildTabOpen = true;
			craftTabOpen = false;
			
			updateTabs();
		}
		
		if (e.getSource() == clearBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			for (int x = 0; x < game.getInventoryManager().getInventory("Crafting-Input").getNumSlots(); x++)
			{
				if (!game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem().getName().equals("Null"))
				{
					game.getInventoryManager().addToInventory(game.getCurrentPlayer().getInventory(), 
															  game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotItem(), 
															  game.getInventoryManager().getInventory("Crafting-Input").getSlots()[x].getSlotStack());
				}
			}
			
			game.getInventoryManager().clearInventory("Crafting-Input");
			game.getInventoryManager().clearInventory("Crafting-Output");
		}
		
		if (e.getSource() == craftBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			for (int x = 0; x < game.getInventoryManager().getInventory("Crafting-Output").getNumSlots(); x++)
			{
				if (!game.getInventoryManager().getInventory("Crafting-Output").getSlots()[x].getSlotItem().getName().equals("Null"))
				{
					game.getInventoryManager().addToInventory(game.getCurrentPlayer().getInventory(), 
															  game.getInventoryManager().getInventory("Crafting-Output").getSlots()[x].getSlotItem(), 
															  game.getInventoryManager().getInventory("Crafting-Output").getSlots()[x].getSlotStack());
				}
			}
			
			game.getInventoryManager().clearInventory("Crafting-Input");
			game.getInventoryManager().clearInventory("Crafting-Output");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == inputPane)
		{
			System.out.print("\nInput");
			
			gameThread.remove(inputPane);
			gameThread.remove(outputPane);
			gameThread.remove(invPane);
			
			gameThread.add(outputPane);
			gameThread.add(invPane);
			
			invToShow = "Crafting-Input";
		}
		else if (e.getSource() == outputPane)
		{
			System.out.print("\nOutput");
			
			gameThread.remove(inputPane);
			gameThread.remove(outputPane);
			gameThread.remove(invPane);
			
			gameThread.add(inputPane);
			gameThread.add(invPane);
			
			invToShow = "Crafting-Output";
		}
		else if (e.getSource() == invPane)
		{
			System.out.print("\nInventory");
			
			gameThread.remove(inputPane);
			gameThread.remove(outputPane);
			gameThread.remove(invPane);
			
			gameThread.add(outputPane);
			gameThread.add(inputPane);
			
			invToShow = game.getCurrentPlayer().getInventory();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
	}
}
