package cn.fyg.pa.domaintest.valueobject.four;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FourTest {
	
	@Resource
	FourDao dao;
	
	@Test
	public void testSave(){
		Four four=new Four();
		four.setId(1L);
		four.setName("test");
		Set<Long> planMonths=new HashSet<Long>();
		planMonths.add(1L);
		planMonths.add(2L);
		planMonths.add(3L);
		
		four.setPlanMonths(planMonths);
		dao.save(four);
		
		Four find = dao.find(1L);
		System.out.println(find);
		
		boolean[] monthCheck = find.getMonthCheck();
		monthCheck[10]=true;
		find.setMonthCheck(monthCheck);
		dao.update(find);
		
		find = dao.find(1L);
		System.out.println(find);
		
	}
	

}
