package jjcard.text.game.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jjcard.text.game.util.ObjectsUtil;

/**
 * Class to hold a list of Patterns and Values and, for a given String, returns
 * the Value for the Pattern the String matches.
 * 
 * @param <S>
 */
public class PatternList<S> {
	private final List<PatternEntry> patternList;

	
	public PatternList(){
		patternList = new LinkedList<>();
	}
	/**
	 * Constructs a PatternList with the same value as the given PatternList
	 * @param list
	 */
	public PatternList(PatternList<S> list){
		patternList = new LinkedList<>(list.patternList);
	}
	/**
	 * Adds the given pattern and value
	 * @param p
	 * @param value
	 */
	public void add(Pattern p, S value) {
		patternList.add(new PatternEntry(p, value));
	}

	/**
	 * Compiles the given string into a pattern and adds it.
	 * @param s
	 * @param value
	 * @throws PatternSyntaxException
	 */
	public void add(String s, S value) throws PatternSyntaxException {
		add(Pattern.compile(s), value);
	}

	/**
	 * Returns the value that connected with the pattern the given text matches.
	 * @param text
	 * @return
	 */
	public S get(String text) {
		S value = null;
		for (PatternEntry entry : patternList) {
			if (entry.matches(text)) {
				value = entry.getValue();
				break;
			}
		}
		return value;
	}

	/**
	 * Class to hold the pattern and the value it returns
	 *
	 */
	private class PatternEntry {
		private final Pattern pattern;
		private final S value;

		public PatternEntry(Pattern p, S value) {
			this.pattern = p;
			this.value = value;
		}

		public S getValue() {
			return value;
		}

		/**
		 * Returns true of text matches Pattern
		 * @param text
		 * @return
		 */
		public boolean matches(String text) {
			return pattern.matcher(text).matches();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		if (o instanceof PatternList){
			return ((PatternList) o).patternList.equals(patternList);
		} else {
			return false;
		}
	}
	public int hashCode(){
		return ObjectsUtil.getHash(ObjectsUtil.DEFAULT_PRIME, patternList);
	}
}
