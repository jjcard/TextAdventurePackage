package jjcard.textGames.game.parser;

public enum BasicTextTokenType implements ITextTokenType {
	ENEMY(true), TALK(false), LOOK(false), GET(false), DROP(false), WEAPON(true), ARMOR(true), MOVE(false), ITEM(true), PLAYER(true);
	
	private boolean isObject;
	
	BasicTextTokenType(boolean isObject){
		this.isObject = isObject;
	}
	BasicTextTokenType(){
		this(true);
	}
	
	public boolean isObject(){
		return isObject;
	}
	public boolean isVerb(){
		return !isObject;
	}
}
