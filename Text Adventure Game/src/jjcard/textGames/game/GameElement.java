package jjcard.textGames.game;

public abstract class GameElement {
	
	

	
	private ElementName elementName;
	private String roomDescription;
	
		public GameElement(){
		elementName = new ElementName();
	}
	public GameElement(String name){
		elementName = new ElementName(name);
	}
	public GameElement(String name, String[] altNames){
		elementName = new ElementName(name, altNames);
	}
	public ElementName getElementName(){
		return elementName;
	}
	public String getStandardName(){
		return elementName == null? null: elementName.getStandardName();
	}
	public void setStandardName(String name){
		elementName.setStanderdName(name);
	}
	public void setElementName(ElementName elementName){
		this.elementName = elementName;
	}
	public void setAltNames(String[] altNames){
		this.elementName.setAltNames(altNames);
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	
	
	
	

}
