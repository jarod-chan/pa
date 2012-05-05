package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cn.fyg.pa.domain.yearchk.YearMangeChkRepositroy;

public class YearMangeChkRepositroyJpa implements YearMangeChkRepositroy {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> getPseronPointByDepartment(Long year,String department) {
		
		return null;
	}

}
