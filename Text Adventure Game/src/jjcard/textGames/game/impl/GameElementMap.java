package jjcard.textGames.game.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.IGameElementMap;
import jjcard.textGames.game.util.EqualsUtil;

public class GameElementMap<A extends IGameElement> implements IGameElementMap<A>{
	
	private Locale locale = Locale.getDefault();
	private HashMap<String, String> altNamesMap;
	private HashMap<String, A> elementMap;
	
	
   public GameElementMap(){
	   super();
	   
	   altNamesMap = new HashMap<String, String>();
	   elementMap = new HashMap<String, A>();
   }
   public GameElementMap(Locale locale){
	   super();
	   
	   altNamesMap = new HashMap<String, String>();
	   elementMap = new HashMap<String, A>();
	   this.locale = locale;
   }
   public GameElementMap(int elementMapCapacity, int altNameMapCapacity){
	   super();
	   altNamesMap = new HashMap<String, String>(altNameMapCapacity);
	   elementMap = new HashMap<String, A>(elementMapCapacity);
   }
   
   public GameElementMap(GameElementMap<A> map){
	   super();
	   altNamesMap = new HashMap<>(map.altNamesMap);
	   elementMap = new HashMap<>(map.elementMap);
	   locale = map.locale;
   }
   private A put(String standerdName, String[] altNames, A gameElement){
	   String standardNameUpper = standerdName.toUpperCase(locale);
	   if (altNames != null){
		   for (String altName: altNames){
			   altNamesMap.put(altName.toUpperCase(locale), standardNameUpper);
		   }		   
	   }
	   altNamesMap.put(standardNameUpper, standardNameUpper);
	   return elementMap.put(standardNameUpper, gameElement);

   }

   /**
    * Sets the Locale to use when changing the case on Strings
    * @param locale
    */
   public void setLocale(Locale locale){
	   this.locale = locale;
   }
	public A put(A gameElement){
		return put(gameElement.getStandardName(), 
				gameElement.getAltNames(), gameElement);
	}
	
	public A get(String name){
		String realKey = altNamesMap.get(name.toUpperCase(locale));
		
		if (realKey != null){
			return elementMap.get(realKey);
		} else {
			return null;
		}
	}
	
	public A getFromStandardName(String standerdName){
		return elementMap.get(standerdName.toUpperCase(locale));
	}
	public boolean containsName(String name){
		return altNamesMap.containsKey(name.toUpperCase(locale));
	}
	public boolean containsStandardName(String standerdName){
		return elementMap.containsKey(standerdName.toUpperCase(locale));
	}
	/**
	 * returns the number of standard and alternate names.
	 * @return
	 */
	public int getNameCount(){
		return altNamesMap.size();
	}
	/**
	 * returns the number of GameElements
	 * @return
	 */
	public int getElementCount(){
		return elementMap.size();
	}
	
	public A remove(String name){
		String realKey = altNamesMap.get(name.toUpperCase(locale));
		A element = elementMap.remove(realKey);
		
		
		//now remove from the altname table
		if (element.getAltNames() != null){
			for (String altName: element.getAltNames()){
				altNamesMap.remove(altName.toUpperCase(locale));
			}
		}
		altNamesMap.remove(element.getStandardName().toUpperCase(locale));
		return element;
	}
	
	/**
	 * gets the all the standard names from the map. All are uppercase.
	 * to get list with original case, call {@link getAllStandardNamesSaveCase()}
	 * @return
	 */
	public String[] getAllStandardNames(){
		return elementMap.keySet().toArray(new String[getElementCount()]);
	}
	
	public String getAllStandardNamesAsString(){
		return elementMap.keySet().toString();
	}
	public String[] getAllStandardNamesSaveCase(){
		String[] names = new String[getElementCount()];
		
		int i = 0;
		for (A element: elementMap.values()){
			names[i++] = element.getStandardName();
		}
		
		return names;
	}

	public Collection<A> getElements(){
		return elementMap.values();
	}
	
	public boolean isEmpty(){
		return elementMap.isEmpty();
	}
	
	public void putAll(GameElementMap<A> map){
		altNamesMap.putAll(map.altNamesMap);
		
		elementMap.putAll(map.elementMap);
	}

	@Override
	public void putAll(IGameElementMap<A> map) {
		for (A gameElement: map.getElements()){
			this.put(gameElement);
		}
		
	}
	
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o instanceof GameElementMap){
			@SuppressWarnings("rawtypes")
			GameElementMap m = (GameElementMap) o;
			if (EqualsUtil.notEqual(elementMap, m.elementMap)){
				return false;
			}
			if (EqualsUtil.notEqual(altNamesMap, m.altNamesMap)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	
	
	

}
