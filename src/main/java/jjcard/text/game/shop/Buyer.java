package jjcard.text.game.shop;

import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
/**
 *
 */
@Experimental
public interface Buyer {

	public void buy(IMob seller, IItem item);
	
	public void buy(IMob seller, IItem item, int amount);
	
	
}
