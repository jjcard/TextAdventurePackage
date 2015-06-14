package jjcard.textGames.game.impl;

import jjcard.textGames.game.IExit;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.util.ObjectsUtil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Class to hold an exit pertaining to a specific ILocation
 * @author jjcard
 *
 */
@JsonDeserialize(builder = Exit.Builder.class)
public class Exit extends AbstractGameElement implements IExit {

	
	public static final Builder NORTH_BUILD = new Exit.Builder().standardName("NORTH");
	public static final Builder SOUTH_BUILD = new Exit.Builder().standardName("SOUTH");
	public static final Builder EAST_BUILD = new Exit.Builder().standardName("EAST");
	public static final Builder WEST_BUILD = new Exit.Builder().standardName("WEST");
	public static final Builder NORTHEAST_BUILD = new Exit.Builder().standardName("NORTHEAST");
	public static final Builder NORTHWEST_BUILD = new Exit.Builder().standardName("NORTHWEST");
	public static final Builder SOUTHEAST_BUILD = new Exit.Builder().standardName("SOUTHEAST");
	public static final Builder SOUTHWEST_BUILD = new Exit.Builder().standardName("SOUTHWEST");
	public static final Builder UP_BUILD = new Exit.Builder().standardName("UP");
	public static final Builder DOWN_BUILD = new Exit.Builder().standardName("DOWN");
	
	
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
	public static final Exit[] SIMPLE_VALUES = new Exit[]{NORTH, SOUTH, EAST, WEST};
	/**
	 * List that contains all compass directions; NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST
	 */
	public static final Exit[] COMPASS_VALUES = new Exit[]{NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST};
	/**
	 * List that includes all static default Exits
	 */
	public static final Exit[] DEFAULT_VALUES = new Exit[]{NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHEAST, UP, DOWN};
	
	@JsonProperty("loc")
	private final ILocation location;
	
	@JsonProperty("hid")
	private boolean hidden = false;

	public static class Builder extends AbstractGameElement.Builder{
		private ILocation location;
		private boolean hidden = false;
		
		public Builder(){
			super();
		}
		public Builder(Exit exit){
			super(exit);
			this.location = exit.location;
		}
		public Builder(AbstractGameElement element){
			super(element);
		}
		public Builder standardName(String name){
			super.standardName(name);
			return this;
		}
		public Builder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		@JsonProperty("loc")
		public Builder location(ILocation location){
			this.location = location;
			return this;
		}
		@JsonProperty("hid")
		public Builder hidden(boolean hidden){
			this.hidden = hidden;
			return this;
		}
		public Exit build(){
			return new Exit(this);
		}
		
		
	}
	
	protected Exit(Builder builder){
		super(builder);
		this.location = builder.location;
		this.hidden = builder.hidden;
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
		return new Builder(this).location(location).build();
	}
	public boolean isHidden(){
		return hidden;
	}
	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}
	
	public boolean equals(Object object){
		if (object == this){
			return true;
		}
		
		if (object instanceof Exit){
			if (!super.equals(object)){
				return false;
			}
			Exit e = (Exit) object;
			if (ObjectsUtil.notEqual(location, e.location)){
				return false;
			}
			if (hidden != e.hidden){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	public int hashCode(){
		final int prime = 23;		
		return ObjectsUtil.getHash(super.hashCode(), prime, location, hidden);
	}
	
	
	
	
	
}
