package jjcard.textGames.game.impl;

import jjcard.textGames.game.impl.Exit;
import jjcard.textGames.game.impl.Location;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}
