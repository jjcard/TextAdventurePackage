package jjcard.textGames.game.dialog;

import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;
/**
 * 
 * Experimental: Subject to Change
 * @param <T>
 */
public interface Talking<T extends ITextTokenType> {

	public void talkTo(ITextTokenStream<T> words);
}
