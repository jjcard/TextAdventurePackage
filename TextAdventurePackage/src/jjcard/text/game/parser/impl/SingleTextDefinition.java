package jjcard.text.game.parser.impl;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.text.game.parser.ITextTokenType;
import jjcard.text.game.util.ObjectsUtil;
/**
 * Version of ITextDefinition that only has one standard name.
 *
 * @param <T>
 */
public class SingleTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {

	@JsonProperty("standard")
	private final String name;
	@JsonCreator
	public SingleTextDefinition(@JsonProperty("type")T type, @JsonProperty("standard")final String name) {
		super(type);
		this.name = name;
	}

	@Override
	public String getStandardToken(final String token) {
		return name;
	}
	
	public static <T extends ITextTokenType> SingleTextDefinition<T> getInstance(T type, final String name){
		return new SingleTextDefinition<>(type, name);
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
			return Objects.equals(name, other.name);
		}
		return false;
	}
	public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(), ObjectsUtil.DEFAULT_PRIME, name);
	}

}
