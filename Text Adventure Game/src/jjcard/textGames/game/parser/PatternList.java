package jjcard.textGames.game.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class to hold a list of Patterns and Values and, for a given String, returns
 * the Value for the Pattern the String matches.
 * 
 * @param <S>
 */
public class PatternList<S> {
	private List<PatternEntry> patternList;

	
	public PatternList(){
		patternList = new LinkedList<PatternEntry>();
	}
	/**
	 * Adds the given pattern and value
	 * @param p
	 * @param value
	 */
	public void add(Pattern p, S value) {
		patternList.add(new PatternEntry(p, value));
	}

	public void add(String s, S value) {
		add(Pattern.compile(s), value);
	}

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

	private class PatternEntry {
		private Pattern pattern;
		private S value;

		public PatternEntry(Pattern p, S value) {
			this.pattern = p;
			this.value = value;
		}

		public S getValue() {
			return value;
		}

		public boolean matches(String text) {
			return pattern.matcher(text).matches();
		}
	}
}
