package jjcard.textGames.game;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * a interface with some basic elements needed for most elements in the game.
 * the name is used to to refer
 * the the object in the game. The names should not be able to changed after creation time, as that can have
 * unknown effects on classes that refer to the IGameElement by that name.
 * @author jjcard
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface IGameElement {
	/**
	 * Returns the name for the element.
	 * @return the name
	 */
	public String getName();
	/**
	 * Returns a description of the element for if it is in a room.
	 * @return the room description
	 */
	public String getRoomDescription();
	
	
}
