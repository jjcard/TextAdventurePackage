package jjcard.textGames.game.parser;

public interface ITextTokenType {

	public boolean isObject();
	
	public boolean isVerb();
	
	/**
	 * Optional operation that returns the basic set of words that this ITextTokenType relates to
	 * @return
	 */
	public String[] defaultWords();
}
