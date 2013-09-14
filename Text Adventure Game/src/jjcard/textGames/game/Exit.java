package jjcard.textGames.game;

public class Exit extends GameElement {

	
	
	
	public static final Exit NORTH = new Exit("NORTH", "N");
	public static final Exit SOUTH = new Exit("SOUTH", "S");
	public static final Exit EAST = new Exit("EAST", "E");
	public static final Exit WEST = new Exit("WEST", "W");
	
	public static final Exit NORTHEAST = new Exit("NORTHEAST", "NE");
	public static final Exit NORTHWEST = new Exit("NORTHWEST", "NW");
	public static final Exit SOUTHEAST = new Exit("SOUTHEAST", "SOUTH EAST","SE");
	public static final Exit SOUTHWEST = new Exit("SOUTHWEST", "SOUTH WEST", "SW");
	
	private Location location;

	
	public Exit (String direction, Location location){
		super(direction);
		this.location = location;
	}

	
	public Exit(String direction, Location location, String... altNames){
		super(direction, altNames);
		this.location = location;
	}
	public Exit(String direction, String...altNames){
		super(direction, altNames);
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
	
	
}
