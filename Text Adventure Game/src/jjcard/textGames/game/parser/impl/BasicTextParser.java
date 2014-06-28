package jjcard.textGames.game.parser.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.PatternList;
import jjcard.textGames.game.parser.TextIndicatorParser;
import jjcard.textGames.game.parser.TextParserError;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.TextTokenStream.TextTokenStreamBuilder;

public class BasicTextParser<T extends ITextTokenType> extends TextIndicatorParser<T, TextIndicator> {
	

	private ITextDictionary<T> dictionary;
	private PatternList<T> textTokenPatterns;
	private PatternList<TextIndicator> textIndicatorPatterns;
	private static final Pattern splitPattern = Pattern.compile("[\\s]+");
	private int objectLimit = 10;
	
	private TextTokenStream<T> previousStream;
	private Map<String, TextIndicator> indicatorMap;
	private List<TextToken<T>> objects;
	private TextToken<T> verb;
	private TextToken<T> withObject;
	private boolean checkingObjects;
		
	
	
	public BasicTextParser() {
		textIndicatorPatterns = new PatternList<>();
		textTokenPatterns = new PatternList<>();
		this.dictionary = new TextDictionary<T>();
	}
	BasicTextParser(ITextDictionary<T> dictionary){
		this();
		this.dictionary = dictionary;
	}
	@Override
	protected String[] splitText(String text) {
		String[] words = splitPattern.split(text);
		return words;
	}

	protected void handleRepeatIndicator(TextTokenStreamBuilder<T> builder,
			String s) {
		// TODO Auto-generated method stub
		
	}
	protected void handleItIndicator(TextTokenStreamBuilder<T> builder, String s) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * creates a Pattern matching the given String and Adds the pattern to the list matching to the given TextTokenType.
	 * For each word, will be checked in the order given.
	 * @param regexPattern
	 * @param type
	 */
	public void addTextTokenTypePattern(String regexPattern, T type){
		addTextTokenTypePattern(Pattern.compile(regexPattern), type);
	}
	/**
	 * Adds the given pattern to the list matching to the given TextTokenType.
	 * For each word, will be checked in the order given.
	 * @param pattern
	 * @param type
	 */
	public void addTextTokenTypePattern(Pattern pattern, T type){
		textTokenPatterns.add(pattern, type);
	}
	/**
	 * creates a Pattern matching the given String and Adds the pattern to the list matching to the given TextIndicator.
	 * For each word, will be checked in the order given.
	 * @param regexPattern
	 * @param type
	 */
	public void addTextIndicatorePattern(String regexPattern, TextIndicator type){
		addTextIndicatorePattern(Pattern.compile(regexPattern), type);
	}
	/**
	 * Adds the given pattern to the list matching to the given TextIndicator.
	 * For each word, will be checked in the order given.
	 * @param pattern
	 * @param type
	 */
	public void addTextIndicatorePattern(Pattern pattern, TextIndicator type){
		textIndicatorPatterns.add(pattern, type);
	}
	/**
	 * Sets the limit on the number of objects allowed before adding an error to the stream.
	 * A number less then 1 indicates no checking.
	 * @param objectLimit
	 */
	public void setObjectLimit(int objectLimit){
		this.objectLimit = objectLimit;
	}
	@Override
	public void setTextDictionary(ITextDictionary<T> dictionary) {
		this.dictionary = dictionary;
		
	}
	@Override
	public ITextDictionary<T> getTextDictionary() {
		return dictionary;
	}
	public void clear(){
		previousStream = null;
	}


	public void setIndicatorMap(Map<String, TextIndicator> indicatorMap){
		this.indicatorMap = indicatorMap;
	}
	@Override
	protected void endParsing(TextTokenStreamBuilder<T> builder) {
		previousStream = builder.build();
		
	}
	@Override
	protected TextTokenStreamBuilder<T> handleVerb(TextTokenStreamBuilder<T> builder,
			TextToken<T> token) {
		if (verb != null){
			//already has verb
			builder.addError(TextParserError.TOO_MANY_VERBS);
		} else {
			verb = token;
		}
		
		return builder;
	}
	@Override
	protected TextTokenStreamBuilder<T> handleObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token) {
		objects.add(token);
		if (checkingObjects && objects.size() > objectLimit){
			builder.addError(TextParserError.TOO_MANY_OBJECTS);
		}
		
		return builder;
		
	}
	@Override
	protected TextTokenStreamBuilder<T> handleWithObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> token) {
		if (withObject != null){
			builder.addError(TextParserError.TOO_MANY_WITH_OBJECTS);
		} else {
			withObject = token;
		}
		return builder;
		
	}
	@Override
	protected TextTokenStreamBuilder<T> handleWordIndicator(TextTokenStreamBuilder<T> builder,
			String word, TextIndicator indicator) {
		switch (indicator) {
		case WITH_INDICATOR:
			withObjectIndicator = true;
			break;
		case IT_INDICATOR:
			handleItIndicator(builder, word);
			break;
		default:
			break;
		}
		
		return builder;
		
	}
	@Override
	protected T getType(String word) {
		T type = dictionary.get(word);
		if (type == null){
			//check against patterns
			 type = textTokenPatterns.get(word);
		}
		return type;
	}

	@Override
	protected TextIndicator getIndicator(String input) {
		TextIndicator indicator = indicatorMap.get(input);
		if (indicator == null){
			//check against patterns
			indicator = textIndicatorPatterns.get(input);
		}
		return indicator;
	}
	
	@Override
	protected void startParsing(String text) {

		
	}
	@Override
	protected TextTokenStreamBuilder<T> handleWholeSentenceIndicator(
			TextIndicator indicator, String input,
			TextTokenStreamBuilder<T> builder) {
		switch (indicator) {
		case REPEAT_INDICATOR:
			return new TextTokenStreamBuilder<>(previousStream);

		default:
			break;
		}
		return builder;
	}
	@Override
	protected void handleEndOfWordParsing(TextTokenStreamBuilder<T> builder,
			String[] words) {
		builder.objects(objects).verb(verb).withObject(withObject);
		
	}
	@Override
	protected void handleStartOfWordParsing(TextTokenStreamBuilder<T> builder,
			String[] words) {
		checkingObjects = objectLimit > 0;
		objects = new LinkedList<TextToken<T>>();
		verb = null;
		withObject = null;
		withObjectIndicator = false;
		
	}
	

	
}
