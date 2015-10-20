package jjcard.textGames.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import jjcard.textGames.game.impl.Exit;
import jjcard.textGames.game.impl.Location;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExitTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Location l = new Location();
		
		Exit Left = Exit.SOUTH.getWithLocation(l);
		
		Assert.assertNotNull(Left.getLocation());
		Assert.assertEquals(l, Left.getLocation());
		
		
		Exit Left2 = Exit.SOUTH;
		
		Assert.assertNull(Left2.getLocation());
		
	}
	@Test
	public void builderStaticTest(){
		Location l = new Location();
		
		Exit north = Exit.NORTH_BUILD.build();
		north = north.getWithLocation(l);
		
		Assert.assertNotNull(north.getLocation());
		Assert.assertEquals(l, north.getLocation());
		
		
		Exit Left2 = Exit.NORTH_BUILD.build();
		
		Assert.assertNull(Left2.getLocation());
	}
	@Test
	public void jsonTest() throws JsonGenerationException, JsonMappingException, IOException{
		String name = "fsafsd";
		String locName = "loc1";
		Location loc = new Location(locName);
		Exit exit = new Exit.Builder().standardName(name).location(loc).hidden(true).build();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, exit);
		
		Exit in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Exit.class);
		
		assertEquals(exit, in);
		assertEquals(loc, in.getLocation());
		assertEquals(name, in.getName());
		assertTrue(in.isHidden());
	}

}