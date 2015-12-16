package jjcard.textGames.game.battle;

import jjcard.textGames.game.IMob;

public class BasicBattleSystem implements IBattleSystem {

	@Override
	public int attackMob(IMob attacker, IMob defender) {
		
		int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
		defender.setHealth(defender.getHealth() - damage);
		
		return damage;

	}

}
