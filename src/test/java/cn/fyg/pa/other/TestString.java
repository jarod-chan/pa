package cn.fyg.pa.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class TestString {
	
	@Test
	@Ignore
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
	@Ignore
	public void testStringFormat(){
		Long l=213L;
		String str=String.format("%s",l);
		assertEquals("213", str);
	}
	
	@Test
	public void testAndOr(){

//		if(returnFalse()&&returnTrue()){
//			
//		}
		if(returnTrue()||returnFalse()){
			
		}
	}
	
	private boolean returnTrue(){
		System.out.println("true is call");
		return true;
	}
	
	private boolean returnFalse(){
		System.out.println("false is call");
		return false;
	}

}
