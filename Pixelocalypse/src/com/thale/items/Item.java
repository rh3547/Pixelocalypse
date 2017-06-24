package com.thale.items;

import javax.swing.ImageIcon;

public class Item 
{
	String name;
	int minLoot;
	int maxLoot;
	int value;
	int stackSize;
	ImageIcon icon;
	String desc;
	String use;
	int useAmt;
	int mod;
	
	public Item(String name, int minLoot, int maxLoot, int value, int stackSize, ImageIcon icon, String desc, String use, int useAmt, int mod)
	{
		this.name = name;
		this.minLoot = minLoot;
		this.maxLoot = maxLoot;
		this.value = value;
		this.stackSize = stackSize;
		this.icon = icon;
		this.desc = desc;
		this.use = use;
		this.useAmt = useAmt;
		this.mod = mod;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getMinLoot()
	{
		return minLoot;
	}
	
	public int getMaxLoot()
	{
		return maxLoot;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int getStackSize()
	{
		return stackSize;
	}
	
	public ImageIcon getIcon()
	{
		return icon;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setMinloot(int minLoot)
	{
		this.minLoot = minLoot;
	}
	
	public void setMaxLoot(int maxLoot)
	{
		this.maxLoot = maxLoot;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public void setStackSize(int stackSize)
	{
		this.stackSize = stackSize;
	}
	
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
	}

	/**
	 * @return the desc
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 * @return the use
	 */
	public String getUse()
	{
		return use;
	}

	/**
	 * @param use the use to set
	 */
	public void setUse(String use)
	{
		this.use = use;
	}

	/**
	 * @return the useAmt
	 */
	public int getUseAmt()
	{
		return useAmt;
	}

	/**
	 * @param useAmt the useAmt to set
	 */
	public void setUseAmt(int useAmt)
	{
		this.useAmt = useAmt;
	}

	/**
	 * @return the mod
	 */
	public int getMod()
	{
		return mod;
	}

	/**
	 * @param mod the mod to set
	 */
	public void setMod(int mod)
	{
		this.mod = mod;
	}
}
