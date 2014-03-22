package jjcard.textGames.game.impl;

import jjcard.textGames.game.IGameElementMap;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;

/**
 * @author jjcard
 *
 */


public class Location implements ILocation {

	private String name;
	private String description;
	
	private IGameElementMap<IItem> inventory;
	private IGameElementMap<IMob> roomMob;
	private IGameElementMap<Exit> exits;
	
	public Location(){
		this.name = new String();
		description = new String();
		inventory = new GameElementMap<IItem>();
		roomMob = new GameElementMap<IMob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name){
		this.name = name;
		description = new String();
		inventory = new GameElementMap<IItem>();
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN){
		this.name = name;
		description = descripN;
		inventory = new GameElementMap<IItem>();
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN, IGameElementMap<IItem> invenN){
		this.name = name;
		description = descripN;
		inventory = invenN;
		roomMob =  new GameElementMap<IMob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN, IGameElementMap<IItem> invenN, IGameElementMap<IMob>  mobs){
		this.name = name;
		description = descripN;
		inventory = invenN;
		roomMob = mobs;
		exits = new GameElementMap<Exit>();
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
	public  IGameElementMap<IMob> getRoomMob() {
		return roomMob;
	}
	public IGameElementMap<Exit> getExits() {
		return exits;
	}
	public IItem addItem(IItem add){
		return inventory.put(add);
	}
	public void setInventory(IGameElementMap<IItem> inventoryNew){
		inventory = inventoryNew;
	}
	public void setRoomMob(IGameElementMap<IMob> roomMobNew){
		roomMob = roomMobNew;
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
	 * adds Exit with given String in Uppercase and location
	 * @param dir
	 * @param room
	 */
	public void addExit(String dir, ILocation room){
		Exit exit = new Exit.ExitBuilder().standardName(dir).location(room).build();
		exits.put(exit);
	}
	
	public void addExit(Exit exit, ILocation room){
		exit.setLocation(room);
		exits.put(exit);
	}

	
	/**
	 * removes Exit under that String
	 * @param dir
	 */
	public void removeExit(String dir){
		 exits.remove(dir); 
	}

	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	public ILocation getExitLocation(String dir){
		Exit exit = exits.get(dir);
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

	public void setName(String name){
		this.name = name;
	}

	public void setDescription(String descrip){
		description = descrip;
	}
	public int compareTo(ILocation other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0 && description != null){
			compare = description.compareTo(other.getDescription());
		}
		return getName().compareTo(other.getName());
	}
	public String getExitsDescriptions(){
		String re = exits.getAllStandardNamesAsString();
		return re.substring(1, re.length()-1);
	}
	public String getInventoryDescriptions(){
		
		StringBuilder re = new StringBuilder();
		for(IItem i: inventory.getElements()){
			if ((!i.isHidden()) && (i.getRoomDescription() != null)){
				re.append(" " + i.getRoomDescription());
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
			re.append(" " + getInventoryDescriptions());
		}
		if (!roomMob.isEmpty()){
			re.append(" " + getMobDescriptions());
		}
		if (!exits.isEmpty()){
			re.append(" The obvious exits are " + getExitsDescriptions());
		}
		return re.toString();
	}
}
