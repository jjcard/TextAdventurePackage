package jjcard.textGames.game.parser;

public interface ITextParser<T extends Enum<T> & ITextTokenType> {
	
	/**
	 * 
	 * @param text
	 */
	public void parseText(String text);

}
