package jjcard.text.game.util;

 import static jjcard.text.game.util.ObjectsUtil.checkArg;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
	 * get instance
	 * @return get instance of MapUtil
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
	public <J, K> Map<J, K> getMapOrNew(Map<J, K> map){
		return (map == null)? new HashMap<J, K>() : map; 
	}
	/**
	 * Sets the Locale that is used to change the case of the String keys.
	 * @param locale
	 */
	public void setLocale(Locale locale){
		checkArg(locale, "locale");
		this.locale = locale;
	}
	public <K extends IGameElement> K addItemToMap(Map<String, K> map, K item){
		return map.put(getUppercase(item.getName()), item);
	}
	public boolean containsKey(Map<String, ?> map, String key){
		return map.containsKey(getUppercase(key));
	}
	public <K extends IGameElement> K getItemFromMap(Map<String, K> map, String key){
		return map.get(getUppercase(key));
	}
	public <K extends IGameElement> K removeItemFromMap(Map<String, K> map, String key){
		return map.remove(getUppercase(key));
	}
	
	public boolean isKeyForItem(String key, IGameElement item){

		if (item == null){
			return false;
		}
		if (key.equalsIgnoreCase(item.getName())){
			return true;
		}
		return false;
	}
	private String getUppercase(String s){
		if (setUppercase){
			return s.toUpperCase(locale);
		}
		return s;
		
	}
	public String getKeysAsString(Map<String, ?> map){
		String keys = map.keySet().toString();
		return keys.toString().substring(1, keys.length() -1);
	}
	
	public String getKeysAsString(Map<String, ?> map, final String delimiter) {
		if (map != null && !map.isEmpty()) {
			return map.keySet().stream().collect(Collectors.joining(delimiter));
		} else {
			return "";
		}
	}

}
