package com.thale.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class DevPanel extends GameAsset
{
	Game game;
	JPanel gameThread;
	
	private int windowX;
	private int windowY;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	
	StringTokenizer token;
	
	private JTextArea devText = new JTextArea();
	
	public DevPanel(Game game)
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		
		gameThread = game.getGameThread();
	}
	
	public void createDevPanel()
	{
		Action enterCommand = new EnterCommand();
		devText.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterCommand");
		devText.getActionMap().put("enterCommand", enterCommand);
	}
	
	public void showDevPanel()
	{
		this.setBounds((windowX / 2) - 408, 700, 800, 30);
		gameThread.add(this);
		gameThread.setComponentZOrder(this, 1);
		this.setLayout(null);
		this.setVisible(true);
		
		// Developer text area
		this.add(devText);
		devText.setBounds(5, 5, 790, 20);
	}
	
	public void removeDevPanel()
	{
		this.removeAll();
		gameThread.remove(this);
		
		this.setVisible(false);
		
		this.updateData(windowX, windowY, btnHeight, btnHeight);
	}
	
	public void update()
	{	
		this.setBounds((windowX / 2) - 408, 700, 800, 30);
		devText.setBounds(5, 5, 790, 20);
	}
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 800, 525, null);
	 }
	
	class EnterCommand extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.print("\n\nCommand Entered");
			
			String commandText = devText.getText();
			token = new StringTokenizer(commandText);
			String tokenText = token.nextToken();
			
			if (tokenText.equals("close"))
			{
				removeDevPanel();
			}
			else if (tokenText.equals("item"))
			{
				tokenText = token.nextToken();
				
				if (tokenText.equals("give"))
				{
					game.getInventoryManager().addToInventory(game.getCurrentPlayer().getInventory(), 
															  game.getItemManager().getItem(token.nextToken()), 
															  Integer.parseInt(token.nextToken()));
				}
			}
			else if (tokenText.equals("inventory"))
			{
				tokenText = token.nextToken();
				
				if (tokenText.equals("clear"))
				{
					game.getInventoryManager().clearInventory(game.getCurrentPlayer().getInventory());
				}
			}
			else if (tokenText.equals("character"))
			{
				tokenText = token.nextToken();
				
				if (tokenText.equals("new"))
				{
					game.getPlayerManager().createNewCharacter();
				}
				else if (tokenText.equals("set"))
				{
					tokenText = token.nextToken();
					
					if (tokenText.equals("lootSkill"))
					{
						game.getCurrentPlayer().getSkills().setLooting(Integer.parseInt(token.nextToken()));
					}
					else if (tokenText.equals("location"))
					{
						game.getCurrentPlayer().setLocation(token.nextToken());
						game.setCurrentLocation(game.getLocationManager().getLocation(game.getCurrentPlayer().getLocation()));
						game.getCurrentPlayer().getScene().setBgImageStr(game.getRespath() + "scenes/" + game.getCurrentPlayer().getLocation() + "Scene.png");
						game.getCurrentPlayer().getScene().resetBgImage();
						game.getSceneManager().regenerateScene(game.getCurrentPlayer());
					}
					if (tokenText.equals("upgradepoints"))
					{
						game.getCurrentPlayer().setUpgradePoints(Integer.parseInt(token.nextToken()));
					}
				}
			}
			else if (tokenText.equals("game"))
			{
				tokenText = token.nextToken();
				
				if (tokenText.equals("set"))
				{
					tokenText = token.nextToken();
					
					if (tokenText.equals("exploreWait"))
					{
						game.setExploreWait(Integer.parseInt(token.nextToken()));
					}
					else if (tokenText.equals("lootWait"))
					{
						game.setLootWait(Integer.parseInt(token.nextToken()));
					}
				}
			}
			else if (tokenText.equals("devMode2168"))
			{
				game.setExploreWait(0);
				game.setLootWait(0);
				game.getCurrentPlayer().getSkills().setLooting(10);
			}
			
			devText.setText("");
			removeDevPanel();
		}
	}
}
