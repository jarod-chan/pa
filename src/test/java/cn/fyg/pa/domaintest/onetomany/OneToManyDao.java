package cn.fyg.pa.domaintest.onetomany;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OneToManyDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public One find(Long id){
		return entityManager.find(One.class, id);
	}
	
	public One findFirst(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<One> query = builder.createQuery(One.class);
		query.from(One.class);
		return entityManager.createQuery(query).getResultList().get(0);
	}
	
	
	/**
	 * 一对多关联查询不能执行
	 * @param id
	 * @param manytype
	 * @return
	 */
	public One findByIdAndType(Long id,String manytype){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<One> query = builder.createQuery(One.class);
		Root<One> from = query.from(One.class);
		Predicate criteria=builder.and(
				builder.equal(from.get("id"), id),
				builder.equal(from.get("manys").get("type"), manytype)
				);
		
		
		query.where(criteria);
		return entityManager.createQuery(query).getSingleResult();
	}
	
	@Transactional
	public One save(One one){
		entityManager.persist(one);
		return one;
	}
	
	@Transactional
	public void saveMany(List<Many> manys){
		for (Many many : manys) {
			entityManager.persist(many);
		}
	}
	
	

}
