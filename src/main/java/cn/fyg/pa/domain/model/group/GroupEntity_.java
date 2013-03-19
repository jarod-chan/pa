package cn.fyg.pa.domain.model.group;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GroupEntity.class)
public abstract class GroupEntity_ {

	public static volatile SingularAttribute<GroupEntity, String> name;
	public static volatile SingularAttribute<GroupEntity, GroupEntity> parent;
	public static volatile SingularAttribute<GroupEntity, String> uuid;
	public static volatile SingularAttribute<GroupEntity, String> code;
	public static volatile SingularAttribute<GroupEntity, String> key;

}

