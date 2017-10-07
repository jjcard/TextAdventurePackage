package jjcard.text.game.parser.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jjcard.text.game.parser.AbstractTextIndicatorParser;
import jjcard.text.game.parser.ITextDefinition;
import jjcard.text.game.parser.ITextDictionary;
import jjcard.text.game.parser.ITextTokenType;
import jjcard.text.game.parser.PatternList;
import jjcard.text.game.parser.TextParserError;
import jjcard.text.game.parser.TextToken;
import jjcard.text.game.parser.impl.TextTokenStream.Builder;
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
	 * Default characters that are wanted to be split by
	 */
	private static final String DEFAULT_DELIMINATORS = " ,.";
	//split pattern based on StackOverflow post by Bart Kiers
	/**
	 * Pattern to split characters using the default deliminators
	 */
	public static final Pattern DEFAULT_SPLIT_PATTERN = compileSplitPattern(DEFAULT_DELIMINATORS);
	private final Pattern splitPattern;
	private static final int DEFAULT_OBJECT_LIMIT = 10;
	private int objectLimit = DEFAULT_OBJECT_LIMIT;
	
	private TextTokenStream<T> previousStream;
	private Map<String, TextIndicator> indicatorMap;
	private List<TextToken<T>> objects;
	private TextToken<T> verb;
	private TextToken<T> withObject;
	private boolean checkingObjects;
		
	/**
	 * Creates a BasicTextParser with the given deliminators. Deliminators are a String of characters, which each individual one should could as a deliminator.
	 * Warning: Puts the given deliminators into a Regex Pattern, any characters that are used in the Java Regular Expression should be escaped properly. 
	 * @param deliminators
	 * @throws PatternSyntaxException
	 */
	public BasicTextParser(String deliminators) throws PatternSyntaxException {
		this.textIndicatorPatterns = new PatternList<>();
		this.textTokenPatterns = new PatternList<>();
		this.dictionary = new TextDictionary<>();
		this.splitPattern = compileSplitPattern(deliminators);
	}
	/**
	 * Creates a BasicTextParser with the given deliminators. Deliminators are a String of characters, which each individual one should could as a deliminator.
	 * Warning: Puts the given deliminators into a Regex Pattern, any characters that are used in the Java Regular Expression should be escaped properly. 
	 * @param dictionary
	 * @param deliminators
	 * @throws PatternSyntaxException
	 */
	public BasicTextParser(ITextDictionary<T> dictionary, String deliminators) throws PatternSyntaxException{
		this(deliminators);
		this.dictionary = dictionary;
	}	
	public BasicTextParser() {
		this.textIndicatorPatterns = new PatternList<>();
		this.textTokenPatterns = new PatternList<>();
		this.dictionary = new TextDictionary<>();
		this.splitPattern = DEFAULT_SPLIT_PATTERN;
	}
	public BasicTextParser(ITextDictionary<T> dictionary){
		this();
		this.dictionary = dictionary;
	}
	/**
	 * Warning: Puts the given deliminators into a Regex Pattern, any characters that are used in the Java Regular Expression should be escaped properly. 
	 * @param deliminators
	 * @return Pattern the split Pattern
	 * @throws PatternSyntaxException
	 */
	public static Pattern compileSplitPattern(String deliminators) throws PatternSyntaxException {
		//split pattern based on StackOverflow post by Bart Kiers
		return Pattern.compile("["+ deliminators +"]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	}
	@Override
	protected String[] splitText(String text) {
		return splitPattern.split(text);
	}
	/**
	 * Called when the {@link TextIndicator.REPEAT_INDICATOR} is found. Returns the Previous TextTokenStream.
	 * @param builder
	 * @param input
	 * @return builder
	 */
	protected Builder<T> handleRepeatIndicator(Builder<T> builder, String input) {
		return new Builder<>(previousStream); 
	}
	/**
	 * Called when the {@link TextIndicator.IT_INDICATOR} is found. calls {@link #handleObject(Builder, TextToken)} with the first object token from the previous Stream.
	 * @param builder
	 * @param input
	 */
	protected void handleItIndicator(Builder<T> builder, String input) {
		handleObject(builder, previousStream.getFirstObject());
	}
	/**
	 * Get a Deliminator String, with each argument escaped to be literal.
	 * @param deliminators
	 * @return the Deliminator String
	 */
	public static String getDeliminators (String... deliminators){
		StringBuilder b = new StringBuilder();
		for (String delim: deliminators){
			b.append(Pattern.quote(delim));
		}
		return b.toString();
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
	protected void endParsing(Builder<T> builder) {
		previousStream = builder.build();
	}
	@Override
	protected Builder<T> handleVerb(Builder<T> builder,
			TextToken<T> token) {
		if (verb != null){
			//already has verb
			builder.addError(TextParserError.TOO_MANY_VERBS);
		} else {
			verb = token;
			builder = builder.verb(verb);
		}
		return builder;
	}
	@Override
	protected Builder<T> handleObject(Builder<T> builder,
			TextToken<T> token) {
		objects.add(token);
		builder = builder.addObject(token);
		if (checkingObjects && objects.size() > objectLimit){
			builder.addError(TextParserError.TOO_MANY_OBJECTS);
		}
		return builder;
	}
	@Override
	protected Builder<T> handleWithObject(Builder<T> builder,
			TextToken<T> token) {
		if (withObject != null){
			builder.addError(TextParserError.TOO_MANY_WITH_OBJECTS);
		} else {
			withObject = token;
			builder = builder.withObject(withObject);
		}
		return builder;
	}
	@Override
	protected Builder<T> handleWordIndicator(Builder<T> builder,
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
	protected TextIndicator getIndicator(String input, String[] words, int index) {
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
	protected Builder<T> handleWholeSentenceIndicator(
			TextIndicator indicator, String input,
			Builder<T> builder) {
		switch (indicator) {
		case REPEAT_INDICATOR://TODO
			return handleRepeatIndicator(builder, input);
		default:
			break;
		}
		return builder;
	}
	@Override
	protected void handleEndOfWordParsing(Builder<T> builder,
			String[] words) {
	}
	@Override
	protected void handleStartOfWordParsing(Builder<T> builder,
			String[] words) {
		checkingObjects = objectLimit > 0;
		objects = new LinkedList<>();
		verb = null;
		withObject = null;
		withObjectIndicator = false;
	}
	
}
