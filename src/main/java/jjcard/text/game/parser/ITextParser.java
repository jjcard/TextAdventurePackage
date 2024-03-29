package jjcard.text.game.parser;


/**
 * 
 * Interface for parsing Text into tokens.
 *
 * @param <T>
 */
public interface ITextParser<T extends ITextTokenType> {
	
	/**
	 * Parses the String and returns a TextTokenStream containing the parsed result.
	 * @param text
	 */
    ITextTokenStream<T> parseText(String text);
	
	/**
	 * Sets the library the parser will use.
	 * @param dictionary
	 */
    void setTextDictionary(ITextDictionary<T> dictionary);
	
	ITextDictionary<T> getTextDictionary();
	/**
	 * Clears any data the parser might remember
	 */
    void clear();

}
