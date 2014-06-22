package jjcard.textGames.game.parser;

import java.util.List;

/**
 * 
 * A class to hold to result from the parser in a way easy for the game to use.
 *
 * @param <T>
 */
public class TextTokenStream<T extends Enum<T> & ITextTokenType> {

	private List<TextToken<T>> objects;
	
	private TextToken<T> verb;
	
	private TextToken<T> withObject;
	
	public TextTokenStream(TextToken<T> verb, List<TextToken<T>> objects){
		if (objects == null){
			throw new NullPointerException("Objects List must be non null");
		}
		this.objects = objects;
		this.verb = verb;
	}
	public TextTokenStream(TextToken<T> verb, List<TextToken<T>> objects, TextToken<T> withObject){
		if (objects == null){
			throw new NullPointerException("Objects List must be non null");
		}
		this.objects = objects;
		this.verb = verb;
		this.withObject = withObject;
	}
	/**
	 * Returns true if the TextTokenStream has a object token in the withObject field.
	 * @return
	 */
	public boolean hasWithObject(){
		return withObject != null;
	}
	
	public List<TextToken<T>> getObjects(){
		return objects;
	}
	public TextToken<T> getVerb(){
		return verb;
	}
	public TextToken<T> getWithObject(){
		return withObject;
	}
	/**
	 * returns true if there is an object token in the objects list.
	 * @return
	 */
	public boolean hasObject(){
		return objects.size() > 0;
	}
	/**
	 * Returns the first Object token if the list if there is one, null otherwise.
	 * @return
	 */
	public TextToken<T> getFirstObject(){
		return hasObject()? objects.get(0): null;
	}

}
