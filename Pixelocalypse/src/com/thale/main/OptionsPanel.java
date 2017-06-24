package com.thale.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class OptionsPanel extends GameAsset implements ActionListener
{
	private Game game;
	private JFrame window;
	private int windowX;
	private int windowY;
	private int numOfBtns = 0;
	private int btnHeight;
	private int btnWidth;
	
	private ImageIcon bgII;
	private Image bgImage;
	
	// Local Components
	
	public OptionsPanel(Game game) 
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/menuPanelBg.png");
		bgImage = bgII.getImage();
		
		this.window = game.getWindow();
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, btnWidth + 50, (btnHeight * numOfBtns) + (90 + 5 * numOfBtns), null);
	 }
	
	public void createOptionsPanel()
	{
		
	}
	
	public void showOptionsPanel()
	{
		
	}
	
	public void removeOptionsPanel()
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void setData(int x, int y, int btnHeight, int btnWidth)
	{
		this.windowX = x;
		this.windowY = y;
		this.btnHeight = btnHeight;
		this.btnWidth = btnWidth;
		window.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
