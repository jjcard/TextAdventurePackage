package jjcard.text.game.battle.impl;

import java.util.function.BiFunction;

import jjcard.text.game.IMob;
import jjcard.text.game.battle.IBattleSystem;

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
		this.onLive = (a, b, c)->{};
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

}
