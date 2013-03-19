package cn.fyg.pa.domain.model.group;

public interface GroupRepository  {
	
	boolean isConflict(GroupEntity groupEntity);
	
	GroupEntity find(String key);
	
	GroupEntity persistent(GroupEntity groupEntity);

	GroupEntity update(GroupEntity groupEntity);
}
