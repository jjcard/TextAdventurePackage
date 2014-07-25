package jjcard.textGames.game.impl;

import static jjcard.textGames.game.util.EqualsUtil.notEqual;
import jjcard.textGames.game.IExit;
import jjcard.textGames.game.IGameElementMap;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
/**
 * An Implementation of ILocation that is also a GameElement and follows the builder pattern.
 *
 */
public class GameElementLocation extends GameElement implements ILocation {

	private static final char SPACE = ' ';
	private static final String EXIT_START = "The obvious exits are";
	private IGameElementMap<IItem> inventory;
	private IGameElementMap<IMob> roomMob;
	private IGameElementMap<IExit> exits;
	
	
	public static class GameElementLocationBuilder extends GameElementBuilder{
		
		private IGameElementMap<IItem> inventory = new GameElementMap<IItem>();
		private IGameElementMap<IMob> roomMob = new GameElementMap<IMob>();
		private IGameElementMap<IExit> exits = new GameElementMap<IExit>();
		
		public GameElementLocationBuilder(){
			super();
		}
		public GameElementLocationBuilder(GameElement e){
			super(e);
		}
		public GameElementLocationBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public GameElementLocationBuilder altNames(String[] altNames){
			super.altNames(altNames);
			return this;
		}
		public GameElementLocationBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public GameElementLocationBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public GameElementLocationBuilder exits(IGameElementMap<IExit> exits){
			if (exits == null){
				this.exits = new GameElementMap<IExit>();
			} else {
				this.exits = exits;
			}
			return this;
		}
		public GameElementLocationBuilder addExit(IExit exit){
			this.exits.put(exit);
			return this;
		}
		public GameElementLocationBuilder roomMobs(IGameElementMap<IMob> roomMobs){
			if (roomMobs == null){
				this.roomMob = new GameElementMap<IMob>();
			} else {
				this.roomMob = roomMobs;
			}	
			return this;
		}
		public GameElementLocationBuilder addMob(IMob mob){
			this.roomMob.put(mob);
			return this;
		}
		public GameElementLocationBuilder inventory(IGameElementMap<IItem> inventory){
			if (inventory == null){
				this.inventory = new GameElementMap<IItem>();
			} else {
				this.inventory = inventory;
			}
			return this;
		}
		public GameElementLocationBuilder addItem(IItem item){
			this.inventory.put(item);
			return this;
		}
		public GameElementLocation build(){
			return new GameElementLocation(this);
		}
	}
	protected GameElementLocation(GameElementLocationBuilder b) {
		super(b);
		this.roomMob = b.roomMob;
		this.exits = b.exits;
		this.inventory = b.inventory;
	}


	@Override
	public String getName() {
		return getStandardName();
	}

	@Override
	public String getDescription() {
		return getRoomDescription();
	}

	@Override
	public void setDescription(String descrip) {
		setRoomDescription(descrip);

	}

	public IGameElementMap<IItem> getInventory(){
		return inventory;
	}
	public  IGameElementMap<IMob> getRoomMob() {
		return roomMob;
	}
	public IGameElementMap<IExit> getExits() {
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



	public int compareTo(ILocation other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0 && getRoomDescription() != null){
			compare = getRoomDescription().compareTo(other.getDescription());
		}
		return compare;
	}
	public String getExitsDescriptions(){
		String re = exits.getAllStandardNamesAsString();
		return re.substring(1, re.length()-1);
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
