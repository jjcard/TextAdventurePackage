package jjcard.text.game.parser.impl;

import java.util.Arrays;

import jjcard.text.game.parser.ITextTokenType;

public enum BasicTextTokenType implements ITextTokenType {
	//objects
	ENEMY(true), WEAPON(true), ARMOR(true), ITEM(true), PLAYER(true), WORDS(true), NPC(true), INVENTORY(true, "inventory"), DIRECTION(true), LOCATION(true), 
	ROOM(true, "room"), MONEY(true, "money"), HEALTH(true, "health"), MAX_HEALTH(true, "max health"), UNKOWN,
	
	//verbs
	 TALK(false, "talk", "say"), LOOK(false, "look"), GET(false, "get"), MOVE(false, "move"), LOOT(false, "loot"), EQUIP(false, "equip"), UNEQUIP(false, "unequip"), 
	 SAVE(false, "save"), QUIT(false, "quit"), DROP(false, "drop"), ATTACK(false, "attack"), INFO(false), BUY(false, "buy"), SELL(false, "sell");
	
	
	//attributes
	private final boolean isObject;
	private final String[] defaultWords;
	private final int length;
	
	private BasicTextTokenType(boolean isObject, String...defaultWords){
		this.isObject = isObject;
		this.defaultWords = defaultWords;
		this.length = defaultWords.length;
	}
	private BasicTextTokenType(){
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
		return Arrays.copyOf(defaultWords, length);
	}
}
