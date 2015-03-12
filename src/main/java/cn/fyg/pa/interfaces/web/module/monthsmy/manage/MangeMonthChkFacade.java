package cn.fyg.pa.interfaces.web.module.monthsmy.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.person.StateEnum;

@Component
public class MangeMonthChkFacade {
	
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	PersonRepository personRepository;

	public  List<MonthChk> getAllPersonMonthChkByPeriod(Long year,
			Long month, String department) {
		 List<MonthChk> monthChks = monthChkRepository.getMonthChkByPeriodAndDepartmentAndState(year,month,department);
		 List<Person> persons=personRepository.getStaffByDeptAndStateOrderById(department,StateEnum.valid,StateEnum.invalid);
		 ManageMonthChkBuilder builder=new ManageMonthChkBuilder(persons,monthChks);
		 return builder.build(year, month);
	}
	
}
