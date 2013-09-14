package jjcard.textGames.game;

/**
 * @author jjcard
 *
 */


public class Location implements Comparable<Location> {

	private String name;
	private String description;
	
	private GameElementMap< Item> inventory;
	private GameElementMap<Mob> roomMob;
	private GameElementMap<Exit> exits;
	
	public Location(){
		this.name = new String();
		description = new String();
		inventory = new GameElementMap<Item>();
		roomMob = new GameElementMap<Mob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name){
		this.name = name;
		description = new String();
		inventory = new GameElementMap<Item>();
		roomMob =  new GameElementMap<Mob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN){
		this.name = name;
		description = descripN;
		inventory = new GameElementMap<Item>();
		roomMob =  new GameElementMap<Mob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN, GameElementMap<Item> invenN){
		this.name = name;
		description = descripN;
		inventory = invenN;
		roomMob =  new GameElementMap<Mob>();
		exits = new GameElementMap<Exit>();
	}
	public Location(String name, String descripN, GameElementMap<Item> invenN, GameElementMap<Mob>  mobs){
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
	public GameElementMap<Item> getInventory(){
		return inventory;
	}
	public  GameElementMap<Mob> getRoomMob() {
		return roomMob;
	}
	public GameElementMap<Exit> getExits() {
		return exits;
	}
	public Item addItem(Item add){
		return inventory.put(add);
	}
	public void setInventory(GameElementMap<Item> inventoryNew){
		inventory = inventoryNew;
	}
	public void setRoomMob(GameElementMap<Mob> roomMobNew){
		roomMob = roomMobNew;
	}
	public Item removeItem(String key){
		return inventory.remove(key);
	}
	public boolean containsItem(String keyR){
		return inventory.containsName(keyR);
	}
	public Mob addMob(Mob m){
		return roomMob.put(m);
		
	}
	public Mob removeMob(String key){
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
	public void addExit(String dir, Location room){
		Exit exit = new Exit(dir, room);
		exits.put(exit);
	}
	
	public void addExit(Exit exit, Location room){
		exit.setLocation(room);
		exits.put(exit);
	}

	
	/**
	 * removes Exit under that String
	 * @param dir
	 */
	public void removeExit(String dir){
		 exits.remove(dir.toUpperCase()); 
	}

	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	public Location getExitLocation(String dir){
		Exit exit = exits.get(dir);
		if (exit != null){
			return exit.getLocation();
		} else {
			return null;
		}
	}
	public Mob getMob(String key){
		return roomMob.get(key);
	}
	public Item getItem(String key){
		return inventory.get(key);
	}
	public boolean containsExit(String dir){
		return exits.containsName(dir.toUpperCase());
	}

	public void setName(String name){
		this.name = name;
	}

	public void setDescription(String descrip){
		description = descrip;
	}
	@Override
	public int compareTo(Location other) {
		int compare = getName().compareTo(other.getName());
		if (compare == 0 && description != null){
			compare = description.compareTo(other.description);
		}
		return getName().compareTo(other.getName());
	}
	public String getExitsDescrip(){
		String re = exits.getAllStandardNamesString();
		return re.substring(1, re.length()-1);
	}
	public String getInventoryDescrip(){
		
		StringBuilder re = new StringBuilder();
		for(Item i: inventory.getElements()){
			if ((!i.isHidden()) && (i.getRoomDescription() != null)){
				re.append(" " + i.getRoomDescription());
			}
		}
		return re.toString();
	}
	public String getMobDescrip(){
		StringBuilder re = new StringBuilder();
		for(Mob m: roomMob.getElements()){
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
			re.append(" " + getInventoryDescrip());
		}
		if (!roomMob.isEmpty()){
			re.append(" " + getMobDescrip());
		}
		if (!exits.isEmpty()){
			re.append(" The obvious exits are " + getExitsDescrip());
		}
		return re.toString();
	}
}
