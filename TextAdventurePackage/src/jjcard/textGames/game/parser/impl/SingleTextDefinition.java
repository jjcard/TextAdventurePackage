package jjcard.textGames.game.parser.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.util.ObjectsUtil;
/**
 * Version of ITextDefinition that only has one standard name.
 * @author jjcard
 *
 * @param <T>
 */
public class SingleTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {

	@JsonProperty("standard")
	private final String standardName;
	@JsonCreator
	public SingleTextDefinition(@JsonProperty("type")T type, @JsonProperty("standard")String standardName) {
		super(type);
		this.standardName = standardName;
	}

	@Override
	public String getStandardToken(String token) {
		return standardName;
	}
	
	public static <T extends ITextTokenType> SingleTextDefinition<T> getInstance(T type, String standardName){
		return new SingleTextDefinition<T>(type, standardName);
	}
	
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (!super.equals(o)){
			return false;
		}
		if (o instanceof SingleTextDefinition<?>){
			SingleTextDefinition<?> other = (SingleTextDefinition<?>) o;
			return ObjectsUtil.equals(standardName, other.standardName);
		}
		return false;
	}
	public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(), ObjectsUtil.DEFAULT_PRIME, standardName);
	}

}
