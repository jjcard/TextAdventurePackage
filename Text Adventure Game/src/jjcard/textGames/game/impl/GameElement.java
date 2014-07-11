package jjcard.textGames.game.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.util.EqualsUtil;

/**
 * a basic class implementing IGameElement
 * @author jjcard
 *
 */
public abstract class GameElement implements IGameElement {
	
	

	private String roomDescription;
	
	private final String standardName;
	private final String[] altNames;
	protected boolean validateFields = true;
	

	/**
	 * A Builder to make a GameElement.
	 * All subclasses should have their own builders to extend this one.
	 * This will also require them to reimplement all the methods here, so the return Builder
	 * will be of the same type. Other solutions to extending Builders were considered, but the code 
	 * ended up being too cumbersome and prone to errors. 
	 * @author jjcard
	 *
	 */
	public static class GameElementBuilder{
		private String standardName;
		private List<String> altNames = new ArrayList<String>();
		private String roomDescription;
		private boolean validateFields = true;
		
		
		public GameElementBuilder(){
			
		}
		public GameElementBuilder(GameElement g){
			this.standardName = g.standardName;
			this.roomDescription = g.roomDescription;
			this.altNames = Arrays.asList(g.altNames);
		}
		public GameElementBuilder standardName(String name){
			this.standardName = name;
			return  this;
		}
		public GameElementBuilder altNames(String[] altNames){
			if (altNames == null){
				this.altNames = new ArrayList<String>();
			} else {
				this.altNames = Arrays.asList(altNames);
			}
			return  this;
		}
		public GameElementBuilder addAltName(String altName){
			this.altNames.add(altName);
			return  this;
		}
		public GameElementBuilder roomDescription(String roomDescription){
			this.roomDescription = roomDescription;
			return this;
		}
		/**
		 * a flag. If true, then the fields are validated before set.
		 * @param validateFields
		 * @return
		 */
		public GameElementBuilder validateFields(boolean validateFields){
			this.validateFields = validateFields;
			return this;
		}
	}
	
	protected GameElement(GameElementBuilder b){
		this.standardName = b.standardName;
		this.roomDescription = b.roomDescription;
		this.altNames = b.altNames.toArray(new String[b.altNames.size()]);
		this.validateFields = b.validateFields;
	}
	public String getStandardName(){
		return standardName;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public String[] getAltNames(){
		return altNames;
	}
	public boolean doValidateFields(){
		return this.validateFields;
	}
	
	public boolean containsName(String name){
		if (name == null){
			return false;
		}
		if (name.equalsIgnoreCase(standardName)){
			return true;
		} else {
			if (altNames != null){
				for (String altName: altNames){
					if (name.equalsIgnoreCase(altName)){
						return true;
					}
				}
			}
			return false;
		}
	}
	/**
	 * Returns the Standard name of the GameElement
	 */
	public String toString(){
		return getStandardName();
	}
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		
		if (o instanceof GameElement){
			final GameElement e = (GameElement) o;
			if (EqualsUtil.notEqual(standardName, e.standardName)){
				return false;
			}
			if (EqualsUtil.notEqual(altNames, e.altNames)){
				return false;
			}
			if (EqualsUtil.notEqual(roomDescription, e.roomDescription)){
				return false;
			}
			
			return true;
		} else {
			return false;
		}
	}
	public int hashcode(){
		int hash = 0;
		final int prime = 23;
		
		hash = prime * hash + standardName == null? 0: standardName.hashCode();
		hash = prime * hash + Arrays.hashCode(altNames);
		hash = prime * hash + roomDescription == null? 0: roomDescription.hashCode();
	
		
		return hash;
	}

	
	
	
	

}
