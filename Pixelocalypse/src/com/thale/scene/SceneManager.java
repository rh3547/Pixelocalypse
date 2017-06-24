package com.thale.scene;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.player.Player;
import com.thale.sprite.Sprite;

public class SceneManager extends GameAsset
{
	Game game;
	JPanel gameThread;
	
	List<Scene> scenes = new ArrayList<Scene>();
	
	public SceneManager(Game game)
	{
		super(game);
		this.game = game;
		gameThread = game.getGameThread();
	}
	
	public void createNewPlayerScene(Player player)
	{	
		player.setScene(new Scene(game, player, game.getRespath() + "scenes/" + player.getLocation() + "Scene.png", new ArrayList<Sprite>()));
		scenes.add(player.getScene());
		
		regenerateScene(player);
		
		System.out.print("\n\n" + player.getScene().getBgImageStr());
	}
	
	public void showSprites(Player player)
	{
		for (int x = 0; x < player.getScene().getSprites().size(); x++)
		{
			player.getScene().add(player.getScene().getSprites().get(x));
			player.getScene().getSprites().get(x).setBounds(player.getScene().getSprites().get(x).getPosX(), 
															player.getScene().getSprites().get(x).getPosY(), 
															128, 128);
			player.getScene().getSprites().get(x).setVisible(true);
		}
		
		if (player.getScene().getSprites().size() > 1)
		{
			for (int x = 0; x < player.getScene().getSprites().size(); x++)
			{
				if (player.getScene().getSprites().get(x).getName().equals("Character"))
				{
					player.getScene().setComponentZOrder(player.getScene().getSprites().get(x), 1);
					break;
				}
			}
		}
	}
	
	public void regenerateScene(Player player)
	{
		player.getScene().getSprites().clear();
		player.getScene().removeAll();
		
		player.getScene().addToSprites(new Sprite("Character", 0, 0, game.getRespath() + "scenes/characterRight.png"));
		populateLoot(player);
	}
	
	public void populateLoot(Player player)
	{
		Random randGen = new Random();
		int[] xArray = {0, 128, 256, 384, 512, 640, 768, 896, 1024, 1152, 1280};
		int[] yArray = {0, 128, 256, 384, 512, 640};
		int textureNum = 0;
		int xPos = 0;
		int yPos = 0;
		
		for (int x = 0; x < 5; x++)
		{
			xPos = randGen.nextInt(10);
			yPos = randGen.nextInt(5);
			textureNum = randGen.nextInt(4);
			
			player.getScene().addToSprites(new Sprite("Loot", xArray[xPos], yArray[yPos], game.getRespath() + "scenes/loot" + textureNum + ".png"));
		}
	}
	
	public void showScene(Player player)
	{
		game.getGameThread().add(player.getScene());
		player.getScene().setBounds(0, 75, 1480, 745);
		
		showSprites(player);
		
		player.getScene().setVisible(true);
	}
	
	public void hideScene(Player player)
	{
		player.getScene().removeAll();
		game.getGameThread().remove(player.getScene());
		player.getScene().setVisible(false);
	}
	
	public void checkPosition(Player player)
	{
		int index = 0;
		int spriteIndex = 100;
		
		for (int x = 0; x < player.getScene().getSprites().size(); x++)
		{
			if (player.getScene().getSprites().get(x).getName().equals("Character"))
			{
				index = x;
				break;
			}
		}
		
		for (int x = 0; x < player.getScene().getSprites().size(); x++)
		{
			if (player.getScene().getSprites().get(index).getPosX() == player.getScene().getSprites().get(x).getPosX() &&
					!player.getScene().getSprites().get(x).getName().equals("Character"))
			{
				if (player.getScene().getSprites().get(index).getPosY() == player.getScene().getSprites().get(x).getPosY())
				{
					System.out.print("\n" + player.getScene().getSprites().get(x).getName());
					spriteIndex = x;
					break;
				}
			}
		}
		
		if (spriteIndex != 100)
		{
			if (player.getScene().getSprites().get(spriteIndex).getName().equals("Loot"))
			{
				game.getLocationManager().addToLootingPlayers(player);
				game.getLocationManager().loot(player, spriteIndex);
				player.setTimesLooted(player.getTimesLooted() + 1);
			}
		}
	}
}
