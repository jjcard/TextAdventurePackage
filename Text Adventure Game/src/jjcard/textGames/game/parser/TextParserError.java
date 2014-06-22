package jjcard.textGames.game.parser;

public enum TextParserError {

	NO_OBJECTS(0, ""), NO_VERB(1, ""), TOO_MANY_VERBS(2, ""), TOO_MANY_OBJECTS(3, ""), TOO_MANY_WITH_OBJECTS(4, "");
	
	private final int code;
	private final String description;
	
	private TextParserError(int code, String description){
		this.code = code;
		this.description = description;
	}
	public int getCode(){
		return code;
	}
	public String getDescription(){
		return description;
	}
}
