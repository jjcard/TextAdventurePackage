package jjcard.textGames.game.parser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.ITextTokenType;

public class TextDictionaryFileUtil {
	public static final String pairPattern = "=";
	public static final String COMMENT_INDICATOR = "#";
	public static interface ValueConvertor<T extends ITextTokenType>{
		public T valueOf(String string);
	}

	private TextDictionaryFileUtil(){
		//all static methods
	}
	/**
	 * Parses the file using the given ValueConvertor to turn Strings into T. The Fields are read as pairs, one per line, separated by a '='. 
	 * @param converter
	 * @param fileLocation
	 * @return
	 * @throws IOException
	 */
	public static <T extends ITextTokenType> ITextDictionary<T> loadDictionaryFromFile(ValueConvertor<T> converter, String fileLocation) throws IOException{
		return loadDictionaryFromFile(converter, new TextDictionary<T>(), new File(fileLocation));
	}
	/**
	 * Parses the file using the given ValueConvertor to turn Strings into T. The Fields are read as pairs, one per line, separated by a '='. 
	 * @param converter
	 * @param dictionary
	 * @param fileLocation
	 * @return
	 * @throws IOException
	 */
	public static <T extends ITextTokenType> ITextDictionary<T> loadDictionaryFromFile(ValueConvertor<T> converter, ITextDictionary<T> dictionary, String fileLocation) throws IOException{
		return loadDictionaryFromFile(converter, dictionary, new File(fileLocation));
	}
	/**
	 * Parses the file using the given ValueConvertor to turn Strings into T. The Fields are read as pairs, one per line, separated by a '='. 
	 * @param converter
	 * @param dictionary
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static <T extends ITextTokenType> ITextDictionary<T> loadDictionaryFromFile(ValueConvertor<T> converter, ITextDictionary<T> dictionary, File file) throws IOException{
		
		if (file.exists()){
			BufferedReader bfr = new BufferedReader(new FileReader(file));
			return loadDictionaryFromFile(converter, dictionary, bfr);
		} else {
			throw new IOException("File cannot be found");
		}
	}
	
	public static <T extends ITextTokenType> ITextDictionary<T> loadDictionaryFromFile(ValueConvertor<T> converter, ITextDictionary<T> dictionary, BufferedReader bfr) throws IOException{
		String line;
		
		while ((line = bfr.readLine()) != null){
			line = line.trim();
			if (!line.startsWith(COMMENT_INDICATOR) && !line.isEmpty()){
				String[] pair = line.split(pairPattern, 2);
				
				if (pair.length < 2){
					//Do something
					System.err.println("line found without full pair");
				} else {
					String key = pair[0].trim();
					
					T value = converter.valueOf(pair[1]);
//					String value = pair[1];
					dictionary.put(key, value);
				}
			}
		}
		bfr.close();
		return dictionary;
	}
//	public static <T extends ITextTokenType> ITextDictionary<T> loadDictionaryWithListFromFile(ValueConvertor<T> converter, ITextDictionary<T> dictionary, File file) throws IOException{
//		if (file.exists()){
//			BufferedReader bfr = new BufferedReader(new FileReader(file));
//			String line;
//			
//			while ((line = bfr.readLine()) != null){
//				line = line.trim();
//				if (!line.startsWith(COMMENT_INDICATOR) && !line.isEmpty()){
//					
//					String[]pair = line.split(pairPattern, 2);
//					if (pair.length < 2){
//						System.err.println("line found without full pair");
//					} else {
//						T value = converter.valueOf(pair[0]);
//						
//						String keys = pair[1].trim();						
//					}
//
//				}
//			}
//			bfr.close();
//			return dictionary;
//		} else {
//			throw new IOException(" File cannot be found");
//		}
//	}
	
}
