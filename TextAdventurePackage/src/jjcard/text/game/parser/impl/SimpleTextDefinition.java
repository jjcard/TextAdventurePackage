package jjcard.text.game.parser.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.text.game.parser.ITextTokenType;

/**
 * A simple pass-through version of TextDefinition, where there is no
 * standardization.
 *
 * @param <T>
 */
public class SimpleTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {
	@JsonCreator
	public SimpleTextDefinition(@JsonProperty("type") T type) {
		super(type);
	}

	@Override
	public String getStandardToken(String token) {
		return token;
	}

	public static <T extends ITextTokenType> SimpleTextDefinition<T> getInstance(T type) {
		return new SimpleTextDefinition<>(type);
	}

}
