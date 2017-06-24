package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class Motives
{
	int health;
	int maxHealth;
	int energy;
	int maxEnergy;
	int hunger;
	int thirst;
	int morale;
	int maxMorale;
	
	public Motives(int health, int maxHealth, int energy, int maxEnergy, int hunger, int thirst, int morale, int maxMorale)
	{
		this.health = health;
		this.maxHealth = maxHealth;
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.hunger = hunger;
		this.thirst = thirst;
		this.morale = morale;
		this.maxMorale = maxMorale;
	}
	
	/**
	 * @return the health
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	/**
	 * @return the energy
	 */
	public int getEnergy()
	{
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(int energy)
	{
		this.energy = energy;
	}

	/**
	 * @return the maxEnergy
	 */
	public int getMaxEnergy()
	{
		return maxEnergy;
	}

	/**
	 * @param maxEnergy the maxEnergy to set
	 */
	public void setMaxEnergy(int maxEnergy)
	{
		this.maxEnergy = maxEnergy;
	}

	/**
	 * @return the hunger
	 */
	public int getHunger()
	{
		return hunger;
	}

	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(int hunger)
	{
		this.hunger = hunger;
	}

	/**
	 * @return the thirst
	 */
	public int getThirst()
	{
		return thirst;
	}

	/**
	 * @param thirst the thirst to set
	 */
	public void setThirst(int thirst)
	{
		this.thirst = thirst;
	}

	/**
	 * @return the morale
	 */
	public int getMorale()
	{
		return morale;
	}

	/**
	 * @param morale the morale to set
	 */
	public void setMorale(int morale)
	{
		this.morale = morale;
	}

	/**
	 * @return the maxMorale
	 */
	public int getMaxMorale()
	{
		return maxMorale;
	}

	/**
	 * @param maxMorale the maxMorale to set
	 */
	public void setMaxMorale(int maxMorale)
	{
		this.maxMorale = maxMorale;
	}
}
