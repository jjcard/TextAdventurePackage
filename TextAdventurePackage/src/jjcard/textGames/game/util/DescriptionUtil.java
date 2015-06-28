package jjcard.textGames.game.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jjcard.textGames.game.ConcealableGameElement;
import jjcard.textGames.game.IGameElement;

public final class DescriptionUtil {
	private static final char SPACE = ' ';
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
	
	public static String getGameElementDescriptions(Map<String,IGameElement> elements){
		return getGameElementDescriptions(elements.values());
	}
	public static String getGameElementDescriptions(Collection<IGameElement> elements){
		StringBuilder re = new StringBuilder();
		for(IGameElement m: elements){
			if (m.getRoomDescription() != null){
				re.append(SPACE).append(m.getRoomDescription());
			}
			
		}
		return re.toString();
	}

}
