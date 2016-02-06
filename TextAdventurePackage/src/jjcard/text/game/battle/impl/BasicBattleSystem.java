package jjcard.text.game.battle.impl;

import jjcard.text.game.util.Experimental;
@Experimental
public class BasicBattleSystem extends FunctionalBattleSystem<Integer> {
	public BasicBattleSystem(BattleConsumer<Integer> onDeath) {
		super((attacker, defender) -> {
			int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
			defender.setHealth(defender.getHealth() - damage);

			return damage;
		} , onDeath);
	}
	public BasicBattleSystem(BattleConsumer<Integer> onDeath, BattleConsumer<Integer> onLive) {
		super((attacker, defender) -> {
			int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
			defender.setHealth(defender.getHealth() - damage);

			return damage;
		} , onDeath, onLive);
	}
}
