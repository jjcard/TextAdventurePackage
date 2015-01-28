package jjcard.textGames.game.parser;

public interface ITextDefinition<T extends ITextTokenType> {

	public T getType();
	
	public String getStandardToken(String token);
}
