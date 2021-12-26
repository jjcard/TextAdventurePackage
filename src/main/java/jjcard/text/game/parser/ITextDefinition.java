package jjcard.text.game.parser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
/**
 * Interface for Definition used in a ITextDictionary.
 *
 * @param <T> extends ITextTokenType
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextDefinition<T extends ITextTokenType> {
	/**
	 * Return the ITextTokenType of this definition
	 * @return T
	 */
	public T getType();
	/**
	 * Returns the standardized form of given token. 
	 * @param token
	 * @return
	 */
	public String getStandardToken(String token);
}
