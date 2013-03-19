package cn.fyg.pa.domain.model.group;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;


@Repository
public class GroupRepositoryImpl implements GroupRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean isConflict(GroupEntity groupEntity) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);
		Root<GroupEntity> from = query.from(GroupEntity.class);
		Predicate criteria=builder.equal(from.get(GroupEntity_.key), groupEntity.getKey());
		if(groupEntity.getUuid()!=null){
			criteria=builder.and(criteria,builder.notEqual(from.get(GroupEntity_.uuid), groupEntity.getUuid()));
		}
		query.where(criteria);
		List<GroupEntity> resultList = entityManager.createQuery(query).getResultList();
		return !resultList.isEmpty();
	}

	@Override
	public GroupEntity find(String key) {
		 return entityManager.find(GroupEntity.class, key);
	}

	@Override
	public GroupEntity persistent(GroupEntity groupEntity) {
		entityManager.persist(groupEntity);
		return groupEntity;
	}

	@Override
	public GroupEntity update(GroupEntity groupEntity) {
		
		String oldCode=entityManager.find(GroupEntity.class, groupEntity.getKey()).getCode();
		String newCode=groupEntity.getCode();
		
		updateGroupCode(oldCode, newCode);
		updateMembershipCode(oldCode,newCode);
		
		return entityManager.merge(groupEntity);
	}

	private void updateMembershipCode(String oldCode, String newCode) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MembershipEntity> query = builder.createQuery(MembershipEntity.class);
		Root<MembershipEntity> from = query.from(MembershipEntity.class);
		query.where(
				builder.or(
						builder.equal(from.get(MembershipEntity_.code), oldCode),
						builder.like(from.get(MembershipEntity_.code), oldCode+".%")
						)
					);
		List<MembershipEntity> result = entityManager.createQuery(query).getResultList();
		for (MembershipEntity membershipEntity : result) {
			membershipEntity.setCode(membershipEntity.getCode().replace(oldCode, newCode));
		}
	}

	private void updateGroupCode(String oldCode, String newCode) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GroupEntity> query = builder.createQuery(GroupEntity.class);
		Root<GroupEntity> from = query.from(GroupEntity.class);
		query.where(
				builder.like(from.get(GroupEntity_.code), oldCode+".%")
				);
		List<GroupEntity> groups = entityManager.createQuery(query).getResultList();
		for (GroupEntity group : groups) {
			group.setCode(group.getCode().replace(oldCode, newCode));
		}
	}

}
