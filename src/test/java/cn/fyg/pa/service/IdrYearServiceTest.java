package cn.fyg.pa.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.model.companykpi.IdrCompany;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.service.IdrYearCompanyService;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IdrYearServiceTest {
	
	@Resource
	IdrYearCompanyService idrYearService;
	
	@Test
	public void save(){
//		IdrYearCompany idrYear=new IdrYearCompany();
//		idrYear.setYear(2030L);
//		
//		IdrCompany idrCompany=new IdrCompany();
//		idrYear.getIdrCompany().add(idrCompany);
//		
//		idrYear=idrYearService.save(idrYear);
	}
	
	@Test //XXX 需要更改 无效测试
	public void testSave(){
		
//		IdrYearCompany retIdrYear=idrYearService.findByYear(2030L);
//		for(IdrCompany idrcpy:retIdrYear.getIdrCompany()){
//			System.out.println(idrcpy.getId());
//		}
		
	}

}
