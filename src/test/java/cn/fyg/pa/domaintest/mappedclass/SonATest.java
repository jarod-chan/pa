package cn.fyg.pa.domaintest.mappedclass;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SonATest {
	
	@Resource
	SonADao dao;
	
	@Test
	public void testSave(){
		SonA sonA=new SonA();
		sonA.setName("f_name");
		sonA.setA_name("a_name");
		dao.save(sonA);
	}

}
