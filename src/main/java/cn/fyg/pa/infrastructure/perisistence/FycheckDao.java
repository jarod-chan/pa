package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.model.yearchk.Fycheck;


@Repository
public class FycheckDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void saveAll(List<Fycheck> list){
		for (Fycheck fycheck : list) {
			if (fycheck.getId() == null) {
				entityManager.persist(fycheck);
			} else {
				entityManager.merge(fycheck);
			}	
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fycheck> getFycheckByUserid(Long id){
		//return entityManager.createQuery("select p from Fycheck p ").getResultList();
		return entityManager.createQuery("select p from Fycheck p where p.chkId=:personid ").setParameter("personid",id).getResultList();
	}
	
	
}
