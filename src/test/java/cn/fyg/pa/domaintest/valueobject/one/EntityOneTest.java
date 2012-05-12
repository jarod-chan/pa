package cn.fyg.pa.domaintest.valueobject.one;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityOneTest {
	
	@Resource
	EntityOneDao dao;
	
	@Test
	public void testSave(){
		EntityOne entityOne = new EntityOne();
		entityOne.setId(100L);
		entityOne.setName("name");
		
		ValueOne valueOne = new ValueOne();
		valueOne.setValuename("valueOne");
		
		entityOne.setValueOne(valueOne);
		
		dao.save(entityOne);
	}

}
