package cn.fyg.pa.domaintest.valueobject.five;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestFive {
	
	@Resource
	OneManyDao oneManyDao;
	
	@Test
	public void testSave(){
		Five five=new Five();
		five.setId(1L);
		five.setOne("one");
		
		List<FiveMany> many=new ArrayList<FiveMany>();
		FiveMany m1=new FiveMany();
		m1.setId(1L);
		m1.setFive(five);
		m1.setMany("many 1");
		many.add(m1);
		FiveMany m2=new FiveMany();
		m2.setId(2L);
		m2.setFive(five);
		m2.setMany("many 2");
		many.add(m2);
		
		five.setManys(many);
		
		oneManyDao.Save(five);
		
		five=oneManyDao.find(1L);
		Assert.assertEquals(2, five.getManys().size());
	}
	
	
	@Test 
	public void testUpdate(){
		Five five=oneManyDao.find(1L);
		
		List<FiveMany> many=new ArrayList<FiveMany>();
		FiveMany m1=new FiveMany();
		m1.setId(3L);
		m1.setFive(five);
		m1.setMany("many 3");
		many.add(m1);
		FiveMany m2=new FiveMany();
		m2.setId(4L);
		m2.setFive(five);
		m2.setMany("many 4");
		many.add(m2);
		
		five.setManys(many);
		
		oneManyDao.update(five);
		
		five=oneManyDao.find(1L);
		Assert.assertEquals(2, five.getManys().size());
	}

}
