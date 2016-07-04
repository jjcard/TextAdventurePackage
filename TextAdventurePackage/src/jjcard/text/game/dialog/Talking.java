package jjcard.text.game.dialog;

import jjcard.text.game.parser.ITextTokenStream;
import jjcard.text.game.parser.ITextTokenType;
import jjcard.text.game.util.Experimental;
/**
 * 
 * @param <T>
 */
@Experimental
public interface Talking<T extends ITextTokenType> {
	@Experimental
	public void talkTo(ITextTokenStream<T> words);
}
