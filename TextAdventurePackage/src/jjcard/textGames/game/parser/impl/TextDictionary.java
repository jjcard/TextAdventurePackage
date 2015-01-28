package jjcard.textGames.game.parser.impl;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.parser.ITextDefinition;
import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;

/**
 * Class implementation to hold the Library for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
public class TextDictionary<T extends ITextTokenType> extends TreeMap<String, ITextDefinition<T>>implements ITextDictionary<T>{

//	private static final Pattern pairPattern = Pattern.compile("=");
//	public static final String COMMENT_INDICATOR = "#";
	/**
	 * 
	 */
	private static final long serialVersionUID = -108288250545705909L;
	private boolean automaticCasing = true;

	public TextDictionary(){
		super();
	}
	public TextDictionary(Map<String, ? extends ITextDefinition<T>> map){
		super(map);
	}
	public TextDictionary(SortedMap<String, ? extends ITextDefinition<T>> map){
		super(map);
	}
	/**
	 * Uses the default values to do an initial load to the Dictionary
	 * @param values
	 */
	@SafeVarargs
	public TextDictionary(ITextDefinition<T>...values){
		super();
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
		super();
		if (values != null){
			for (T value: values){
				if (value.defaultWords() != null){
					ITextDefinition<T> definition = new SimpleTextDefinition<T>(value);
					putAll(definition , value.defaultWords());	
				}
				
			}
		}
	}
	/**
	 * For the given IGameElements, adds the standard name for the element to the map plus any alternate names.
	 * @param element
	 * @param value
	 */
	public void putAll(ITextDefinition<T> value, IGameElement... elements){
		for (IGameElement element: elements){
			put(element, value);
		}
	}
	public void putAll(T value, IGameElement... elements){
		for (IGameElement element: elements){
			put(element, new SimpleTextDefinition<T>(value));
		}
	}
	/**
	 * Adds the standard name for the element to the map.
	 * @param element
	 * @param value
	 */
	public void put(IGameElement element, ITextDefinition<T> value){
		put(element.getStandardName(), value);
		
	}
	@Override
	public void putAll(Collection<String> keys, ITextDefinition<T> value) {
		for (String key: keys){
			put(key, value);
		}
		
	}
	public void putAllElements(Collection<IGameElement> keys, ITextDefinition<T> value){
		for (IGameElement g: keys){
			put(g, value);
		}
	}
	public void put(ITextDefinition<T> value, IGameElement element, String...keys){
		put(element, value);
		putAll(value, keys);
	}
	public void putAll(ITextDefinition<T> value, String...keys){
		for (String key: keys){
			put(key, value);
		}
	}
	public void putAll(T value, String...keys){
		ITextDefinition<T> def = new SimpleTextDefinition<T>(value);
		putAll(def, keys);
	}
	public ITextDefinition<T> put(String key, ITextDefinition<T> value){
		return super.put(automaticCasing? key.toLowerCase():key, value);
	}
	public ITextDefinition<T> get(Object key){
		return super.get(automaticCasing? key.toString().toLowerCase(): key);
	}
	@Override
	public void setAutomaticCasing(boolean doAutomaticCasing) {
		automaticCasing = doAutomaticCasing;
		
	}
	
	
	
//	private void loadPrintMap(String fileLocation) throws IOException{
//		File file = new File(fileLocation);
//		if (file.exists()){
//			BufferedReader bfr = new BufferedReader(new FileReader(file));
//			String line;
//			
//			while ((line = bfr.readLine()) != null){
//				if (!line.startsWith(COMMENT_INDICATOR) && !line.isEmpty()){
//					String[] pair = pairPattern.split(line);
//					
//					if (pair.length < 2){
//						//Do something
//					} else {
//						String key = pair[0].trim();
//						
//						T value = T.valueOf( pair[1]);
////						String value = pair[1];
//						this.put(key, value);
//					}
//				}
//			}
//			bfr.close();
//			
//		} else {
//			throw new IOException("String File cannot be found");
//		}
//	}

}
