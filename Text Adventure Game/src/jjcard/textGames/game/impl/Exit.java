package jjcard.textGames.game.impl;

import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.util.EqualsUtil;

/**
 * Class to hold an exit pertaining to a specific Location
 * @author jjcard
 *
 */
public class Exit extends GameElement {

	
	public static final ExitBuilder NORTH_BUILD = new Exit.ExitBuilder().standardName("NORTH").addAltName("N");
	public static final ExitBuilder SOUTH_BUILD = new Exit.ExitBuilder().standardName("SOUTH").addAltName("S");
	public static final ExitBuilder EAST_BUILD = new Exit.ExitBuilder().standardName("EAST").addAltName("E");
	public static final ExitBuilder WEST_BUILD = new Exit.ExitBuilder().standardName("WEST").addAltName("W");
	public static final ExitBuilder NORTHEAST_BUILD = new Exit.ExitBuilder().standardName("NORTHEAST").addAltName("NE");
	public static final ExitBuilder NORTHWEST_BUILD = new Exit.ExitBuilder().standardName("NORTHWEST").addAltName("NW");
	public static final ExitBuilder SOUTHEAST_BUILD = new Exit.ExitBuilder().standardName("SOUTHEAST").addAltName("SE");
	public static final ExitBuilder SOUTHWEST_BUILD = new Exit.ExitBuilder().standardName("SOUTHWEST").addAltName("SW");
	public static final ExitBuilder UP_BUILD = new Exit.ExitBuilder().standardName("UP").addAltName("U");
	public static final ExitBuilder DOWN_BUILD = new Exit.ExitBuilder().standardName("DOWN").addAltName("D");
	
	
	public static final Exit NORTH = NORTH_BUILD.build();
	public static final Exit SOUTH = SOUTH_BUILD.build();
	public static final Exit EAST = EAST_BUILD.build();
	public static final Exit WEST = WEST_BUILD.build();
	
	public static final Exit NORTHEAST = NORTHEAST_BUILD.build();
	public static final Exit NORTHWEST = NORTHWEST_BUILD.build();
	public static final Exit SOUTHEAST = SOUTHEAST_BUILD.build();
	public static final Exit SOUTHWEST = SOUTHWEST_BUILD.build();
	

	public static final Exit UP = new Exit.ExitBuilder().standardName("UP").addAltName("U").build();
	public static final Exit DOWN = new Exit.ExitBuilder().standardName("DOWN").addAltName("D").build();
	
	public static final Exit[] defaultValues = new Exit[]{NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHEAST, UP, DOWN};
	private ILocation location;

	public static class ExitBuilder extends GameElementBuilder{
		private ILocation location;
		
		
		public ExitBuilder(){
			super();
		}
		public ExitBuilder(Exit e){
			super(e);
			this.location = e.location;
		}
		public ExitBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public ExitBuilder altNames(String[] altNames){
			super.altNames(altNames);
			return this;
		}
		public ExitBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public ExitBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public ExitBuilder location(ILocation location){
			this.location = location;
			return this;
		}
		public Exit build(){
			return new Exit(this);
		}
		
		
	}
	
	protected Exit(ExitBuilder b){
		super(b);
		this.location = b.location;
	}
	public ILocation getLocation() {
		return location;
	}

	public void setLocation(ILocation location) {
		this.location = location;
	}
	/**
	 * Returns a new Exit with the same properites as the current one with the given location
	 * @param location
	 * @return
	 */
	public Exit getWithLocation(ILocation location){
		return new ExitBuilder(this).location(location).build();
	}
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		
		if (o instanceof Exit){
			if (!super.equals(o)){
				return false;
			}
			if (EqualsUtil.notEqual(location, ((Exit) o).location)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
}
