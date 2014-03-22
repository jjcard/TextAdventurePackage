package jjcard.textGames.game;

import jjcard.textGames.game.impl.Exit;
import jjcard.textGames.game.impl.GameElementMap;
import jjcard.textGames.game.impl.Item;
import jjcard.textGames.game.impl.Mob;

public interface ILocation extends  Comparable<ILocation>{

	public String getName();
	public void setName(String name);
	public String getDescription();
	public void setDescription(String descrip);
	public GameElementMap<Item> getInventory();
	public  GameElementMap<Mob> getRoomMob();
	public GameElementMap<Exit> getExits();
	public Item addItem(Item add);
	public void setInventory(GameElementMap<Item> inventoryNew);
	public void setRoomMob(GameElementMap<Mob> roomMobNew);
	public Item removeItem(String key);
	public boolean containsItem(String keyR);
	public Mob addMob(Mob m);
	public Mob removeMob(String key);
	public boolean containsMob(String m);
	public void addExit(String dir, ILocation room);
	public void addExit(Exit exit, ILocation room);
	public void removeExit(String dir);
	/**
	 * returns Location corresponding to directions
	 * @param dir
	 * @return
	 */
	public ILocation getExitLocation(String dir);
	public Mob getMob(String key);
	public Item getItem(String key);
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
