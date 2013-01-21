package cn.fyg.pa.interfaces.module.manage.yearchk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import cn.fyg.pa.interfaces.module.manage.yearchk.dto.CheckPage;
import cn.fyg.pa.interfaces.module.manage.yearchk.dto.ChkmangeTab;
import cn.fyg.pa.interfaces.module.manage.yearchk.dto.PersonPointBean;
import cn.fyg.pa.interfaces.module.manage.yearchk.facade.YearchkFacade;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.Tool;

@Controller
@RequestMapping("/mange/{personId}/yearchk")
public class ManageYearChkCtl {
	
	@Resource
	PersonRepository personRepository;
	@Resource
	YearConfigService yearConfigService;
	@Resource
	YearchkFacade yearchkFacade;
	@Resource
	ChkitemRepository chkitemRepository;
	@Resource
	YearMangeChkService yearMangeChkService;
	
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
	
		Long year = 0L;
		try {
			year = yearConfigService.getEnableYear();
		} catch (EnableYearNotExist e) {
			map.put("message","当前时间无法进行年终员工考核");
			return "yearchk/managechk/nottime";
		}
		
		List<PersonPointBean> personPointList = yearchkFacade.getPersonList(year, person.getDepartment()); 
		
		map.put("year", year);
		map.put("person",person);
		map.put("personPointList", personPointList);
		map.put("message",new SessionMPR(session).getMessage());

		return "yearchk/managechk/list";
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
		
		List<ChkmangeTab> chkmangeTabs =yearchkFacade.getChkmangeTab(year, checkPerson);
		
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
		
		new SessionMPR(session).setMessage("保存成功,"+checkPerson.getName()+"合计得"+getPoint+"分");
		return "redirect:../../";
	}

}
