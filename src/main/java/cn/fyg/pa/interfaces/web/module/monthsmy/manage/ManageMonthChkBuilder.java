package cn.fyg.pa.interfaces.web.module.monthsmy.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkFactory;
import cn.fyg.pa.domain.model.person.Person;

public class ManageMonthChkBuilder {

	private List<MonthChk> monthChks;
	private List<Person> persons;

	public ManageMonthChkBuilder(List<Person> persons, List<MonthChk> monthChks) {
		this.monthChks = monthChks;
		this.persons = persons;
	}

	public List<MonthChk> build(Long year, Long month) {
		Map<Long, MonthChk> map = createPersonMonthChk();
		return createMonthChkList(year, month, map);
	}

	private List<MonthChk> createMonthChkList(Long year, Long month,Map<Long, MonthChk> map) {
		List<MonthChk> returnList = new ArrayList<MonthChk>();
		for (Person person : this.persons) {
			MonthChk monthChk = map.get(person.getId());
			if (monthChk == null) {
				monthChk=MonthChkFactory.createMonthChk(person, year, month);
			}
			returnList.add(monthChk);
		}
		return returnList;
	}

	private Map<Long, MonthChk> createPersonMonthChk() {
		Map<Long, MonthChk> returnMap = new HashMap<Long, MonthChk>();
		for (MonthChk monthChk : this.monthChks) {
			returnMap.put(monthChk.getPerson().getId(), monthChk);
		}
		return returnMap;
	}

}
