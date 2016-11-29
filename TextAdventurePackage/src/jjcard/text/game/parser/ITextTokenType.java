package jjcard.text.game.parser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
/**
 * Interface for types of TextTokens
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextTokenType {

	public boolean isObject();
	
	public default boolean isVerb(){
		return !isObject();
	}
	
	/**
	 * Optional operation that returns the basic set of words that this ITextTokenType relates to
	 * @return
	 */
	public String[] defaultWords();
}
