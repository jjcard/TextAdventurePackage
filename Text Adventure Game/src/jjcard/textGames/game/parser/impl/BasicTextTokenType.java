package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;

public enum BasicTextTokenType implements ITextTokenType {
	//objects
	ENEMY(true), WEAPON(true), ARMOR(true), ITEM(true), PLAYER(true), WORDS(true), NPC(true), INVENTORY(true, "inventory"), DIRECTION(true), UNKOWN,
	
	//verbs
	 TALK(false, "talk"), LOOK(false, "look"), GET(false, "get"), MOVE(false, "move"), LOOT(false, "loot"), EQUIP(false, "equip"), UNEQUIP(false, "unequip"), SAVE(false, "save"), QUIT(false, "quit"), DROP(false, "drop"), ATTACK(false, "attack");
	
	
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
