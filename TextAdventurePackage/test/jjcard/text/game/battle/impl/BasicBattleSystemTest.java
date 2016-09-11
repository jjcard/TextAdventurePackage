package jjcard.text.game.battle.impl;
import static org.junit.Assert.*;

import org.junit.Test;

import jjcard.text.game.impl.Mob;

public class BasicBattleSystemTest {

	@Test
	public void equalAttackDefenseTest() {
		BasicBattleSystem fixture = new BasicBattleSystem(FunctionalBattleSystem.getNoOpBattleConsumer());
		
		Mob attacker = new Mob.Builder().health(10).attack(3).build();
		
		Mob defender = new Mob.Builder().health(10).defense(3).build();
		
		assertEquals(10, defender.getHealth());
		
		int damageOut = fixture.attackMob(attacker, defender);
		assertEquals(0, damageOut);
		assertEquals(10, defender.getHealth());
	}
	@Test
	public void overKillTest(){
		BasicBattleSystem fixture = new BasicBattleSystem(FunctionalBattleSystem.getNoOpBattleConsumer());
		
		Mob attacker = new Mob.Builder().health(10).attack(10000).build();
		
		Mob defender = new Mob.Builder().health(10).defense(10).build();
		
		assertEquals(10, defender.getHealth());
		
		int damageOut = fixture.attackMob(attacker, defender);
		assertEquals(9990, damageOut);
		assertEquals(0, defender.getHealth());
	}

}
