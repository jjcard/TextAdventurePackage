package jjcard.text.game.events.impl;

import jjcard.text.game.events.ITextEvent;

public class SimpleTextEvent implements ITextEvent{

	private final String key;
	private final String commandKey;
	

	public SimpleTextEvent(String key, String commandKey){
		this.key = key;
		this.commandKey = commandKey;
	}
	public String getCommandKey() {
		return commandKey;
	}
	public String getKey(){
		return key;
	}
	
}
