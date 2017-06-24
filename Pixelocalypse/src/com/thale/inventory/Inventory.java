package com.thale.inventory;

import javax.swing.JButton;

public class Inventory 
{
	public String name;
	public int numSlots;
	public Slot[] slots;
	public JButton[] slotButtons;
	
	public Inventory(String name, int numSlots, Slot[] slots, JButton[] slotButtons)
	{
		this.name = name;
		this.numSlots = numSlots;
		this.slots = slots;
		this.slotButtons = slotButtons;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getNumSlots()
	{
		return numSlots;
	}
	
	public Slot[] getSlots()
	{
		return slots;
	}
	
	public JButton[] getSlotButtons()
	{
		return slotButtons;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setNumSlots(int numSlots)
	{
		this.numSlots = numSlots;
	}
	
	public void setSlots(Slot[] slots)
	{
		this.slots = slots;
	}
	
	public void setSlotButtons(JButton[] slotButtons)
	{
		this.slotButtons = slotButtons;
	}
}
