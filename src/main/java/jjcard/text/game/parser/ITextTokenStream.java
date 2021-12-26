package jjcard.text.game.parser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
/**
 * Interface for containing TextTokens returned from the parser.
 *
 * @param <T> extends ITextTokenType
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextTokenStream<T extends ITextTokenType> {
	
	/**
	 * Returns the first Object token if the list if there is one, null
	 * otherwise.
	 * 
	 * @return
	 */
	public TextToken<T> getFirstObject();
	
	/**
	 * returns true if there is an object token in the objects list.
	 * 
	 * @return
	 */
	public boolean hasObject();
	
	/**
	 * Returns the withObject TextToken
	 * 
	 * @return
	 */
	public TextToken<T> getWithObject();
	
	
	/**
	 * Returns the verb TextToken
	 * 
	 * @return
	 */
	public TextToken<T> getVerb();
	
	
	/**
	 * Returns the list of Object TextTokens
	 * 
	 * @return
	 */
	public List<TextToken<T>> getObjects();
	
	
	/**
	 * Returns true if the TextTokenStream has a object token in the withObject
	 * field.
	 * 
	 * @return
	 */
	public boolean hasWithObject();

}
