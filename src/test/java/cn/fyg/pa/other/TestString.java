package cn.fyg.pa.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestString {
	
	@Test
	public void testString(){
		String str="xxx";
		String ret = changeStr(str);
		assertTrue(str == ret);
		System.out.println(str);
	}
	
	public String changeStr(String str){
		str = "xxx";
		return str;
	}
	
	@Test
	public void testStringFormat(){
		Long l=213L;
		String str=String.format("%s",l);
		assertEquals("213", str);
	}

}
