package jjcard.text.game.parser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
/**
 * Interface for types of TextTokens
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextTokenType {
	/**
	 *  Whether can be used as a object
	 */
	public boolean isObject();
	/**
	 * Whether can be used as a verb
	 * <br>
	 * Default implementation returns opposite of {@link #isObject()}
	 * @return is verb
	 */
	public default boolean isVerb(){
		return !isObject();
	}
	
	/**
	 * Optional operation that returns the basic set of words that this ITextTokenType relates to
	 * @return
	 */
	public String[] defaultWords();
}
