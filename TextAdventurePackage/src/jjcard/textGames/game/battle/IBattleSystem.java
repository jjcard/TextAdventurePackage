package jjcard.textGames.game.battle;

import jjcard.textGames.game.IMob;
/**
 * System for attacked, defending against an attack
 *
 */
public interface IBattleSystem {
	
	public int attackMob(IMob attacker, IMob defender);

}
