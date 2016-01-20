package jjcard.text.game.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

import jjcard.text.game.util.ObjectsUtil;

public class ObjectsUtilTest {

	@Test
	public void hashCodeTest() {
		final int prime = 23;
		String s1 = "wfksdlajkf";
		String s2 = "fjajslfjs";
		
		String[] words = {"fdsaf", "fdsafsdafsdfafsdafasdf", "bioeoiwo"};
		
		
		int hashCode = ObjectsUtil.getHash(prime, s1, s2, words);
		
		assertFalse(hashCode == 0);
		
		
		int hashCode2 = 1;
		hashCode2 = hashCode2 * prime + s1.hashCode();
		hashCode2 = hashCode2 * prime + s2.hashCode();
		hashCode2 = hashCode2 * prime + Arrays.hashCode(words);
		
		assertEquals(hashCode2, hashCode);
	}

}
