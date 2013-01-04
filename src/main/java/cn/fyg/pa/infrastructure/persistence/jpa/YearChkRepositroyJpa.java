package cn.fyg.pa.infrastructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.yearchk.Fycheck;
import cn.fyg.pa.domain.model.yearchk.YearChkRepositroy;

@Repository
public class YearChkRepositroyJpa implements YearChkRepositroy{
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<Fycheck> getPersonYearChkAboutPerson(Long year,Person aboutPerson, Person chkPerson) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fycheck> query = builder.createQuery(Fycheck.class);
		Root<Fycheck> root = query.from(Fycheck.class);
		Predicate chkPersonCriteria=builder.equal(root.get("chkId"), chkPerson.getId());
		Predicate yearCriteria=builder.equal(root.get("year"), year);
		Predicate aboutPersonCriteria=builder.equal(root.get("colId"), aboutPerson.getId());
		aboutPersonCriteria=builder.or(aboutPersonCriteria,builder.equal(root.get("rowId"), aboutPerson.getId()));
		query.where(chkPersonCriteria,yearCriteria,aboutPersonCriteria);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void saveFychecks(List<Fycheck> fychecks) {
		for (Fycheck fycheck : fychecks) {
			if (fycheck.getId() == null) {
				entityManager.persist(fycheck);
			} else {
				entityManager.merge(fycheck);
			}	
		}
	}

	@Override
	public List<Fycheck> getPersonYearChkByChkperson(Long year,Person person) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fycheck> query = builder.createQuery(Fycheck.class);
		Root<Fycheck> root = query.from(Fycheck.class);
		Predicate yearCriteria=builder.equal(root.get("year"),year);
		Predicate chkPersonCriteria=builder.equal(root.get("chkId"), person.getId());
		query.where(yearCriteria,chkPersonCriteria);
		return entityManager.createQuery(query).getResultList();
	}
}
