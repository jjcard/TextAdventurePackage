package jjcard.text.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Mob;
import jjcard.text.game.impl.Weapon;

public class MobTest {
	private Mob mob;
//	private IMob imob;
	private Mob mob2;
	Weapon weapon;
	
	@Before
	public void setUp(){
		mob = new Mob.Builder().name("Mecha-Goblin").viewDescription("It never wondered at all, just made itself Awesome").validateFields(false)
				.build();
//		imob = mob;
		mob2 = new Mob.Builder().name("Flower").validateFields(false)
				.viewDescription("A giant, able to crush mountains....he got his name when he was smaller.").build();
		
		int cost = 54;
		String weaponName = "sword";
		int weaponAttack = 100;
		boolean hidden = false;
		int critChance = 50;
		int dur = 10;
		weapon = new Weapon.Builder().name(weaponName)
				.cost(cost).attack(weaponAttack).critChance(critChance).durability(dur).hidden(hidden).build();
	}
	@After
	public void tearDown(){
		mob = null;
//		imob = null;
		mob2 = null;
	}
	@Test
	public void equalsAndHashCodeTest(){
		assertFalse(mob.equals(null));
		assertFalse(mob.equals("Mecha-Goblin"));
		assertTrue(mob.equals(mob));
		assertTrue(mob.hashCode() == mob.hashCode());
		
		assertFalse(mob.equals(mob2));
		assertFalse(mob.hashCode() == mob2.hashCode());
		
	}
	@Test
	public void equalsANdHashCodeTest2(){
		Mob mob2 = new Mob.Builder().validateFields(false).name("Mecha-Goblin")
				.viewDescription("It never wondered at all, just made itself Awesome").build();

		
		assertTrue(mob.equals(mob2));
		assertTrue(mob.hashCode() == mob2.hashCode());
		
		mob.setAttack(4);
		assertFalse(mob.equals(mob2));
		assertFalse(mob2.equals(mob));
		assertFalse(mob.hashCode() == mob2.hashCode());
		
		mob2.setAttack(4);
		assertTrue(mob.equals(mob2));
		assertTrue(mob2.equals(mob));
		assertTrue(mob.hashCode() == mob2.hashCode());
		//defense one set, different
		mob.setDefense(4);
		mobsNotEqual(mob, mob2);
		//defense both set, different
		mob2.setDefense(5);
		mobsNotEqual(mob, mob2);
		//defense both set, same
		mob2.setDefense(4);
		mobsEqual(mob, mob2);
		//health, one set, different
		mob.setHealth(17);
		mobsNotEqual(mob, mob2);
		//health, both set, different
		mob2.setHealth(299);
		mobsNotEqual(mob, mob2);
		//health, both set, same
		mob2.setHealth(17);
		mobsEqual(mob, mob2);
		
		
		Item item = new Item.Builder().name("vendor trash").viewDescription("doesn't do anything").build();
		Item item2 = new Item.Builder().name("vendor trash: the sequel").viewDescription("doesn't do anything").build();
		//items, one set, different
		mob.addItem(item);
		mobsNotEqual(mob, mob2);
		//items both set, different
		mob2.addItem(item2);
		mobsNotEqual(mob, mob2);
		//items both set, same
		mob.addItem(item2);
		mob2.addItem(item);
		mobsEqual(mob, mob2);
		
		//weapon, one set, different
		mob.setWeapon(weapon);
		mobsNotEqual(mob, mob2);
		
		//weapon both set, same
		mob2.setWeapon(weapon);
		mobsEqual(mob, mob2);
	}
	private void mobsEqual(Mob mob, Mob mob2){
		assertTrue(mob.equals(mob2));
		assertTrue(mob2.equals(mob));
		assertTrue(mob.hashCode() == mob2.hashCode());
	}
	private void mobsNotEqual(Mob mob, Mob mob2){
		assertFalse(mob.equals(mob2));
		assertFalse(mob2.equals(mob));
		assertFalse(mob.hashCode() == mob2.hashCode());
	}
	@Test
	public void jsontest() throws JsonGenerationException, JsonMappingException, IOException {
		String name = "sword";

		boolean hostile = true;
		int attack = 30;
		int def = 100;
		
		Mob mob = new Mob.Builder().name(name).attack(attack).defense(def).hostile(hostile).weapon(weapon).build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, mob);
		
		Mob in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Mob.class);
		assertEquals(mob, in);
		assertEquals(name, in.getName());
		assertEquals(weapon, in.getWeapon());
		assertEquals(hostile, in.isHostile());

	}
	@Test
	public void initTest_String() {
		Mob mob = new Mob("init_string mob");
		assertNotNull(mob);
		assertEquals("init_string mob", mob.getName());
		assertNull("shouldn't have been constructed", mob.getRoomDescription());
	}
	@Test
	public void getArmourKeyTest() {
		Mob mob = new Mob("armour dummy");
		
		assertNull(mob.getArmour());
		assertNull(mob.getArmourKey());
		
		Armour tutorialArmour = new Armour("kinda crappy armour");
		mob.setArmour(tutorialArmour);
		assertEquals(tutorialArmour, mob.getArmour());
		assertEquals("kinda crappy armour", mob.getArmourKey());
	}
	@Test
	public void setHiddenTest() {
		Mob mob = new Mob("sometimes sneaky goblin");
		
		assertFalse(mob.isHidden());
		
		mob.setHidden(true);
		assertTrue(mob.isHidden());
	}

}
