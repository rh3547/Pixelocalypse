package com.thale.player;

import java.util.List;

import com.thale.inventory.Inventory;
import com.thale.scene.Scene;

/**
 * Player:
 * 
 * @author Ryan Hochmuth
 *
 * Description:
 *		A character in the game.  There are multiple characters per game.
 *		When your playable character dies, you take control of another in your group.
 *		You can also switch to playing another character at any time. 	
 */
public class Player 
{
	private String name;
	private int level;
	private int xp;
	private String inventory;
	private String location;
	private Scene scene;
	private Motives motives;
	private Attributes attributes;
	private Skills skills;
	private int upgradePoints;
	private int timesLooted;
	private String[] locations;
	
	/**
	 * @param name
	 * @param stats
	 * @param level
	 * @param xp
	 * @param inventory
	 * @param location
	 * @param scene
	 * @param motives
	 * @param attributes
	 * @param skills
	 * @param upgradePoints
	 * @param timesLooted
	 */
	public Player(String name, int level, int xp, String inventory, String location, 
				  Scene scene, Motives motives, Attributes attributes, Skills skills, 
				  int upgradePoints, int timesLooted, String[] locations)
	{
		this.name = name;
		this.level = level;
		this.xp = xp;
		this.inventory = inventory;
		this.location = location;
		this.scene = scene;
		this.motives = motives;
		this.attributes = attributes;
		this.skills = skills;
		this.upgradePoints = upgradePoints;
		this.timesLooted = timesLooted;
		this.locations = locations;
	}

	/**
	 * @return the player's name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name, the player's name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * @return the player's level
	 */
	public int getLevel() 
	{
		return level;
	}

	/**
	 * @param level, the player's level to set
	 */
	public void setLevel(int level) 
	{
		this.level = level;
	}
	
	/**
	 * @return the player's xp
	 */
	public int getXp() 
	{
		return xp;
	}

	/**
	 * @param xp, the player's xp to set
	 */
	public void setXp(int xp) 
	{
		this.xp = xp;
	}
	
	public void increaseXp(int xp)
	{
		this.xp += xp;
	}
	
	/**
	 * @return the player's inventory
	 */
	public String getInventory() 
	{
		return inventory;
	}

	/**
	 * @param inventory, the player's inventory to set
	 */
	public void setInventory(String inventory) 
	{
		this.inventory = inventory;
	}
	
	/**
	 * @return the player's location
	 */
	public String getLocation() 
	{
		return location;
	}

	/**
	 * @param location, the player's location to set
	 */
	public void setLocation(String location) 
	{
		this.location = location;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	/**
	 * @return the motives
	 */
	public Motives getMotives()
	{
		return motives;
	}

	/**
	 * @param motives the motives to set
	 */
	public void setMotives(Motives motives)
	{
		this.motives = motives;
	}

	/**
	 * @return the attributes
	 */
	public Attributes getAttributes()
	{
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Attributes attributes)
	{
		this.attributes = attributes;
	}

	/**
	 * @return the skills
	 */
	public Skills getSkills()
	{
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(Skills skills)
	{
		this.skills = skills;
	}

	/**
	 * @return the upgradePoints
	 */
	public int getUpgradePoints()
	{
		return upgradePoints;
	}

	/**
	 * @param upgradePoints the upgradePoints to set
	 */
	public void setUpgradePoints(int upgradePoints)
	{
		this.upgradePoints = upgradePoints;
	}

	/**
	 * @return the timesLooted
	 */
	public int getTimesLooted()
	{
		return timesLooted;
	}

	/**
	 * @param timesLooted the timesLooted to set
	 */
	public void setTimesLooted(int timesLooted)
	{
		this.timesLooted = timesLooted;
	}

	/**
	 * @return the locations
	 */
	public String[] getLocations()
	{
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(String[] locations)
	{
		this.locations = locations;
	}
}
