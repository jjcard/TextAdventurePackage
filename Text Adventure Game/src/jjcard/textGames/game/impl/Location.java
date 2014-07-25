package jjcard.textGames.game.impl;

import static jjcard.textGames.game.util.EqualsUtil.notEqual;
import jjcard.textGames.game.IExit;
import jjcard.textGames.game.IGameElementMap;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;

/**
 * @author jjcard
 *
 */


public class Location implements ILocation {

	private static final char SPACE = ' ';
	private final String name;
	private String description;
	
	private IGameElementMap<IItem> inventory;
	private IGameElementMap<IMob> roomMob;
	private IGameElementMap<IExit> exits;
	
	public Location(){
		this.name = new String();
		description = new String();
		inventory = new GameElementMap<IItem>();
		roomMob = new GameElementMap<IMob>();
		exits = new GameElementMap<IExit>();
	}
	public Location(String name){
		this.name = name;
		description = "";
		inventory = new GameElementMap<IItem>();
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<IExit>();
	}
	public Location(String name, String descripN){
		this.name = name;
		description = descripN;
		inventory = new GameElementMap<IItem>();
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<IExit>();
	}
	public Location(String name, String descripN, IGameElementMap<IItem> invenN){
		this.name = name;
		description = descripN;
		setInventory(invenN);
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<IExit>();
	}
	public Location(String name, String descripN, IGameElementMap<IItem> invenN, IGameElementMap<IMob>  mobs){
		this.name = name;
		description = descripN;
		setInventory(invenN);
		setMobs(mobs);
		exits = new GameElementMap<IExit>();
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public IGameElementMap<IItem> getInventory(){
		return inventory;
	}
	public  IGameElementMap<IMob> getMobs() {
		return roomMob;
	}
	public IGameElementMap<IExit> getExits() {
		return exits;
	}
	public IItem addItem(IItem add){
		return inventory.put(add);
	}
	public void setInventory(IGameElementMap<IItem> inventoryNew){
		if (inventoryNew == null){
			inventory = new GameElementMap<IItem>();
		} else {
			inventory = inventoryNew;
		}
	}
	public void setMobs(IGameElementMap<IMob> roomMobNew){
		if (roomMobNew == null){
			this.roomMob = new GameElementMap<IMob>();
		} else {
			this.roomMob = roomMobNew;
		}
	}
	public IItem removeItem(String key){
		return inventory.remove(key);
	}
	public boolean containsItem(String keyR){
		return inventory.containsName(keyR);
	}
	public IMob addMob(IMob m){
		return roomMob.put(m);
		
	}
	public IMob removeMob(String key){
		return roomMob.remove(key);
	}
	public boolean containsMob(String m){
		return roomMob.containsName(m);

	}
	/**
	 * adds Exit with given String and location
	 * @param dir
	 * @param room
	 */
	public void addExit(String dir, ILocation room){
		Exit exit = new Exit.ExitBuilder().standardName(dir).location(room).build();
		exits.put(exit);
	}
	
	public void addExit(IExit exit){
		exits.put(exit);
	}

	
	/**
	 * removes Exit under that String
	 * @param dir
	 * @return 
	 */
	public IExit removeExit(String dir){
		 return exits.remove(dir); 
	}

	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	public ILocation getExitLocation(String dir){
		IExit exit = exits.get(dir);
		if (exit != null){
			return exit.getLocation();
		} else {
			return null;
		}
	}
	public IMob getMob(String key){
		return roomMob.get(key);
	}
	public IItem getItem(String key){
		return inventory.get(key);
	}
	public boolean containsExit(String dir){
		return exits.containsName(dir);
	}


	public void setDescription(String descrip){
		description = descrip;
	}
	public int compareTo(ILocation other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0 && description != null){
			compare = description.compareTo(other.getDescription());
		}
		return compare;
	}
	public String getExitsDescriptions(){
		return exits.getAllStandardNamesAsString();
	}
	public String getInventoryDescriptions(){
		
		StringBuilder re = new StringBuilder();
		for(IItem i: inventory.getElements()){
			if ((!i.isHidden()) && (i.getRoomDescription() != null)){
				re.append(SPACE).append(i.getRoomDescription());
			}
		}
		return re.toString();
	}
	public String getMobDescriptions(){
		StringBuilder re = new StringBuilder();
		for(IMob m: roomMob.getElements()){
			if (m.getRoomDescription() != null){
				re.append(m.getRoomDescription());
			}
			
		}
		return re.toString();
	}
	/**
	 * 
	 * @return room description, description of items and mobs in room, and exits. 
	 */
	public String showRoom(){
		
		StringBuilder re = new StringBuilder(description);
		
		if (!inventory.isEmpty()){
			re.append(SPACE).append(getInventoryDescriptions());
		}
		if (!roomMob.isEmpty()){
			re.append(SPACE).append(getMobDescriptions());
		}
		if (!exits.isEmpty()){
			re.append(" The obvious exits are " + getExitsDescriptions());
		}
		return re.toString();
	}
	
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		
		if (o instanceof Location){
			Location l = (Location) o;
			if (notEqual(name, l.name)){
				return false;
			}
			if (notEqual(inventory, l.inventory)){
				return false;
			}
			if (notEqual(roomMob, l.roomMob)){
				return false;
			}
			if (notEqual(exits, l.exits)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
