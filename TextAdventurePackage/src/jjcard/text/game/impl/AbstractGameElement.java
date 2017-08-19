package jjcard.text.game.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.text.game.IGameElement;
import jjcard.text.game.util.ObjectsUtil;

/**
 * a basic class implementing IGameElement
 *
 */

@JsonDeserialize(builder = AbstractGameElement.Builder.class)
public abstract class AbstractGameElement implements IGameElement{
	@JsonProperty("roomDescrip")
	private String roomDescription;
	@JsonProperty("name")
	private final String name;
	@JsonProperty("viewDescrip")
	private String viewDescription;
	@JsonProperty("valFields")
	protected boolean validateFields = true;
	

	/**
	 * A Builder to make a GameElement.
	 * All subclasses should have their own builders to extend this one.
	 * This will also require them to reimplement all the methods here, so the return Builder
	 * will be of the same type. Other solutions to extending Builders were considered, but the code 
	 * ended up being too cumbersome and prone to errors. 
	 *
	 */
	public static class Builder{
		private String name = "";
		private String roomDescription;
		private String viewDescription;
		private boolean validateFields = true;
		
		/**
		 * Creates a new builder for a AbstractGameElement
		 */
		public Builder(){
			//default empty constructer
		}
		public Builder(AbstractGameElement element){
			this.name = element.name;
			this.roomDescription = element.roomDescription;
		}
		@JsonProperty("name")
		/**
		 * name of the Element. Default is ""
		 * @param name
		 * @return this
		 */
		public Builder name(String name){
			this.name = name;
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
		@JsonProperty("viewDescrip")
		public Builder viewDescription(String viewDescription){
			this.viewDescription = viewDescription;
			return this;
		}
	}
	
	protected AbstractGameElement(Builder builder){
		ObjectsUtil.checkArg(builder.name, "name");
		this.name = builder.name;
		this.roomDescription = builder.roomDescription;
		this.validateFields = builder.validateFields;
		this.viewDescription = builder.viewDescription;
	}
	protected AbstractGameElement(String name){
		ObjectsUtil.checkArg(name, "name");
		this.name = name;
	}
	public final String getName(){
		return name;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public String getViewDescription(){
		return viewDescription;
	}
	public void setViewDescription(String viewDescription){
		this.viewDescription = viewDescription;
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
			if (ObjectsUtil.notEqual(name, element.name)){
				return false;
			}
			if (ObjectsUtil.notEqual(roomDescription, element.roomDescription)){
				return false;
			}
			if (ObjectsUtil.notEqual(viewDescription, element.viewDescription)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	public int hashCode(){
		return ObjectsUtil.getHash(ObjectsUtil.DEFAULT_PRIME, name, roomDescription, viewDescription);
	}
}
