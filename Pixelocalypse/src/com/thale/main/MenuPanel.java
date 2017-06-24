package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class MenuPanel extends GameAsset implements ActionListener
{
	private Game game;
	private JPanel gameThread;
	private JFrame window;
	private int windowX;
	private int windowY;
	private int numOfBtns = 0;
	private int btnHeight;
	private int btnWidth;
	public List<String> btnName = new ArrayList<String>();
	public List<Integer> btnNum = new ArrayList<Integer>();
	private int menuPanelY = 0;
	private int panelYSize;
	private boolean menuOpen = false;
	
	// Images
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	private JLabel menuLbl = new JLabel();
	private JSeparator menuSep = new JSeparator();
	private JButton closeMenuBtn = new JButton();
	private JButton saveBtn = new JButton();
	private JButton loadBtn = new JButton();
	private JButton optionsBtn = new JButton();
	private JButton exitBtn = new JButton();
	
	public MenuPanel(Game game) 
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		
		gameThread = game.getGameThread();
		window = game.getWindow();
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns), null);
	 }
	
	public void updateData(int x, int y, int btnWidth, int btnHeight)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
		window.repaint();
	}
	
	public void createMenuPanel()
	{	
		/*
		 * 	Create menu panel
		 */
		numOfBtns = 0;
		menuPanelY = -150;
		this.setBounds
		((windowX / 2) - ((btnWidth + 62) / 2), menuPanelY, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns));
		gameThread.add(this);
		this.setLayout(null);
		gameThread.setComponentZOrder(this, 1);
		this.setVisible(true);
		
		// Menu label
		this.add(menuLbl);
		menuLbl.setFont(pixelFont);
		menuLbl.setForeground(Color.white);
		menuLbl.setText("Menu");
		
		// Menu Separator
		this.add(menuSep);
		
		// Close button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "closeMenuBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(closeMenuBtn);
		closeMenuBtn.addActionListener(this);
		closeMenuBtn.setBorderPainted(false);
		closeMenuBtn.setContentAreaFilled(false);
		closeMenuBtn.setFocusPainted(false);
		closeMenuBtn.setIcon(btnBase);
		closeMenuBtn.setRolloverIcon(btnHover);
		closeMenuBtn.setText("Close");
		closeMenuBtn.setHorizontalTextPosition(JButton.CENTER);
		closeMenuBtn.setVerticalTextPosition(JButton.CENTER);
		closeMenuBtn.setFont(pixelFont);
		closeMenuBtn.setForeground(Color.white);
		closeMenuBtn.setVisible(true);
		
		// Save button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "saveBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(saveBtn);
		saveBtn.addActionListener(this);
		saveBtn.setBorderPainted(false);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setFocusPainted(false);
		saveBtn.setIcon(btnBase);
		saveBtn.setRolloverIcon(btnHover);
		saveBtn.setText("Save");
		saveBtn.setHorizontalTextPosition(JButton.CENTER);
		saveBtn.setVerticalTextPosition(JButton.CENTER);
		saveBtn.setFont(pixelFont);
		saveBtn.setForeground(Color.white);
		saveBtn.setVisible(true);
		
		// Load button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "loadBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(loadBtn);
		loadBtn.addActionListener(this);
		loadBtn.setBorderPainted(false);
		loadBtn.setContentAreaFilled(false);
		loadBtn.setFocusPainted(false);
		loadBtn.setIcon(btnBase);
		loadBtn.setRolloverIcon(btnHover);
		loadBtn.setText("Load");
		loadBtn.setHorizontalTextPosition(JButton.CENTER);
		loadBtn.setVerticalTextPosition(JButton.CENTER);
		loadBtn.setFont(pixelFont);
		loadBtn.setForeground(Color.white);
		loadBtn.setVisible(true);
		
		// Options button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "optionsBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(optionsBtn);
		optionsBtn.addActionListener(this);
		optionsBtn.setBorderPainted(false);
		optionsBtn.setContentAreaFilled(false);
		optionsBtn.setFocusPainted(false);
		optionsBtn.setIcon(btnBase);
		optionsBtn.setRolloverIcon(btnHover);
		optionsBtn.setText("Options");
		optionsBtn.setHorizontalTextPosition(JButton.CENTER);
		optionsBtn.setVerticalTextPosition(JButton.CENTER);
		optionsBtn.setFont(pixelFont);
		optionsBtn.setForeground(Color.white);
		optionsBtn.setVisible(true);
		
		// Exit button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "exitBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(exitBtn);
		exitBtn.addActionListener(this);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setIcon(btnBase);
		exitBtn.setRolloverIcon(btnHover);
		exitBtn.setText("Exit");
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.CENTER);
		exitBtn.setFont(pixelFont);
		exitBtn.setForeground(Color.white);
		exitBtn.setVisible(true);
		
		menuOpen = true;
		panelYSize = (btnHeight * numOfBtns) + (90 + 5 * numOfBtns);
	}
	
	public void showMenuPanel()
	{
		numOfBtns = 0;
		menuPanelY = -150;
		this.setBounds
		((windowX / 2) - ((btnWidth + 62) / 2), menuPanelY, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns));
		gameThread.add(this);
		this.setLayout(null);
		gameThread.setComponentZOrder(this, 1);
		this.setVisible(true);
		
		// Menu label
		this.add(menuLbl);
		menuLbl.setFont(pixelFont);
		menuLbl.setForeground(Color.white);
		menuLbl.setText("Menu");
		
		// Menu Separator
		this.add(menuSep);
		
		// Close button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "closeMenuBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(closeMenuBtn);
		closeMenuBtn.setBorderPainted(false);
		closeMenuBtn.setContentAreaFilled(false);
		closeMenuBtn.setFocusPainted(false);
		closeMenuBtn.setIcon(btnBase);
		closeMenuBtn.setRolloverIcon(btnHover);
		closeMenuBtn.setText("Close");
		closeMenuBtn.setHorizontalTextPosition(JButton.CENTER);
		closeMenuBtn.setVerticalTextPosition(JButton.CENTER);
		closeMenuBtn.setFont(pixelFont);
		closeMenuBtn.setForeground(Color.white);
		closeMenuBtn.setVisible(true);
		
		// Save button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "saveBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(saveBtn);
		saveBtn.setBorderPainted(false);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setFocusPainted(false);
		saveBtn.setIcon(btnBase);
		saveBtn.setRolloverIcon(btnHover);
		saveBtn.setText("Save");
		saveBtn.setHorizontalTextPosition(JButton.CENTER);
		saveBtn.setVerticalTextPosition(JButton.CENTER);
		saveBtn.setFont(pixelFont);
		saveBtn.setForeground(Color.white);
		saveBtn.setVisible(true);
		
		// Load button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "loadBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(loadBtn);
		loadBtn.setBorderPainted(false);
		loadBtn.setContentAreaFilled(false);
		loadBtn.setFocusPainted(false);
		loadBtn.setIcon(btnBase);
		loadBtn.setRolloverIcon(btnHover);
		loadBtn.setText("Load");
		loadBtn.setHorizontalTextPosition(JButton.CENTER);
		loadBtn.setVerticalTextPosition(JButton.CENTER);
		loadBtn.setFont(pixelFont);
		loadBtn.setForeground(Color.white);
		loadBtn.setVisible(true);
		
		// Options button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "optionsBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(optionsBtn);
		optionsBtn.setBorderPainted(false);
		optionsBtn.setContentAreaFilled(false);
		optionsBtn.setFocusPainted(false);
		optionsBtn.setIcon(btnBase);
		optionsBtn.setRolloverIcon(btnHover);
		optionsBtn.setText("Options");
		optionsBtn.setHorizontalTextPosition(JButton.CENTER);
		optionsBtn.setVerticalTextPosition(JButton.CENTER);
		optionsBtn.setFont(pixelFont);
		optionsBtn.setForeground(Color.white);
		optionsBtn.setVisible(true);
		
		// Exit button
		numOfBtns += 1;
		btnName.add(numOfBtns - 1, "exitBtn");
		btnNum.add(numOfBtns - 1, numOfBtns);
		this.add(exitBtn);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setIcon(btnBase);
		exitBtn.setRolloverIcon(btnHover);
		exitBtn.setText("Exit");
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.CENTER);
		exitBtn.setFont(pixelFont);
		exitBtn.setForeground(Color.white);
		exitBtn.setVisible(true);
		
		menuOpen = true;
		panelYSize = (btnHeight * numOfBtns) + (90 + 5 * numOfBtns);
	}
	
	public void removeMenuPanel()
	{
		menuOpen = false;
		
		numOfBtns = 0;
		
		this.removeAll();
		gameThread.remove(this);
		
		this.setVisible(false);
		closeMenuBtn.setVisible(false);
		
		this.updateData(windowX, windowY, btnWidth, btnHeight);
	}
	
	public void updatePosition()
	{
		if (menuOpen == true && this.getY() < 69)
		{
			this.setBounds
			((windowX / 2) - ((btnWidth + 62) / 2), menuPanelY, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns));
			
			menuPanelY += 20;
		}
		
		if (menuOpen == true && this.getY() > 69)
		{
			this.updateData(windowX, windowY, btnWidth, btnHeight);
			
			menuPanelY = 70;
			
			this.setBounds
			((windowX / 2) - ((btnWidth + 62) / 2), menuPanelY, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns));
			
			menuLbl.setBounds
			(((btnWidth + 50) / 2) - (btnWidth - 97), 10, btnWidth, btnHeight);
			menuSep.setBounds
			(((btnWidth + 50) / 2) - (btnWidth - 60), 37, btnWidth, 5);
			
			for (int x = 0; x < btnName.size(); x++)
			{
				if (btnName.get(x).matches("closeMenuBtn"))
				{
					closeMenuBtn.setBounds
					(25, (btnHeight * btnNum.get(x).intValue()) + 20, btnWidth, btnHeight);
				}	
			}
			for (int x = 0; x < btnName.size(); x++)
			{
				if (btnName.get(x).matches("saveBtn"))
				{
					saveBtn.setBounds
					(25, (btnHeight * btnNum.get(x).intValue()) + ((5 * btnNum.get(x).intValue()) - 5) + 20, btnWidth, btnHeight);
				}	
			}
			for (int x = 0; x < btnName.size(); x++)
			{
				if (btnName.get(x).matches("loadBtn"))
				{
					loadBtn.setBounds
					(25, (btnHeight * btnNum.get(x).intValue()) + ((5 * btnNum.get(x).intValue()) - 5) + 20, btnWidth, btnHeight);
				}	
			}
			for (int x = 0; x < btnName.size(); x++)
			{
				if (btnName.get(x).matches("optionsBtn"))
				{
					optionsBtn.setBounds
					(25, (btnHeight * btnNum.get(x).intValue()) + ((5 * btnNum.get(x).intValue()) - 5) + 20, btnWidth, btnHeight);
				}	
			}
			for (int x = 0; x < btnName.size(); x++)
			{
				if (btnName.get(x).matches("exitBtn"))
				{
					exitBtn.setBounds
					(25, (btnHeight * btnNum.get(x).intValue()) + ((5 * btnNum.get(x).intValue()) - 5) + 20, btnWidth, btnHeight);
				}
			}
			
		}
		if (menuOpen == false)
		{
			this.setBounds
			((windowX / 2) - ((btnWidth + 62) / 2), menuPanelY, btnWidth + 50, panelYSize);
			
			menuPanelY -= 20;
			panelYSize -= 2;
			
			if (this.getY() == -150)
			{
				removeMenuPanel();
			}
		}
	}
	
	public boolean isMenuOpen()
	{
		return menuOpen;
	}
	
	public void setMenuOpen(boolean menuOpen)
	{
		this.menuOpen = menuOpen;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == closeMenuBtn)	// Closes the menu
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			menuOpen = false;
		}
		
		if (e.getSource() == saveBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			if (game.getLastSave().equals(""))
			{
				game.getSaveGame().saveGame();
			}
			else
			{
				game.getSaveGame().quickSave();
			}
		}
		
		if (e.getSource() == loadBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			game.getLoadGame().loadGame();
			
			menuOpen = false;
		}
		
		if (e.getSource() == optionsBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
		}
		
		if (e.getSource() == exitBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			System.exit(0);
		}
	}
}
