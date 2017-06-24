package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class Attributes
{
	int strength;
	int constitution;
	int agility;
	int intelligence;
	int charisma;
	
	public Attributes(int strength, int constitution, int agility, int intelligence, int charisma)
	{
		this.strength = strength;
		this.constitution = constitution;
		this.agility = agility;
		this.intelligence = intelligence;
		this.charisma = charisma;
	}

	/**
	 * @return the strength
	 */
	public int getStrength()
	{
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength)
	{
		this.strength = strength;
	}

	/**
	 * @return the constitution
	 */
	public int getConstitution()
	{
		return constitution;
	}

	/**
	 * @param constitution the constitution to set
	 */
	public void setConstitution(int constitution)
	{
		this.constitution = constitution;
	}

	/**
	 * @return the agility
	 */
	public int getAgility()
	{
		return agility;
	}

	/**
	 * @param agility the agility to set
	 */
	public void setAgility(int agility)
	{
		this.agility = agility;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence()
	{
		return intelligence;
	}

	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}

	/**
	 * @return the charisma
	 */
	public int getCharisma()
	{
		return charisma;
	}

	/**
	 * @param charisma the charisma to set
	 */
	public void setCharisma(int charisma)
	{
		this.charisma = charisma;
	}
}
