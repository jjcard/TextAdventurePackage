package jjcard.text.game.battle;

import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
/**
 * System for attacked, defending against an attack
 */
@Experimental
@FunctionalInterface
public interface IBattleSystem<R> {
	/**
	 * @param attacker
	 * @param defender
	 * @return the damage done
	 */
//	@Experimental
	public R attackMob(IMob attacker, IMob defender);

}
