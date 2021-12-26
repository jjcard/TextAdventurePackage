package jjcard.text.game.parser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextIndicator {

	/**
	 * If true, the indicator can apply to the whole phrase
	 * @return
	 */
    boolean isWholeSentenceIndicator();
	/**
	 * If true, the indicator can be used on individual words
	 * @return
	 */
    boolean isWordIndicator();
}
