package jjcard.textGames.game;

public class LocationUtil {


	
	public Location goDirection(Location l, String dir){
		if (l.containsExit(dir)){
			return l.getExitLocation(dir);
		}
			return null;
	}


	

}
