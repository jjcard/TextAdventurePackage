package jjcard.text.game.events.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;

public class SimpleTextEventListener implements ITextEventListener {

	public static final String DEFAULT_TEXT_LOCATION = "resources"+File.separator+"Strings.txt";
	public static final String COMMENT_INDICATOR = "#";
	public static final String PAIR_INDICATOR = "=";
	private static final Pattern PAIR_PATTERN = Pattern.compile(PAIR_INDICATOR);
	public static final String ESCAPE_CHARACTER = "\\\\";
	private static final Pattern KEY_INPUT_PATTERN = Pattern.compile("[^"+ESCAPE_CHARACTER+"]<KEY>");
	
	
	private final Map<String, String> printMap;
	private PrintStream out = System.out;
	
	public SimpleTextEventListener() throws IOException {
		printMap = new TreeMap<>();
		loadPrintMap(DEFAULT_TEXT_LOCATION);
	}
	public SimpleTextEventListener(PrintStream out) throws IOException {
		printMap = new TreeMap<>();
		loadPrintMap(DEFAULT_TEXT_LOCATION);
		this.out = out;
	}
	public SimpleTextEventListener(String fileLocation) throws IOException{
		printMap = new TreeMap<>();
		loadPrintMap(fileLocation);
	}
	public SimpleTextEventListener(String fileLocation, PrintStream out) throws IOException{
		printMap = new TreeMap<>();
		loadPrintMap(fileLocation);
		this.out = out;
	}
	
	private void loadPrintMap(String fileLocation) throws IOException{
		File file = new File(fileLocation);
		if (file.exists()){
            try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = bfr.readLine()) != null) {
                    if (!line.startsWith(COMMENT_INDICATOR) && !line.isEmpty()) {
                        String[] pair = PAIR_PATTERN.split(line);

                        if (pair.length < 2) {
                            // TODO Do something
                        } else {
                            String key = pair[0].trim();
                            String value = pair[1];
                            printMap.put(key, value);
                        }
                    }
                }
            }
			
		} else {
			throw new IOException("String File not found");
		}
	}
	private String formatString(String value, String keyReplace){
		String formattedString = value;
		if (keyReplace != null){
			formattedString = KEY_INPUT_PATTERN.matcher(value).replaceAll(keyReplace);
		}
		
		return formattedString;
		
		
	}
	@Override
	public boolean handleEvent(ITextEvent event) {
		SimpleTextEvent e  = (SimpleTextEvent) event;
		String key = event.key();
		
		String toPrint = printMap.get(key);
		
		if (toPrint != null){
			toPrint  = formatString(toPrint, e.commandKey());
			out.println(toPrint);
			return true;
		}
		return false;
	}

}
