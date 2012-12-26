package cn.fyg.pa.interfaces.module.person.yearchk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.PersonSummaryService;
import cn.fyg.pa.application.YearCheckService;
import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.application.YearchkStateService;
import cn.fyg.pa.domain.model.monthchk.MonthChk;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkItem;
import cn.fyg.pa.domain.model.monthchk.MonthChkRepository;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.model.yearchk.Fycheck;
import cn.fyg.pa.domain.model.yearchk.YearChkRepositroy;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/person/{personId}/yearchk")
public class PersonYearChkCtl {
	
	@Resource
	PersonRepository personRepository;
	@Resource
	YearConfigService yearConfigService;
	@Resource 
	YearChkRepositroy yearChkRepositroy;
	@Resource
	YearCheckService yearCheckService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toYearChk(@ModelAttribute("person")Person chkPerson,Map<String,Object> map,HttpSession session){
		Long year=0L;
		try {
			year=yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
			return "yearchk/personchk/nottime";
		}
		
		List<Person> sameTypePerson=personRepository.getStaffByTypeValid(chkPerson.getType());
		sameTypePerson=removePerson(sameTypePerson,chkPerson);
		List<Fycheck> hasChkFycheck=yearChkRepositroy.getPersonYearChkByChkperson(year,chkPerson);
		Map<String,Fycheck> hasChecksValues=changeChecksToMap(hasChkFycheck);
		PageBuilder builder=new PageBuilder(year, chkPerson, sameTypePerson, hasChecksValues);
		List<RowBean> rowBeanList = builder.createRowBeanList();
		map.put("year", year);
		map.put("rowBeanList", rowBeanList);
		map.put("message",new SessionMPR(session).getMessage());
		return "yearchk/personchk/yearchk";
	}
	
	private Map<String, Fycheck> changeChecksToMap(List<Fycheck> hasChkFycheck) {
		Map<String, Fycheck> returnMap=new HashMap<String, Fycheck>();
		for (Fycheck fycheck : hasChkFycheck) {
			returnMap.put(fycheck.getColId()+":"+fycheck.getRowId(), fycheck);
		}
		return returnMap;
	}

	private List<Person> removePerson(List<Person> sameTypePerson,Person... persons) {
		for (Person comparePerson : persons) {
			Iterator<Person> iterator = sameTypePerson.iterator();
			while (iterator.hasNext()) {
				Person person = iterator.next();
				if (person.getId().equals(comparePerson.getId())) {
					sameTypePerson.remove(person);
					break;
				}
			}
		}
		return sameTypePerson;
	}
	
	
	@Resource
	MonthChkRepository monthChkRepository;
	@Resource
	PersonSummaryService personSummaryService;
	@Resource
	YearchkStateService yearchkStateService;
	
	@RequestMapping(value="/personchk/{colPersonId}/comparework/{rowPersonId}",method=RequestMethod.GET)
	public String toCompareWork(@PathVariable("colPersonId") Long colPersonId,@PathVariable("rowPersonId") Long rowPersonId,Map<String,Object> map,HttpSession session){
		Long year=0L;
		try {
			year=yearConfigService.getEnableYear();
		}catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
		}
		
		map.put("year",year);
		
		Person colPerson=personRepository.find(colPersonId);
		List<MonthChk> colMonthChk = monthChkRepository.getMonthChkByPersonAndState(year, colPerson, MonthChkEnum.FINISHED);
		List<MonthChkItem> colItems=fetchMonthChkItems(colMonthChk);
		map.put("colItems", colItems);
		map.put("colPerson", colPerson);
		
		Person rowPerson = personRepository.find(rowPersonId);
		List<MonthChk> rowMonthChk = monthChkRepository.getMonthChkByPersonAndState(year, rowPerson, MonthChkEnum.FINISHED);
		List<MonthChkItem> rowItems=fetchMonthChkItems(rowMonthChk);
		map.put("rowItems", rowItems);
		map.put("rowPerson", rowPerson);
		
		return "yearchk/personchk/comparework";
	}

	private List<MonthChkItem> fetchMonthChkItems(List<MonthChk> colMonthChk) {
		ArrayList<MonthChkItem> monthChkItems = new ArrayList<MonthChkItem>();
		for(MonthChk monthChk:colMonthChk){
			monthChkItems.addAll(monthChk.getMonthChkItems());
		}
		return monthChkItems;
	}
	
	@RequestMapping(value="/personchk/{colPersonId}/comparesummary/{rowPersonId}",method=RequestMethod.GET)
	public String toCompareSummary(@PathVariable("colPersonId") Long colPersonId,@PathVariable("rowPersonId") Long rowPersonId,Map<String,Object> map,HttpSession session){
		Long year=0L;
		try {
			year=yearConfigService.getEnableYear();
		}catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
		}
		
		map.put("year",year);
		
		Person colPerson=personRepository.find(colPersonId);
		PersonSummary colSummary = personSummaryService.find(year, colPersonId);
		map.put("colSummary", colSummary);
		map.put("colPerson", colPerson);
		
		Person rowPerson = personRepository.find(rowPersonId);
		PersonSummary rowSummary = personSummaryService.find(year, rowPersonId);
		map.put("rowSummary", rowSummary);
		map.put("rowPerson", rowPerson);
		
		return "yearchk/personchk/comparesummary";
	}

}
