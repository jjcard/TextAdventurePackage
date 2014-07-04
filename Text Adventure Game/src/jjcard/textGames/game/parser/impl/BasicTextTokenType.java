package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;

public enum BasicTextTokenType implements ITextTokenType {
	//objects
	ENEMY(true), WEAPON(true), ARMOR(true), ITEM(true), PLAYER(true), WORDS(true), NPC(true), INVENTORY(true), DIRECTION(true), UNKOWN,
	
	//verbs
	 TALK(false), LOOK(false), GET(false), MOVE(false), LOOT(false), EQUIP(false), UNEQUIP(false), SAVE(false), QUIT(false), DROP(false), ATTACK(false);
	
	
	//attributes
	private boolean isObject;
	private String[] defaultWords;
	
	BasicTextTokenType(boolean isObject, String...defaultWords){
		this.isObject = isObject;
		this.defaultWords = defaultWords;
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
	@Override
	public String[] defaultWords() {
		return defaultWords;
	}
}
