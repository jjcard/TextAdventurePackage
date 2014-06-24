package jjcard.textGames.game.parser;

/**
 * 
 * Interface TextParsers should use.
 *
 * @param <T>
 */
public interface ITextParser<T extends Enum<T> & ITextTokenType> {
	
	/**
	 * Parses the String and returns a TextTokenStream containing the parsed result.
	 * @param text
	 */
	public TextTokenStream<T> parseText(String text);
	
	/**
	 * Sets the library the parser will use.
	 * @param library
	 */
	public void setTextDictionary(ITextDictionary<T> dictionary);
	
	public ITextDictionary<T> getTextDictionary();
	/**
	 * Clears any data the parser might remember
	 */
	public void clear();

}