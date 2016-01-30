package jjcard.text.game.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jjcard.text.game.ConcealableGameElement;
import jjcard.text.game.IGameElement;
import jjcard.text.game.ILocation;
/**
 * Utility class for working on GameElements and ConcealableGameElements to display the results.
 * All methods are null safe and will return an empty string if null passed in.
 *
 */
public final class DescriptionUtil {
	private static final String COMMA_DELIMINATOR = ", ";
	public static final char SPACE = ' ';
	private static final String EMPTY = "";
	public static final String DEFAULT_EXIT_START = "The obvious exits are ";
	private DescriptionUtil(){
		//all static methods
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
	/**
	 * Returns comma separated String of element names, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the comma separated String
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> String getConcealableNames(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNames(elements == null? null: elements.values(), excludeHidden);
	}
	/**
	 * Returns comma separated String of element names, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the comma separated String
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> String getConcealableNames(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(I::getName).collect(Collectors.joining(COMMA_DELIMINATOR));
	}
	/**
	 * Returns List of element names, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the List
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Map<?, I> elements, boolean excludeHidden){
		return getConcealableNamesList(elements == null? null: elements.values(), excludeHidden);
	}
	/**
	 * Returns List of element names, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the List
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> List<String> getConcealableNamesList(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(I::getName).collect(Collectors.toList());
	}
	/**
	 * Returns comma separated String of element room descriptions, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the comma separated String
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Map<?,I> elements, boolean excludeHidden){
		return getConceableDescriptions(elements == null? null: elements.values(), excludeHidden);
	}
	/**
	 * Returns comma separated String of element room descriptions, filtering out hidden elements if <code>excludeHidden</code> is true
	 * @param elements
	 * @param excludeHidden
	 * @return the comma separated String
	 * @see #getConcealableStream(Collection, boolean)
	 */
	public static <I extends ConcealableGameElement> String getConceableDescriptions(Collection<I> elements, boolean excludeHidden){
		return getConcealableStream(elements, excludeHidden).map(I::getRoomDescription).collect(Collectors.joining(COMMA_DELIMINATOR));
	}
	/**
	 * Return comma separated String of element room descriptions.
	 * @param elements
	 * @return comma separated String
	 */
	public static <I extends IGameElement> String getGameElementDescriptions(Map<?,I> elements){
		return getGameElementDescriptions(elements == null? null: elements.values());
	}
	/**
	 * Return comma separated String of element room descriptions.
	 * @param elements
	 * @return comma separated String
	 */
	public static <I extends IGameElement> String  getGameElementDescriptions(Collection<I> elements){
		if (elements == null){
			return EMPTY;
		}
		return elements.stream().map(I::getRoomDescription).collect(Collectors.joining(COMMA_DELIMINATOR));
	}
	/**
	 * Returns String containing the description of the <code>location</code> along with it's Inventory, Mob, and Exits Description if they are non-empty.
	 * The <code>exitStart<code> is placed before the Description for the exits, if any.
	 * @param l
	 * @param exitStart
	 * @return description of room and it's elements
	 */
	public static String showRoom(final ILocation location, final String exitStart){
		if (location == null){
			return EMPTY;
		}
		StringBuilder re = new StringBuilder(location.getDescription());
		String curDescrip;
		if (!location.getInventory().isEmpty()&& !(curDescrip = location.getInventoryDescriptions()).isEmpty()){
			re.append(SPACE).append(curDescrip);
		}
		if (!location.getMobs().isEmpty()&& !(curDescrip = location.getMobDescriptions()).isEmpty()){
			re.append(SPACE).append(curDescrip);
		}
		if (!location.getExits().isEmpty() && !(curDescrip = location.getExitsDescriptions()).isEmpty()){
			re.append(SPACE).append(exitStart).append(curDescrip);
		}
		return re.toString();
	}
	/**
	 * Returns String containing the description of the <code>location</code> along with it's Inventory, Mob, and Exits Description if they are non-empty.
	 * Calls {@link #showRoom(ILocation, String)} with {@link #DEFAULT_EXIT_START}
	 * @param l
	 * @return
	 * @see #showRoom(ILocation)
	 */
	public static String showRoom(final ILocation l){
		return showRoom(l, DEFAULT_EXIT_START);
	}
}
