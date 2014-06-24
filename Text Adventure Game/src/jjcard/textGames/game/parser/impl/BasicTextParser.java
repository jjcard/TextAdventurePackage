package jjcard.textGames.game.parser.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.TextParserError;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.TextTokenStream.TextTokenStreamBuilder;

public class BasicTextParser<T extends Enum<T> & ITextTokenType> implements ITextParser<T> {
	/**
	 * Indicators
	 */
	public static enum TextIndicators{
		/**
		 * Indicator meaning objects after are now 'with' objects
		 */
		WITH_INDICATOR, 
		/**
		 * Indicator meaning an previous object
		 */
		/**
		 * Indicator meaning to repeat the previous command
		 */
		IT_INDICATOR, REPEAT_INDICATOR;
	}

	private ITextDictionary<T> dictionary;
	private static final Pattern splitPattern = Pattern.compile("[\\s]+");
	private int objectLimit = 10;
	
	private TextTokenStream<T> previousStream;
	private Map<String, TextIndicators> indicatorMap;
		
	
	
	BasicTextParser(ITextDictionary<T> dictionary){
		this.dictionary = dictionary;
	}
	@Override
	public TextTokenStream<T> parseText(String text) {
		
		
		TextTokenStreamBuilder<T> builder = new TextTokenStreamBuilder<>();
		String[] words = splitPattern.split(text);
		
		List<TextToken<T>> objects = new LinkedList<TextToken<T>>();
		TextToken<T> verb = null;
		TextToken<T> withObject = null;
		
		//flag to use one a 'with' indicator has been found.
		boolean foundWithObjectIndicator = false;
		
		boolean checkingObjects = objectLimit > 0;
		
		
		T type;
		for (String s: words){
			type = dictionary.get(s);
			if (type != null){
				//has a type defined in the dictionary
				TextToken<T> token = new TextToken<T>(s, type);
				if (token.isObject()){
					if (foundWithObjectIndicator){
						withObject = handleWithObject(builder, withObject,
								token);
					} else {
						handleObject(builder, objects, checkingObjects, token);						
					}

				} else if (token.isVerb()){
					verb = handleVerb(builder, verb, token);
				}
			} else {
				//not a dictionary word.
				TextIndicators indicator = indicatorMap.get(s);
				if (indicator != null){
					switch(indicator){
					case WITH_INDICATOR:
						foundWithObjectIndicator = true;
						break;
					case IT_INDICATOR:
						handleItIndicator(builder, objects, s);
						break;
					case REPEAT_INDICATOR:
						handleRepeatIndicator(builder, s);
						break;
					}
				}
				//else do nothing
				
			}
		}
		previousStream = builder.build();
		return builder.build();
	}
	protected void handleRepeatIndicator(TextTokenStreamBuilder<T> builder,
			String s) {
		// TODO Auto-generated method stub
		
	}
	protected void handleItIndicator(TextTokenStreamBuilder<T> builder,
			List<TextToken<T>> objects, String s) {
		// TODO Auto-generated method stub
		
	}
	protected TextToken<T> handleWithObject(TextTokenStreamBuilder<T> builder,
			TextToken<T> withObject, TextToken<T> token) {
		if (withObject != null){
			builder.addError(TextParserError.TOO_MANY_WITH_OBJECTS);
		} else {
			withObject = token;
		}
		return withObject;
	}
	protected void handleObject(TextTokenStreamBuilder<T> builder,
			List<TextToken<T>> objects, boolean checkingObjects,
			TextToken<T> token) {
		objects.add(token);
		if (checkingObjects && objects.size() > objectLimit){
			builder.addError(TextParserError.TOO_MANY_OBJECTS);
		}
	}
	protected TextToken<T> handleVerb(TextTokenStreamBuilder<T> builder,
			TextToken<T> verb, TextToken<T> token) {
		if (verb != null){
			//already has verb
			builder.addError(TextParserError.TOO_MANY_VERBS);
		} else {
			verb = token;
		}
		return verb;
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


	public void setIndicatorMap(Map<String, TextIndicators> indicatorMap){
		this.indicatorMap = indicatorMap;
	}
	
}
