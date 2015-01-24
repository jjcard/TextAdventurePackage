package jjcard.textGames.game.impl;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.util.EqualsUtil;

/**
 * a basic class implementing IGameElement
 * @author jjcard
 *
 */
public abstract class AbstractGameElement implements IGameElement{
	
	

	private String roomDescription;
	
	private final String standardName;
//	private final String[] altNames;
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
		private String roomDescription;
		private boolean validateFields = true;
		
		
		public GameElementBuilder(){
			
		}
		public GameElementBuilder(AbstractGameElement g){
			this.standardName = g.standardName;
			this.roomDescription = g.roomDescription;
		}
		public GameElementBuilder standardName(String name){
			this.standardName = name;
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
	
	protected AbstractGameElement(GameElementBuilder b){
		this.standardName = b.standardName;
		this.roomDescription = b.roomDescription;
		this.validateFields = b.validateFields;
	}
	public final String getStandardName(){
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
	 * Returns the Standard name of the GameElement
	 */
	public String toString(){
		return getStandardName();
	}
	public boolean equals(Object object){
		if (object == this){
			return true;
		}
		
		if (object instanceof AbstractGameElement){
			final AbstractGameElement e = (AbstractGameElement) object;
			if (EqualsUtil.notEqual(standardName, e.standardName)){
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
	public int hashCode(){
		final int prime = 23;		
		return EqualsUtil.getHash(prime, standardName, roomDescription);
	}


	
	
	
	

}
