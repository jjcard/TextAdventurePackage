package jjcard.textGames.game.events.impl;

import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.impl.Commands;
import jjcard.textGames.game.impl.ReturnCom;

public class SimpleTextEvent implements ITextEvent{

	private final Commands command;
	private final ReturnCom returnCom;
	private final String commandKey;
	
	public SimpleTextEvent(Commands command, String commandKey){
		this.command = command;
		this.commandKey = commandKey;
		this.returnCom = null;
	}
	public SimpleTextEvent(ReturnCom returnCom, String commandKey){
		this.returnCom = returnCom;
		this.commandKey = commandKey;
		this.command = null;
	}
	public SimpleTextEvent(Commands command, String commandKey, ReturnCom returnCom){
		this.command = command;
		this.commandKey = commandKey;
		this.returnCom = returnCom;
	}

	public Commands getCommand() {
		return command;
	}

	
	public String getCommandKey() {
		return commandKey;
	}

	public ReturnCom getReturnComand() {
		return returnCom;
	}
	public String getKey(){
		String key = "";
		if (command != null){
			key += command.toString();
		}
		if (returnCom != null){
			key += returnCom.toString();
		}
		
		return key;
	}
	
}
