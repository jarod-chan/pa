package cn.fyg.pa.help;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestString {
	
	@Test
	public void testFormat(){
		String str="[value]";
		String formatstr=String.format("%s d", str);
		assertEquals(str+" d",formatstr);
	}

}
