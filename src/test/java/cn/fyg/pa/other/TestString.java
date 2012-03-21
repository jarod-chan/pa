package cn.fyg.pa.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestString {
	
	@Test
	public void testString(){
		String str="xxx";
		String ret = changeStr(str);
//		str = "abcdefg";
		assertTrue(str == ret);
//		assertTrue(new String("abc") == new String("abc"));
		System.out.println(str);
	}
	
	public String changeStr(String str){

		int a;
		String s = new String("abc");
//		str = "a\bcdefg";
		return s;
	}

}
