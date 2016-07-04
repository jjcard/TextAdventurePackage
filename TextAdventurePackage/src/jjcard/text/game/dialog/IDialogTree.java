package jjcard.text.game.dialog;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jjcard.text.game.util.Experimental;
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
@Experimental
public interface IDialogTree {
	@Experimental
	public List<IDialogChoice> getDialogChoices();
	

}
