package cn.fyg.pa.interfaces.web.module.yearpk.gm;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.module.yearpk.managesc.dto.ChkmangeTab;
import cn.fyg.pa.interfaces.web.module.yearpk.managesc.dto.PersonPointBean;
import cn.fyg.pa.interfaces.web.module.yearpk.managesc.facade.YearchkFacade;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/yearpk/gm")
public class GmanageYearChkCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(GmanageYearChkCtl.class);
	
	@Resource
	PersonRepository personRepository;
	@Resource
	YearConfigService yearConfigService;
	@Resource
	YearchkFacade yearchkFacade;
	@Resource
	DepartmentRepository departmentRepository;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toListWithDefaultDepartment(Person person,Map<String,Object> map,HttpSession session){
		return toList(person,null,map,session);
	}
	
	@RequestMapping(value="/department/{departmentId}",method=RequestMethod.GET)
	@PersonIn(0)
	public String toList(Person person,@PathVariable("departmentId")Long departmenId
			,Map<String,Object> map,HttpSession session){
		
		Long year = 0L;
		try {
			year = yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","年终考核已经被关闭");
			return "yearchk/gmanagechk/list";
		}
		List<Department> departments = departmentRepository.findDepartmentsByGmanage(person);
		Department selDepartment=null;
		if(departmenId==null){		
			selDepartment=departments.get(0);
		}else{
			selDepartment=departmentRepository.find(departmenId);			
		}
		map.put("departments", departments);
		map.put("selDepartment", selDepartment);
		
		List<PersonPointBean> personPointList = yearchkFacade.getPersonList(year,selDepartment.getName()); 
		map.put("year", year);
		map.put("person",person);
		map.put("personPointList", personPointList);
		map.put("message",new SessionMPR(session).getMessage());

		return "yearchk/gmanagechk/list";
	}

	@RequestMapping(value="/person/{checkPersonId}/year/{year}",method=RequestMethod.GET)
	@PersonIn(0)
	public String toItem(Person person,@PathVariable("checkPersonId") Long checkPersonId,@PathVariable("year")Long year,Map<String,Object> map,HttpSession session){
		
		Person checkPerson=personRepository.find(checkPersonId);
		
		Department selDepartment = departmentRepository.findDepartmentByName(checkPerson.getDepartment());
		map.put("selDepartment", selDepartment);
		
		List<ChkmangeTab> chkmangeTabs =yearchkFacade.getChkmangeTab(year, checkPerson);
		Long sumall=getSumallPoint(chkmangeTabs);
		
		map.put("year", year);
		map.put("person",person);
		map.put("checkPerson", checkPerson);
		map.put("chkmangeTabs",chkmangeTabs);
		map.put("sumall",sumall);
		map.put("message",new SessionMPR(session).getMessage());
		
		return "yearchk/gmanagechk/item";
	}

	private Long getSumallPoint(List<ChkmangeTab> chkmangeTabs) {
		Long sumall=0L;
		for (ChkmangeTab chkmangeTab : chkmangeTabs) {
			sumall+=chkmangeTab.getChkitem().getPoint();
		}
		return sumall;
	}

}
