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
	 * @return true if infinite amount
	 * <br> Default: returns <code>{@link #getAmount()} == -1</code>
	 */
	public default boolean isInfinite(){
		return getAmount() == -1;
	}

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
	/**
	 * check for if item is in stock.
	 * @return
	 * <br> Default: returns true if {@link #isInfinite()} or amount is > 0
	 */
	public default boolean isAvailable(){
		return isInfinite() || getAmount() > 0;
	}

}
