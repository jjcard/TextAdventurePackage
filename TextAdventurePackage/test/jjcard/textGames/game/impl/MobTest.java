package jjcard.textGames.game.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobTest {

	@Test
	public void jsontest() throws JsonGenerationException, JsonMappingException, IOException {
		String name = "sword";

		boolean hostile = true;
		int attack = 30;
		int def = 100;
		
		int cost = 54;
		String weaponName = "sword";
		int weaponAttack = 100;
		boolean hidden = false;
		int critChance = 50;
		int dur = 10;
		Weapon weapon = new Weapon.WeaponBuilder().standardName(weaponName)
				.cost(cost).attack(weaponAttack).critChance(critChance).durability(dur).hidden(hidden).build();
		
		Mob mob = new Mob.MobBuilder().standardName(name).attack(attack).defense(def).hostile(hostile).weapon(weapon).build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, mob);
		
		Mob in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Mob.class);
		assertEquals(mob, in);
		assertEquals(name, in.getStandardName());
		assertEquals(weapon, in.getWeapon());
		assertEquals(hostile, in.isHostile());

	}

}
