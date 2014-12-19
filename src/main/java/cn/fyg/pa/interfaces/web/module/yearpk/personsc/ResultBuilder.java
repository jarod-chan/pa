package cn.fyg.pa.interfaces.web.module.yearpk.personsc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.person.Person;

public class ResultBuilder {
	
	private List<Person> personList;
	
	private Map<Long,Object[]> map;

	public ResultBuilder(List<Person> personList, Map<Long, Object[]> map) {
		super();
		this.personList = personList;
		this.map = map;
	} 
	
	public List<ResultBean> create(){
		ArrayList<ResultBean> resultList = new ArrayList<ResultBean>();
		for (Person person : personList) {
			ResultBean resultBean = new ResultBean();
			resultBean.setPerson(person);
			Object[] obj=map.get(person.getId());
			if(obj!=null){
				resultBean.setWin(((BigDecimal)obj[1]).longValue());
				resultBean.setDraw(((BigDecimal)obj[2]).longValue());
				resultBean.setLose(((BigDecimal)obj[3]).longValue());
			}
			resultList.add(resultBean);
		}
		return resultList;
	}

}
