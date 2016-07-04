package jjcard.text.game.dialog;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jjcard.text.game.util.Experimental;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
@Experimental
public interface IDialogChoice {
	
	@Experimental
	public String getDescription();

}
