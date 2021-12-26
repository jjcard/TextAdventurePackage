package jjcard.text.game;

import jjcard.text.game.impl.ItemUse;

public interface IItem extends ConcealableGameElement{
	
	boolean isMovable();
	
	ItemUse getUse();
	
	void setUse(ItemUse use);
	
	/**
	 * returns true if item can be retrieved by the player
	 * @return
	 */
	boolean canGet();
	
	@Override
	void setHidden(boolean hidden);
	
	void setMovable(boolean movable);
	
	int getCost();
	
	int getLevel();
	
	
	

}
