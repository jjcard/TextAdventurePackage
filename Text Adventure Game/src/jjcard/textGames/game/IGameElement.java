package jjcard.textGames.game;

/**
 * a interface with some basic elements needed for most elements in the game.
 * the standard name and alternate names are used to to refer
 * the the classes in the game.
 * @author jjcard
 *
 */
public interface IGameElement {
	
	public String getStandardName();
	
	public String getRoomDescription();
	
	public void setRoomDescription(String roomDescription);
	
	public String[] getAltNames();

}
