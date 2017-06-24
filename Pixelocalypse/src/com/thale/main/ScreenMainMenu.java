package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.thale.engine.Game;
import com.thale.engine.Screen;
import com.thale.engine.ScreenHandler;

public class ScreenMainMenu extends Screen implements ActionListener
{
	private Game game;
	private JPanel gameThread;
	
	// Images
	private ImageIcon logoII;
	private Image logoImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnBaseBig;
	private ImageIcon btnHoverBig;
	private ImageIcon btnBaseMed;
	private ImageIcon btnHoverMed;
	
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	private Font pixelFontLarge = new Font("Minecraftia", Font.PLAIN, 32);
	
	private JButton playBtn = new JButton();
	private JButton exitBtn = new JButton();
	
	public ScreenMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
		this.game = screenHandler.getGame();
		
		logoII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		logoImage = logoII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnBaseBig = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseBig.png");
		btnHoverBig = new ImageIcon(game.getRespath() + "gui/buttons/btnHoverBig.png");
		btnBaseMed = new ImageIcon(game.getRespath() + "gui/buttons/btnBaseMed.png");
		btnHoverMed = new ImageIcon(game.getRespath() + "gui/buttons/btnHoverMed.png");
		
		this.gameThread = game.getGameThread();
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
	      
	    game.getWindow().setIconImage(img);
	    
		game.getWindow().repaint();
		
		// Play button
		gameThread.add(playBtn);
		playBtn.addActionListener(this);
		playBtn.setBorderPainted(false);
		playBtn.setContentAreaFilled(false);
		playBtn.setFocusPainted(false);
		playBtn.setIcon(btnBase);
		playBtn.setRolloverIcon(btnHover);
		playBtn.setText("Play");
		playBtn.setHorizontalTextPosition(JButton.CENTER);
		playBtn.setVerticalTextPosition(JButton.CENTER);
		playBtn.setFont(pixelFont);
		playBtn.setForeground(Color.white);
		playBtn.setToolTipText("Play the game.");
		playBtn.setVisible(true);
		
		// Exit button
		gameThread.add(exitBtn);
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
		exitBtn.setToolTipText("Exit the game.");
		exitBtn.setVisible(true);
	}

	@Override
	public void onUpdate()
	{
		playBtn.setBounds((game.getWindowX() / 2) - 125, 300, 100, 30);
		exitBtn.setBounds((game.getWindowX() / 2) + 25, 300, 100, 30);
	}

	@Override
	public void onTick()
	{
		
	}

	@Override
	public void onDraw(Graphics2D g2d)
	{
		g2d.drawImage(logoImage, (game.getWindowX() / 2) - 165, 200, 325, 80, null);
		
		g2d.setFont(pixelFontLarge);
		g2d.setColor(Color.white);
		g2d.drawString("Pixelocalypse", (game.getWindowX() / 2) - 135, 250);
		
		g2d.setFont(pixelFont);
		g2d.setColor(Color.white);
		g2d.drawString("Pixelocalypse Alpha 0.8", 15, 770);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == playBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			gameThread.removeAll();
			this.getScreenHandler().showScreen(new ScreenPixelocalypse(game.getScreenHandler()));
		}
		
		if (e.getSource() == exitBtn)
		{
			game.getAudioHandler().playSound("btnPressed.wav");
			
			System.exit(0);
		}
	}
}
