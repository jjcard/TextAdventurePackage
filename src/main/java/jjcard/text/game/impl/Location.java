package jjcard.text.game.impl;

import static jjcard.text.game.util.MapUtil.newHashMap;
import static jjcard.text.game.util.ObjectsUtil.notEqual;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.text.game.IExit;
import jjcard.text.game.IItem;
import jjcard.text.game.ILocation;
import jjcard.text.game.IMob;
import jjcard.text.game.util.DescriptionUtil;
import jjcard.text.game.util.MapUtil;
import jjcard.text.game.util.ObjectsUtil;

/**
 * Basic class to implement {@link ILocation}
 *
 */
public class Location implements ILocation {
	@JsonIgnore
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
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
	
	public Location(@JsonProperty("name") String name){
		this(name, "");
	}
	public Location(String name, String description){
		this.name = name;
		this.description = description;
		inventory = newHashMap();
		roomMob =  newHashMap();
		exits = newHashMap();
	}
	public Location(String name, String description, Map<String, IItem> inventory){
		this.name = name;
		this.description = description;
		setInventory(inventory);
		roomMob =  newHashMap();
		exits = newHashMap();
	}
	public Location(String name, String description, Map<String, IItem> inventory, Map<String, IMob>  mobs){
		this.name = name;
		this.description = description;
		setInventory(inventory);
		setMobs(mobs);
		exits = newHashMap();
	}
	@Override
    @JsonProperty("name")
	public String getName(){
		return name;
	}
	@Override
    @JsonProperty("descrip")
	public String getDescription(){
		return description;
	}
	@Override
    @JsonProperty("inventory")
	public Map<String, IItem> getInventory(){
		return inventory;
	}
	@Override
    @JsonProperty("mobs")
	public  Map<String, IMob> getMobs() {
		return roomMob;
	}
	@Override
    @JsonProperty("exits")
	public Map<String, IExit> getExits() {
		return exits;
	}
	@Override
    public IItem addItem(IItem add){
		return MAP_UTIL.addItemToMap(inventory, add);
	}
	@Override
    @JsonProperty("inventory")
	public void setInventory(Map<String, IItem> inventory){
		this.inventory = MapUtil.getMapOrNew(inventory);
	}
	@Override
    @JsonProperty("mobs")
	public void setMobs(Map<String, IMob> mobs){
		this.roomMob = MapUtil.getMapOrNew(mobs);
	}
	@Override
    @JsonProperty("exits")
	public void setExits(Map<String, IExit> exits){
		this.exits = MapUtil.getMapOrNew(exits);
	}
	@Override
    public IItem removeItem(String key){
		return MAP_UTIL.removeItemFromMap(inventory, key);
	}
	@Override
    public boolean containsItem(String keyR){
		return MAP_UTIL.containsKey(inventory,keyR);
	}
	@Override
    public IMob addMob(IMob m){
		return MAP_UTIL.addItemToMap(roomMob, m);
	}
	@Override
    public IMob removeMob(String key){
		return MAP_UTIL.removeItemFromMap(roomMob, key);
	}
	@Override
    public boolean containsMob(String m){
		return MAP_UTIL.containsKey(roomMob, m);
	}
	/**
	 * adds Exit with given String and location
	 * @param dir
	 * @param room
	 */
	@Override
    public IExit addExit(String dir, ILocation room){
		Exit exit = new Exit.Builder().name(dir).location(room).build();
		return MAP_UTIL.addItemToMap(exits, exit);
	}
	
	@Override
    public IExit addExit(IExit exit){
		return MAP_UTIL.addItemToMap(exits, exit);
	}
	/**
	 * removes Exit under that String
	 * @param dir
	 * @return 
	 */
	@Override
    public IExit removeExit(String dir){
		return MAP_UTIL.removeItemFromMap(exits, dir);
	}

	@Override
    @JsonIgnore
	public IExit getExit(String dir){
		return MAP_UTIL.getItemFromMap(exits, dir);
	}
	@Override
    @JsonIgnore
	public IMob getMob(String key){
		return MAP_UTIL.getItemFromMap(roomMob,key);
	}
	@Override
    @JsonIgnore
	public IItem getItem(String key){
		return MAP_UTIL.getItemFromMap(inventory, key);
	}
	@Override
    public boolean containsExit(String dir){
		return MAP_UTIL.containsKey(exits, dir);
	}
	@JsonProperty("descrip")
	public void setDescription(String descrip){
		description = descrip;
	}
	@Override
    public int compareTo(ILocation other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0){
			compare = ObjectsUtil.compareTo(description,other.getDescription());
		}
		return compare;
	}
	@Override
    public String getExitsDescriptions(){
		return DescriptionUtil.getConcealableNames(exits, true);
	}
	@Override
    public String getInventoryDescriptions(){
		return DescriptionUtil.getConceableRoomDescriptions(inventory, true);
	}
	@Override
    public String getMobDescriptions(){
		return DescriptionUtil.getConceableRoomDescriptions(roomMob, true);
	}
	/**
	 * Checks that the name and description are equals. uses {@link ObjectsUtil#equalKeys(Map, Map)}
	 * when it checks if the inventory, mobs, and exits are equal.
	 */
	@Override
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
			if (ObjectsUtil.notEqualKeys(inventory, l.inventory)){
				return false;
			}
			if (ObjectsUtil.notEqualKeys(roomMob, l.roomMob)){
				return false;
			}
			if (ObjectsUtil.notEqualKeys(exits, l.exits)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Gets the hash code of values in Location, using {@link ObjectsUtil#getKeysHash(Map)}
	 * for getting hash of exits, inventory, and mobs
	 */
	@Override
    public int hashCode(){
		int start = 1;
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getKeysHash(exits);
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getKeysHash(inventory);
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getKeysHash(roomMob);
		return ObjectsUtil.getHashWithStart(start, ObjectsUtil.DEFAULT_PRIME, name, description);
	}
}
