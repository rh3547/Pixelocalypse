package com.thale.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.player.Player;

public class LevelUpPanel extends GameAsset implements ActionListener
{
	Game game;
	
	// Images & Icons
	private ImageIcon bgII;
	private Image bgImage;
	private ImageIcon btnBase;
	private ImageIcon btnHover;
	private ImageIcon btnDisabled;
	
	// Local Components
	private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 16);
	
	// Local Variables
	private int windowX = 1480;
	private int windowY = 820;
	private int btnWidth = 100;
	private int btnHeight = 30;
	
	private JLabel titleLbl = new JLabel();
	private JSeparator topSep = new JSeparator();
	private JLabel nameLbl = new JLabel();
	private JLabel upLbl = new JLabel();
	private JButton closeBtn = new JButton();
	
	public LevelUpPanel(Game game)
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		btnBase = new ImageIcon(game.getRespath() + "gui/buttons/btnBase.png");
		btnHover = new ImageIcon(game.getRespath() + "gui/buttons/btnHover.png");
		btnDisabled = new ImageIcon(game.getRespath() + "gui/buttons/btnDisabled.png");
	}
	
	public void showLevelUpPanel(Player player)
	{
		game.getGameThread().add(this);
		game.getGameThread().setComponentZOrder(this, 1);
		this.setBounds((windowX / 2) - 400, (windowY / 2) - 150, 800, 300);
		this.setLayout(null);
		this.setVisible(true);
		
		// Title label
		this.add(titleLbl);
		titleLbl.setText("Level Up!");
		titleLbl.setFont(pixelFont);
		titleLbl.setForeground(Color.white);
		
		// Top separator
		this.add(topSep);
		
		// Name label
		this.add(nameLbl);
		nameLbl.setText(player.getName() + " is now level " + player.getLevel());
		nameLbl.setFont(pixelFont);
		nameLbl.setForeground(Color.white);
		
		// Upgrade Points label
		this.add(upLbl);
		upLbl.setText(player.getName() + " has gained 2 upgrade points.");
		upLbl.setFont(pixelFont);
		upLbl.setForeground(Color.white);
		
		// Close button
		this.add(closeBtn);
		closeBtn.addActionListener(this);
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setFocusPainted(false);
		closeBtn.setIcon(btnBase);
		closeBtn.setRolloverIcon(btnHover);
		closeBtn.setDisabledIcon(btnDisabled);
		closeBtn.setText("Close");
		closeBtn.setHorizontalTextPosition(JButton.CENTER);
		closeBtn.setVerticalTextPosition(JButton.CENTER);
		closeBtn.setFont(pixelFont);
		closeBtn.setForeground(Color.white);
		closeBtn.setVisible(true);
		
		titleLbl.setBounds(((800 / 2) - (titleLbl.getText().length() * 5)), 60, 250, 30);
		topSep.setBounds((800 / 2) - (300), 100, 600, 5);
		nameLbl.setBounds(((800 / 2) - (nameLbl.getText().length() * 5)), 140, 500, 30);
		upLbl.setBounds(((800 / 2) - (upLbl.getText().length() * 5)), 180, 500, 30);
		closeBtn.setBounds((800 / 2) - (btnWidth / 2), 230, btnWidth, btnHeight);
	}
	
	public void removeLevelUpPanel()
	{
		this.removeAll();
		this.setVisible(false);
		game.getGameThread().remove(this);
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 800, 300, null);
	 }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == closeBtn)
		{
			game.getAudioHandler().playSound("btnPressed2.wav");
			
			removeLevelUpPanel();
		}
	}
}
