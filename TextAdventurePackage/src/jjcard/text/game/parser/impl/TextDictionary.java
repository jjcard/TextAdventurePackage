package jjcard.text.game.parser.impl;

import static jjcard.text.game.util.ObjectsUtil.checkArg;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jjcard.text.game.IGameElement;
import jjcard.text.game.parser.ITextDefinition;
import jjcard.text.game.parser.ITextDictionary;
import jjcard.text.game.parser.ITextTokenType;

/**
 * Class implementation to hold the Library for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
public class TextDictionary<T extends ITextTokenType> extends TreeMap<String, ITextDefinition<T>>implements ITextDictionary<T>{

	private static final boolean DEFAULT_AUTOMATIC_CASING = true;

	private static final long serialVersionUID = -108288250545705909L;
	private final boolean automaticCasing;
	private Locale locale = Locale.getDefault(); 

	public TextDictionary(){
		this(DEFAULT_AUTOMATIC_CASING);
	}
	public TextDictionary(boolean automaticCasing){
		super();
		this.automaticCasing = automaticCasing;
	}
	public TextDictionary(Map<String, ? extends ITextDefinition<T>> map){
		this(map, DEFAULT_AUTOMATIC_CASING);
	}
	public TextDictionary(Map<String, ? extends ITextDefinition<T>> map, boolean automaticCasing){
		super(map);
		this.automaticCasing = automaticCasing;
	}
	public TextDictionary(SortedMap<String, ? extends ITextDefinition<T>> map){
		this(map, DEFAULT_AUTOMATIC_CASING);
	}
	public TextDictionary(SortedMap<String, ? extends ITextDefinition<T>> map, boolean automaticCasing){
		super(map);
		this.automaticCasing = automaticCasing;
	}
	/**
	 * Uses the default values to do an initial load to the Dictionary
	 * @param values
	 */
	@SafeVarargs
	public TextDictionary(ITextDefinition<T>...values){
		this(DEFAULT_AUTOMATIC_CASING, values);
	}
	/**
	 * Uses the default values to do an initial load to the Dictionary
	 * @param values
	 */
	@SafeVarargs
	public TextDictionary( boolean automaticCasing, ITextDefinition<T>...values){
		super();
		this.automaticCasing = automaticCasing;
		if (values != null){
			for (ITextDefinition<T> value: values){
				if (value.getType().defaultWords() != null){
					putAll(value, value.getType().defaultWords());	
				}
			}
		}
	}	
	@SafeVarargs
	public TextDictionary(T...values){
		this(DEFAULT_AUTOMATIC_CASING, values);
	}
	@SafeVarargs
	public TextDictionary(boolean automaticCasing, T...values){
		super();
		this.automaticCasing = automaticCasing;
		if (values != null){
			for (T value: values){
				if (value.defaultWords() != null){
					ITextDefinition<T> definition = new SimpleTextDefinition<>(value);
					putAll(definition , value.defaultWords());	
				}
				
			}
		}
	}
	/**
	 * Sets the Locale that is used to change the case of the String keys.
	 * @param locale
	 * @throws IllegalArgumentException if locale is null
	 */
	public void setLocale(Locale locale) throws IllegalArgumentException{
		checkArg(locale, "locale");
		this.locale = locale;
	}
	/**
	 * For the given IGameElements, adds the standard name for the element to the map.
	 * @param value
	 * @param elements
	 * @return this
	 */
	public TextDictionary<T> putAll(ITextDefinition<T> value, IGameElement... elements){
		for (IGameElement element: elements){
			put(element, value);
		}
		return this;
	}
	/**
	 * 
	 * @param value
	 * @param elements
	 * @return this
	 */
	public TextDictionary<T> putAll(T value, IGameElement... elements){
		for (IGameElement element: elements){
			put(element, new SimpleTextDefinition<>(value));
		}
		return this;
	}
	/**
	 * Adds the standard name for the element to the map.
	 * @param element
	 * @param value
	 * @return  previous value associated with element's name
	 */
	public ITextDefinition<T> put(IGameElement element, ITextDefinition<T> value){
		return put(element.getName(), value);
		
	}
	@Override
	public void putAll(Collection<String> keys, ITextDefinition<T> value) {
		for (String key: keys){
			put(key, value);
		}
	}
	/**
	 * Set multiple keys to the same value
	 * @param keys
	 * @param value
	 * @return this
	 */
	public TextDictionary<T> putAllElements(Collection<IGameElement> keys, ITextDefinition<T> value){
		for (IGameElement g: keys){
			put(g, value);
		}
		return this;
	}
	/**
	 * 
	 * @param value
	 * @param element
	 * @param keys
	 * @return this
	 */
	public TextDictionary<T> put(ITextDefinition<T> value, IGameElement element, String...keys){
		put(element, value);
		putAll(value, keys);
		return this;
	}
	/**
	 * 
	 * @param value
	 * @param keys
	 * @return this
	 */
	public TextDictionary<T> putAll(ITextDefinition<T> value, String...keys){
		for (String key: keys){
			put(key, value);
		}
		return this;
	}
	/**
	 * 
	 * @param value
	 * @param keys
	 * @return this
	 */
	public TextDictionary<T> putAll(T value, String...keys){
		ITextDefinition<T> def = new SimpleTextDefinition<>(value);
		return putAll(def, keys);
	}
	
	public ITextDefinition<T> put(String key, ITextDefinition<T> value){
		return super.put(automaticCasing? key.toUpperCase(locale):key, value);
	}
	public ITextDefinition<T> get(Object key){
		return super.get(automaticCasing? key.toString().toUpperCase(locale): key);
	}
}
