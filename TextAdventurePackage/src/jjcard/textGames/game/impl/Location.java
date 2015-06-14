package jjcard.textGames.game.impl;

import static jjcard.textGames.game.util.ObjectsUtil.notEqual;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.textGames.game.IExit;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.util.MapUtil;
import jjcard.textGames.game.util.ObjectsUtil;

/**
 * @author jjcard
 *
 */


public class Location implements ILocation {
	@JsonIgnore
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
	private static final char SPACE = ' ';
	@JsonProperty("name")
	private final String name;
	@JsonProperty("descrip")
	private String description;
	@JsonProperty("inventory")
	private Map<String, IItem >inventory;
	@JsonProperty("mobs")
	private Map<String, IMob> roomMob;
	@JsonProperty("exits")
	private Map<String, IExit> exits;
	
	public Location(){
		this.name = "";
		description = "";
		inventory = new HashMap<String, IItem>();
		roomMob = new HashMap<String, IMob>();
		exits = new HashMap<String, IExit>();
	}
	public Location(@JsonProperty("name") String name){
		this.name = name;
		description = "";
		inventory = new HashMap<String, IItem>();
		roomMob =  new HashMap<String, IMob>();
		exits = new HashMap<String, IExit>();
	}
	public Location(String name, String description){
		this.name = name;
		this.description = description;
		inventory = new HashMap<String, IItem>();
		roomMob =  new HashMap<String, IMob>();
		exits = new HashMap<String, IExit>();
	}
	public Location(String name, String description, Map<String, IItem> invenN){
		this.name = name;
		this.description = description;
		setInventory(invenN);
		roomMob =  new HashMap<String, IMob>();
		exits = new HashMap<String, IExit>();
	}
	public Location(String name, String descripN, Map<String, IItem> invenN, Map<String, IMob>  mobs){
		this.name = name;
		description = descripN;
		setInventory(invenN);
		setMobs(mobs);
		exits = new HashMap<String, IExit>();
	}
	@JsonProperty("name")
	public String getName(){
		return name;
	}
	@JsonProperty("descrip")
	public String getDescription(){
		return description;
	}
	@JsonProperty("inventory")
	public Map<String, IItem> getInventory(){
		return inventory;
	}
	@JsonProperty("mobs")
	public  Map<String, IMob> getMobs() {
		return roomMob;
	}
	@JsonProperty("exits")
	public Map<String, IExit> getExits() {
		return exits;
	}
	public IItem addItem(IItem add){
		return MAP_UTIL.addItemToMap(inventory, add);
	}
	@JsonProperty("inventory")
	public void setInventory(Map<String, IItem> inventoryNew){
		inventory = MAP_UTIL.getMapOrNew(inventoryNew);
	}
	@JsonProperty("mobs")
	public void setMobs(Map<String, IMob> roomMobNew){
		this.roomMob = MAP_UTIL.getMapOrNew(roomMobNew);
	}
	@JsonProperty("exits")
	public void setExits(Map<String, IExit> exits){
		this.exits = MAP_UTIL.getMapOrNew(exits);
	}
	public IItem removeItem(String key){
		return MAP_UTIL.removeItemFromMap(inventory, key);
	}
	public boolean containsItem(String keyR){
		return MAP_UTIL.containsKey(inventory,keyR);
	}
	public IMob addMob(IMob m){
		return MAP_UTIL.addItemToMap(roomMob, m);
		
	}
	public IMob removeMob(String key){
		return MAP_UTIL.removeItemFromMap(roomMob, key);
	}
	public boolean containsMob(String m){
		return MAP_UTIL.containsKey(roomMob, m);

	}
	/**
	 * adds Exit with given String and location
	 * @param dir
	 * @param room
	 */
	public IExit addExit(String dir, ILocation room){
		Exit exit = new Exit.Builder().standardName(dir).location(room).build();
		return MAP_UTIL.addItemToMap(exits, exit);
	}
	
	public IExit addExit(IExit exit){
		return MAP_UTIL.addItemToMap(exits, exit);
	}
	/**
	 * removes Exit under that String
	 * @param dir
	 * @return 
	 */
	public IExit removeExit(String dir){
		return MAP_UTIL.removeItemFromMap(exits, dir);
	}

	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	@JsonIgnore
	public ILocation getExitLocation(String dir){
		IExit exit = getExit(dir);
		if (exit != null){
			return exit.getLocation();
		} else {
			return null;
		}
	}
	@JsonIgnore
	public IExit getExit(String dir){
		return MAP_UTIL.getItemFromMap(exits, dir);
	}
	@JsonIgnore
	public IMob getMob(String key){
		return MAP_UTIL.getItemFromMap(roomMob,key);
	}
	@JsonIgnore
	public IItem getItem(String key){
		return MAP_UTIL.getItemFromMap(inventory, key);
	}
	public boolean containsExit(String dir){
		return MAP_UTIL.containsKey(exits, dir);
	}
	@JsonProperty("descrip")
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
	@JsonIgnore
	public String getExitsDescriptions(){
		StringBuilder re = new StringBuilder();
		for (IExit e: exits.values()){
			if (!e.isHidden()){
				re.append(e.getName()).append(", ");
			}
		}
		if (re.length() > 0){
			return re.substring(0, re.length() -2);
		} else {
			return "";
		}
	}
	@JsonIgnore
	public String getInventoryDescriptions(){
		
		StringBuilder re = new StringBuilder();
		for(IItem i: inventory.values()){
			if (!i.isHidden() && i.getRoomDescription() != null){
				re.append(SPACE).append(i.getRoomDescription());
			}
		}
		return re.toString();
	}
	@JsonIgnore
	public String getMobDescriptions(){
		StringBuilder re = new StringBuilder();
		for(IMob m: roomMob.values()){
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
			if (notEqual(description, l.description)){
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
	public int hashCode(){
		return ObjectsUtil.getHash(ObjectsUtil.DEFAULT_PRIME, name, description, inventory, roomMob, exits);
	}
}
