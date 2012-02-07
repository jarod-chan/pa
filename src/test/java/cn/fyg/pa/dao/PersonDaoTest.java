package cn.fyg.pa.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.TypeEnum;


@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {
	
	@Autowired
	private PersonDao personDao;


	//@Test
	public void shouldSaveAPerson() {
		Person p = new Person();
		Long id=100L;
		p.setId(id);
		p.setName("tim");
		p.setChkstr("t");
//		p.setTypeEnum(TypeEnum.N);
//		personDao.persist(p);
//		personDao.flush();
//		p.setTypeEnum(TypeEnum.P);
//		personDao.flush();
//		Person retp=personDao.find(id);
//	//	personDao.refresh(retp);
//		assertNotNull(retp.getId());
// 		assertEquals("N",retp.getTypeEnum().getCode());
	}
	
	@Test
	@Transactional
	public void testMerge() {
		Person p = new Person();
		Long id=100L;
		p.setId(id);
		p.setName("tim");
		p.setChkstr("t");
		p.setType(TypeEnum.N);
		personDao.persist(p);
		Person newp=personDao.merge(p);
 		Person retp=personDao.find(id);
		assertNotNull(retp.getId());
	}



}
