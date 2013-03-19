package cn.fyg.pa.domain.model.group;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public MembershipEntity findByKey(String userKey, String groupKey) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.equal(from.get(MembershipEntity_.userKey), userKey),
				builder.equal(from.get(MembershipEntity_.groupKey), groupKey)
				);
		List<MembershipEntity> result = entityManager.createQuery(query).getResultList();
		if(result.size()>1){
			throw new RuntimeException(String.format("userKey:[%s] groupKey:[%s] 重复", userKey,groupKey));
		}
		return result.isEmpty()?null:result.get(0);
	}

	@Override
	public MembershipEntity persistent(MembershipEntity membershipEntity) {
		entityManager.persist(membershipEntity);
		return membershipEntity;
	}

	@Override
	public MembershipEntity update(MembershipEntity membershipEntity) {
		return entityManager.merge(membershipEntity);
	}

	@Override
	public void remove(MembershipEntity membershipEntity) {
		entityManager.remove(membershipEntity);
	}

	@Override
	public List<MembershipEntity> findByGroupKey(String groupKey) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.equal(from.get(MembershipEntity_.groupKey), groupKey)
				);
		query.orderBy(builder.asc(from.get(MembershipEntity_.userKey)));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<MembershipEntity> findByUserKey(String userKey) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.equal(from.get(MembershipEntity_.userKey), userKey)
				);
		query.orderBy(builder.asc(from.get(MembershipEntity_.groupKey)));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<MembershipEntity> findByCode(String code) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.equal(from.get(MembershipEntity_.code), code)
				);
		query.orderBy(
				builder.asc(from.get(MembershipEntity_.userKey))
				);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public MembershipEntity findMaxCodeLikeGroupKeyAndUserKey(String rootCode,
			String userKey) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.or(						
						builder.equal(from.get(MembershipEntity_.code), rootCode),
						builder.like(from.get(MembershipEntity_.code), rootCode+".%")
						),
				builder.equal(from.get(MembershipEntity_.userKey), userKey)
				)
			.orderBy(
				builder.desc(from.get(MembershipEntity_.code))
				);
		List<MembershipEntity> result = entityManager.createQuery(query).getResultList();
		return result.isEmpty()?null:result.get(0);
	}

	@Override
	public List<MembershipEntity> findByCodeList(List<String> codeList) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				from.get(MembershipEntity_.code).in(codeList)
				);
		query.orderBy(
				builder.desc(from.get(MembershipEntity_.code)),
				builder.asc(from.get(MembershipEntity_.userKey))
				);
		return entityManager.createQuery(query).getResultList();	
	}

}
