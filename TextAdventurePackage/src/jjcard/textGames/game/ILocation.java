package jjcard.textGames.game;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * An ILocation contains a collection of IItems, IMobs, and IExits and methods to use them.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
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
	public void setExits(Map<String, IExit> exits);
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
	 * Gets the description for the exits. Must be non-null
	 * @return
	 */
	@JsonIgnore
	public String getExitsDescriptions();
	/**
	 * Gets the description for each item in the inventory. Must be non-null
	 * @return
	 */
	@JsonIgnore
	public String getInventoryDescriptions();
	/**
	 * Gets the description for each mob. Must be non-null
	 * @return
	 */
	@JsonIgnore
	public String getMobDescriptions();
	
}
