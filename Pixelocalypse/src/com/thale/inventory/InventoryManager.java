package com.thale.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;
import com.thale.items.Item;
import com.thale.items.ItemManager;

/**
 * InventoryManager: 
 *
 * 	This class handles everything to do with
 *  inventories.  It holds a list of every
 *  inventory in the game.  Inventories can
 *  be obtained, created, and manipulated here.
 * 
 * @author Ryan Hochmuth
 */

public class InventoryManager extends GameAsset implements ActionListener, MouseMotionListener, MouseListener
{
	public List<Inventory> inventories = new ArrayList<Inventory>();
	public String shownInventory;
	
	private Slot firstSlot;
	private Slot secondSlot;
	private Slot storedSlot = null;
	private String storedSlotInv;
	
	private boolean craftMode = false;
	
	private Game game;
	private ItemManager itemMan;
	
	public Inventory defaultInv;
	
	// Slot Components
	private ImageIcon icon;
	private ImageIcon slotBtnIcon;
	private ImageIcon slotSelectedIcon;
	private ImageIcon slotHoverIcon;
	
	// Local Components
	private DescPanel descPanel;
	
	public InventoryManager(Game game) 
	{
		super(game);
		this.game = game;
		
		descPanel = new DescPanel(game);
		
		slotBtnIcon = new ImageIcon(game.getRespath() + "gui/backgrounds/slotBtnIcon.png");
		slotSelectedIcon = new ImageIcon(game.getRespath() + "gui/backgrounds/slotSelected.png");
		slotHoverIcon = new ImageIcon(game.getRespath() + "gui/backgrounds/slotHover.png");
		
		itemMan = game.getItemManager();
	}
	
	public Inventory getInventory(String name)
	{
		int index = 0;
		
		for (int count = 0; count < inventories.size(); count++)
		{
			if (inventories.get(count).getName().equals(name))
			{
				index = count;
			}
		}
		
		return inventories.get(index);
	}
	
	public int getInventoryIndex(String name)
	{
		int index = 0;
		
		for (int count = 0; count < inventories.size(); count++)
		{
			if (inventories.get(count).getName().equals(name))
			{
				index = count;
			}
		}
		
		return index;
	}
	
	public void createNewInventory(String name, int numSlots)
	{
		Slot[] slots = new Slot[numSlots];
		JButton[] slotButtons = new JButton[numSlots];
		
		for (int x = 0; x < numSlots; x++)
		{
			Item nullItem = itemMan.getItem("Null");
			icon = nullItem.getIcon();
			JLabel itemIcon = new JLabel();
			JLabel stackLbl = new JLabel();
			
			JButton slotButton = new JButton();
			slotButton.addActionListener(this);
			slotButton.addMouseListener(this);
			
			slotButtons[x] = slotButton;	// Creates the buttons used for selecting slots
			
			slots[x] = new Slot(game, x, nullItem, 0, 0, icon, itemIcon, stackLbl);	// Creates and adds a new slot to the inventory
		}
		
		inventories.add(new Inventory(name, numSlots, slots, slotButtons));
	}
	
	public void deleteInventory(String name)
	{
		inventories.remove(getInventoryIndex(name));
	}
	
	public void setInventoryOnTop(String name, JPanel panel)
	{
		for (int x = 0; x < getInventory(name).getNumSlots(); x++)
		{
			panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
		}
	}
	
	public void showInventory(String name, JPanel panel, int posX, int posY)
	{
		for (int x = 0; x < getInventory(name).getNumSlots(); x++)
		{
			panel.add(getInventory(name).getSlots()[x]);
			
			if (x < 5)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x), posY, 48, 56);
				
