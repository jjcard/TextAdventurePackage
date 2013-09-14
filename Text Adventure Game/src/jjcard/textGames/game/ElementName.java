package jjcard.textGames.game;

public class ElementName {
	private String standerdName;
	private String[] altNames;
	
	public ElementName(){
		altNames = new String[0];
	}
	public ElementName(String standerdName){
		this.standerdName = standerdName;
		altNames = new String[0];
	}
	
	public ElementName(String standerdName, String[] altnames){
		this.standerdName = standerdName;
		this.altNames = altnames;
	}

	public String getStandardName() {
		return standerdName;
	}
	public void setStanderdName(String standerdName) {
		this.standerdName = standerdName;
	}
	public String[] getAltNames() {
		return altNames;
	}
	public void setAltNames(String[] altNames) {
		this.altNames = altNames;
	}
	
	

}
