package cn.fyg.pa.interfaces.yearchk.managechk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.yearchk.Fychkitem;
import cn.fyg.pa.domain.yearchk.Fychkmange;
import cn.fyg.pa.domain.yearchk.YearMangeChkRepositroy;
import cn.fyg.pa.infrastructure.perisistence.FychkitemDao;
import cn.fyg.pa.infrastructure.perisistence.FychkmangeDao;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.page.ChkmangeTab;
import cn.fyg.pa.interfaces.tool.CommonModelAndView;
import cn.fyg.pa.interfaces.tool.Constant;
import cn.fyg.pa.interfaces.tool.CookieUtil;
import cn.fyg.pa.interfaces.tool.Tool;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Person> departmentPersons=personRepository.getStaffByDept(person.getDepartment());
	
		List<Object[]> pointArr=yearMangeChkRepositroy.getPseronPointByDepartment(year,person.getDepartment());
		Map<Long,Long> personPoint=getPersonPointMap(pointArr);
		List<PersonPointBean> tabData=makeTable(departmentPersons,personPoint); 
		
		map.put("year", year);
		map.put("person",person);
		map.put("tabData", tabData);


		return "yearchk/manageuser_list";
	}
	
	public String list_back(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		
		Long personId=person.getId();
		List<Person> sameDepartmentPerson=personRepository.getStaffByDept(person.getDepartment());
		
		List<Object[]> pointArr=fychkmangeDao.getPseronPoint(personId);
		Map<Long,Long> personPoint=getPersonPointMap(pointArr);
		List<PersonPointBean> tabData=makeTable(sameDepartmentPerson,personPoint); 
		

		map.put("person",person);
		map.put("tabData", tabData);


		return "yearchk/manageuser_list";
	}
	
	@Autowired
	private PersonDao fypersonDao;
	
	@Autowired
	private FychkmangeDao fychkmangeDao;
	
	private List<PersonPointBean> makeTable(List<Person> sameDepartmentPerson,
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

	private Map<Long,Long> getPersonPointMap(List<Object[]> pointArr) {
		Map<Long,Long> personPoint=new HashMap<Long,Long>();
		for (Object[] objects : pointArr) {
			personPoint.put((Long)objects[0], (Long)objects[1]);
		}
		return personPoint;
	}
	
	
	@Autowired
	private FychkitemDao fychkitemDao;
	
	private Map<Long,Fychkmange> hasCheckMap;
	
	private Long sumall=new Long(0);
	
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listDepartmentPseron(@RequestParam(value="mangeId",required=false) Long mangeId,@RequestParam(value="personId",required=true) Long personId,String msg,@CookieValue(value="chkstr",required=false) String cookieChkstr){
		logger.info("Received request to mangechk list");
		
		Person manage = fypersonDao.find(mangeId);
//		if (!CookieUtil.checkLogin(cookieChkstr, manage))
//			return CommonModelAndView.getHomeModelAndView();
		
		Person person=fypersonDao.find(personId);
		
		List<Fychkitem> items=fychkitemDao.getAllByIdAsc();
		List<Fychkmange> manges=fychkmangeDao.getCurrMangeToPersonChk(mangeId, personId);
		
		hasCheckMap=chkitemToMap(manges);
		List<ChkmangeTab> tabData=makeTable(items);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("manage",manage);
		mav.addObject("person", person);
		mav.addObject("tabData",tabData);
		mav.addObject("sumall",sumall);
		mav.addObject("msg", msg);
		mav.setViewName("yearchk/chkmanage_list");
		return mav;
	}

	private Map<Long, Fychkmange> chkitemToMap(List<Fychkmange> manges) {
		Map<Long,Fychkmange> map=new HashMap<Long,Fychkmange>();
		for (Fychkmange fychkmange : manges) {
			map.put(fychkmange.getItemid(), fychkmange);
		}
		return map;
	}

	private List<ChkmangeTab> makeTable(List<Fychkitem> items) {
		List<ChkmangeTab> result=new ArrayList<ChkmangeTab>();
		if(items.isEmpty()) return result;
		String type=items.get(0).getType();
		Long typesum=new Long(0);
		int rownum=0;
		sumall=new Long(0);
		for (int i=0,len=items.size();i<len;i++) {
			Fychkitem fychkitem=items.get(i);
			ChkmangeTab page=new ChkmangeTab();
			page.setChkitem(fychkitem);
			Fychkmange fychkmange = getFychkmange(fychkitem.getId());
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
			sumall+=fychkitem.getPoint();
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

	private Fychkmange getFychkmange(Long chkitemId) {
		Fychkmange fychkmange=hasCheckMap.get(chkitemId);
		if(fychkmange==null){
			fychkmange=new Fychkmange();
			fychkmange.setVal(new Long(1));
		}
		return fychkmange;
	}
}
