package jjcard.textGames.game;

/**
 * a interface with some basic elements needed for most elements in the game.
 * the standard name and alternate names are used to to refer
 * the the classes in the game. The names should be be able to changed after creation time, as that can have
 * unknown effects on classes that refer to the IGameElement by that name, such as the IGameElementMap.
 * @author jjcard
 *
 */
public interface IGameElement {
	/**
	 * Returns the standard name for the element.
	 * @return
	 */
	public String getStandardName();
	/**
	 * Returns a description of the element for if it is in a room.
	 * @return
	 */
	public String getRoomDescription();
	
	
	/**
	 * Returns a array of any alternate names the element can be found under.
	 * @return
	 */
	public String[] getAltNames();

}
