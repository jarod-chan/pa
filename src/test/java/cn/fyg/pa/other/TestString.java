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
	@Ignore
	public void testAndOr(){

		if(returnFalse()&&returnTrue()){
			
		}
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
	
	@Test
	public void testLong(){
		Long year=2011L;
		Long month=11L;
		
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		
		assertEquals(2011, year.intValue());
		assertEquals(12, month.intValue());
		
		year=2011L;
		month=12L;
		
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		
		assertEquals(2012, year.intValue());
		assertEquals(1, month.intValue());
		
		year=2011L;
		month=3L;
		
		month=month+1L;
		if(month.intValue()>12){
			year=year+1L;
			month=1L;
		}
		
		assertEquals(2011, year.intValue());
		assertEquals(4, month.intValue());
	}

}
