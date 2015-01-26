package jjcard.textGames.game.util;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jjcard.textGames.game.IGameElement;
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
	 * Sets the Locale that is used to change the case of the String keys.
	 * @param locale
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}
	public <K extends IGameElement> K addItemToMap(Map<String, K> map, K item){
		K previous = map.put(getUppercase(item.getStandardName()), item);
		return previous;
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
		if (key.equalsIgnoreCase(item.getStandardName())){
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
	
	public String getKeysAsString(Map<String, ?> map, final String delimiter){
		Set<String> keySet = map.keySet();
		if (!keySet.isEmpty()){
			
			Iterator<String> keyIter = keySet.iterator();
			String key = keyIter.next();
			StringBuilder keysB = new StringBuilder(key);
			while (keyIter.hasNext()){
				keysB.append(delimiter).append(keyIter.next());				
			}
			
			return keysB.toString();			
		} else {
			return "";
		}

		
	}

}
