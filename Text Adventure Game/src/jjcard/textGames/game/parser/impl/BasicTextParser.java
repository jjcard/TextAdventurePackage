package jjcard.textGames.game.parser.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.TextIndicatorParser;
import jjcard.textGames.game.parser.TextParserError;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.TextTokenStream.TextTokenStreamBuilder;

public class BasicTextParser<T extends Enum<T> & ITextTokenType> extends TextIndicatorParser<T, TextIndicator> {
	

	private ITextDictionary<T> dictionary;
	private static final Pattern splitPattern = Pattern.compile("[\\s]+");
	private int objectLimit = 10;
	
	private TextTokenStream<T> previousStream;
	private Map<String, TextIndicator> indicatorMap;
	private List<TextToken<T>> objects;
	private TextToken<T> verb;
	private TextToken<T> withObject;
	private boolean checkingObjects;
		
	
	
	BasicTextParser(ITextDictionary<T> dictionary){
		this.dictionary = dictionary;
	}
//	@Override
//	public TextTokenStream<T> parseText(String text) {
//		
//		
//		TextTokenStreamBuilder<T> builder = new TextTokenStreamBuilder<>();
//		
//		TextIndicators ti = indicatorMap.get(text);
//		if (ti != null && ti.isWholeSentenceIndicator()){
//			builder = handleWholeSentenceIndicator(ti, builder);
//		} else {
//			String[] words = splitWords(text);
//
//			List<TextToken<T>> objects = new LinkedList<TextToken<T>>();
//			TextToken<T> verb = null;
//			TextToken<T> withObject = null;
//
//			// flag to use one a 'with' indicator has been found.
//			boolean foundWithObjectIndicator = false;
//
//			boolean checkingObjects = objectLimit > 0;
//
//			T type;
//			for (String s : words) {
//				type = dictionary.get(s);
//				if (type != null) {
//					// has a type defined in the dictionary
//					TextToken<T> token = new TextToken<T>(s, type);
//					if (token.isObject()) {
//						if (foundWithObjectIndicator) {
//							withObject = handleWithObject(builder, withObject,
//									token);
//						} else {
//							handleObject(builder, objects, checkingObjects,
//									token);
//						}
//
//					} else if (token.isVerb()) {
//						verb = handleVerb(builder, verb, token);
//					}
//				} else {
//					// not a dictionary word.
//					TextIndicators indicator = indicatorMap.get(s);
//					if (indicator != null && indicator.isWordIndicator()) {
//						foundWithObjectIndicator = handleWordIndicator(builder,
//								objects, foundWithObjectIndicator, s, indicator);
//					}
//					// else do nothing
//
//				}
//			}			
//		}
//
//		previousStream = builder.build();
//		return builder.build();
//	}
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
		return dictionary.get(word);
	}

	@Override
	protected TextIndicator getIndicator(String input) {
		return indicatorMap.get(input);
	}
	
	@Override
	protected void startParsing(String text) {
		checkingObjects = objectLimit > 0;
		objects = new LinkedList<TextToken<T>>();
		verb = null;
		withObject = null;
		
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
	
}
