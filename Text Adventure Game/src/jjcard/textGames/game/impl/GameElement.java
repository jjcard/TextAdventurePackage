package jjcard.textGames.game.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jjcard.textGames.game.IGameElement;

/**
 * a basic class implementing IGameElement
 * @author jjcard
 *
 */
public abstract class GameElement implements IGameElement {
	
	

	private String roomDescription;
	
	protected String standardName;
	private String[] altNames;
	

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
		
		public GameElementBuilder standardName(String name){
			this.standardName = name;
			return  this;
		}
		public GameElementBuilder altNames(String[] altNames){
			this.altNames = Arrays.asList(altNames);
			
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
	}
	
	protected GameElement(GameElementBuilder b){
		this.standardName = b.standardName;
		this.roomDescription = b.roomDescription;
		this.altNames = b.altNames.toArray(new String[b.altNames.size()]);
	}
//	public GameElement(String name){
//		standardName = name;
//		altNames =  new String[0];
//	}
//	public GameElement(String name, String[] altNames){
//		standardName = name;
//		this.altNames = altNames;
//	}

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

	
	
	
	

}
