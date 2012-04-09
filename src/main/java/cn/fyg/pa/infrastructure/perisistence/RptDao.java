package cn.fyg.pa.infrastructure.perisistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class RptDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCheckPoint() {
		String sql="select  x.id,x.name,x.department,y.realpt from fyperson x," +
				"(select a.personid,sum(a.val*b.point)/5 as realpt " +
				"from fychkmange a,fychkitem b where a.itemid=b.id group by a.personid) y " +
				"where x.id=y.personid order by id asc";
		return entityManager.createNativeQuery(sql).getResultList();
	}
	
	/**
	 * 此处的2380 1521 是被考核的分数，直接根据项目部的人数和职能部室的人数来计算两个值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getVal(){
		
		String sql="    select colid,sum(val+1) as val from ( select colid,rowid,val from fycheck    union all    select rowid,colid,-val from fycheck    ) as temp group by temp.colid";
		sql=" select x.id as id,case when x.type='N' then y.val/2380*100 else y.val/1521*100 end as point from fyperson as x,("+sql+" ) as y where x.id=y.colid";
		return entityManager.createNativeQuery(sql).getResultList();
	}
}
