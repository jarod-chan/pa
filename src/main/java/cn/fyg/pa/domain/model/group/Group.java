package cn.fyg.pa.domain.model.group;

public interface Group {

	String getKey();

	void setKey(String key);

	String getName();

	void setName(String name);

	Group getParent();

	void setParent(Group parent);

}