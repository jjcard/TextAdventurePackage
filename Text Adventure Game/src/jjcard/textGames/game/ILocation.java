package jjcard.textGames.game;


public interface ILocation extends Comparable<ILocation>{

	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String descrip);
	public IGameElementMap<IItem> getInventory();
	public IGameElementMap<IMob> getRoomMob();
	public IGameElementMap<IExit> getExits();
	public IItem addItem(IItem add);
	public void setInventory(IGameElementMap<IItem> inventoryNew);
	public void setRoomMob(IGameElementMap<IMob> roomMobNew);
	public IItem removeItem(String key);
	public boolean containsItem(String keyR);
	public IMob addMob(IMob m);
	public IMob removeMob(String key);
	public boolean containsMob(String m);
	public void addExit(IExit exit);
	public void addExit(String dir, ILocation room);
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
	public String getExitsDescriptions();
	public String getInventoryDescriptions();
	public String getMobDescriptions();
	
}
