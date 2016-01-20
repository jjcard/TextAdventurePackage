package jjcard.text.game.battle;

import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
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
