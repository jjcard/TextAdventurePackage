package jjcard.textGames.game.battle;

import jjcard.textGames.game.IMob;
import jjcard.textGames.game.util.Experimental;
/**
 * System for attacked, defending against an attack
 */
@Experimental
public interface IBattleSystem {
	/**
	 * @param attacker
	 * @param defender
	 * @return the damage done
	 */
	@Experimental
	public int attackMob(IMob attacker, IMob defender);

}
