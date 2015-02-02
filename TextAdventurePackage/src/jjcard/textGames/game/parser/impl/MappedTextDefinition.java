package jjcard.textGames.game.parser.impl;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.util.ObjectsUtil;

public class MappedTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {
	@JsonProperty("map")
	private final Map<String, String> standerizedMap;
	public MappedTextDefinition(T type){
		super(type);
		this.standerizedMap = new TreeMap<>();
	}
	@JsonCreator
	public MappedTextDefinition(@JsonProperty("type")T type, @JsonProperty("map") Map<String, String> standarizedMap ) {
		super(type);
		this.standerizedMap = standarizedMap;
	}


	public String putEntry(String key, String value){
		return this.standerizedMap.put(key, value);
	}
	@Override
	public String getStandardToken(String token) {
		String returnVal = null;
		if (standerizedMap != null){
			returnVal = standerizedMap.get(token);
		}
		if (returnVal == null){
			returnVal = token;
		}
		return returnVal;
		
	}
	public boolean equals(Object o){
		if (super.equals(o)){
			if (o instanceof MappedTextDefinition<?>) {
				return this.standerizedMap
						.equals(((MappedTextDefinition<?>) o).standerizedMap);
			}
		}
		return false;
		
	}
	public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(), ObjectsUtil.DEFAULT_PRIME, standerizedMap);
	}
	public static <T extends ITextTokenType> MappedTextDefinition<T> getInstance(T type){
		return new MappedTextDefinition<T>(type);
	}
	public static <T extends ITextTokenType> MappedTextDefinition<T> getInstance(T type, Map<String, String> map){
		return new MappedTextDefinition<T>(type, map);
	}

}
