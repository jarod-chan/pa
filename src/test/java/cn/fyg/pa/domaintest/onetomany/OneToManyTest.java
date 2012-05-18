package cn.fyg.pa.domaintest.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *测试一对多时，用多的一方关联保存少的一方
 *
 */
@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToManyTest {
	
	@Resource
	OneToManyDao dao;

	@Test
	public void testSave(){
		
		One one=new One();
		one.setName("o1");
		
		List<Many> list = new ArrayList<Many>();
		Many m1=new Many();
		m1.setId(1L);
		m1.setName("m1");
		m1.setType("t1");
		m1.setOne(one);
		list.add(m1);
		Many m2=new Many();
		m2.setId(2L);
		m2.setName("m2");
		m2.setType("t2");
		m2.setOne(one);
		list.add(m2);
		Many m3=new Many();
		m3.setId(3L);
		m3.setName("m3");
		m3.setType("t3");
		m3.setOne(one);
		list.add(m3);
				
	    dao.saveMany(list);
		
	    One returnOne=dao.findFirst();
	    
	    System.out.println(returnOne);
		
	}
}
