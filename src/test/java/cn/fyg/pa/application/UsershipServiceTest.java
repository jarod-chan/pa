package cn.fyg.pa.application;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.pa.domain.model.usership.Usership;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UsershipServiceTest {
	
	@Autowired
	UsershipService UsershipService;
	
	@Test
	public void test(){
		Usership usership=new Usership();
		usership.setId(1L);
		usership.setKey("czj");
		usership.setParentKey(null);
		usership.setTag("Tmanage");
		usership.setCode("czj");
		usership.setTree("T1");
		UsershipService.save(usership);
		
		usership=new Usership();
		usership.setId(2L);
		usership.setKey("myq");
		usership.setParentKey("czj");
		usership.setTag("Gmanage");
		usership.setCode("czj.myq");
		usership.setTree("T1");
		UsershipService.save(usership);
		
		String parent = UsershipService.parent("T1", "myq");
		assertTrue(parent.equals("czj"));
	}
	

}
