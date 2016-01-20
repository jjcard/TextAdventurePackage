package jjcard.text.game.parser;

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * Interface to hold the Dictionary for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextDictionary<T extends ITextTokenType> extends Map<String, ITextDefinition<T>>{

	
	public void putAll(Collection<String> keys, ITextDefinition<T> value);
	
//	/**
//	 * If this is true, Dictionary should automatically set casing on words to lowercase when using
//	 * put or get.
//	 * @param doAutomaticCasing
//	 */
//	public void setAutomaticCasing(boolean doAutomaticCasing);
}
