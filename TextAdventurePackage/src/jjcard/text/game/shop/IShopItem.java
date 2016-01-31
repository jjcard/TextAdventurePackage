package jjcard.text.game.shop;

import jjcard.text.game.IGameElement;
import jjcard.text.game.util.Experimental;
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
	 * Gets the room description of the underlying item
	 */
	public String getRoomDescription();
	/**
	 * Gets the view description for the shop item or underlying item
	 */
	public String getViewDescription();
	/**
	 * Returns the price
	 * @return
	 */
	public int getPrice();

}
