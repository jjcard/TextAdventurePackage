package jjcard.textGames.game;

import java.util.Map;

/**
 * An ILocation contains a collection of IItems, IMobs, and IExits and methods to use them.
 *
 */
public interface ILocation extends Comparable<ILocation>{

	/**
	 * Gets the name of the Location. Should only be set once.
	 * @return
	 */
	public String getName();
	public String getDescription();
	public void setDescription(String description);
	public Map<String, IItem> getInventory();
	public Map<String, IMob> getMobs();
	public Map<String, IExit> getExits();
	public IItem addItem(IItem add);
	public void setInventory(Map<String, IItem> inventory);
	public void setMobs(Map<String, IMob> mobs);
	public IItem removeItem(String key);
	public boolean containsItem(String key);
	public IMob addMob(IMob mob);
	public IMob removeMob(String key);
	public boolean containsMob(String key);
	public IExit addExit(IExit exit);
	public IExit addExit(String dir, ILocation room);
	/**
	 * Removes the exit with the given direction and returns it.
	 * @param dir
	 * @return
	 */
	public IExit removeExit(String dir);
	/**
	 * returns Location corresponding to directions
	 * @param dir
	 * @return
	 */
	public ILocation getExitLocation(String dir);
	public IMob getMob(String key);
	public IItem getItem(String key);
	public boolean containsExit(String dir);
	/**
	 * 
	 * @return room description, description of items and mobs in room, and exits. 
	 */
	public String showRoom();
	/**
	 * Gets the description for the exits.
	 * @return
	 */
	public String getExitsDescriptions();
	public String getInventoryDescriptions();
	public String getMobDescriptions();
	
}