				panel.add(getInventory(name).getSlotButtons()[x]);
				getInventory(name).getSlotButtons()[x].setBorderPainted(false);
				getInventory(name).getSlotButtons()[x].setContentAreaFilled(false);
				getInventory(name).getSlotButtons()[x].setFocusPainted(false);
				panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
				getInventory(name).getSlotButtons()[x].setBounds((posX) + (48 * x), posY, 48, 56);
				getInventory(name).getSlotButtons()[x].setEnabled(true);
				getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
				getInventory(name).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
				getInventory(name).getSlotButtons()[x].setVisible(true);
			}
			
			if (x >= 5 && x <= 10)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 5), posY + 56, 48, 56);
				
				panel.add(getInventory(name).getSlotButtons()[x]);
				getInventory(name).getSlotButtons()[x].setBorderPainted(false);
				getInventory(name).getSlotButtons()[x].setContentAreaFilled(false);
				getInventory(name).getSlotButtons()[x].setFocusPainted(false);
				panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
				getInventory(name).getSlotButtons()[x].setBounds((posX) + (48 * x) - (48 * 5), posY + 56, 48, 56);
				getInventory(name).getSlotButtons()[x].setEnabled(true);
				getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
				getInventory(name).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
				getInventory(name).getSlotButtons()[x].setVisible(true);
			}
			
			if (x >= 10 && x <= 15)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 10), posY + (56 * 2), 48, 56);
				
				panel.add(getInventory(name).getSlotButtons()[x]);
				getInventory(name).getSlotButtons()[x].setBorderPainted(false);
				getInventory(name).getSlotButtons()[x].setContentAreaFilled(false);
				getInventory(name).getSlotButtons()[x].setFocusPainted(false);
				panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
				getInventory(name).getSlotButtons()[x].setBounds((posX) + (48 * x) - (48 * 10), posY + (56 * 2), 48, 56);
				getInventory(name).getSlotButtons()[x].setEnabled(true);
				getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
				getInventory(name).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
				getInventory(name).getSlotButtons()[x].setVisible(true);
			}
			
			if (x >= 15 && x <= 20)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 15), posY + (56 * 3), 48, 56);
				
				panel.add(getInventory(name).getSlotButtons()[x]);
				getInventory(name).getSlotButtons()[x].setBorderPainted(false);
				getInventory(name).getSlotButtons()[x].setContentAreaFilled(false);
				getInventory(name).getSlotButtons()[x].setFocusPainted(false);
				panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
				getInventory(name).getSlotButtons()[x].setBounds((posX) + (48 * x) - (48 * 15), posY + (56 * 3), 48, 56);
				getInventory(name).getSlotButtons()[x].setEnabled(true);
				getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
				getInventory(name).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
				getInventory(name).getSlotButtons()[x].setVisible(true);
			}
			
			if (x >= 20 && x < 25)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 20), posY + (56 * 4), 48, 56);
				
				panel.add(getInventory(name).getSlotButtons()[x]);
				getInventory(name).getSlotButtons()[x].setBorderPainted(false);
				getInventory(name).getSlotButtons()[x].setContentAreaFilled(false);
				getInventory(name).getSlotButtons()[x].setFocusPainted(false);
				panel.setComponentZOrder(getInventory(name).getSlotButtons()[x], 1);
				getInventory(name).getSlotButtons()[x].setBounds((posX) + (48 * x) - (48 * 20), posY + (56 * 4), 48, 56);
				getInventory(name).getSlotButtons()[x].setEnabled(true);
				getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
				getInventory(name).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
				getInventory(name).getSlotButtons()[x].setVisible(true);
			}
			
			if (getInventory(name).getSlots()[x].getSlotStack() == 0)
			{
				getInventory(name).getSlots()[x].getStackLbl().setVisible(false);
			}
			else
			{
				getInventory(name).getSlots()[x].getStackLbl().setVisible(true);
			}
			
			getInventory(name).getSlots()[x].setVisible(true);
		}
		
		this.shownInventory = name;
	}
	
	public void updateInventoryPosition(String name, JPanel panel, int posX, int posY)
	{
		for (int x = 0; x < getInventory(name).getNumSlots(); x++)
		{
			panel.add(getInventory(name).getSlots()[x]);
			
			if (x < 5)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x), posY, 48, 56);
			}
			
			if (x >= 5 && x <= 10)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 5), posY + 56, 48, 56);
			}
			
			if (x >= 10 && x <= 15)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 10), posY + (56 * 2), 48, 56);
			}
			
			if (x >= 15 && x <= 20)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 15), posY + (56 * 3), 48, 56);
			}
			
			if (x >= 20 && x < 25)
			{
				getInventory(name).getSlots()[x].setBounds((posX) + (48 * x) - (48 * 20), posY + (56 * 4), 48, 56);
			}
			
			if (getInventory(name).getSlots()[x].getSlotStack() == 0)
			{
				getInventory(name).getSlots()[x].getStackLbl().setVisible(false);
			}
			else
			{
				getInventory(name).getSlots()[x].getStackLbl().setVisible(true);
			}
			
			getInventory(name).getSlots()[x].setVisible(true);
		}
	}
	
	public void hideInventory(String name, JPanel panel)
	{		
		for (int x = 0; x < getInventory(name).getNumSlots(); x++)
		{
			panel.remove(getInventory(name).getSlots()[x]);
			getInventory(name).getSlotButtons()[x].setIcon(slotBtnIcon);
			panel.remove(getInventory(name).getSlotButtons()[x]);
		}
		
		shownInventory = null;
		
		firstSlot = null;
		secondSlot = null;
	}
	
	public void addToSlot(String name, int slotNum, Item item, int amount)
	{
		Inventory inventory = getInventory(name);
		
		if (slotNum <= inventory.getNumSlots())
		{
			Slot[] slots = inventory.getSlots();
			Slot slot = slots[slotNum];
			
			if (slot.getSlotItem() == item)
			{
				if ((slot.getSlotStack() + amount) <= slot.getSlotMaxStack())
				{
					slot.setSlotStack(slot.getSlotStack() + amount);
					slot.getStackLbl().setText(Integer.toString(slot.getSlotStack()));
				}
				else
				{
					System.out.print
					("\n\nCannot add " + amount + " to slot " + slotNum + " because it would exceed the slot max stack.");
				}
			}
			else if (slot.getSlotItem().getName().equals("Null"))
			{
				System.out.print("\n\nThe item " + item.getName() + " is not in slot " + slotNum + ".");
				
				if (amount <= item.getStackSize())
				{
					slot.setSlotItem(item);
					slot.setIcon(item.getIcon());
					slot.getItemIcon().setIcon(slot.getIcon());
					
					slot.setSlotMaxStack(item.getStackSize());
					
					slot.setSlotStack(amount);
					slot.getStackLbl().setText(Integer.toString(slot.getSlotStack()));
				}
				else
				{
					System.out.print
					("\n\nCannot add " + amount + " to slot " + slotNum + " because it would exceed the slot max stack.");
				}
			}
			else
			{
				System.out.print
				("\n\nThere is another item in slot " + slotNum + " so " + item.getName() + " was not added.");
			}
			
			if (slot.getSlotStack() == 0)
			{
				slot.getStackLbl().setVisible(false);
			}
			else
			{
				slot.getStackLbl().setVisible(true);
			}
		}
		else
		{
			System.out.print
			("\n\nThe slot number " + slotNum + " is not in the inventory specified.");
		}
	}
	
	public void removeFromSlot(String name, int slotNum, Item item, int amount)
	{
		Inventory inventory = getInventory(name);
		Slot[] slots = inventory.getSlots();
		Slot slot = slots[slotNum];
		
		Item nullItem = itemMan.getItem("Null");
		
		if (slot.getSlotItem() == item)
		{
			if ((slot.getSlotStack() - amount) >= 0)
			{
				slot.setSlotStack(slot.getSlotStack() - amount);
				slot.getStackLbl().setText(Integer.toString(slot.getSlotStack()));
			}
			else
			{
				System.out.print
				("\n\nThere is not enough " + item.getName() + " in slot " + slotNum + " to remove " + amount + ".");
			}
			
			if (slot.getSlotStack() == 0)
			{
				slot.setSlotItem(nullItem);
				slot.setIcon(nullItem.getIcon());
				slot.getItemIcon().setIcon(slot.getIcon());
				slot.getStackLbl().setVisible(false);
			}
			else
			{
				slot.getStackLbl().setVisible(true);
			}
		}
		else
		{
			System.out.print("\n\nThe item " + item.getName() + " is not in slot " + slotNum + ".");
		}
	}
	
	public void moveItemToSlot(Slot slot1, String slot1Inv, Slot slot2, String slot2Inv)
	{
		if (slot2.getSlotItem().getName().equals("Null"))
		{
			addToSlot(slot2Inv, slot2.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
			removeFromSlot(slot1Inv, slot1.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
		}
		else if (slot1.getSlotItem().equals(slot2.getSlotItem()))
		{
			if ((slot1.getSlotStack() + slot2.getSlotStack()) <= slot2.getSlotMaxStack())
			{
				addToSlot(slot2Inv, slot2.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
				removeFromSlot(slot1Inv, slot1.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
			}
			else
			{
				int difference = slot2.getSlotMaxStack() - slot2.getSlotStack();
				
				addToSlot(slot2Inv, slot2.getSlotNum(), slot1.getSlotItem(), difference);
				removeFromSlot(slot1Inv, slot1.getSlotNum(), slot1.getSlotItem(), difference);
			}
		}
		else
		{
			Item item = slot2.getSlotItem();
			int stack = slot2.getSlotStack();
			
			removeFromSlot(slot2Inv, slot2.getSlotNum(), slot2.getSlotItem(), slot2.getSlotStack());
			addToSlot(slot2Inv, slot2.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
			
			removeFromSlot(slot1Inv, slot1.getSlotNum(), slot1.getSlotItem(), slot1.getSlotStack());
			addToSlot(slot1Inv, slot1.getSlotNum(), item, stack);
		}
	}
	
	public void deleteItems()
	{
		if (firstSlot != null)
		{
			removeFromSlot(shownInventory, firstSlot.getSlotNum(), firstSlot.getSlotItem(), firstSlot.getSlotStack());
			firstSlot = null;
		}
	}
	
	public void addToInventory(String invName, Item item, int amount)
	{
		Inventory inventory = getInventory(invName);
		Slot[] slots = inventory.getSlots();
		boolean itemAdded = false;
		
		for (int x = 0; x < inventory.getNumSlots(); x++)
		{
			if (item == slots[x].getSlotItem())
			{
				if ((slots[x].getSlotStack() + amount) <= slots[x].getSlotMaxStack())
				{
					System.out.print
					("\n\nCombined " + amount + " " + item.getName() + " with " + slots[x].getSlotStack() + " " + item.getName() + 
					" in slot " + x + ".  For a total of " + (slots[x].getSlotStack() + amount) + ".");
					
					addToSlot(invName, x, item, amount);
					itemAdded = true;
					amount -= amount;
					
					break;
				}
			}
		}
		
		if (amount > 0 && itemAdded == false)
		{
			for (int x = 0; x < inventory.getNumSlots(); x++)
			{
				if (item == slots[x].getSlotItem())
				{
					int difference = slots[x].getSlotMaxStack() - slots[x].getSlotStack();
					
					System.out.print
					("\n\nCombined " + difference + " " + item.getName() + " with " + slots[x].getSlotStack() + " " + item.getName() + 
					" in slot " + x + ".  For a total of " + slots[x].getSlotMaxStack() + ".");
					
					addToSlot(invName, x, item, difference);
					
					amount -= difference;
					
					System.out.print
					("\n\nAmount to add remaining is " + amount + ".");
				}
			}
		}
		
		if (amount > 0 && itemAdded == false)
		{
			for (int x = 0; x < inventory.getNumSlots(); x++)
			{
				if (slots[x].getSlotItem() == itemMan.getItem("Null"))
				{
					if (amount <= item.getStackSize())
					{
						addToSlot(invName, x, item, amount);
						
						System.out.print
						("\n\nAdded " + amount + " " + item.getName() + " to slot " + x + ".");
						
						break;
					}
					else
					{
						int difference = amount - item.getStackSize();
						
						addToSlot(invName, x, item, amount - difference);
					}
				}
			}
		}
	}
	
	public void clearInventory(String invName)
	{
		Inventory inventory = getInventory(invName);
		Slot[] slots = inventory.getSlots();
		
		for (int x = 0; x < slots.length; x++)
		{
			slots[x].setSlotItem(game.getItemManager().getItem("Null"));
			slots[x].setSlotStack(0);
			slots[x].setIcon(game.getItemManager().getItem("Null").getIcon());
			slots[x].getItemIcon().setIcon(slots[x].getIcon());
			slots[x].getStackLbl().setVisible(false);
		}
	}
	
	public void updateInventorySize(Inventory inventory, int numSlots)
	{
		String invName = inventory.getName();
		createNewInventory(invName + "Temp", numSlots);
		
		for (int x = 0; x < inventory.getNumSlots(); x++)
		{
			addToSlot(invName + "Temp", x, inventory.getSlots()[x].getSlotItem(), inventory.getSlots()[x].getSlotStack());
		}
		
		deleteInventory(invName);
		
		getInventory(invName + "Temp").setName(invName);
	}
	
	public void storeSlot()
	{
		storedSlot = firstSlot;
		storedSlotInv = shownInventory;
		//firstSlot = null;
	}
	
	public Slot getFirstSlot()
	{
		return firstSlot;
	}
	
	public void setFirstSlot(Slot slot)
	{
		this.firstSlot = slot;
	}
	
	public void resetFirstSlot()
	{
		getInventory(shownInventory).getSlotButtons()[firstSlot.getSlotNum()].setIcon(slotBtnIcon);
		getInventory(shownInventory).getSlotButtons()[firstSlot.getSlotNum()].setRolloverIcon(slotHoverIcon);
	}
	
	public Inventory getDefaultInventory()
	{
		return defaultInv;
	}
	
	public void setDefaultInventory(String invName)
	{
		this.defaultInv = getInventory(invName);
	}
	
	public String getShownInventory()
	{
		return shownInventory;
	}
	
	public void setShownInventory(String shownInventory)
	{
		this.shownInventory = shownInventory;
	}
	
	public void setCraftMode(boolean craftMode)
	{
		this.craftMode = craftMode;
	}
	
	public List<Inventory> getInventories()
	{
		return inventories;
	}
	
	public void setInventories(List<Inventory> inventories)
	{
		this.inventories = inventories;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		System.out.print("\n\n" + getShownInventory());
		
		if (craftMode == false)
		{
			setShownInventory(game.getCurrentPlayer().getInventory());
			
			if (storedSlot == null)
			{
				for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
				{
					if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
					{
						System.out.print("\n\nSlot " + x + " in " + shownInventory + " was clicked.");
						
						if (firstSlot != null)
						{
							secondSlot = getInventory(shownInventory).getSlots()[x];
							System.out.print("\n\nSecond Selected: " + x);
							
							if (firstSlot != secondSlot)
							{
								moveItemToSlot(firstSlot, shownInventory, secondSlot, shownInventory);
							}
							else
							{
								getInventory(shownInventory).getSlotButtons()[x].setIcon(slotBtnIcon);
								getInventory(shownInventory).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
							}
							
							firstSlot = null;
							secondSlot = null;
						}
						else if (firstSlot == null)
						{
							getInventory(shownInventory).getSlotButtons()[x].setIcon(slotSelectedIcon);
							getInventory(shownInventory).getSlotButtons()[x].setRolloverIcon(slotSelectedIcon);
							System.out.print("\n\nFirst Selected: " + x);
							firstSlot = getInventory(shownInventory).getSlots()[x];
							
							if (game.getKeyboardListener().isKeyPressed(KeyEvent.VK_DELETE))
							{
								deleteItems();
								
								firstSlot = null;
								getInventory(shownInventory).getSlotButtons()[x].setIcon(slotBtnIcon);
								getInventory(shownInventory).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
							}
						}
					}
					else
					{
						getInventory(shownInventory).getSlotButtons()[x].setIcon(slotBtnIcon);
						getInventory(shownInventory).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
					}
				}
			}
			else
			{
				for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
				{
					if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
					{
						System.out.print("\n\nSlot " + x + " in " + shownInventory + " was clicked.");
						
						secondSlot = getInventory(shownInventory).getSlots()[x];
						
						if (storedSlot != secondSlot)
						{
							moveItemToSlot(storedSlot, storedSlotInv, secondSlot, shownInventory);
							//removeFromSlot
							//(storedSlotInv, storedSlot.getSlotNum(), storedSlot.getSlotItem(), storedSlot.getSlotStack());
							//addToSlot(storedSlotInv, storedSlot.getSlotNum(), secondSlot.getSlotItem(), secondSlot.getSlotStack());
						}
						else
						{
							getInventory(shownInventory).getSlotButtons()[x].setIcon(slotBtnIcon);
							getInventory(shownInventory).getSlotButtons()[x].setRolloverIcon(slotHoverIcon);
						}
						
						firstSlot = null;
						secondSlot = null;
						storedSlot = null;
					}
				}
			}
		}
		else if (craftMode == true)
		{
			setShownInventory(game.getCurrentPlayer().getInventory());
			
			for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
			{
				if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
				{
					System.out.print("\n\nSlot " + x + " in " + shownInventory + " was clicked.");
					
					addToInventory("Crafting-Input", 
							       getInventory(shownInventory).getSlots()[x].getSlotItem(), 1);
					
					removeFromSlot(shownInventory, x, 
							       getInventory(shownInventory).getSlots()[x].getSlotItem(), 1);
					
					game.getCraftingManager().checkRecipe();
					
					break;
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
		{
			if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
			{
				if (craftMode == true)
				{
					descPanel.hidePanel();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
		{
			if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
			{
				if (!getInventory(shownInventory).getSlots()[x].getSlotItem().getName().equals("Null"))
				{
					if (craftMode == false)
					{
						descPanel.showPanel(getInventory(shownInventory).getSlots()[x].getSlotItem().getName(), 
								getInventory(shownInventory).getSlots()[x].getSlotItem().getDesc(),
								(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getX() - 175,
								(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getY() - 135);
					}
					else
					{
						if (shownInventory.equals("Crafting-Input"))
						{
							descPanel.showPanel(getInventory(shownInventory).getSlots()[x].getSlotItem().getName(), 
									getInventory(shownInventory).getSlots()[x].getSlotItem().getDesc(),
									(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getX() - 175,
									(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getY() - 135);
						}
						else
						{
							descPanel.showPanel(getInventory(shownInventory).getSlots()[x].getSlotItem().getName(), 
									getInventory(shownInventory).getSlots()[x].getSlotItem().getDesc(),
									(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getX() - 730,
									(int)getInventory(shownInventory).getSlotButtons()[x].getLocationOnScreen().getY() - 135);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		for (int x = 0; x < getInventory(shownInventory).getNumSlots(); x++)
		{
			if (e.getSource() == getInventory(shownInventory).getSlotButtons()[x])
			{
				if (!getInventory(shownInventory).getSlots()[x].getSlotItem().getName().equals("Null"))
				{
					descPanel.hidePanel();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
	}
}
