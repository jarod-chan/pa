package cn.fyg.pa.application.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.application.page.MangePage;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.infrastructure.perisistence.FychkmangeDao;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.tool.CommonModelAndView;
import cn.fyg.pa.tool.Constant;
import cn.fyg.pa.tool.CookieUtil;
import cn.fyg.pa.tool.Tool;



@Controller
@RequestMapping("/fymanage/")
public class FymanageController {
	@Autowired
	private PersonDao fypersonDao;
	
	@Autowired
	private FychkmangeDao fychkmangeDao;
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listDepartmentPseron(@RequestParam(value="personId",required=true) Long personId,String msg,@CookieValue(value="chkstr",required=false) String cookieChkstr){
		
		Person currPerson = fypersonDao.find(personId);
		if (!CookieUtil.checkLogin(cookieChkstr, currPerson))
			return CommonModelAndView.getHomeModelAndView();
		
		
		List<Person> sameDepartmentPerson=fypersonDao.getAllFypersonSameDepartmentAndNotSelf(personId, currPerson.getDepartment());
		
		List<Object[]> pointArr=fychkmangeDao.getPseronPoint(personId);
		Map<Long,Long> personPoint=getPersonPointMap(pointArr);
		List<MangePage> tabData=makeTable(sameDepartmentPerson,personPoint); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("currPerson",currPerson);
		mav.addObject("tabData", tabData);
		mav.addObject("msg", msg);
		mav.setViewName("fy/manageuser_list");
		return mav;
	}

	private List<MangePage> makeTable(List<Person> sameDepartmentPerson,
			Map<Long, Long> personPoint) {
		List<MangePage> ret=new ArrayList<MangePage>();
		for (Person fyperson : sameDepartmentPerson) {
			MangePage page=new MangePage();
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
}
