package jjcard.textGames.game.parser;

import java.util.Collection;
import java.util.Map;

/**
 * Interface to hold the Dictionary for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
public interface ITextDictionary<T extends Enum<T> & ITextTokenType> extends Map<String, T>{

	
	public void putAll(Collection<String> keys, T value);
}
