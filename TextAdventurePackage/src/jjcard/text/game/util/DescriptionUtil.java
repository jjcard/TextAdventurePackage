package jjcard.text.game.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jjcard.text.game.ConcealableGameElement;
import jjcard.text.game.IGameElement;
import jjcard.text.game.ILocation;

public final class DescriptionUtil {
	public static final char SPACE = ' ';
	public static final String DEFAULT_EXIT_START = "The obvious exits are ";
	private DescriptionUtil(){
		super(); //all static methods
	}
	
	public static <I extends ConcealableGameElement> String getConcealableNames(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNames(elements == null? null: elements.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> String getConcealableNames(Collection<I> elements, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		StringBuilder re = new StringBuilder();
		if (elements != null && !elements.isEmpty()){
			for (ConcealableGameElement e: elements){
				if (includeHidden || !e.isHidden()){
					re.append(e.getName()).append(", ");
				}
			}			
		}
		if (re.length() > 0){
			return re.substring(0, re.length() -2);
		} else {
			return "";
		}
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNamesList(elements == null? null: elements.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Collection<I> elements, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		List<String> descrips = new LinkedList<String>();
		if (elements != null){
			for (ConcealableGameElement e: elements){
				if (includeHidden || !e.isHidden()){
					descrips.add(e.getName());
				}
			}			
		}
		return descrips;
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Map<?,I> inventory, boolean excludeHidden){
		return getConceableDescriptions(inventory == null? null: inventory.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Collection<I> inventory, boolean excludeHidden){
		boolean includeHidden = !excludeHidden;
		StringBuilder re = new StringBuilder();
		for(ConcealableGameElement i: inventory){
			if (includeHidden || !i.isHidden() ){
				re.append(SPACE).append(i.getRoomDescription());
			}
		}
		return re.toString();
	}
	
	public static <I extends IGameElement> String getGameElementDescriptions(Map<?,I> elements){
		return getGameElementDescriptions(elements == null? null: elements.values());
	}
	public static <I extends IGameElement> String  getGameElementDescriptions(Collection<I> elements){
		StringBuilder re = new StringBuilder();
		if (elements != null) {
			boolean first = true;
			for (IGameElement m : elements) {
				if (m.getRoomDescription() != null) {
					if (first){
						re.append(m.getRoomDescription());
						first = false;
					} else {
						re.append(", ").append(m.getRoomDescription());
					}
				}

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
