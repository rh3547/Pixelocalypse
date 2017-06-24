package com.thale.player;

import com.thale.engine.Game;
import com.thale.engine.GameAsset;

public class Skills
{
	int looting;
	
	public Skills(int looting)
	{
		this.looting = looting;
	}

	/**
	 * @return the looting
	 */
	public int getLooting()
	{
		return looting;
	}

	/**
	 * @param looting the looting to set
	 */
	public void setLooting(int looting)
	{
		this.looting = looting;
	}
}
