package cn.fyg.pa.domaintest.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.pa.domaintest.EntityDao;
import cn.fyg.pa.domaintest.EntityObject;
import cn.fyg.pa.domaintest.Value;



@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityDaoTest {
	
	@Resource
	EntityDao ed;
	
	@Test
	public void testSave(){
		EntityObject eo=new EntityObject();
		eo.setId(100L);
		eo.setName("eo name");
		Value vo=new Value();
		vo.setNumber("num 1");
		vo.setName("name 1");
		eo.getValues().add(vo);
		vo=new Value();
		vo.setNumber("num 2");
		vo.setName("name 2");
		eo.getValues().add(vo);
		
		
		vo=new Value();
		vo.setNumber("num 0");
		vo.setName("name 0");
		eo.setVo(vo);
		
		vo.setEo(eo);
		
		ed.save(eo);
		
		
	}

}
