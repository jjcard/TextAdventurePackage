package jjcard.text.game;

import jjcard.text.game.impl.ItemUse;

public interface IItem extends ConcealableGameElement{
	
	public boolean isMovable();
	
	public ItemUse getUse();
	
	public void setUse(ItemUse use);
	
	/**
	 * returns true if item can be retrieved by the player
	 * @return
	 */
	public boolean canGet();
	
	public void setHidden(boolean hidden);
	
	public void setMovable(boolean movable);
	
	public int getCost();
	
	public int getLevel();
	
	
	

}
