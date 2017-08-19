package jjcard.text.game.util;

 import static jjcard.text.game.util.ObjectsUtil.checkArg;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import jjcard.text.game.IGameElement;
/**
 * Singleton to help work with Maps that deal with String keys and IGameElements.
 *
 */
public final class MapUtil {
	
	private Locale locale = Locale.getDefault();
	
	private static MapUtil instance;
	private boolean setUppercase = true;
	/**
	 * get instance of map util. Lazy loaded
	 * @return instance of MapUtil
	 */
	public static MapUtil getInstance(){
		if (instance == null){
			instance = new MapUtil();
		}
		return instance;
	}
	private MapUtil(){
		//singleton
	}
	/**
	 * Set if keys put into and retrieved from the map should be set to uppercase first.
	 * This is done so the String keys of the map can ignore casing. <code> true </code> by default.
	 * @param setUppercase
	 */
	public void setSetUppercase(boolean setUppercase){
		this.setUppercase = setUppercase;
	}
	/**
	 * If the map is null, returns new HashMap, otherwise returns the map
	 * @param map
	 * @return the given map or a new instance if map was null
	 */
	public static <J, K> Map<J, K> getMapOrNew(Map<J, K> map){
		return (map == null)? new HashMap<>() : map;
	}
	/**
	 * If the map is null, returns new constructor.get(), otherwise returns the map
	 * @param map
	 * @param constructor
	 * @return the given map or a new instance if map was null
	 */
	public static <J, K> Map<J, K> getMapOrNew(Map<J, K> map, Supplier<Map<J, K>> constructor){
		return (map == null)? constructor.get() : map; 
	}
	/**
	 * Returns new HashMap
	 * @return
	 */
	public static <J, K> Map<J, K> newHashMap(){
		return new HashMap<>();
	}
	/**
	 * Sets the Locale that is used to change the case of the String keys.
	 * @param locale
	 * @throws IllegalArgumentException if locale is null
	 */
	public void setLocale(Locale locale) throws IllegalArgumentException {
		checkArg(locale, "locale");
		this.locale = locale;
	}
	/**
	 * 
	 * @param map
	 * @param item
	 * @return previous item associated with the key
	 */
	public <K extends IGameElement> K addItemToMap(Map<String, K> map, K item){
		return map.put(getUppercase(item.getName()), item);
	}
	public boolean containsKey(Map<String, ?> map, String key){
		return map.containsKey(getUppercase(key));
	}
	public <K extends IGameElement> K getItemFromMap(Map<String, K> map, String key){
		return map.get(getUppercase(key));
	}
	/**
	 * Removes and returns item with given key
	 * @param map
	 * @param key
	 * @return item associated with key
	 */
	public <K extends IGameElement> K removeItemFromMap(Map<String, K> map, String key){
		return map.remove(getUppercase(key));
	}
	/**
	 * Null-safe case ignoring check for key being for given item
	 * @param key
	 * @param item
	 * @return
	 */
	public static boolean isKeyForItem(String key, IGameElement item){
		if (item == null){
			return false;
		}
		return key.equalsIgnoreCase(item.getName());
	}

	private String getUppercase(String s) {
		return setUppercase ? s.toUpperCase(locale) : s;

	}
	public static boolean isEmpty(Map<?, ?> map){
		return map == null || map.isEmpty();
	}
	public static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
	public static String getKeysAsString(Map<String, ?> map){
		if (isEmpty(map)){
			return "";
		}
		String keys = map.keySet().toString();
		return keys.substring(1, keys.length() -1);
	}
	
	public static String getKeysAsString(Map<String, ?> map, final String delimiter) {
		if (isNotEmpty(map)) {
			return map.keySet().stream().collect(Collectors.joining(delimiter));
		} else {
			return "";
		}
	}

}
