package com.thale.crafting;

import com.thale.items.Item;

public class Recipe
{
	String name;
	Item[] recipeInput;
	int[] inputAmounts;
	Item[] recipeOutput;
	int[] outputAmounts;
	
	public Recipe(String name, Item[] recipeInput, int[] inputAmounts, Item[] recipeOutput, int[] outputAmounts)
	{
		this.name = name;
		this.recipeInput = recipeInput;
		this.inputAmounts = inputAmounts;
		this.recipeOutput = recipeOutput;
		this.outputAmounts = outputAmounts;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the recipeInput
	 */
	public Item[] getRecipeInput()
	{
		return recipeInput;
	}

	/**
	 * @param recipeInput the recipeInput to set
	 */
	public void setRecipeInput(Item[] recipeInput)
	{
		this.recipeInput = recipeInput;
	}

	/**
	 * @return the inputAmounts
	 */
	public int[] getInputAmounts()
	{
		return inputAmounts;
	}

	/**
	 * @param inputAmounts the inputAmounts to set
	 */
	public void setInputAmounts(int[] inputAmounts)
	{
		this.inputAmounts = inputAmounts;
	}

	/**
	 * @return the recipeOutput
	 */
	public Item[] getRecipeOutput()
	{
		return recipeOutput;
	}

	/**
	 * @param recipeOutput the recipeOutput to set
	 */
	public void setRecipeOutput(Item[] recipeOutput)
	{
		this.recipeOutput = recipeOutput;
	}

	/**
	 * @return the outputAmounts
	 */
	public int[] getOutputAmounts()
	{
		return outputAmounts;
	}

	/**
	 * @param outputAmounts the outputAmounts to set
	 */
	public void setOutputAmounts(int[] outputAmounts)
	{
		this.outputAmounts = outputAmounts;
	}
}
