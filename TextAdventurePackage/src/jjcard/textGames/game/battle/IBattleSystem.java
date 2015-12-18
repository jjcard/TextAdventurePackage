package jjcard.textGames.game.battle;

import jjcard.textGames.game.IMob;
/**
 * System for attacked, defending against an attack
 * Experimental: Subject to Change
 */
public interface IBattleSystem {
	/**
	 * Experimental: Subject to Change
	 * @param attacker
	 * @param defender
	 * @return the damage done
	 */
	public int attackMob(IMob attacker, IMob defender);

}
