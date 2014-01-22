package cn.fyg.pa.interfaces.web.module.monthlog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.ManageEnum;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.summarysnapshot.SnapshotItem;
import cn.fyg.pa.interfaces.web.module.monthlog.dto.PageItemBean;

@Component
public class SummarySnapshotFacade {

	@Resource
	PersonRepository personReposetory;
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	DepartmentRepository departmentRepository;
	
	public List<SnapshotItem> buildSnapshotItemList(Long year, Long month) {
		List<Person> persons=personReposetory.findPersonByManageOrderById(ManageEnum.N);
		List<Person> managers=personReposetory.findPersonByManageOrderById(ManageEnum.Y);
		List<Department> departments=departmentRepository.findAllDepartmentsOrderById();
		List<MonthChk> monthChks=monthChkRepository.findMonthChkByPeriod(year, month);
		SnapshotItemBuilder itemBuilder=new SnapshotItemBuilder(persons, managers, departments, monthChks);
		return itemBuilder.bulid();
	}
	
	public PageItemBean buildPageItemBean(List<SnapshotItem> snapshotItme){
		PageItemBuilder pageBuilder=new PageItemBuilder(snapshotItme);
		return pageBuilder.build();
	}

		
}
