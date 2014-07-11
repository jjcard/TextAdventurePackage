package jjcard.textGames.game.util;

import java.util.Arrays;


public class EqualsUtil {

	
	private EqualsUtil(){
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
	
	public static int getHash(final int prime, Object...objects){
		return getHash(1, prime, objects);
	}
	public static int getHash(int startingHash, final int prime, Object...objects){
		int hash = startingHash;
		
		if (objects != null){
			for (Object o: objects){
				if (o instanceof Object[]){
					hash = hash * prime + (o == null? 0: Arrays.hashCode((Object[])o));
				} else {
					hash = hash * prime + (o == null? 0: o.hashCode());
				}
				
			}
		}
		
		return hash;
	}
	public static int getHash(final int prime, Object[] ...objectArrays){
		int hash = 1;
		
		if (objectArrays != null){
			for (Object o: objectArrays){
				hash = hash * prime + (o == null? 0: Arrays.hashCode((Object[])o));
			}
		}
		
		return hash;
	}
}
