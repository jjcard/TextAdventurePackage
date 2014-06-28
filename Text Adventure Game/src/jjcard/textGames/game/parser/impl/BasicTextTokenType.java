package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;

public enum BasicTextTokenType implements ITextTokenType {
	//objects
	ENEMY(true), WEAPON(true), ARMOR(true), ITEM(true), PLAYER(true), WORDS(true), NPC(true), INVENTORY(true), UNKOWN,
	
	//verbs
	 TALK(false), LOOK(false), GET(false), MOVE(false), LOOT(false), EQUIP(false), UNEQUIP(false), SAVE(false), QUIT(false), DROP(false);
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
