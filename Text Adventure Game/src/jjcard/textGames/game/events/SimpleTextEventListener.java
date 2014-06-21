package jjcard.textGames.game.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class SimpleTextEventListener implements ITextEventListener {

	public  static final String defaultTextLocation = "resources"+File.separator+"Strings.txt";
	public static final String COMMENT_INDICATOR = "#";
	private static final Pattern pairPattern = Pattern.compile("=");
	private static final String ESCAPE_CHARACTER = "\\\\";
	private static final Pattern keyInputPattern = Pattern.compile("[^"+ESCAPE_CHARACTER+"]<KEY>");
	
	
	private Map<String, String> printMap;
	private PrintStream out = System.out;
	
	public SimpleTextEventListener() throws IOException{
		printMap = new TreeMap<String, String>();
		loadPrintMap(defaultTextLocation);
	}
	public SimpleTextEventListener(PrintStream out) throws IOException{
		printMap = new TreeMap<String, String>();
		loadPrintMap(defaultTextLocation);
		this.out = out;
	}
	public SimpleTextEventListener(String fileLocation) throws IOException{
		printMap = new TreeMap<String, String>();
		loadPrintMap(fileLocation);
	}
	public SimpleTextEventListener(String fileLocation, PrintStream out) throws IOException{
		printMap = new TreeMap<String, String>();
		loadPrintMap(fileLocation);
		this.out = out;
	}
	
	private void loadPrintMap(String fileLocation) throws IOException{
		File file = new File(fileLocation);
		if (file.exists()){
			BufferedReader bfr = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = bfr.readLine()) != null){
				if (!line.startsWith(COMMENT_INDICATOR) && !line.isEmpty()){
					String[] pair = pairPattern.split(line);
					
					if (pair.length < 2){
						//Do something
					} else {
						String key = pair[0].trim();
						String value = pair[1];
						printMap.put(key, value);
					}
				}
			}
			bfr.close();
			
		} else {
			throw new IOException("String File cannot be found");
		}
	}
	private String formatString(String value, String keyReplace){
		String formatedString = value;
		if (keyReplace != null){
			keyInputPattern.matcher(value).replaceAll(keyReplace);	
		}
		
		return formatedString;
		
		
	}
	@Override
	public boolean handleEvent(ITextEvent event) {
		SimpleTextEvent e  = (SimpleTextEvent) event;
		String key = e.getKey();
		
		String toPrint = printMap.get(key);
		
		if (toPrint != null){
			toPrint  = formatString(toPrint, e.getCommandKey());
			out.println(toPrint);
			return true;
		}
		return false;
	}

}
