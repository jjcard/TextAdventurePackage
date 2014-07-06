package jjcard.textGames.game.util;


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
}
