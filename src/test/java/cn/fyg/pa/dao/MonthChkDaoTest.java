package cn.fyg.pa.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;
import cn.fyg.pa.model.Person;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MonthChkDaoTest {
	
	@Autowired
	private MonthChkDao monthChkDao;
	
	@Autowired
	private MonthChkItemDao monthChkItemDao;
	
	@Test
	public void testNull(){
		
	}
	
//	@Test
	public void testSave(){
		MonthChk monthChk=new MonthChk();
		monthChk.setMonth(2000L);
		MonthChkItem item1=new MonthChkItem();
		MonthChkItem item2=new MonthChkItem();
		item2.setSn(123456L);
		item1.setMonthChk(monthChk);
		item2.setMonthChk(monthChk);
		monthChk.getMonthChkItems().add(item1);
		monthChk.getMonthChkItems().add(item2);
		monthChkDao.save(monthChk);
		
		MonthChk monthChkNew=monthChkDao.find(1L);
		
		
		monthChkNew.getMonthChkItems().clear();
		MonthChkItem item2update=new MonthChkItem();
		item2update.setId(2L);
		item2update.setTask("update2");
		item2update.setMonthChk(monthChkNew);
		monthChkNew.getMonthChkItems().add(item2update);
		MonthChkItem item3=new MonthChkItem();
		item3.setMonthChk(monthChkNew);
		monthChkNew.getMonthChkItems().add(item3);
		
		monthChkDao.save(monthChkNew);
		
		MonthChk result=monthChkDao.find(1L);
	}
	
//	@Test
	public void testRemove(){
		MonthChk monthChk=new MonthChk();
		MonthChkItem item=new MonthChkItem();
		monthChk.getMonthChkItems().add(item);
		monthChkDao.save(monthChk);
		
		monthChkDao.remove(monthChk);
		MonthChkItem getItem=monthChkItemDao.find(1L);
		System.out.println(getItem);
	}
	
//	@Test
	public void testExistMonthChk(){
		MonthChk monthChk=new MonthChk();
		monthChk.setYear(2012L);
		monthChk.setMonth(2L);
		monthChkDao.save(monthChk);
		
//		MonthChk monthChkNew=monthChkDao.getMonthChk(new Person(), 2012L, 2L);
		
	//	Assert.assertEquals(1, monthChkNew.getId().intValue());
	}
}
