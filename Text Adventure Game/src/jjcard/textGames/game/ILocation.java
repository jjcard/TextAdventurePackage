package jjcard.textGames.game;

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
	public IGameElementMap<IItem> getInventory();
	public IGameElementMap<IMob> getMobs();
	public IGameElementMap<IExit> getExits();
	public IItem addItem(IItem add);
	public void setInventory(IGameElementMap<IItem> inventory);
	public void setMobs(IGameElementMap<IMob> mobs);
	public IItem removeItem(String key);
	public boolean containsItem(String key);
	public IMob addMob(IMob m);
	public IMob removeMob(String key);
	public boolean containsMob(String m);
	public void addExit(IExit exit);
	public void addExit(String dir, ILocation room);
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
