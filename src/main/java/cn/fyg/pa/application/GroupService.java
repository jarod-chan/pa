package cn.fyg.pa.application;

import java.util.List;

import cn.fyg.pa.domain.model.group.Group;

public interface GroupService {
	
	Group createGroup();
	
	Group saveGroup(Group group);
	
	Group findGroup(String key);
	
	void createMembership(String userKey, String groupKey);

	void deleteMembership(String userKey, String groupKey);
	
	StringList groupUserKey(String groupKey);
	
	StringList userGroupKey(String userKey);
	
	StringList parentUserKey(String groupKey,String userKey);
	
	StringList parentUserKey(String groupKey,String userKey,int parentLevel);
	
	List<StringList> reportLine(String groupKey,String userKey);
}
