package jjcard.text.game.parser;

import jjcard.text.game.parser.impl.TextTokenStream;
import jjcard.text.game.parser.impl.TextTokenStream.Builder;

/**
 * An abstract class that that be extended to handle some of the basic parts of parsing using ITextIndicators.
 *
 * @param <T> extends ITextTokenType
 * @param <K> extends ITextIndicator
 */
public abstract class AbstractTextIndicatorParser<T extends ITextTokenType, K extends ITextIndicator>
		implements ITextParser<T> {

	/**
	 * Indicator for the next object being a withObject. While it is true, objects will be handling like withObjects.
	 */
	protected boolean withObjectIndicator;

	public TextTokenStream<T> parseText(String input) {
		startParsing(input);

		Builder<T> builder = new Builder<>();
		String[] words = splitText(input);
		/**
		 * The current index in the list
		 */
		int index = -1;
		K indicator = getIndicator(input, words, index);
		if (indicator != null && indicator.isWholeSentenceIndicator()) {
			builder = handleWholeSentenceIndicator(indicator, input, builder);
		} else {
			
			handleStartOfWordParsing(builder, words);

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
		return new TextToken<>(word, def.getStandardToken(word), type);
	}
	/**
	 * Called if the parsing of all the words, if got to that point.
	 * @param builder
	 * @param words
	 */
	protected abstract void handleEndOfWordParsing(Builder<T> builder, String[] words);

	/**
	 * If parsing word by word, calls this before starting
	 * @param builder
	 * @param words
	 */
	protected abstract void handleStartOfWordParsing(Builder<T> builder, String[] words);

	/**
	 * Method called when parsing of input ends. Tears down anything that is a per-parse variable.
	 * @param builder
	 */
	protected abstract void endParsing(Builder<T> builder);

	/**
	 * Called when a verb is found when parsing
	 * @param builder
	 * @param token
	 * @return builder
	 */
	protected abstract Builder<T> handleVerb(Builder<T> builder, TextToken<T> token);

	/**
	 * Called when a object is found when parsing
	 * @param builder
	 * @param token
	 * @return builder
	 */
	protected abstract Builder<T> handleObject(Builder<T> builder,
			TextToken<T> token);

	/**
	 * Called when a withObjects is found when parsing
	 * @param builder
	 * @param token
	 * @return builder
	 */
	protected abstract Builder<T> handleWithObject(Builder<T> builder,
			TextToken<T> token);

	/**
	 * Called when a single word ITextIndicator is found
	 * @param builder
	 * @param word
	 * @param indicator
	 * @return builder
	 */
	protected abstract Builder<T> handleWordIndicator(
			Builder<T> builder, String word, K indicator);

/**
 * Called to get the Type of a word. Can return null.
 * @param word the current word
 * @param words the parsed words
 * @param index the current word's index. -1 signifies the whole sentence
 * @return the TextToken
 */
	protected abstract TextToken<T> getTextToken(String word, String[] words, int index);

	/**
	 * Called 
	 * @param indicator
	 * @param input
	 * @param builder
	 * @return builder
	 */
	protected abstract Builder<T> handleWholeSentenceIndicator(K indicator, String input,
			Builder<T> builder);

	/**
	 * Called to get the Indicator for the given input. Can return null.
	 * @param input
	 * @param words the parsed words
	 * @param index of current word. -1 signifies the whole sentence
	 * @return the Indicator
	 */
	protected abstract K getIndicator(String input, String[] words, int index);

	/**
	 * Called to split the input to the individual parsing words
	 * @param input the input String
	 * @return the parsed array of strings
	 */
	protected abstract String[] splitText(String input);

	/**
	 * Method called when parsing of input starts. Sets up anything that is a per-parse variable
	 * @param text
	 */
	protected abstract void startParsing(String text);
}
