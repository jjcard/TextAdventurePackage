package jjcard.textGames.game.parser;

import jjcard.textGames.game.parser.TextTokenStream.TextTokenStreamBuilder;

/**
 * An abstract class that that be extended to handle some of the basic parts of parsing using ITextIndicators.
 *
 * @param <T>
 * @param <K>
 */
public abstract class TextIndicatorParser<T extends ITextTokenType, K extends ITextIndicator>
		implements ITextParser<T> {

	protected boolean withObjectIndicator;

	public TextTokenStream<T> parseText(String input) {
		startParsing(input);

		String[] words = splitText(input);

		TextTokenStreamBuilder<T> builder = new TextTokenStreamBuilder<T>();

		K indicator = getIndicator(input);
		if (indicator != null && indicator.isWholeSentenceIndicator()) {
			builder = handleWholeSentenceIndicator(indicator, input, builder);
		} else {
			for (String word : words) {
				T type = getType(word);
				if (type != null) {
					// has a type defined in the dictionary
					TextToken<T> token = new TextToken<T>(word, type);
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
					indicator = getIndicator(word);
					if (indicator != null && indicator.isWordIndicator()) {
						builder = handleWordIndicator(builder, word, indicator);

					}
				}
			}
		}

		endParsing(builder);
		return builder.build();
	}

	/**
	 * Method called when parsing of input ends. Tears down anything that is a per-parse variable.
	 * @param builder
	 */
	protected abstract void endParsing(TextTokenStreamBuilder<T> builder);

	protected abstract TextTokenStreamBuilder<T> handleVerb(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	protected abstract TextTokenStreamBuilder<T> handleObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	protected abstract TextTokenStreamBuilder<T> handleWithObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token);

	protected abstract TextTokenStreamBuilder<T> handleWordIndicator(
			TextTokenStreamBuilder<T> builder, String word, K indicator);

	protected abstract T getType(String word);

	protected abstract TextTokenStreamBuilder<T> handleWholeSentenceIndicator(K indicator, String input,
			TextTokenStreamBuilder<T> builder);

	protected abstract K getIndicator(String input);

	protected abstract String[] splitText(String input);

	/**
	 * Method called when parsing of input starts. Sets up anything that is a per-parse variable
	 * @param text
	 */
	protected abstract void startParsing(String text);
}
