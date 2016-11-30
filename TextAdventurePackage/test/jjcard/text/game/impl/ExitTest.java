package jjcard.text.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.impl.Exit;
import jjcard.text.game.impl.Location;

public class ExitTest {

	@Test
	public void getWithTest() {
		Location l = new Location("with Loc");
		
		Exit Left = Exit.SOUTH.getWithLocation(l);
		
		Assert.assertNotNull(Left.getLocation());
		Assert.assertEquals(l, Left.getLocation());
		
		
		Exit Left2 = Exit.SOUTH;
		
		assertNull(Left2.getLocation());
		
	}
	@Test
	public void equalsTest(){
		Exit first = new Exit.Builder().name("North").build();
		
		Exit snd = new Exit.Builder().name("North").build();
		
		assertEquals(first, snd);
		
		assertEquals(first.hashCode(), snd.hashCode());
		Location l = new Location("test Loc");
		Exit thrd = new Exit.Builder(first).location(l).build();
		
		
		assertFalse(first.equals(thrd));
		assertFalse(first.hashCode() == thrd.hashCode());
	}
	@Test
	public void builderStaticTest(){
		Location l = new Location("North Loc");
		
		Exit north = Exit.NORTH_BUILD.build();
		north = north.getWithLocation(l);
		
		assertNotNull(north.getLocation());
		Assert.assertEquals(l, north.getLocation());
		
		
		Exit Left2 = Exit.NORTH_BUILD.build();
		
		assertNull(Left2.getLocation());
	}
	@Test
	public void jsonTest() throws JsonGenerationException, JsonMappingException, IOException{
		String name = "fsafsd";
		String locName = "loc1";
		Location loc = new Location(locName);
		Exit exit = new Exit.Builder().name(name).location(loc).hidden(true).build();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, exit);
		
		Exit in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Exit.class);
		
		assertEquals(exit, in);
		assertEquals(loc, in.getLocation());
		assertEquals(name, in.getName());
		assertTrue(in.isHidden());
	}
	
	@Test
	public void getWithLocationAsNameTest(){
		String locName = "locSameNameTest";
		Location loc = new Location(locName);
		Exit exit = Exit.getWithLocationAsName(loc);
		assertNotNull(exit);
		assertNotNull(exit.getLocation());
		assertEquals(loc, exit.getLocation());
		assertEquals(locName, exit.getName());
	}
	@Test
	public void locationAndNameBuilderTest(){
		String locName = "locSameNameTest2";
		Location loc = new Location(locName);
		
		Exit exit = new Exit.Builder().locationAndName(loc).build();
		assertNotNull(exit);
		assertNotNull(exit.getLocation());
		assertEquals(loc, exit.getLocation());
		assertEquals(locName, exit.getName());
	}

}
