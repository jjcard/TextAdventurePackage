package jjcard.textGames.game.parser.impl;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;

/**
 * Class implementation to hold the Library for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
public class TextDictionary<T extends ITextTokenType> extends TreeMap<String, T>implements ITextDictionary<T>{

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
	public TextDictionary(Map<String, ? extends T> map){
		super(map);
	}
	public TextDictionary(SortedMap<String, ? extends T> map){
		super(map);
	}
	/**
	 * Uses the default values to do an initial load to the Dictionary
	 * @param values
	 */
	@SafeVarargs
	public TextDictionary(T...values){
		super();
		if (values != null){
			for (T value: values){
				if (value.defaultWords() != null){
					putAll(value, value.defaultWords());	
				}
				
			}
		}
	}

	/**
	 * Adds the standard name for the element to the map plus any alternate names.
	 * @param element
	 * @param value
	 */
	public void put(IGameElement element, T value){
		put(element.getStandardName(), value);
		
		if (element.getAltNames() != null){
			for (String altName: element.getAltNames()){
				put(altName, value);
			}
		}
	}
	@Override
	public void putAll(Collection<String> keys, T value) {
		for (String key: keys){
			put(key, value);
		}
		
	}
	public void putAll(T value, String...keys){
		for (String key: keys){
			put(key, value);
		}
	}
	public T put(String key, T value){
		return super.put(automaticCasing? key.toLowerCase():key, value);
	}
	public T get(Object key){
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
