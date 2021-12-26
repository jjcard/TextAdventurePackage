package jjcard.text.game;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * An interface with some basic operations needed for most elements in the jjcard.text.game.
 * The name is used to refer the object in the jjcard.text.game, usually as a key in a map.
 * The name should not be able to changed after creation time, as that can have
 * unknown effects on classes that refer to the IGameElement by that name.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface IGameElement {
	/**
	 * Returns the name for the element.
	 * @return the name
	 */
	String getName();
	/**
	 * Returns a description of the element when the entire room is looked at.
	 * @return the room description
	 */
	String getRoomDescription();
	/**
	 * Returns a description of the element when viewed individually. 
	 * @return vies description
	 */
	String getViewDescription();
	
	
}
