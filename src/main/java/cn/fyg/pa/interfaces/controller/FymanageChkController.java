package cn.fyg.pa.interfaces.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.domain.model.Fychkitem;
import cn.fyg.pa.domain.model.Fychkmange;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.infrastructure.perisistence.FychkitemDao;
import cn.fyg.pa.infrastructure.perisistence.FychkmangeDao;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.page.CheckPage;
import cn.fyg.pa.interfaces.page.ChkmangeTab;
import cn.fyg.pa.interfaces.tool.CommonModelAndView;
import cn.fyg.pa.interfaces.tool.Constant;
import cn.fyg.pa.interfaces.tool.CookieUtil;
import cn.fyg.pa.interfaces.tool.Tool;


@Controller
@RequestMapping("/mangechk/")
public class FymanageChkController {
	
	private static final Logger logger = LoggerFactory.getLogger(FymanageChkController.class);

	@Autowired
	private PersonDao fypersonDao;
	@Autowired
	private FychkitemDao fychkitemDao;
	@Autowired
	private FychkmangeDao fychkmangeDao;
	
	private Map<Long,Fychkmange> hasCheckMap;
	
	private Long sumall=new Long(0);
	
	@RequestMapping(method=RequestMethod.POST,value="save") 
	public ModelAndView saveFycheck(@ModelAttribute CheckPage checkPage,@RequestParam(value="mangeId",required=true) Long mangeId,@RequestParam(value="personId",required=true) Long personId,@CookieValue(value="chkstr",required=false) String cookieChkstr) {
		logger.info("Received request to mangechk save");
		Person manage = fypersonDao.find(mangeId);
		if (!CookieUtil.checkLogin(cookieChkstr, manage))
			return CommonModelAndView.getHomeModelAndView();
		Person person=fypersonDao.find(personId);
		
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
			fychkmange.setMangeid(mangeId);
			fychkmange.setPersonid(personId);
			fychkmange.setItemid(checkPage.getItemid().get(i));
			fychkmange.setVal(checkPage.getVal().get(i));
			saveList.add(fychkmange);
			Fychkitem fychkitem=fychkitemDao.find(checkPage.getItemid().get(i));
			totalPoin+=fychkitem.getPoint()*fychkmange.getVal();
		}
		fychkmangeDao.removeList(removeList);
		fychkmangeDao.saveList(saveList);
		
		String getPoint=Tool.format(new BigDecimal(totalPoin).divide(Constant.POINT_LEVEL,3,BigDecimal.ROUND_HALF_DOWN));
		
		String msg="保存成功,员工"+person.getName()+"获得"+getPoint+"分,继续操作或者返回上页";
		return listDepartmentPseron(mangeId,personId,msg,cookieChkstr);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listDepartmentPseron(@RequestParam(value="mangeId",required=true) Long mangeId,@RequestParam(value="personId",required=true) Long personId,String msg,@CookieValue(value="chkstr",required=false) String cookieChkstr){
		logger.info("Received request to mangechk list");
		
		Person manage = fypersonDao.find(mangeId);
		if (!CookieUtil.checkLogin(cookieChkstr, manage))
			return CommonModelAndView.getHomeModelAndView();
		
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
		mav.setViewName("fy/chkmanage_list");
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
