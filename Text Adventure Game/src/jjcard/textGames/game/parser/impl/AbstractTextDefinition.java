package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextDefinition;
import jjcard.textGames.game.parser.ITextTokenType;

public abstract class AbstractTextDefinition<T extends ITextTokenType> implements ITextDefinition<T>{
	
	private final T type;
	
	public AbstractTextDefinition(T type){
		if (type == null){
			throw new NullPointerException("type");
		}
		this.type = type;
	}
	public T getType(){
		return type;
	}
	/**
	 * Returns the standardToken for the given token
	 * @param token
	 * @return the standardToken
	 */
	public abstract String getStandardToken(String token);
	
	

}
