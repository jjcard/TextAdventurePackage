package jjcard.text.game.dialog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jjcard.text.game.util.Experimental;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
@Experimental
public abstract class DialogResponse {

	@JsonProperty("next")
	private IDialogTree next = null;
	@Experimental
	public boolean hasNextDialogTree(){
		return next != null;
	}
	@Experimental
	public IDialogTree getNextDialogTree(){
		return next;
	}
}
