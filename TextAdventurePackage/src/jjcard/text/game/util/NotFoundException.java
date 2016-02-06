package jjcard.text.game.util;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3608450508245539075L;
	
	public NotFoundException(){
		super();
	}
	public NotFoundException(String message){
		super(message);
	}
	public NotFoundException(Throwable cause){
		super(cause);
	}
	public NotFoundException(String message, Throwable cause){
		super(message, cause);
	}

}
