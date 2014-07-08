package jjcard.textGames.game.impl;

import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.util.EqualsUtil;

/**
 * Class to hold an exit pertaining to a specific Location
 * @author jjcard
 *
 */
public class Exit extends GameElement {

	
	
	
	public static final Exit NORTH = new Exit.ExitBuilder().standardName("NORTH").addAltName("N").build();
	public static final Exit SOUTH = new Exit.ExitBuilder().standardName("SOUTH").addAltName("S").build();
	public static final Exit EAST = new Exit.ExitBuilder().standardName("EAST").addAltName("E").build();
	public static final Exit WEST = new Exit.ExitBuilder().standardName("WEST").addAltName("W").build();
	
	public static final Exit NORTHEAST = new Exit.ExitBuilder().standardName("NORTHEAST").addAltName("NE").build();
	public static final Exit NORTHWEST = new Exit.ExitBuilder().standardName("NORTHWEST").addAltName("NW").build();
	public static final Exit SOUTHEAST = new Exit.ExitBuilder().standardName("SOUTHEAST").addAltName("SE").build();
	public static final Exit SOUTHWEST = new Exit.ExitBuilder().standardName("SOUTHWEST").addAltName("SW").build();
	
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
