package jjcard.textGames.game.parser.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.textGames.game.parser.ITextTokenType;
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

}
