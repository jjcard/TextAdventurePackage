package jjcard.text.game.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Mob;

public class MobBuilderTest {

	@Test
	public void builderInitTest(){
		Mob.Builder builder = new Mob.Builder();
		assertNotNull(builder);
		assertNotNull(builder.build());
		
	}
	@Test
	public void nameTest(){
		Mob.Builder builder = new Mob.Builder();
		String exp = "experienced one";
		builder.name(exp);
		Mob mob = builder.build();
		assertEquals(exp, mob.getName());
		
		builder.name(exp).name("neophyte");
		
		mob = builder.build();
		assertEquals("neophyte", mob.getName());
	}
	@Test
	public void addItemTest(){
		Mob.Builder builder = new Mob.Builder();
		
		Item item = new Item.Builder().name("Token").cost(1).build();
		builder.addItem(item);
		
		Mob mob = builder.name("Mob").build();
		
		assertTrue(mob.containsItem("Token"));
		assertNotNull(mob.getItem("Token"));
	}

}
