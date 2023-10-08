package jjcard.text.game.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Util class for all objects
 *
 */
public final class ObjectsUtil {

	/** To match Arrays.hashCode**/
	public static final int DEFAULT_PRIME = 31;
	private ObjectsUtil(){
		super();//util class
	}
	public static boolean notEqual(Object a, Object b){
		return !Objects.equals(a, b);
	}
	public static <K, V> boolean notEqualsSize(Map<K, V> a, Map<K, V> b){
		return !equalsSize(a, b);
	}
	public static <K, V> boolean equalsSize(Map<K, V> a, Map<K, V> b){
		if (a == b){
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.size() == b.size();
	}

	public static <K> boolean equalsSize(Collection<K> a, Collection<K> b){
		if (a == b){
			return true;
		}
		if (a == null|| b == null){
			return false;
		}
		return a.size() == b.size();
	}
	/**
	 * null safe check for Maps not having equal key sets.
	 * @return true if both maps don't have same key set
	 */
	public static boolean notEqualKeys(Map<String, ?> a, Map<String, ?> b){
		return !equalKeys(a, b);
	}
	/**
	 * If both maps are non-null, checks if the key set on each is the same
	 * @return true if key sets of maps are the same
	 */
	public static boolean equalKeys(Map<String, ?> a, Map<String, ?> b){
		if (a == b){
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.keySet().equals(b.keySet());
	}

	/**
	 * Returns hash of given objects. Hash starts with <code>startingHash</code>, multiplying by <code>prime</code>. Is Null safe.
	 * @param startingHash
	 * @param objects
	 * @return computed hash
	 */
	public static int getHashWithStart(int startingHash, Object...objects){
		int hash = startingHash;
		
		for (Object o : objects) {
			if (o instanceof Object[]) {
				hash = hash * DEFAULT_PRIME + Arrays.hashCode((Object[]) o);
			} else {
				hash = hash * DEFAULT_PRIME + (o == null ? 0 : o.hashCode());
			}
		}
		
		return hash;
	}
	/**
	 * Returns hash of key set of the given map. Null safe.
	 * @param keyMap map to pull key set from
	 * @return int, the Hash of key set
	 */
	public static int getKeysHash(Map<String, ?> keyMap){
		if (keyMap == null){
			return 0;
		}
		return keyMap.keySet().hashCode();
		
	}
	/**
	 * throws exception if the argument is null with the given string as detail 
	 * @param arg
	 * @param name name to put in detail message
	 * @throws IllegalArgumentException if arg is null
	 */
	public static void checkArg(Object arg, final String name) throws IllegalArgumentException{
		if (arg == null){
			throw new IllegalArgumentException(name);
		}
	}
	
	/**
	 * Null safe compare operation. Returns -1 if <i>a</i> is only null, 1 if
	 * <i>b</i> is only null, otherwise compares them.
	 * @return compared value
	 * @see Comparable
	 */
	public static <A extends Comparable<A>> int compareTo(A a, A b){
		if (a == b){
			return 0;
		}
		if (a == null){
			return -1;
		}
		if (b == null){
			return 1;
		}
		return a.compareTo(b);
	}
}
