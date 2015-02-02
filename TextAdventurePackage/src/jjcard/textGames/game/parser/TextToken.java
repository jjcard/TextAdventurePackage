package jjcard.textGames.game.parser;

import jjcard.textGames.game.util.ObjectsUtil;

/**
 * A Class for containing the parsed Token.
 *
 */
public class TextToken<T extends ITextTokenType> implements ITextTokenType{

	private final String token;
	private final String standardToken;
	
	private final T type;
//	public TextToken(String token, T type){
//		this.token = token;
//		this.type = type;
//		this.standardToken = token;
//	}
	public TextToken(String token, String standardToken, T type){
		if (token == null){
			throw new NullPointerException("token");
		}
		if (type == null){
			throw new NullPointerException("type");
		}
		if (standardToken == null){
			throw new NullPointerException("standardToken");
		}
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
	public String toString(){
		return "TextToken=[type=" + type + ", token=" + token + ", standardToken=" + standardToken + "]";
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
	public int hashCode(){
		return ObjectsUtil.getHash(ObjectsUtil.DEFAULT_PRIME, token, standardToken, type);
	}
}
