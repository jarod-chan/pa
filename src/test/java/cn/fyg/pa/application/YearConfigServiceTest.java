package cn.fyg.pa.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static  org.junit.Assert.*;

import cn.fyg.pa.domain.yearchk.EnableYearNotExist;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class YearConfigServiceTest {
	
	@Autowired
	YearConfigService yearConfigService;
	
	@Test
	public void testEnableYear() throws EnableYearNotExist{
		yearConfigService.enableYear(2009L);
		yearConfigService.enableYear(2010L);
		yearConfigService.enableYear(2011L);
		
		yearConfigService.enableYear(2010L);
		Long year=yearConfigService.getEnableYear();
		assertEquals(2010, year.intValue());
	}
	
	@Test
	public void diableYear() {
		yearConfigService.enableYear(2009L);
		yearConfigService.enableYear(2010L);
		yearConfigService.enableYear(2011L);
		
		yearConfigService.diableYear(2011L);
		
		try {
			yearConfigService.getEnableYear();
			fail();
		} catch (EnableYearNotExist e) {
			assertNotNull(e);
		}
	
	}
	

}
