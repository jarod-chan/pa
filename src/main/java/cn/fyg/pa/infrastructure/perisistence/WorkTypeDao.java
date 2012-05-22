package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.worktype.WorkType;


@Repository
public class WorkTypeDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<WorkType> getAll(){
		String sql = "select w from WorkType w order by w.sn asc";
		List<WorkType> retList=entityManager.createQuery(sql, WorkType.class).getResultList();
		return retList;
	}

}
