package jjcard.text.game.battle.impl;

import java.util.function.BiFunction;

import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
/**
 * Basic Battle System that implements the onAttack function of the {@link FunctionalBattleSystem}.
 * Damage is calculated as the attacker's {@link IMob#getFullAttack()} - defender's {@link IMob#getFullDefense()}. 
 * This is then subtracted from the defender's health. The return value is the total damage done.
 *
 */
@Experimental
public class BasicBattleSystem extends FunctionalBattleSystem<Integer> {
	private static final BiFunction<IMob, IMob, Integer> ON_ATTACK = (attacker, defender) -> {
		int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
		defender.setHealth(Math.max(defender.getHealth() - damage, 0));
		return damage;
	};

	public BasicBattleSystem(BattleConsumer<Integer> onDeath) {
		super(ON_ATTACK, onDeath);
	}

	public BasicBattleSystem(BattleConsumer<Integer> onDeath, BattleConsumer<Integer> onLive) {
		super(ON_ATTACK, onDeath, onLive);
	}
}
