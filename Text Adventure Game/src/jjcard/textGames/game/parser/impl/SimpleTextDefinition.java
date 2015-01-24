package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;
/**
 * A simple pass-through version of TextDefinition, where there is no standardization.
 *
 * @param <T>
 */
public class SimpleTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {

	public SimpleTextDefinition(T type) {
		super(type);
	}

	@Override
	public String getStandardToken(String token) {
		return token;
	}
	
	public static <T extends ITextTokenType> SimpleTextDefinition<T> getInstance(T type){
		return new SimpleTextDefinition<T>(type);
	}

}
