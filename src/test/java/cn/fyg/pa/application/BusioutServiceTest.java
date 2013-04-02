package cn.fyg.pa.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.pa.domain.model.atten.busiout.Busiout;
import cn.fyg.pa.domain.model.atten.common.Dayitem;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BusioutServiceTest {
	
	@Autowired
	BusioutService busioutService;
	
	@Test
	public void test(){
		Busiout busiout=new Busiout();
		Dayitem dayItem=new Dayitem();
		dayItem.setYear(2013L);
		dayItem.setMonth(4L);
/*		dayitem.s
		busiout=busioutService.save(busiout);*/
		System.out.println(busiout.getId());
	}

}
