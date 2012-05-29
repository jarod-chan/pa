package cn.fyg.pa.infrastructure.perisistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.yearchk.Fychkmange;
import cn.fyg.pa.domain.model.yearchk.YearMangeChkRepositroy;

@Repository
public class YearMangeChkRepositroyJpa implements YearMangeChkRepositroy {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> getPseronPointsByDepartment(Long year,String department) {
		@SuppressWarnings("unchecked")
		List<Object[]> ret=entityManager.createQuery("select mange.personid,sum(mange.val*items.point) " +
				"from Fychkmange mange,Fychkitem items " +
				"where mange.itemid=items.id " +
				"and mange.year=:year " +
				"and mange.personid in (" +
				"select id from fyperson where manage=:manage and department=:department )" +
				"group by mange.personid")
				.setParameter("year", year)
				.setParameter("manage", ManageEnum.N)
				.setParameter("department", department)
				.getResultList();
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fychkmange> getPseronChkmange(Long year, Person person) {
		return entityManager.createQuery("select p from Fychkmange p where year=:year and personid=:personid order by id asc")
				.setParameter("year", year)
				.setParameter("personid", person.getId())
				.getResultList();
	}
	
	@Override
	public void saveList(List<Fychkmange> list) {
		for (Fychkmange fychkmange : list) {
			if (fychkmange.getId() == null) {
				entityManager.persist(fychkmange);
			} else {
				entityManager.merge(fychkmange);
			}	
		}
	}

	@Override
	public void removeList(List<Fychkmange> list) {
		for (Fychkmange fychkmange : list) {
			entityManager.remove(entityManager.merge(fychkmange));
		}	
	}



}
