package jjcard.textGames.game.parser;

/**
 * A Class for containing the parsed Token.
 *
 */
public class TextToken<T extends ITextTokenType> implements ITextTokenType{

	private String token;
	
	private T type;
	
	public TextToken(String token, T type){
		this.token = token;
		this.type = type;
	}
	public String getToken(){
		return token;
	}
	public T getType(){
		return type;
	}
	
	public boolean isObject(){
		return type.isObject();
	}
	public boolean isVerb(){
		return type.isVerb();
	}
	@Override
	public String[] defaultWords() {
		return type.defaultWords();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		if (o instanceof TextToken){
			return ((TextToken) o).type.equals(this.type) && ((TextToken) o).token.equals(this.token);
		} else {
			return false;
		}
	}
}
