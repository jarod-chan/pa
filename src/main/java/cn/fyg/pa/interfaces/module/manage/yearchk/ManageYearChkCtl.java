package cn.fyg.pa.interfaces.module.manage.yearchk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.application.YearMangeChkService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.yearchk.ChkitemRepository;
import cn.fyg.pa.domain.model.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.model.yearchk.Fychkitem;
import cn.fyg.pa.domain.model.yearchk.Fychkmange;
import cn.fyg.pa.domain.model.yearchk.YearMangeChkRepositroy;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.Tool;

@Controller
@RequestMapping("/mange/{personId}/yearchk")
public class ManageYearChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(ManageYearChkCtl.class);
	
	@Resource
	PersonRepository personRepository;
	
	@Resource
	YearMangeChkRepositroy yearMangeChkRepositroy;
	
	@Resource
	YearConfigService yearConfigService;
	
	@Resource
	ChkitemRepository chkitemRepository;
	
	@Resource
	YearMangeChkService yearMangeChkService;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
	
		Long year = 0L;
		try {
			year = yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
			return "yearchk/managechk/nottime";
		}
		
		
		List<Person> departmentPersons=personRepository.getStaffByDept(person.getDepartment());
	
		List<Object[]> personPointArray=yearMangeChkRepositroy.getPseronPointsByDepartment(year,person.getDepartment());
		Map<Long,Long> personPointMap=getPersonPointMap(personPointArray);
		List<PersonPointBean> personPointList=caretPersonPointList(departmentPersons,personPointMap); 
		
		map.put("year", year);
		map.put("person",person);
		map.put("personPointList", personPointList);
		map.put("message",new SessionMPR(session).getMessage());

		return "yearchk/managechk/list";
	}
	
	private Map<Long,Long> getPersonPointMap(List<Object[]> pointArr) {
		Map<Long,Long> personPoint=new HashMap<Long,Long>();
		for (Object[] objects : pointArr) {
			personPoint.put((Long)objects[0], (Long)objects[1]);
		}
		return personPoint;
	}

	private List<PersonPointBean> caretPersonPointList(List<Person> sameDepartmentPerson,
			Map<Long, Long> personPoint) {
		List<PersonPointBean> ret=new ArrayList<PersonPointBean>();
		for (Person fyperson : sameDepartmentPerson) {
			PersonPointBean page=new PersonPointBean();
			page.setPerson(fyperson);
			Long point=personPoint.get(fyperson.getId());
			if(point!=null){
				String getPoint=Tool.format(new BigDecimal(point).divide(Constant.POINT_LEVEL,3,BigDecimal.ROUND_HALF_DOWN));
				page.setGetPoint(getPoint);
			}
			ret.add(page);
		}
		return ret;
	}

	@RequestMapping(value="/person/{checkPersonId}",method=RequestMethod.GET)
	public String toPersonchk(@ModelAttribute("person")Person person,@PathVariable("checkPersonId") Long checkPersonId,Map<String,Object> map,HttpSession session){
		
		Long year = 0L;
		try {
			year = yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
			return "yearchk/managechk/nottime";
		}

		Person checkPerson=personRepository.find(checkPersonId);
		
		List<Fychkitem> yearItems=chkitemRepository.getItemsByYear(year);
		List<Fychkmange> chkmanges=yearMangeChkRepositroy.getPseronChkmange(year, checkPerson);
		
		Map<Long,Fychkmange> hasCheckMap=chkitemToMap(chkmanges);
		List<ChkmangeTab> chkmangeTabs=createChkmangeTabs(yearItems,hasCheckMap);
		Long sumall=getSumallPoint(chkmangeTabs);
		
		map.put("year", year);
		map.put("person",person);
		map.put("checkPerson", checkPerson);
		map.put("chkmangeTabs",chkmangeTabs);
		map.put("sumall",sumall);
		map.put("message",new SessionMPR(session).getMessage());
		return "yearchk/managechk/personchk";
	}
	
	private Long getSumallPoint(List<ChkmangeTab> chkmangeTabs) {
		Long sumall=0L;
		for (ChkmangeTab chkmangeTab : chkmangeTabs) {
			sumall+=chkmangeTab.getChkitem().getPoint();
		}
		return sumall;
	}

	private Map<Long, Fychkmange> chkitemToMap(List<Fychkmange> manges) {
		Map<Long, Fychkmange> map = new HashMap<Long, Fychkmange>();
		for (Fychkmange fychkmange : manges) {
			map.put(fychkmange.getItemid(), fychkmange);
		}
		return map;
	}
	
	private List<ChkmangeTab> createChkmangeTabs(List<Fychkitem> items, Map<Long, Fychkmange> hasCheckMap) {
		List<ChkmangeTab> result=new ArrayList<ChkmangeTab>();
		if(items.isEmpty()) return result;
		String type=items.get(0).getType();
		Long typesum=new Long(0);
		int rownum=0;
		
		for (int i=0,len=items.size();i<len;i++) {
			Fychkitem fychkitem=items.get(i);
			ChkmangeTab page=new ChkmangeTab();
			page.setChkitem(fychkitem);
			Fychkmange fychkmange = getFychkmange(fychkitem.getId(),hasCheckMap);
			boolean ischeck=isCheck(fychkitem,fychkmange);
			page.setIscheck(ischeck);
			page.setChkmange(fychkmange);
			result.add(page);
			if(fychkitem.getType().equals(type)){
				typesum+=fychkitem.getPoint();
				rownum++;
			}else{
				ChkmangeTab begPage=result.get(i-rownum);
				begPage.setSpecial("Y");
				begPage.setType(type);
				begPage.setTypesum(typesum);
				begPage.setRownum(rownum);
				type=fychkitem.getType();
				typesum=fychkitem.getPoint();
				rownum=1;
			}
		}
		ChkmangeTab begPage=result.get(items.size()-rownum);
		begPage.setSpecial("Y");
		begPage.setType(type);
		begPage.setTypesum(typesum);
		begPage.setRownum(rownum);
		return result;
	}
	
	private boolean isCheck(Fychkitem fychkitem, Fychkmange fychkmange) {
		if(fychkmange.getId()!=null){
			return true;
		}
		if(fychkmange.getId()==null && fychkitem.getMust()){
			return true;
		}
		return false;
	}
	
	private Fychkmange getFychkmange(Long chkitemId,Map<Long, Fychkmange> hasCheckMap) {
		Fychkmange fychkmange=hasCheckMap.get(chkitemId);
		if(fychkmange==null){
			fychkmange=new Fychkmange();
			fychkmange.setVal(new Long(1));
		}
		return fychkmange;
	}
	
	
	
	
	@RequestMapping(value="/person/{checkPersonId}/save",method=RequestMethod.POST)
	public String savePersonchk(@ModelAttribute CheckPage checkPage,@RequestParam("year") Long year,@ModelAttribute("person")Person person,@PathVariable("checkPersonId") Long checkPersonId,Map<String,Object> map,HttpSession session) {
		Person checkPerson=personRepository.find(checkPersonId);
		
		List<Fychkmange> saveList=new ArrayList<Fychkmange>();
		List<Fychkmange> removeList=new ArrayList<Fychkmange>();
		int totalPoin=0;
		for (int i=0,len=checkPage.getId().size();i<len;i++) {
			if(!checkPage.getFlag().get(i)){
				if(checkPage.getId().get(i)!=null){
					Fychkmange fychkmange=new Fychkmange();
					fychkmange.setId(checkPage.getId().get(i));
					removeList.add(fychkmange);
				}	
				continue;
			}
			Fychkmange fychkmange=new Fychkmange();
			fychkmange.setId(checkPage.getId().get(i));
			fychkmange.setYear(year);
			fychkmange.setMangeid(person.getId());
			fychkmange.setPersonid(checkPerson.getId());
			fychkmange.setItemid(checkPage.getItemid().get(i));
			fychkmange.setVal(checkPage.getVal().get(i));
			saveList.add(fychkmange);
			Fychkitem fychkitem=chkitemRepository.find(checkPage.getItemid().get(i));
			totalPoin+=fychkitem.getPoint()*fychkmange.getVal();
		}

		yearMangeChkService.saveAndRemoveList(saveList,removeList);
		
		String getPoint=Tool.format(new BigDecimal(totalPoin).divide(Constant.POINT_LEVEL,3,BigDecimal.ROUND_HALF_DOWN));
		
		new SessionMPR(session).setMessage("保存成功,合计"+getPoint+"分");
		return "redirect:../"+checkPersonId;
	}

}
