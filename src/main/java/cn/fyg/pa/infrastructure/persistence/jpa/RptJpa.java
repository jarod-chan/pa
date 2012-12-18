package cn.fyg.pa.infrastructure.persistence.jpa;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class RptJpa {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 计算部门经理评价中的得分
	 * @param year
	 * @return
	 */
	public List<Object[]> getCheckPoint(Long year) {
		String sql="select  x.id,x.name,x.department,y.realpt from fyperson x," +
				"(select a.personid,sum(a.val*b.point)/5 as realpt " +
				"from fychkmange a,fychkitem b where a.itemid=b.id and a.year=:year group by a.personid) y " +
				"where x.id=y.personid order by id asc";
		@SuppressWarnings("unchecked")
		List<Object[]> resultList=entityManager.createNativeQuery(sql).setParameter("year", year).getResultList();
		return resultList;
	}
	
	
	/**
	 * 计算员工评价中的得分
	 * @param year
	 * @return
	 */
	public List<Object[]> getVal(Long year){	
		PersonTotalNum personTotalNum=getPersonTotalNum(year);
		
		String sql="select colid,sum(val+1) as val from ( select colid,rowid,val from fycheck where year=:year union all select rowid,colid,-val from fycheck where year=:year) as temp group by temp.colid";
		sql=" select x.id as id,case when x.type='N' then y.val/"+personTotalNum.getNnum()+"*100 else y.val/"+personTotalNum.getYnum()+"*100 end as point from fyperson as x,("+sql+" ) as y where x.id=y.colid";
		@SuppressWarnings("unchecked")
		List<Object[]> resultList=entityManager.createNativeQuery(sql).setParameter("year", year).getResultList();
		return resultList;
	}
	
	private class PersonTotalNum{
		private long Ynum=1l;
		private long Nnum=1l;
		
		public long getYnum() {
			return Ynum;
		}
		public void setYnum(long ynum) {
			Ynum = ynum;
		}
		public long getNnum() {
			return Nnum;
		}
		public void setNnum(long nnum) {
			Nnum = nnum;
		}
		
		
	}

	/**
	 * 计算项目部和职能部室的总分数
	 * @param year
	 * @return
	 */
	private PersonTotalNum getPersonTotalNum(Long year) {
		PersonTotalNum personTotalNum=new PersonTotalNum();
		String sql="select distinct(X.personId) as personId from (select colid as personId from fycheck where year=:year union all  select rowid as personId from fycheck where year=:year) as X";
		sql="select P.type,count(Y.personId) from("+sql+") Y,fyperson P where y.personId=P.id group by P.type";
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = entityManager.createNativeQuery(sql).setParameter("year", year).getResultList();
		for (Object[] objects : resultList) {
			if(objects[0].equals("Y")){
				long Ynum=((BigInteger)objects[1]).longValue();
				personTotalNum.setYnum((Ynum-1)*(Ynum-2)*2);
			}
			if(objects[0].equals("N")){
				long Nnum=((BigInteger)objects[1]).longValue();
				personTotalNum.setNnum((Nnum-1)*(Nnum-2)*2);
			}
		}
		
		return personTotalNum;
	}
}
