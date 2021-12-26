package jjcard.text.game;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jjcard.text.game.impl.Exit;
import jjcard.text.game.util.DescriptionUtil;

/**
 * An ILocation contains a collection of IItems, IMobs, and IExits and methods to use them.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ILocation extends Comparable<ILocation>{

	/**
	 * Gets the name of the Location. Should only be set once.
	 * @return name
	 */
    String getName();
	String getDescription();
	Map<String, IItem> getInventory();
	Map<String, IMob> getMobs();
	Map<String, IExit> getExits();

	
	void setInventory(Map<String, IItem> inventory);
	void setMobs(Map<String, IMob> mobs);
	void setExits(Map<String, IExit> exits);
	/**
	 * Adds <i>item</i> to Items map.
	 * @param item
	 * @return previous IItem associated with it's key
	 */
    IItem addItem(IItem item);
	IItem removeItem(String key);
	boolean containsItem(String key);
	IItem getItem(String key);
	/**
	 * Adds <i>mob</i> to Mobs map.
	 * @param mob
	 * @return previous IMob associated with it's key
	 */
    IMob addMob(IMob mob);
	IMob removeMob(String key);
	boolean containsMob(String key);
	IMob getMob(String key);
	/**
	 * Adds exit to Exits map
	 * @param exit
	 * @return previous exit associated with dir key of exit
	 */
    IExit addExit(IExit exit);
	/**
	 * Removes the exit with the given direction and returns it.
	 * @param dir
	 * @return removed IExit
	 */
    IExit removeExit(String dir);
	boolean containsExit(String dir);
	IExit getExit(String dir);
	/**
	 * Creates IExit for given room and direction and adds it to the Exits map.
	 * <br> Default: Creates a {@link Exit}
	 * @param dir
	 * @param room
	 * @return previous exit associated with dir
	 */
	default IExit addExit(final String dir, ILocation room){
		final Exit exit = new Exit.Builder().name(dir).location(room).build();
		return addExit(exit);
	}

	/**
	 * returns Location corresponding to directions
	 * <br> Default: Calls {@link #getExit(String)} and calls {@link Exit#getLocation()} on it
	 * @param dir
	 * @return ILocation
	 */
	default ILocation getExitLocation(String dir){
		final IExit exit = getExit(dir);
		return exit == null? null: exit.getLocation();
	}
	
	/**
	 * 
	 * @return room description, description of items and mobs in room, and exits. 
	 * <br> Default: Calls 
	 * <blockquote>
	 * {@link DescriptionUtil}.{@link DescriptionUtil#showRoom(ILocation) showRoom(<i>this</i>)}
	 * </blockquote>
	 */
	default String showRoom(){
		return DescriptionUtil.showRoom(this);
	}
	/**
	 * Gets the description for the exits. Must be non-null
	 * <br> Default: Calls 
	 * <blockquote>
	 * {@link DescriptionUtil}.{@link DescriptionUtil#getConcealableNames(Map, boolean) getConcealableNames(getExits(), true)}
	 * </blockquote>
	 * @return
	 */
	@JsonIgnore
	default String getExitsDescriptions(){
		return DescriptionUtil.getConcealableNames(getExits(), true);
	}
	/**
	 * Gets the description for each item in the inventory. Must be non-null
	 * <br> Default: Calls 
	 * <blockquote>
	 * {@link DescriptionUtil}.{@link DescriptionUtil#getConcealableNames(Map, boolean) getConcealableNames(getInventory(), true)}
	 * </blockquote>
	 * @return
	 */
	@JsonIgnore
	default String getInventoryDescriptions(){
		return DescriptionUtil.getConceableRoomDescriptions(getInventory(), true);
	}
	/**
	 * Gets the description for each mob. Must be non-null
	 * <br> Default: Calls 
	 * <blockquote>
	 * {@link DescriptionUtil}.{@link DescriptionUtil#getConcealableNames(Map, boolean) getConcealableNames(getMobs(), true)}
	 * </blockquote>
	 * @return
	 */
	@JsonIgnore
	default String getMobDescriptions(){
		return DescriptionUtil.getConceableRoomDescriptions(getMobs(), true);
	}
	
}
