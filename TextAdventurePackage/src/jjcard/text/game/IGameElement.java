package jjcard.text.game;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * An interface with some basic operations needed for most elements in the game.
 * The name is used to to refer the the object in the game, usually as a key in a map. 
 * The name should not be able to changed after creation time, as that can have
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
	 * Returns a description of the element when the entire room is looked at.
	 * @return the room description
	 */
	public String getRoomDescription();
	/**
	 * Returns a description of the element when viewed individually. 
	 * @return
	 */
	public String getViewDescription();
	
	
}
