package cn.fyg.pa.domain.model.group;

import java.util.List;


public interface MembershipRepository{
	
	MembershipEntity findByKey(String userKey, String groupKey);
	
	MembershipEntity persistent(MembershipEntity membershipEntity);
	
	MembershipEntity update(MembershipEntity membershipEntity);

	void remove(MembershipEntity membershipEntity);
	
	List<MembershipEntity> findByGroupKey(String groupKey);
	
	List<MembershipEntity> findByUserKey(String userKey);
	
	List<MembershipEntity> findByCode(String code);
	
	List<MembershipEntity> findByCodeList(List<String> codeList);
	
	MembershipEntity findMaxCodeLikeGroupKeyAndUserKey(String rootCode,String userKey);
}
