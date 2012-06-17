package textGames;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jjcard
 *
 */


public class Location implements Comparable<Location>{
	public static final int UNDEFINED = 0;
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST  = 3;
	public static final int WEST  = 4;
	public static final int UP    = 5;
	public static final int DOWN  = 6;
	public static final int NORTHEAST = 7;
	public static final int NORTHWEST = 8;
	public static final int SOUTHEAST = 9;
	public static final int SOUTHWEST = 10;
	
	/**
	 * String[] containing basic directions
	 */
	public static final String[] dirName = {
		"UNDEFINED",
		"NORTH",
		"SOUTH",
		"EAST",
		"WEST",
		"UP",
		"DOWN",
		"NORTHEAST",
		"NORTHWEST",
		"SOUTHEAST",
		"SOUTHWEST"
	};
	private static int dirNameLength = dirName.length;
	private String name;
	private String description;
	private Map<String, Item> inventory;
	private Map<String, Mob> roomMob;
	private Map<String, Location> exits;
	
	public Location(){
		name = new String();
		description = new String();
		inventory = new HashMap<String, Item>();
		roomMob = new HashMap<String, Mob>();
		exits = new HashMap<String, Location>();
	}
	public Location(String nameN){
		name = nameN;
		description = new String();
		inventory = new HashMap<String, Item>();
		roomMob =  new HashMap<String, Mob>();
		exits = new HashMap<String, Location>();
	}
	public Location(String nameN, String descripN){
		name = nameN;
		description = descripN;
		inventory = new HashMap<String, Item>();
		roomMob =  new HashMap<String, Mob>();
		exits = new HashMap<String, Location>();
	}
	public Location(String nameN, String descripN, Map<String, Item> invenN){
		name = nameN;
		description = descripN;
		inventory = invenN;
		roomMob =  new HashMap<String, Mob>();
		exits = new HashMap<String, Location>();
	}
	public Location(String nameN, String descripN, Map<String, Item> invenN, Map<String, Mob> mobs){
		name = nameN;
		description = descripN;
		inventory = invenN;
		roomMob = mobs;
		exits = new HashMap<String, Location>();
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public Map<String, Item> getInventory(){
		return inventory;
	}
	public  Map<String, Mob> getRoomMob() {
		return roomMob;
	}
	public Map<String, Location> getExits() {
		return exits;
	}
	public Item addItem(String key, Item add){
		return inventory.put(key, add);
	}
	public void setInventory(Map<String, Item> inventoryNew){
		inventory = inventoryNew;
	}
	public void setRoomMob(Map<String, Mob> roomMobNew){
		roomMob = roomMobNew;
	}
	public Item removeItem(Item key){
		return inventory.remove(key);
	}
	public boolean containsItem(String keyR){
		return inventory.containsKey(keyR);
	}
	public Mob addMob(String key, Mob m){
		return roomMob.put(key, m);
		
	}
	public Mob removeMob(String key){
		return roomMob.remove(key);
	}
	public boolean containsMob(String m){
		return roomMob.containsKey(m);

	}
	/**
	 * adds Exit with given String in Uppercase and loctaion
	 * @param dir
	 * @param room
	 */
	public void addExit(String dir, Location room){
		exits.put(dir.toUpperCase(), room);
	}
	public void addExit(int dir, Location room){
		if(dir < dirName.length){
			exits.put(dirName[dir], room);
		}
		
	}
	
	/**
	 * romves Exit under that String in uppercase
	 * @param dir
	 */
	public void removeExit(String dir){
		 exits.remove(dir.toUpperCase()); 
	}
	public void removeExit(int dir){
		exits.remove(dirName[dir]);
	}
	/**
	 * returns Location corresponding to dir to uppercase
	 * @param dir
	 * @return
	 */
	public Location getExitLocation(String dir){
		return exits.get(dir.toUpperCase());
	}
	public Location getExitLocation(int dir){
		if (dirNameLength < dir){
			return exits.get(dirName[dir]);
		}
		return null;
		
	}
	public Mob getMob(String key){
		return roomMob.get(key);
	}
	public Item getItem(String key){
		return inventory.get(key);
	}
	public boolean containsExit(String dir){
		return exits.containsKey(dir.toUpperCase());
	}
	public boolean containsExit(int dir){
		return exits.containsKey(dirName[dir]);
	}
	public void setName(String change){
		name = change;
	}
	public void setDescription(String change){
		description = change;
	}
	@Override
	public int compareTo(Location other) {
		return name.compareTo(other.name);
	}
	public String getExitsDescrip(){
		String re = exits.keySet().toString();
		return re.substring(1, re.length()-1);
	}
	public String getInventoryDescrip(){
		
		StringBuilder re = new StringBuilder();
		for(Item i: inventory.values()){
			if ((!i.getHidden()) && (i.getRoomDescrip() != null)){
				re.append(" " + i.getRoomDescrip());
			}
		}
		return re.toString();
	}
	public String getMobDescrip(){
		StringBuilder re = new StringBuilder();
		for(Mob m: roomMob.values()){
			if (m.getRoomDescrip() != null){
				re.append(m.getRoomDescrip());
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
