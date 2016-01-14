package jjcard.textGames.game.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Util class for all objects
 * @author User
 *
 */
public final class ObjectsUtil {

	public static final int DEFAULT_PRIME = 31;
	private ObjectsUtil(){
		super();
	}
	
	public static boolean equals(Object a, Object b){
		if (a == b){
			return true;
		}
		if (a == null|| b == null){
			return false;
		}
		
		return a.equals(b);
	}
	public static boolean notEqual(Object a, Object b){
		return !equals(a, b);
	}
	public static <K, V> boolean notEqualsSize(Map<K, V> a, Map<K, V> b){
		return !equalsSize(a, b);
	}
	public static <K, V> boolean equalsSize(Map<K, V> a, Map<K, V> b){
		if (a == b){
			return true;
		}
		if (a == null|| b == null){
			return false;
		}
		return a.size() == b.size();
	}
	
	public static <K> boolean notEqualsSize(Collection<K> a, Collection<K> b){
		return !equalsSize(a, b);
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
	public static <K> boolean notEqualKeys(Map<String, K> a, Map<String, K> b){
		return !equalKeys(a, b);
	}
	/**
	 * If both maps are non-null, checks if the key set on each is the same
	 * @param a
	 * @param b
	 * @return true if key sets of maps are the same
	 */
	public static <K> boolean equalKeys(Map<String, K> a, Map<String, K> b){
		if (a == b){
			return true;
		}
		if (a == null|| b == null){
			return false;
		}
		return a.keySet().equals(b.keySet());
	}
	public static int getHash(final int prime, Object...objects){
		return getHashWithStart(1, prime, objects);
	}
	public static int getHashWithStart(int startingHash, final int prime, Object...objects){
		int hash = startingHash;
		
		if (objects != null){
			for (Object o: objects){
				if (o instanceof Object[]){
					hash = hash * prime + Arrays.hashCode((Object[])o);
				} else {
					hash = hash * prime + (o == null? 0: o.hashCode());
				}
				
			}
		}
		
		return hash;
	}
	public static int getkeysHash(Map<String, ?> a){
		if (a == null){
			return 0;
		}
		return a.keySet().hashCode();
		
	}
	/**
	 * throws exception if the argument is null with the given string as detail 
	 * @param arg
	 * @param name
	 */
	public static void checkArg(Object arg, final String name){
		if (arg == null){
			throw new NullPointerException(name);
		}
	}
	
}
