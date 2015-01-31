package jjcard.textGames.game.parser.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jjcard.textGames.game.parser.AbstractTextIndicatorParser;
import jjcard.textGames.game.parser.ITextDefinition;
import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.PatternList;
import jjcard.textGames.game.parser.TextParserError;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.impl.TextTokenStream.TextTokenStreamBuilder;
/**
 * Basic text parser class.
 *
 * @param <T>
 */
public class BasicTextParser<T extends ITextTokenType> extends AbstractTextIndicatorParser<T, TextIndicator> {
	

	private ITextDictionary<T> dictionary;
	private final PatternList<ITextDefinition<T>> textTokenPatterns;
	private final PatternList<TextIndicator> textIndicatorPatterns;
	/**
	 * The characters that are wanted to be split by
	 */
	private static final String DELIMINATORS = " ,.";
	//split pattern based on StackOverflow post by Bart Kiers
	public static final Pattern SPLIT_PATTERN = Pattern.compile("["+ DELIMINATORS +"]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	private static final int DEFAULT_OBJECT_LIMIT = 10;
	private int objectLimit = DEFAULT_OBJECT_LIMIT;
	
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
	public BasicTextParser(ITextDictionary<T> dictionary){
		this();
		this.dictionary = dictionary;
	}
	@Override
	protected String[] splitText(String text) {
		return SPLIT_PATTERN.split(text);
	}

	protected void handleRepeatIndicator(TextTokenStreamBuilder<T> builder,
			String s) {
		// TODO Auto-generated method stub
		
	}
	protected void handleItIndicator(TextTokenStreamBuilder<T> builder, String s) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * creates a Pattern matching the given String and Adds the pattern to the list matching to the given AbstractTextDefinition.
	 * For each word, will be checked in the order given.
	 * @param regexPattern
	 * @param type
	 */
	public void addTextTokenTypePattern(String regexPattern, ITextDefinition<T> type){
		addTextTokenTypePattern(Pattern.compile(regexPattern), type);
	}
	/**
	 * Adds the given pattern to the list matching to the given AbstractTextDefinition.
	 * For each word, will be checked in the order given.
	 * @param pattern
	 * @param type
	 */
	public void addTextTokenTypePattern(Pattern pattern, ITextDefinition<T> type){
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
	 * A number less then 1 indicates no checking. The default is 10.
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
	public Map<String, TextIndicator> getIndicatorMap(){
		return indicatorMap;
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
	protected TextToken<T> getTextToken(String word, String[] words, int index){
		
		
		ITextDefinition<T> def = getDefinition(word, words, index);
		if (def != null){
			return createTextToken(word, def);
		}
		return null;
	}
	protected ITextDefinition<T> getDefinition(String word, String[] words, int index) {
		//TODO something else here
		ITextDefinition<T> def =  dictionary.get(word);
		if (def == null){
			//check against patterns
			def = textTokenPatterns.get(word);
		}
		return def;
	}

	@Override
	protected TextIndicator getIndicator(String input) {
		TextIndicator indicator = indicatorMap == null? null: indicatorMap.get(input);
		if (indicator == null){
			//check against patterns
			indicator = textIndicatorPatterns.get(input);
		}
		return indicator;
	}
	
	@Override
	protected void startParsing(String text) {
		//nothing to see here folks
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
