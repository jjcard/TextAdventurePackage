package jjcard.textGames.game.dialog;

import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.util.Experimental;
/**
 * 
 * Experimental: Subject to Change
 * @param <T>
 */
@Experimental
public interface Talking<T extends ITextTokenType> {

	public void talkTo(ITextTokenStream<T> words);
}
