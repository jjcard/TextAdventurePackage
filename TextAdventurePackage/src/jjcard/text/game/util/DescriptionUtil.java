package jjcard.text.game.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jjcard.text.game.ConcealableGameElement;
import jjcard.text.game.IGameElement;
import jjcard.text.game.ILocation;

public final class DescriptionUtil {
	public static final char SPACE = ' ';
	private static final String EMPTY = "";
	public static final String DEFAULT_EXIT_START = "The obvious exits are ";
	private DescriptionUtil(){
		//all static methods
	}
	
	public static <I extends ConcealableGameElement> String getConcealableNames(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNames(elements == null? null: elements.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> String getConcealableNames(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(e -> e.getName()).collect(Collectors.joining(", "));
	}
	/**
	 * Returns stream that is empty of elements is null or empty, otherwise returns stream with elements filtered out depending on <code> excludeHidden</code>
	 * @param elements
	 * @param excludeHidden
	 * @return Stream
	 */
	public static <I extends ConcealableGameElement> Stream<I> getConcealableStream(Collection<I> elements, boolean excludeHidden){
		if (elements == null || elements.isEmpty()){
			return Stream.empty();
		}
		final boolean includeHidden = !excludeHidden;
		return elements.stream().filter((e) -> includeHidden || !e.isHidden());
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNamesList(elements == null? null: elements.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(e -> e.getName()).collect(Collectors.toList());
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Map<?,I> inventory, boolean excludeHidden){
		return getConceableDescriptions(inventory == null? null: inventory.values(), excludeHidden);
	}
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(e -> e.getRoomDescription()).collect(Collectors.joining(", "));
	}
	
	public static <I extends IGameElement> String getGameElementDescriptions(Map<?,I> elements){
		return getGameElementDescriptions(elements == null? null: elements.values());
	}
	public static <I extends IGameElement> String  getGameElementDescriptions(Collection<I> elements){
		if (elements == null){
			return EMPTY;
		}
		return elements.stream().map(e -> e.getRoomDescription()).collect(Collectors.joining(", "));
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
