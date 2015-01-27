package jjcard.textGames.game.impl;

import static jjcard.textGames.game.util.ObjectsUtil.notEqual;

import java.util.HashMap;
import java.util.Map;

import jjcard.textGames.game.IExit;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.util.MapUtil;
/**
 * An Implementation of ILocation that is also a GameElement and follows the builder pattern.
 *
 */
public class GameElementLocation extends AbstractGameElement implements ILocation {
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
	private static final char SPACE = ' ';
	private static final String EXIT_START = "The obvious exits are";
	private Map<String,IItem> inventory;
	private Map<String,IMob> roomMob;
	private Map<String,IExit> exits;
	
	
	public static class GameElementLocationBuilder extends GameElementBuilder{
		
		private Map<String,IItem> inventory = new HashMap<String,IItem>();
		private Map<String,IMob> roomMob = new HashMap<String,IMob>();
		private Map<String,IExit> exits = new HashMap<String,IExit>();
		
		public GameElementLocationBuilder(){
			super();
		}
		public GameElementLocationBuilder(AbstractGameElement element){
			super(element);
		}
		public GameElementLocationBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		/**
		 * the room description and also the description
		 */
		public GameElementLocationBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public GameElementLocationBuilder exits(Map<String,IExit> exits){
			if (exits == null){
				this.exits = new HashMap<String,IExit>();
			} else {
				this.exits = exits;
			}
			return this;
		}
		public GameElementLocationBuilder addExit(IExit exit){
//			this.exits.put(exit);
			MAP_UTIL.addItemToMap(exits, exit);
			return this;
		}
		public GameElementLocationBuilder roomMobs(Map<String,IMob> roomMobs){
			if (roomMobs == null){
				this.roomMob = new HashMap<String,IMob>();
			} else {
				this.roomMob = roomMobs;
			}	
			return this;
		}
		public GameElementLocationBuilder addMob(IMob mob){
			MAP_UTIL.addItemToMap(roomMob,mob);
			return this;
		}
		public GameElementLocationBuilder inventory(Map<String,IItem> inventory){
			if (inventory == null){
				this.inventory = new HashMap<String,IItem>();
			} else {
				this.inventory = inventory;
			}
			return this;
		}
		public GameElementLocationBuilder addItem(IItem item){
			MAP_UTIL.addItemToMap(inventory,item);
			return this;
		}
		public GameElementLocation build(){
			return new GameElementLocation(this);
		}
	}
	protected GameElementLocation(GameElementLocationBuilder builder) {
		super(builder);
		this.roomMob = builder.roomMob;
		this.exits = builder.exits;
		this.inventory = builder.inventory;
	}


	/**
	 * Gets the name aka the StandardName
	 */
	@Override
	public String getName() {
		return getStandardName();
	}

	/**
	 * Gets the description aka the room description.
	 */
	@Override
	public String getDescription() {
		return getRoomDescription();
	}

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
		Exit exit = new Exit.ExitBuilder().standardName(dir).location(room).build();
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
		return MAP_UTIL.getKeysAsString(exits);
	}
	public String getInventoryDescriptions(){
		
		StringBuilder re = new StringBuilder();
		for(IItem i: inventory.values()){
			if (!i.isHidden() && i.getRoomDescription() != null){
				re.append(SPACE).append(i.getRoomDescription());
			}
		}
		return re.toString();
	}
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
		
		StringBuilder re = new StringBuilder(getRoomDescription());
		
		if (!inventory.isEmpty()){
			re.append(SPACE).append(getInventoryDescriptions());
		}
		if (!roomMob.isEmpty()){
			re.append(SPACE).append(getMobDescriptions());
		}
		if (!exits.isEmpty()){
			re.append(SPACE).append(EXIT_START).append(SPACE).append(getExitsDescriptions());
		}
		return re.toString();
	}
	
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o instanceof GameElementLocation){
			GameElementLocation l = (GameElementLocation) o;
			
			if (!super.equals(l)){
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
