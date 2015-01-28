package jjcard.textGames.game.parser.impl;

import java.util.Map;
import java.util.TreeMap;

import jjcard.textGames.game.parser.ITextTokenType;

public class MappedTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {

	private Map<String, String> standerizedMap;
	public MappedTextDefinition(T type){
		super(type);
		this.standerizedMap = new TreeMap<>();
	}
	public MappedTextDefinition(T type, Map<String, String> standarizedMap ) {
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

}
