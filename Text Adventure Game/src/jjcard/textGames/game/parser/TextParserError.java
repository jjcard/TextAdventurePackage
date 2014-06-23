package jjcard.textGames.game.parser;

public enum TextParserError {

	NO_OBJECTS(0, "Text contains no objects."), NO_VERB(1, "Text contains no verb."), 
	TOO_MANY_VERBS(2, "Text contains too many verbs."), TOO_MANY_OBJECTS(3, "Text contains too many objects"), 
	TOO_MANY_WITH_OBJECTS(4, "Text contains too many with objects."),
	OTHER(5, "Other parsing exception.");
	
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
