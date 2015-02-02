package jjcard.textGames.game.impl;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.util.ObjectsUtil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * a basic class implementing IGameElement
 * @author jjcard
 *
 */

@JsonDeserialize(builder = AbstractGameElement.Builder.class)
public abstract class AbstractGameElement implements IGameElement{
	@JsonProperty("roomDescrip")
	private String roomDescription;
	@JsonProperty("name")
	private final String standardName;
	@JsonProperty("valFields")
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
	public static class Builder{
		private String standardName;
		private String roomDescription;
		private boolean validateFields = true;
		
		/**
		 * Creates a new builder for a AbstractGameElement
		 */
		public Builder(){
			//default empty constructer
		}
		public Builder(AbstractGameElement element){
			this.standardName = element.standardName;
			this.roomDescription = element.roomDescription;
		}
		@JsonProperty("name")
		public Builder standardName(String name){
			this.standardName = name;
			return  this;
		}
		@JsonProperty("roomDescrip")
		public Builder roomDescription(String roomDescription){
			this.roomDescription = roomDescription;
			return this;
		}
		/**
		 * a flag. If true, then the fields are validated before set.
		 * @param validateFields
		 * @return
		 */
		@JsonProperty("valFields")
		public Builder validateFields(boolean validateFields){
			this.validateFields = validateFields;
			return this;
		}
	}
	
	protected AbstractGameElement(Builder builder){
		this.standardName = builder.standardName;
		this.roomDescription = builder.roomDescription;
		this.validateFields = builder.validateFields;
	}
	public final String getName(){
		return standardName;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public boolean doValidateFields(){
		return this.validateFields;
	}
	/**
	 * Returns the name of the GameElement
	 */
	public String toString(){
		return getName();
	}
	public boolean equals(Object object){
		if (object == this){
			return true;
		}
		
		if (object instanceof AbstractGameElement){
			final AbstractGameElement element = (AbstractGameElement) object;
			if (ObjectsUtil.notEqual(standardName, element.standardName)){
				return false;
			}
			if (ObjectsUtil.notEqual(roomDescription, element.roomDescription)){
				return false;
			}
			
			return true;
		} else {
			return false;
		}
	}
	public int hashCode(){
		final int prime = 23;		
		return ObjectsUtil.getHash(prime, standardName, roomDescription);
	}
}
