package jjcard.textGames.game.shop;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.util.Experimental;
/**
 * Interface for displaying items that can be bought from seller.
 */
@Experimental
public interface IShopItem extends IGameElement{
	
	/**
	 * Returns the amount of the item the shop has to sell
	 * @return
	 */
	public int getAmount();
	
	/**
	 * Returns true if has infinite amount of the item to sell
	 * @return
	 */
	public boolean isInfinite();
	

	/**
	 * Gets the description of the item
	 */
	public String getRoomDescription();
	
	/**
	 * Returns the price
	 * @return
	 */
	public int getPrice();

}
