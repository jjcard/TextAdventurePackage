package jjcard.textGames.game;

public abstract class GameElement {
	
	

	
	private String roomDescription;
	
	private String standardName;
	private String[] altNames;
	
		public GameElement(){
		altNames =  new String[0];
	}
	public GameElement(String name){
		standardName = name;
		altNames =  new String[0];
	}
	public GameElement(String name, String[] altNames){
		standardName = name;
		this.altNames = altNames;
	}

	public String getStandardName(){
		return standardName;
	}
	public void setStandardName(String name){
		this.standardName = name;
	}
	public void setAltNames(String[] altNames){
		this.altNames = altNames;
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
