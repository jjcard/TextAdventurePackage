package jjcard.text.game.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ObjectsUtilTest {

	@Test
	public void hashCodeTest() {
		final int prime = 23;
		String s1 = "wfksdlajkf";
		String s2 = "fjajslfjs";
		
		String[] words = {"fdsaf", "fdsafsdafsdfafsdafasdf", "bioeoiwo"};
		
		
		int hashCode = ObjectsUtil.getHashWithStart(1, s1, s2, words);
		
		assertFalse(hashCode == 0);
		
		
		int hashCode2 = 1;
		hashCode2 = hashCode2 * prime + s1.hashCode();
		hashCode2 = hashCode2 * prime + s2.hashCode();
		hashCode2 = hashCode2 * prime + Arrays.hashCode(words);
		
		assertEquals(hashCode2, hashCode);
	}

}
