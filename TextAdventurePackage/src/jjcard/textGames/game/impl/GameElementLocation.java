package jjcard.textGames.game.impl;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.textGames.game.IExit;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.util.DescriptionUtil;
import jjcard.textGames.game.util.MapUtil;
import jjcard.textGames.game.util.ObjectsUtil;
/**
 * An Implementation of ILocation that is also a GameElement and follows the builder pattern.
 *
 */
@JsonDeserialize(builder = GameElementLocation.Builder.class)
public class GameElementLocation extends AbstractGameElement implements ILocation {
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
	@JsonProperty("inventory")
	private Map<String,IItem> inventory;
	@JsonProperty("mobs")
	private Map<String,IMob> roomMob;
	@JsonProperty("exits")
	private Map<String,IExit> exits;
	
	
	public static class Builder extends AbstractGameElement.Builder{
		
		private Map<String,IItem> inventory = new HashMap<String,IItem>();
		private Map<String,IMob> roomMob = new HashMap<String,IMob>();
		private Map<String,IExit> exits = new HashMap<String,IExit>();
		
		public Builder(){
			super();
		}
		public Builder(AbstractGameElement element){
			super(element);
		}
		public Builder name(String name){
			super.name(name);
			return this;
		}
		/**
		 * the room description and also the description
		 */
		public Builder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		@JsonProperty("exits")
		public Builder exits(Map<String,IExit> exits){
			if (exits == null){
				this.exits = new HashMap<String,IExit>();
			} else {
				this.exits = exits;
			}
			return this;
		}
		public Builder addExit(IExit exit){
			MAP_UTIL.addItemToMap(exits, exit);
			return this;
		}
		@JsonProperty("mobs")
		public Builder mobs(Map<String,IMob> roomMobs){
			if (roomMobs == null){
				this.roomMob = new HashMap<String,IMob>();
			} else {
				this.roomMob = roomMobs;
			}	
			return this;
		}
		public Builder addMob(IMob mob){
			MAP_UTIL.addItemToMap(roomMob,mob);
			return this;
		}
		@JsonProperty("inventory")
		public Builder inventory(Map<String,IItem> inventory){
			if (inventory == null){
				this.inventory = new HashMap<String,IItem>();
			} else {
				this.inventory = inventory;
			}
			return this;
		}
		public Builder addItem(IItem item){
			MAP_UTIL.addItemToMap(inventory,item);
			return this;
		}
		public GameElementLocation build(){
			return new GameElementLocation(this);
		}
	}
	protected GameElementLocation(Builder builder) {
		super(builder);
		this.roomMob = builder.roomMob;
		this.exits = builder.exits;
		this.inventory = builder.inventory;
	}


	/**
	 * Gets the description aka the room description.
	 */
	@JsonIgnore
	@Override
	public String getDescription() {
		return getRoomDescription();
	}
	@JsonIgnore
	@Override
	public void setDescription(String descrip) {
		setRoomDescription(descrip);
	}

	public Map<String,IItem> getInventory(){
		return inventory;
	}
	public  Map<String,IMob> getMobs() {
		return roomMob;
	}
	public Map<String,IExit> getExits() {
		return exits;
	}
	public IItem addItem(IItem add){
		return MAP_UTIL.addItemToMap(inventory,add);
	}
	public void setInventory(Map<String,IItem> inventoryNew){
		if (inventoryNew == null){
			inventory = new HashMap<String,IItem>();
		} else {
			inventory = inventoryNew;
		}
		
	}
	public void setMobs(Map<String,IMob> roomMob){
		if (roomMob == null){
			this.roomMob = new HashMap<String,IMob>();
		} else {
			this.roomMob = roomMob;
		}
		
	}
	public IItem removeItem(String key){
		return MAP_UTIL.removeItemFromMap(inventory, key);
	}
	public boolean containsItem(String key){
		return MAP_UTIL.containsKey(inventory, key);
	}
	public IMob addMob(IMob mob){
		return MAP_UTIL.addItemToMap(roomMob,mob);
		
	}
	public IMob removeMob(String key){
		return MAP_UTIL.removeItemFromMap(roomMob,key);
	}
	public boolean containsMob(String key){
		return MAP_UTIL.containsKey(roomMob,key);

	}
	/**
	 * adds Exit with given String in Uppercase and location
	 * @param dir
	 * @param room
	 * @return 
	 */
	public IExit addExit(String dir, ILocation room){
		Exit exit = new Exit.Builder().name(dir).location(room).build();
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
		 return MAP_UTIL.removeItemFromMap(exits,dir); 
	}

	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	public ILocation getExitLocation(String dir){
		IExit exit = getExit(dir);
		if (exit != null){
			return exit.getLocation();
		} else {
			return null;
		}
	}
	public IExit getExit(String dir){
		return MAP_UTIL.getItemFromMap(exits, dir);
	}
	public IMob getMob(String key){
		return MAP_UTIL.getItemFromMap(roomMob,key);
	}
	public IItem getItem(String key){
		return MAP_UTIL.getItemFromMap(inventory,key);
	}
	public boolean containsExit(String dir){
		return MAP_UTIL.containsKey(exits,dir);
	}



	public int compareTo(ILocation other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0 && getRoomDescription() != null){
			compare = getRoomDescription().compareTo(other.getDescription());
		}
		return compare;
	}
	public String getExitsDescriptions(){
		return DescriptionUtil.getConcealableNames(exits, true);
	}
	public String getInventoryDescriptions(){
		return DescriptionUtil.getConceableDescriptions(inventory, true);
	}
	public String getMobDescriptions(){
		return DescriptionUtil.getConceableDescriptions(roomMob, true);
	}
	/**
	 * 
	 * @return room description, description of items and mobs in room, and exits. 
	 */
	public String showRoom(){
		return DescriptionUtil.showRoom(this);
	}
	/**
	 * Checks that the name and description are equals. uses {@link ObjectsUtil#equalKeys(Map, Map)}
	 * to check if the inventory, roomMobs, and exits are equal.
	 */
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (!super.equals(o)){
			return false;
		}
		if (o instanceof GameElementLocation){
			GameElementLocation l = (GameElementLocation) o;
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
	public int hashCode(){
		int start = super.hashCode();
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getkeysHash(exits);
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getkeysHash(inventory);
		start = start * ObjectsUtil.DEFAULT_PRIME  + ObjectsUtil.getkeysHash(roomMob);
		return start;
	}


	@Override
	public void setExits(Map<String, IExit> exits) {
		if (exits == null){
			this.exits = new HashMap<String, IExit>();
		} else {
			this.exits = exits;
		}
		
	}

}
