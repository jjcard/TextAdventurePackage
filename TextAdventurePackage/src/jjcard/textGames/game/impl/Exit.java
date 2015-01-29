package jjcard.textGames.game.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.textGames.game.IExit;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.util.ObjectsUtil;

/**
 * Class to hold an exit pertaining to a specific ILocation
 * @author jjcard
 *
 */
@JsonDeserialize(builder = Exit.ExitBuilder.class)
public class Exit extends AbstractGameElement implements IExit {

	
	public static final ExitBuilder NORTH_BUILD = new Exit.ExitBuilder().standardName("NORTH");
	public static final ExitBuilder SOUTH_BUILD = new Exit.ExitBuilder().standardName("SOUTH");
	public static final ExitBuilder EAST_BUILD = new Exit.ExitBuilder().standardName("EAST");
	public static final ExitBuilder WEST_BUILD = new Exit.ExitBuilder().standardName("WEST");
	public static final ExitBuilder NORTHEAST_BUILD = new Exit.ExitBuilder().standardName("NORTHEAST");
	public static final ExitBuilder NORTHWEST_BUILD = new Exit.ExitBuilder().standardName("NORTHWEST");
	public static final ExitBuilder SOUTHEAST_BUILD = new Exit.ExitBuilder().standardName("SOUTHEAST");
	public static final ExitBuilder SOUTHWEST_BUILD = new Exit.ExitBuilder().standardName("SOUTHWEST");
	public static final ExitBuilder UP_BUILD = new Exit.ExitBuilder().standardName("UP");
	public static final ExitBuilder DOWN_BUILD = new Exit.ExitBuilder().standardName("DOWN");
	
	
	public static final Exit NORTH = NORTH_BUILD.build();
	public static final Exit SOUTH = SOUTH_BUILD.build();
	public static final Exit EAST = EAST_BUILD.build();
	public static final Exit WEST = WEST_BUILD.build();
	
	public static final Exit NORTHEAST = NORTHEAST_BUILD.build();
	public static final Exit NORTHWEST = NORTHWEST_BUILD.build();
	public static final Exit SOUTHEAST = SOUTHEAST_BUILD.build();
	public static final Exit SOUTHWEST = SOUTHWEST_BUILD.build();
	

	public static final Exit UP = UP_BUILD.build();
	public static final Exit DOWN = DOWN_BUILD.build();
	
	/**
	 * List that includes North, South, East, And West
	 */
	public static final Exit[] simpleValues = new Exit[]{NORTH, SOUTH, EAST, WEST};
	/**
	 * List that contains all compass directions; NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST
	 */
	public static final Exit[] compassValues = new Exit[]{NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST};
	/**
	 * List that includes all static default Exits
	 */
	public static final Exit[] defaultValues = new Exit[]{NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHEAST, UP, DOWN};
	
	@JsonProperty("loc")
	private final ILocation location;

	public static class ExitBuilder extends GameElementBuilder{
		private ILocation location;
		
		
		public ExitBuilder(){
			super();
		}
		public ExitBuilder(Exit exit){
			super(exit);
			this.location = exit.location;
		}
		public ExitBuilder(AbstractGameElement element){
			super(element);
		}
		public ExitBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public ExitBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		@JsonProperty("loc")
		public ExitBuilder location(ILocation location){
			this.location = location;
			return this;
		}
		public Exit build(){
			return new Exit(this);
		}
		
		
	}
	
	protected Exit(ExitBuilder builder){
		super(builder);
		this.location = builder.location;
	}
	/**
	 * Gets the Location
	 */
	public ILocation getLocation() {
		return location;
	}

	/**
	 * Returns a new Exit with the same properties as the current one with the given location
	 * @param location
	 * @return copy of Exit with location
	 */
	public Exit getWithLocation(ILocation location){
		return new ExitBuilder(this).location(location).build();
	}
	public boolean equals(Object object){
		if (object == this){
			return true;
		}
		
		if (object instanceof Exit){
			if (!super.equals(object)){
				return false;
			}
			if (ObjectsUtil.notEqual(location, ((Exit) object).location)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
}
