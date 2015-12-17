package jjcard.textGames.game.battle.impl;

import jjcard.textGames.game.IMob;
import jjcard.textGames.game.battle.IBattleSystem;

public class BasicBattleSystem implements IBattleSystem {

	@Override
	public int attackMob(IMob attacker, IMob defender) {
		
		int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
		defender.setHealth(defender.getHealth() - damage);
		
		return damage;

	}

}
