package cn.fyg.pa.interfaces.web.module.yearpk.personsc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import cn.fyg.pa.domain.model.person.StateEnum;
import cn.fyg.pa.domain.model.summary.PersonSummary;
import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.model.yearchk.Fycheck;
import cn.fyg.pa.domain.model.yearchk.YearChkRepositroy;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.Ptt;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.PttUtil;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.impl.PttUtilFycheck;
import cn.fyg.pa.interfaces.web.module.yearpk.personsc.participation.impl.PttUtilRowBean;
import cn.fyg.pa.interfaces.web.shared.message.impl.SessionMPR;

@Controller
@RequestMapping("/personsc")
public class PersonYearChkCtl {
	
	private static final String PATH = "yearchk/personchk/";
	private interface Page {
		String  EDIT= PATH + "yearchk";
		String  COMMIT= PATH + "yearchk_commit";
		String  NOTTIME= PATH + "nottime";
		String  COMPARE_WORK= PATH + "comparework";
		String  COMPARE_SUMMARY= PATH + "comparesummary";
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	YearConfigService yearConfigService;
	@Resource 
	YearChkRepositroy yearChkRepositroy;
	@Resource
	YearCheckService yearCheckService;
	

	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toYearChk(Person chkPerson,Map<String,Object> map,HttpSession session){
		Long year=0L;
		try {
			year=yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","年度横向评价功能暂时关闭！");
			return Page.NOTTIME;
		}
		//添加无效人员过滤（部分返聘人员不参与年度绩效评价）
		if(chkPerson.getState()==StateEnum.invalid){
			map.put("message","你无须参与年度横向评价！");
			return Page.NOTTIME;
		}
		//如果部门参与人数太少，则不用参与横向评价
		String chkPersonDept=chkPerson.getDepartment();
		List<Person> sameDeptPerson=personRepository.getStaffByDeptValid(chkPersonDept);
		sameDeptPerson=removePerson(sameDeptPerson,chkPerson);
		if(sameDeptPerson==null || sameDeptPerson.size()<=1){
			map.put("message","你部门的人数太少，无需参与评价！");
			return Page.NOTTIME;
		}
		
		boolean commit = yearchkStateService.isCommit(year, chkPerson.getId());
		
		List<Fycheck> hasChkFycheck=yearChkRepositroy.getPersonYearChkByChkperson(year,chkPerson);
		Map<String,Fycheck> hasChecksValues=changeChecksToMap(hasChkFycheck);
		
		if(!commit){				
			PageBuilder builder=new PageBuilder(year, chkPerson, sameDeptPerson, hasChecksValues);
			List<RowBean> rowBeanList = builder.createRowBeanList();
			int maxLen_dept=builder.getMaxLen();
			
			//计算参与度
			PttUtil pttUtil=new PttUtilRowBean(rowBeanList);
			Ptt ptt = pttUtil.computer();
			map.put("ptt", ptt);
			
			map.put("rowBeanList", rowBeanList);
			map.put("maxLen", maxLen_dept);		
		}else{		
			
			Map<Long, Object[]> personYearChkResult = yearChkRepositroy.getPersonYearChkResult(year, chkPerson);
			ResultBuilder builder = new ResultBuilder(sameDeptPerson,personYearChkResult);
			List<ResultBean> resultBeanList = builder.create();
			map.put("resultBeanList", resultBeanList);		
		}
		
		map.put("person", chkPerson);
		map.put("year", year);
		map.put("message",new SessionMPR(session).getMessage());
		return commit?Page.COMMIT:Page.EDIT;
		
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
		map.put("message",new SessionMPR(session).getMessage());
		
		return Page.COMPARE_WORK;
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
		
		return Page.COMPARE_SUMMARY;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setAutoGrowCollectionLimit(1000);
	}
	
	/**
	 *获得用户提交值，如果直接提交，表单可能过大，造成jetty服务器越界
	 *可以通过修改jetty.xml来取消限制
	 */
	@RequestMapping(value="/{year}/save",method=RequestMethod.POST)
	@PersonIn(0)
	public String save(Person chkPerson,@PathVariable("year")Long year,RecvBean recvBean,HttpSession session){
		List<Fycheck> fycheck=recvBean.getFk();
		fycheck=fillFycheck(fycheck,year,chkPerson);
	    yearCheckService.saveFychecks(fycheck);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/personsc";
	}
	
	private List<Fycheck> fillFycheck(List<Fycheck> fycheckList, Long year,Person chkPerson) {
		for (Fycheck fycheck : fycheckList) {
			fycheck.setYear(year);
			fycheck.setChkId(chkPerson.getId());
		}
		return fycheckList;
	}


	
	@RequestMapping(value="/{year}/commit",method=RequestMethod.POST)
	@PersonIn(0)
	public String commit(Person chkPerson,@PathVariable("year")Long year,RecvBean recvBean,HttpSession session){
		List<Fycheck> fycheck=recvBean.getFk();
		fycheck=fillFycheck(fycheck,year,chkPerson);
	    yearCheckService.saveFychecks(fycheck);
	    PttUtil pttUtil=new PttUtilFycheck(fycheck);
		Ptt ptt = pttUtil.computer();
		if(ptt.isPass()){			
			yearchkStateService.commitYearchk(year, chkPerson.getId());
			new SessionMPR(session).setMessage("提交成功！");
		}else{
			new SessionMPR(session).setMessage("参与度没有达到要求，提交失败！");
		}
		
		return "redirect:/personsc";
	}


}
