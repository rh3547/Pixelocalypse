package com.thale.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class DescPanel extends GameAsset
{
private Game game;

private Font titleFont = new Font("Minecraftia", Font.BOLD, 17);
private Font pixelFont = new Font("Minecraftia", Font.PLAIN, 15);

private ImageIcon bgII;
private Image bgImage;

private JLabel nameLbl = new JLabel();
private JSeparator sep = new JSeparator();
private JLabel descLbl = new JLabel();
private JLabel descLbl2 = new JLabel();
private JLabel descLbl3 = new JLabel();
private JLabel descLbl4 = new JLabel();

private String desc1 = "";
private String desc2 = "";
private String desc3 = "";
private String desc4 = "";
	
	public DescPanel(Game game)
	{
		super(game);
		this.game = game;
		
		bgII = new ImageIcon(game.getRespath() + "gui/backgrounds/pane.png");
		bgImage = bgII.getImage();
	}
	
	public void showPanel(String name, String desc, int x, int y)
	{
		desc1 = "";
		desc2 = "";
		desc3 = "";
		desc4 = "";
		
		this.setLayout(null);
		game.getGameThread().add(this);
		game.getGameThread().setComponentZOrder(this, 1);
		this.setBounds(x, y, 500, 200);
		
		name = name.replace("-", " ");
		
		nameLbl.setFont(titleFont);
		nameLbl.setForeground(Color.white);
		nameLbl.setText(name);
		this.add(nameLbl);
		nameLbl.setBounds(10, 10, 500, 30);
		nameLbl.setVisible(true);
		
		this.add(sep);
		sep.setBounds(10, 45, 460, 5);
		
		if (desc.contains("~") == true)
		{
			desc4 = desc.substring(desc.indexOf('~'), desc.length());
			desc = desc.substring(0, desc.indexOf('~') - 1);
		}
		
		String[] strArray = desc.split(" ");
		
		for (int count = 0; count < strArray.length; count++)
		{
			if (desc1.length() < 35)
			{
				desc1 = desc1 + strArray[count] + " ";
			}
			else if (desc2.length() < 35)
			{
				desc2 = desc2 + strArray[count] + " ";
			}
			else
			{
				desc3 = desc3 + strArray[count] + " ";
			}
		}
		
		descLbl.setFont(pixelFont);
		descLbl.setForeground(Color.white);
		descLbl.setText(desc1);
		this.add(descLbl);
		descLbl.setBounds(10, 53, 500, 30);
		descLbl.setVisible(true);
		
		descLbl2.setFont(pixelFont);
		descLbl2.setForeground(Color.white);
		descLbl2.setText(desc2);
		this.add(descLbl2);
		descLbl2.setBounds(10, 85, 500, 30);
		descLbl2.setVisible(true);
		
		descLbl3.setFont(pixelFont);
		descLbl3.setForeground(Color.white);
		descLbl3.setText(desc3);
		this.add(descLbl3);
		descLbl3.setBounds(10, 117, 500, 30);
		descLbl3.setVisible(true);
		
		descLbl4.setFont(pixelFont);
		descLbl4.setForeground(Color.white);
		descLbl4.setText(desc4);
		this.add(descLbl4);
		descLbl4.setBounds(10, 147, 500, 30);
		descLbl4.setVisible(true);
		
		this.setVisible(true);
	}
	
	public void hidePanel()
	{
		this.removeAll();
		game.getGameThread().remove(this);
	}
	
	@Override
	 protected void paintComponent(Graphics g) 
	 {
	     super.paintComponent(g); // paint the background image and scale it to fill the entire space
	     
	     g.drawImage(bgImage, 0, 0, 500, 200, null);
	 }
}
