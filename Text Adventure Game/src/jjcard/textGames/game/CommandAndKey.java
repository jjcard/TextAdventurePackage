package jjcard.textGames.game;

public class CommandAndKey {
	private Commands command;
	private String key;
	
	public CommandAndKey(Commands com){
		command = com;
	}
	public CommandAndKey(Commands com, String keyN){
		command = com;
		key = keyN;
	}
	public Commands getCommand() {
		return command;
	}
	public void setCommand(Commands command) {
		this.command = command;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean hasKey(){
		return key != null && key.length() > 0;
	}
	public boolean equals(Object o){
		if (o instanceof CommandAndKey){
			CommandAndKey ck = (CommandAndKey)o;
			return this.command.equals(ck.getCommand()) && this.key.equals(ck.getKey());
		}
		return false;
	}
	public String toString(){
		return command.toString() + ": " + key;
	}

}
