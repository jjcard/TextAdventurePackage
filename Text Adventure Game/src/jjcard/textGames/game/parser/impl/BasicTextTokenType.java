package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.impl.TextDictionaryFileUtil.ValueConvertor;

public enum BasicTextTokenType implements ITextTokenType {
	//objects
	ENEMY(true), WEAPON(true), ARMOR(true), ITEM(true), PLAYER(true), WORDS(true), NPC(true), INVENTORY(true, "inventory"), DIRECTION(true), LOCATION(true), UNKOWN,
	
	//verbs
	 TALK(false, "talk"), LOOK(false, "look"), GET(false, "get"), MOVE(false, "move"), LOOT(false, "loot"), EQUIP(false, "equip"), UNEQUIP(false, "unequip"), 
	 SAVE(false, "save"), QUIT(false, "quit"), DROP(false, "drop"), ATTACK(false, "attack"), INFO(false);
	
	
	//attributes
	private final boolean isObject;
	private final String[] defaultWords;
	
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
	public static class BasicTextTokenTypeConverter implements ValueConvertor<BasicTextTokenType>{
		@Override
		public BasicTextTokenType valueOf(String string) {
			return BasicTextTokenType.valueOf(string);
		}
		
	}
}
