package jjcard.text.game.dialog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
public abstract class DialogResponse {

	@JsonProperty("next")
	private IDialogTree next = null;
	
	public boolean hasNextDialogTree(){
		return next != null;
	}
	public IDialogTree getNextDialogTree(){
		return next;
	}
}
