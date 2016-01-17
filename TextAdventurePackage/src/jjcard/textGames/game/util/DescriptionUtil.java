package jjcard.textGames.game.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jjcard.textGames.game.ConcealableGameElement;
import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.ILocation;

public final class DescriptionUtil {
	public static final char SPACE = ' ';
	public static final String DEFAULT_EXIT_START = "The obvious exits are ";
	private DescriptionUtil(){
		super(); //all static methods
	}
	
	public static <I extends ConcealableGameElement> String getConcealableNames(Map<String, I> exits, boolean excludeHidden){
		return getConcealableNames(exits.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> String getConcealableNames(Collection<I> exits, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		StringBuilder re = new StringBuilder();
		for (ConcealableGameElement e: exits){
			if (includeHidden || !e.isHidden()){
				re.append(e.getName()).append(", ");
			}
		}
		if (re.length() > 0){
			return re.substring(0, re.length() -2);
		} else {
			return "";
		}
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Map<String, I> exits, boolean excludeHidden){
		return getConcealableNamesList( exits.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Collection<I> exits, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		List<String> descrips = new LinkedList<String>();
		for (ConcealableGameElement e: exits){
			if (includeHidden || !e.isHidden()){
				descrips.add(e.getName());
			}
		}
		return descrips;
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Map<String,I> inventory, boolean excludeHidden){
		return getConceableDescriptions(inventory.values(), excludeHidden);
		
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Collection<I> inventory, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		StringBuilder re = new StringBuilder();
		for(ConcealableGameElement i: inventory){
			if (includeHidden || (!i.isHidden() && i.getRoomDescription() != null)){
				re.append(SPACE).append(i.getRoomDescription());
			}
		}
		return re.toString();
	}
	
	public static <I extends IGameElement> String getGameElementDescriptions(Map<String,I> elements){
		return getGameElementDescriptions(elements.values());
	}
	public static <I extends IGameElement> String  getGameElementDescriptions(Collection<I> elements){
		StringBuilder re = new StringBuilder();
		for(IGameElement m: elements){
			if (m.getRoomDescription() != null){
				re.append(SPACE).append(m.getRoomDescription());
			}
			
		}
		return re.toString();
	}
	public static String showRoom(final ILocation l, final String exitStart){
		StringBuilder re = new StringBuilder(l.getDescription());
		String curDescrip;
		if (!l.getInventory().isEmpty()&& !(curDescrip = l.getInventoryDescriptions()).isEmpty()){
			re.append(SPACE).append(curDescrip);
		}
		if (!l.getMobs().isEmpty()&& !(curDescrip = l.getMobDescriptions()).isEmpty()){
			re.append(SPACE).append(curDescrip);
		}
//		String exitDescrips;
		if (!l.getExits().isEmpty() && !(curDescrip = l.getExitsDescriptions()).isEmpty()){
			re.append(SPACE).append(exitStart).append(curDescrip);
		}
		return re.toString();
	}
	/**
	 * Calls {@link #showRoom(ILocation, String)} with {@link #DEFAULT_EXIT_START}
	 * @param l
	 * @return
	 */
	public static String showRoom(final ILocation l){
		return showRoom(l, DEFAULT_EXIT_START);
	}

}
