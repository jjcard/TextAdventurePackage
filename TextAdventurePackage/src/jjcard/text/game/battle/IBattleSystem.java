package jjcard.text.game.battle;

import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
/**
 * Interface for attacked, defending against an attack
 *
 * @param <R> return type
 */
@Experimental
@FunctionalInterface
public interface IBattleSystem<R> {
	/**
	 * @param attacker
	 * @param defender
	 * @return the damage done
	 */
	public R attackMob(IMob attacker, IMob defender);

}
