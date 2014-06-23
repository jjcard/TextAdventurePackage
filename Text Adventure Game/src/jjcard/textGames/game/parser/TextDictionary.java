package jjcard.textGames.game.parser;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class implementation to hold the Library for use during parsing.
 * Should have all the verbs and objects used in the game, or else parsing may not work correctly.
 *
 */
public class TextDictionary<T extends Enum<T> & ITextTokenType> extends TreeMap<String, T>implements ITextDictionary<T>{

//	private static final Pattern pairPattern = Pattern.compile("=");
//	public static final String COMMENT_INDICATOR = "#";
	/**
	 * 
	 */
	private static final long serialVersionUID = -108288250545705909L;

	public TextDictionary(){
		
	}
	public TextDictionary(Map<String, ? extends T> map){
		super(map);
	}
	public TextDictionary(SortedMap<String, ? extends T> map){
		super(map);
	}

	@Override
	public void putAll(Collection<String> keys, T value) {
		for (String key: keys){
			put(key, value);
		}
		
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
