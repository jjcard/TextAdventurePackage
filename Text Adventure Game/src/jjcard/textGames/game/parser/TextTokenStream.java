package jjcard.textGames.game.parser;

import java.util.LinkedList;
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

	private List<TextParserError> errors;

	public static class TextTokenStreamBuilder<T extends Enum<T> & ITextTokenType>{
		
		private List<TextToken<T>> objects = new LinkedList<TextToken<T>>();
		private TextToken<T> verb;
		private TextToken<T> withObject;
		private List<TextParserError> errors = new LinkedList<TextParserError>();
		private boolean validateInput = true;
		
		public TextTokenStreamBuilder() {
			
		}
		public TextTokenStreamBuilder<T> verb(TextToken<T> verb){
			this.verb = verb;
			return this;
		}
		public TextTokenStreamBuilder<T> withObject(TextToken<T> withObject){
			this.withObject = withObject;
			return this;
		}
		public TextTokenStreamBuilder<T> objects(List<TextToken<T>> objects){
			if (objects == null){
				objects = new LinkedList<TextToken<T>>();
			}
			this.objects = objects;
			return this;
		}
		public TextTokenStreamBuilder<T> addObject(TextToken<T> object){
			objects.add(object);
			return this;
		}
		public TextTokenStreamBuilder<T> addObjects(List<TextToken<T>> objects){
			if (objects != null){
				this.objects.addAll(objects);
			}
			return this;
		}
		public TextTokenStreamBuilder<T> errors(List<TextParserError> errors){
			if (errors == null){
				errors = new LinkedList<TextParserError>();
			}
			this.errors = errors;
			return this;
		}
		public TextTokenStreamBuilder<T> addError(TextParserError error){
			this.errors.add(error);
			return this;
		}
		/**
		 * Sets the flag to validate the input and add errors to the stream.
		 * It is true by default
		 * @param validateInput
		 * @return
		 */
		public TextTokenStreamBuilder<T> validateInput(boolean validateInput){
			this.validateInput = validateInput;
			return this;
		}
		public TextTokenStream<T> build(){
			return new TextTokenStream<>(verb, objects, withObject, errors, validateInput);
		}
	}
	private TextTokenStream(TextToken<T> verb, List<TextToken<T>> objects,
			TextToken<T> withObject, List<TextParserError> errors, boolean validateInput) {

		this.objects = objects;
		this.verb = verb;
		this.withObject = withObject;
		this.errors = errors;
		
		if (validateInput){
			validateInput();
		}
	}
	private void validateInput(){
		if (objects.isEmpty() && !containsError(TextParserError.NO_OBJECTS)){
			errors.add(TextParserError.NO_OBJECTS);
		}
		if (verb == null && !containsError(TextParserError.NO_VERB)){
			errors.add(TextParserError.NO_VERB);
		}
	}
	/**
	 * Returns true if the TextTokenStream has a object token in the withObject
	 * field.
	 * 
	 * @return
	 */
	public boolean hasWithObject() {
		return withObject != null;
	}

	public List<TextToken<T>> getObjects() {
		return objects;
	}

	public TextToken<T> getVerb() {
		return verb;
	}

	public TextToken<T> getWithObject() {
		return withObject;
	}

	/**
	 * returns true if there is an object token in the objects list.
	 * 
	 * @return
	 */
	public boolean hasObject() {
		return objects.size() > 0;
	}

	/**
	 * Returns the first Object token if the list if there is one, null
	 * otherwise.
	 * 
	 * @return
	 */
	public TextToken<T> getFirstObject() {
		return hasObject() ? objects.get(0) : null;
	}

	public List<TextParserError> getErrors() {
		return errors;
	}

	public boolean containsError(TextParserError e) {
		return errors.contains(e);
	}

}
