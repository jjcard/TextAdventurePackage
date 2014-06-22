package jjcard.textGames.game.parser;

/**
 * 
 * A class to hold to result from the parser in a way easy for the game to use.
 *
 * @param <T>
 */
public class TextTokenStream<T extends Enum<T> & ITextTokenType> {

	private TextToken<T> object;
	
	private TextToken<T> verb;
	
	private TextToken<T> withObject;
	
	public TextTokenStream(TextToken<T> verb, TextToken<T> object){
		this.object = object;
		this.verb = verb;
	}
	public TextTokenStream(TextToken<T> verb, TextToken<T> object, TextToken<T> withObject){
		this.object = object;
		this.verb = verb;
		this.withObject = withObject;
	}
	
	public boolean hasWithObject(){
		return withObject != null;
	}
	
	public TextToken<T> getObject(){
		return object;
	}
	public TextToken<T> getVerb(){
		return verb;
	}
	public TextToken<T> getWithObject(){
		return withObject;
	}

}
