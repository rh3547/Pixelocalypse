package com.thale.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import com.thale.items.Item;

public class Location 
{
	String name;
	int rarity;
	String[] loot;
	int[] lootChance;
	ImageIcon icon;
	int danger;
	int level;
	
	public Location(String name, int rarity, String[] loot, int[] lootChance, ImageIcon icon, int danger, int level)
	{
		this.name = name;
		this.rarity = rarity;
		this.loot = loot;
		this.lootChance = lootChance;
		this.icon = icon;
		this.danger = danger;
		this.level = level;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getRarity()
	{
		return rarity;
	}
	
	public String[] getLoot()
	{
		return loot;
	}
	
	public int[] getLootChance()
	{
		return lootChance;
	}
	
	public ImageIcon getIcon()
	{
		return icon;
	}
	
	public int getDanger()
	{
		return danger;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setRarity(int rarity)
	{
		this.rarity = rarity;
	}
	
	public void setLoot(String[] loot)
	{
		this.loot = loot;
	}
	
	public void setLootChance(int[] lootChance)
	{
		this.lootChance = lootChance;
	}
	
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}
	
	public void setDanger(int danger)
	{
		this.danger = danger;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
}
