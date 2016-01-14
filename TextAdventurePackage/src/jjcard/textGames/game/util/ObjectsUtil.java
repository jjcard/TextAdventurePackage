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
