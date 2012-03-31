package cn.fyg.pa.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.FychkitemDao;
import cn.fyg.pa.model.Fychkitem;



@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckitemTest {
	
	@Autowired
	private FychkitemDao fychkitemDao;
	
	@Test
	public void testMerge() {
		Fychkitem chkitem=new Fychkitem();
		Long id=1L;
		chkitem.setId(id);
		chkitem.setContent("test");
		fychkitemDao.persist(chkitem);
		
		Fychkitem fk1=fychkitemDao.find(1L);
		Fychkitem fk2=fychkitemDao.find(1L);
		Assert.assertFalse(fk1.equals(fk2));
	}

}
