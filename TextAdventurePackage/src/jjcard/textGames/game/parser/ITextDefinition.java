package jjcard.textGames.game.parser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public interface ITextDefinition<T extends ITextTokenType> {

	public T getType();
	
	public String getStandardToken(String token);
}
