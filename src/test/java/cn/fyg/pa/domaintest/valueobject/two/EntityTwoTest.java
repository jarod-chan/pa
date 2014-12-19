package cn.fyg.pa.domaintest.valueobject.two;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityTwoTest {
	
	@Resource
	EntityTwoDao entityTwoDao;
	
	@Test
	public void testSave(){
		EntityTwo entityTwo = new EntityTwo();
		entityTwo.setId(100L);
		entityTwo.setName("name");
	
		ValueTwo vt1=new ValueTwo();
		vt1.setName("vt1_name");
		ValueTwo vt2=new ValueTwo();
		vt2.setName("vt2_name");
		entityTwo.getValueTwo().add(vt1);
		entityTwo.getValueTwo().add(vt2);
		entityTwoDao.save(entityTwo);
	}
}
