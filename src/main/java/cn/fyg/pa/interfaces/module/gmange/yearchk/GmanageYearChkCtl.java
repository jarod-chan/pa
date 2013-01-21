package cn.fyg.pa.interfaces.module.gmange.yearchk;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.interfaces.module.manage.yearchk.dto.ChkmangeTab;
import cn.fyg.pa.interfaces.module.manage.yearchk.dto.PersonPointBean;
import cn.fyg.pa.interfaces.module.manage.yearchk.facade.YearchkFacade;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/gmange/{personId}/yearchk")
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
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toListWithDefaultDepartment(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		return toList(person,null,map,session);
	}
	
	@RequestMapping(value="/department/{departmentId}",method=RequestMethod.GET)
	public String toList(@ModelAttribute("person")Person person,@PathVariable("departmentId")Long departmenId
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
	public String toItem(@ModelAttribute("person")Person person,@PathVariable("checkPersonId") Long checkPersonId,@PathVariable("year")Long year,Map<String,Object> map,HttpSession session){
		
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
