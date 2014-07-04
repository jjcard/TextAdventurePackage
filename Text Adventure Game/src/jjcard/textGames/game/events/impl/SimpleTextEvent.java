package jjcard.textGames.game.events.impl;

import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.impl.Commands;
import jjcard.textGames.game.impl.ReturnCom;

public class SimpleTextEvent implements ITextEvent{

	private Commands command;
	private ReturnCom returnCom;
	private String commandKey;
	
	public SimpleTextEvent(Commands command, String commandKey){
		this.command = command;
		this.commandKey = commandKey;
	}
	public SimpleTextEvent(ReturnCom returnCom, String commandKey){
		this.returnCom = returnCom;
		this.commandKey = commandKey;
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
