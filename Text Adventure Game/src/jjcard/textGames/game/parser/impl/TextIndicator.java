package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextIndicator;

/**
 * Indicators
 */
public enum TextIndicator implements ITextIndicator{
	/**
	 * Indicator meaning objects after are now 'with' objects
	 */
	WITH_INDICATOR(false, true), 
	/**
	 * Indicator meaning an previous object
	 */
	/**
	 * Indicator meaning to repeat the previous command
	 */
	IT_INDICATOR(false, true), 
	REPEAT_INDICATOR(true, false);
	
	private final boolean isWholeSentence;
	private final boolean isWord;
	
	private TextIndicator(boolean isWholeSentence, boolean isWord){
		this.isWholeSentence = isWholeSentence;
		this.isWord = isWord;
	}
	@Override
	public boolean isWholeSentenceIndicator() {
		return isWholeSentence;
	}
	@Override
	public boolean isWordIndicator() {
		return isWord;
	}
	
	
}