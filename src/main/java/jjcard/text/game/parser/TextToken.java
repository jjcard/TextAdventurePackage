package jjcard.text.game.parser;

import java.util.Objects;

import static jjcard.text.game.util.ObjectsUtil.checkArg;

/**
 * A Class for containing the parsed Token. Includes to original token, the
 * standardized token, and the ITextTokenType type.
 *
 */
public class TextToken<T extends ITextTokenType> implements ITextTokenType{

	private final String token;
	private final String standardToken;
	
	private final T type;

	/**
	 * 
	 * @param token
	 * @param standardToken
	 * @param type
	 * @throws IllegalArgumentException if token, standardToken, or type is null
	 */
	public TextToken(String token, String standardToken, T type) throws IllegalArgumentException{
		checkArg(token, "token");
		checkArg(type, "type");
		checkArg(standardToken, "standardToken");
		this.token = token;
		this.type = type;
		this.standardToken = standardToken;
	}
	/**
	 * Returns the unprocessed Token taken from the input
	 * @return
	 */
	public String getToken(){
		return token;
	}
	/**
	 * Returns the Type of the token
	 * @return
	 */
	public T getType(){
		return type;
	}
	/**
	 * Returns the standardized form of the Token
	 * @return
	 */
	public String getStandardToken(){
		return standardToken;
	}
	
	@Override
    public boolean isObject(){
		return type.isObject();
	}
	@Override
    public boolean isVerb(){
		return type.isVerb();
	}
	@Override
	public String[] defaultWords() {
		return type.defaultWords();
	}
	@Override
    public String toString(){
		return "TextToken=[type=" + type + ", token=" + token + ", standardToken=" + standardToken + "]";
	}
	@Override
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
	@Override
    public int hashCode(){
		return Objects.hash(token, standardToken, type);
	}
}
