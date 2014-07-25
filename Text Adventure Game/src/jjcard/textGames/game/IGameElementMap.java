package jjcard.textGames.game;

import java.util.Collection;

public interface IGameElementMap<A extends IGameElement> {
	
	
	/**
	 * Puts the given Game Element in the game, with the game elements 
	 * standard Name and Alternate Names.
	 * @param gameElement
	 * @return previous element with the standard name of the gameElement
	 */
	public A put(A gameElement);
	/**
	 * Returns the GameElement A for the given Name, either standard or alternate.
	 * Returns Null if no element found. 
	 * @param name
	 * @return
	 */
	public A get(String name);
	
	/**
	 * Returns the GameElement A that has the given Standard Name. Returns Null if no element found.
	 * @param standerdName
	 * @return
	 */
	public A getFromStandardName(String standardName);
	/**
	 * Returns true if the map contains a GameElement associated with the given name
	 * @param name
	 * @return
	 */
	public boolean containsName(String name);
	
	public boolean containsStandardName(String standerdName);
	
	public int getNameCount();
	
	public int getElementCount();
	
	public A remove(String name);
	
	public String[] getAllStandardNames();
	
	/**
	 * Gets all the standard names in a way that can be shown to the player. Standard way would be a comma delaminated list.
	 * @return
	 */
	public String getAllStandardNamesAsString();
	
	public String[] getAllStandardNamesSaveCase();
	/**
	 * returns all the elements in the map.
	 * do not modify elements this way, as results of modification can be undefined.
	 * @return
	 */
	public Collection<A> getElements();
	
	public boolean isEmpty();
	
	public void putAll(IGameElementMap<A> map);

}
