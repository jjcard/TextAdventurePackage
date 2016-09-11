package jjcard.text.game.battle.impl;

import java.util.function.BiFunction;

import jjcard.text.game.IMob;
import jjcard.text.game.battle.IBattleSystem;
/**
 * Class for taking in lambdas for dealing with happens on attack, on death, and on living during a battle.
 *
 * @param <R> the return type
 */
public class FunctionalBattleSystem<R> implements IBattleSystem<R> {
	@FunctionalInterface
	public static interface BattleConsumer<T>{
		public void accept(IMob attacker, IMob defender, T battleResult);
	}
	BiFunction<IMob, IMob, R> onAttack;
	BattleConsumer<R> onDeath;
	BattleConsumer<R> onLive;
	
	public FunctionalBattleSystem(BiFunction<IMob, IMob, R> onAttack, BattleConsumer<R> onDeath, BattleConsumer<R> onLive){
		this.onAttack = onAttack;
		this.onDeath = onDeath;
		this.onLive = onLive;
	}
	public FunctionalBattleSystem(BiFunction<IMob, IMob, R> onAttack, BattleConsumer<R> onDeath){
		this.onAttack = onAttack;
		this.onDeath = onDeath;
		this.onLive = getNoOpBattleConsumer();
	}
	@Override
	public R attackMob(IMob attacker, IMob defender) {
		R result = onAttack.apply(attacker, defender);
		if (defender.isDead()){
			onDeath.accept(attacker, defender, result);
		} else {
			onLive.accept(attacker, defender, result);
		}
		return result;

	}
	/**
	 * Gets a BattleConsumer that performs no operations
	 * @return BattleConsumer<R>
	 */
	public static <R> BattleConsumer<R> getNoOpBattleConsumer(){
		return (a, b, c)->{};
	}

}
