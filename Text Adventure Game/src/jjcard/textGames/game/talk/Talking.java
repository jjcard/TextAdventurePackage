package jjcard.textGames.game.talk;

import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;

public interface Talking<T extends ITextTokenType> {

	public void talkTo(ITextTokenStream<T> words);
}
