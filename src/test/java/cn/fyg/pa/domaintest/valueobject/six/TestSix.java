package cn.fyg.pa.domaintest.valueobject.six;

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
public class TestSix {
	
	@Resource
	SixDao sixDao;
	
	@Test
	public void testSave(){
		Six six=new Six();
		six.setId(1L);
		six.setOne("one");
		
		List<SixMany> many=new ArrayList<SixMany>();
		SixMany m1=new SixMany();
		m1.setId(1L);
		m1.setMany("many 1");
		many.add(m1);
		SixMany m2=new SixMany();
		m2.setId(2L);
		m2.setMany("many 2");
		many.add(m2);
		
		six.setManys(many);
		
		sixDao.Save(six);
		
		six=sixDao.find(1L);
		Assert.assertEquals(2, six.getManys().size());
	}
	
	
	@Test 
	public void testUpdate(){
		Six six=sixDao.find(1L);
		
		List<SixMany> many=new ArrayList<SixMany>();
		SixMany m1=new SixMany();
		m1.setId(3L);
		m1.setMany("many 3");
		many.add(m1);
		SixMany m2=new SixMany();
		m2.setId(4L);
		m2.setMany("many 4");
		many.add(m2);
		
		six.setManys(many);
		
		sixDao.update(six);
		
		six=sixDao.find(1L);
		Assert.assertEquals(2, six.getManys().size());
	}

}
