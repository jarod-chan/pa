package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.domain.yearchk.Fychkmange;


@Repository
public class FychkmangeDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Fychkmange find(Long id) {
		return entityManager.find(Fychkmange.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fychkmange> getCurrMangeToPersonChk(Long mangId,Long personId){
		return entityManager.createQuery("select p from Fychkmange p where mangeid=:mangeid and personid=:personid order by id asc")
				.setParameter("mangeid", mangId)
				.setParameter("personid", personId)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPseronPoint(Long mangId){
		List<Object[]> ret=entityManager.createQuery("select mange.personid,sum(mange.val*items.point) " +
				"from Fychkmange mange,Fychkitem items " +
				"where mange.itemid=items.id  " +
				"and mange.mangeid=:mangeid "+
				"group by mange.personid")
				.setParameter("mangeid", mangId)
				.getResultList();
		return ret;
	}
	
	@Transactional
	public void saveList(List<Fychkmange> list){
		for (Fychkmange fychkmange : list) {
			if (fychkmange.getId() == null) {
				entityManager.persist(fychkmange);
			} else {
				entityManager.merge(fychkmange);
			}	
		}
	}
	
	@Transactional
	public void removeList(List<Fychkmange> list){
		for (Fychkmange fychkmange : list) {
			entityManager.remove(entityManager.merge(fychkmange));
		}	
	}
	
	
}
