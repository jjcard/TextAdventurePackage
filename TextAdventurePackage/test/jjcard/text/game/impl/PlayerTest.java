package jjcard.text.game.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.impl.Player;
import jjcard.text.game.impl.Weapon;

public class PlayerTest {

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
		
		int xp = 45000;
		int lvl = 10;
		Weapon weapon = new Weapon.Builder().name(weaponName)
				.cost(cost).attack(weaponAttack).critChance(critChance).durability(dur).hidden(hidden).build();
		
		Player player = new Player.Builder().name(name).attack(attack).defense(def).hostile(hostile).xp(xp).level(lvl).weapon(weapon).build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, player);
		
		Player in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Player.class);
		assertEquals(player, in);
		assertEquals(name, in.getName());
		assertEquals(weapon, in.getWeapon());
		assertEquals(hostile, in.isHostile());
		assertEquals(xp, in.getXp());
		assertEquals(lvl, in.getLevel());

	}

}
