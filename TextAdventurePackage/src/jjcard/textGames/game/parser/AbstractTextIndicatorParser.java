package jjcard.textGames.game.parser;

import jjcard.textGames.game.parser.impl.TextTokenStream;
import jjcard.textGames.game.parser.impl.TextTokenStream.TextTokenStreamBuilder;

/**
 * An abstract class that that be extended to handle some of the basic parts of parsing using ITextIndicators.
 *
 * @param <T>
 * @param <K>
 */
public abstract class AbstractTextIndicatorParser<T extends ITextTokenType, K extends ITextIndicator>
		implements ITextParser<T> {

	/**
	 * Indicator for the next object being a withObject. While it is true, objects will be handling like withObjects.
	 */
	protected boolean withObjectIndicator;

	public TextTokenStream<T> parseText(String input) {
		startParsing(input);

		

		TextTokenStreamBuilder<T> builder = new TextTokenStreamBuilder<T>();
		String[] words = splitText(input);
		int index = -1;
		K indicator = getIndicator(input, words, index);
		if (indicator != null && indicator.isWholeSentenceIndicator()) {
			builder = handleWholeSentenceIndicator(indicator, input, builder);
		} else {
			
			handleStartOfWordParsing(builder, words);
			/**
			 * The current index in the list
			 */
			index = 0;
			for (String word : words) {
				TextToken<T> token = getTextToken(word, words, index);
				
				if (token != null) {
					// has a type defined in the dictionary
					if (token.isObject()) {
						if (withObjectIndicator) {
							builder = handleWithObject(builder, token);
						} else {
							builder = handleObject(builder, token);
						}

					} else if (token.isVerb()) {
						builder = handleVerb(builder, token);
					}
				} else {
					// not a dictionary word.
					indicator = getIndicator(word, words, index);
					if (indicator != null && indicator.isWordIndicator()) {
						builder = handleWordIndicator(builder, word, indicator);

					}
				}
				
				index++;
			}
			
			handleEndOfWordParsing(builder, words);
		}

		endParsing(builder);
		return builder.build();
	}

	protected TextToken<T> createTextToken(String word, ITextDefinition<T> def){
		T type = def.getType();
		// has a type defined in the dictionary
		return new TextToken<T>(word, def.getStandardToken(word), type);
	}
	/**
	 * Called if the parsing of all the words, if got to that point.
	 * @param builder
	 * @param words
	 */
	protected abstract void handleEndOfWordParsing(TextTokenStreamBuilder<T> builder,
			String[] words);

	/**
	 * If parsing word by word, calls this before starting
	 * @param builder
	 * @param words
	 */
	protected abstract void handleStartOfWordParsing(TextTokenStreamBuilder<T> builder,
			String[] words);

	/**
	 * Method called when parsing of input ends. Tears down anything that is a per-parse variable.
	 * @param builder
	 */
	protected abstract void endParsing(TextTokenStreamBuilder<T> builder);

	/**
	 * Called when a verb is found when parsing
	 * @param builder
	 * @param token
	 * @return
	 */
	protected abstract TextTokenStreamBuilder<T> handleVerb(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	/**
	 * Called when a object is found when parsing
	 * @param builder
	 * @param token
	 * @return
	 */
	protected abstract TextTokenStreamBuilder<T> handleObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	/**
	 * Called when a withObjects is found when parsing
	 * @param builder
	 * @param token
	 * @return
	 */
	protected abstract TextTokenStreamBuilder<T> handleWithObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	/**
	 * Called when a single word ITextIndicator is found
	 * @param builder
	 * @param word
	 * @param indicator
	 * @return
	 */
	protected abstract TextTokenStreamBuilder<T> handleWordIndicator(
			TextTokenStreamBuilder<T> builder, String word, K indicator);

/**
 * Called to get the Type of a word. Can return null.
 * @param word
 * @param words
 * @param index
 * @return
 */
	protected abstract TextToken<T> getTextToken(String word, String[] words, int index);

	/**
	 * Called 
	 * @param indicator
	 * @param input
	 * @param builder
	 * @return
	 */
	protected abstract TextTokenStreamBuilder<T> handleWholeSentenceIndicator(K indicator, String input,
			TextTokenStreamBuilder<T> builder);

	/**
	 * Called to get the Indicator for the given input. Can return null. Index is negative if it is for the whole sentence
	 * @param input
	 * @return
	 */
	protected abstract K getIndicator(String input, String[] words, int index);

	/**
	 * Called to split the input to the individual parsing words
	 * @param input
	 * @return
	 */
	protected abstract String[] splitText(String input);

	/**
	 * Method called when parsing of input starts. Sets up anything that is a per-parse variable
	 * @param text
	 */
	protected abstract void startParsing(String text);
}
