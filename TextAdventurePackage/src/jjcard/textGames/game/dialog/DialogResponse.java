package jjcard.textGames.game.dialog;

public abstract class DialogResponse {

	private DialogTree next = null;
	
	public boolean hasNextDialogTree(){
		return next != null;
	}
	public DialogTree getNextDialogTree(){
		return next;
	}
}
