package cn.fyg.pa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.model.WorkType;


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
