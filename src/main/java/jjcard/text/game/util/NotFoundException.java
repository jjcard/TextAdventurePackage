package jjcard.text.game.util;

import java.io.Serial;

public class NotFoundException extends Exception {

	@Serial
	private static final long serialVersionUID = 3608450508245539075L;
	
	public NotFoundException(){
		super();
	}
	public NotFoundException(String message){
		super(message);
	}

}
