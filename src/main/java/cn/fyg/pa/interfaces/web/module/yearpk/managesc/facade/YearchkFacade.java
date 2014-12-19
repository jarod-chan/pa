package cn.fyg.pa.interfaces.web.module.yearpk.managesc.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.YearConfigService;
import cn.fyg.pa.application.YearMangeChkService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.yearchk.ChkitemRepository;
import cn.fyg.pa.domain.model.yearchk.Fychkitem;
import cn.fyg.pa.domain.model.yearchk.Fychkmange;
import cn.fyg.pa.domain.model.yearchk.YearMangeChkRepositroy;
import cn.fyg.pa.interfaces.web.module.yearpk.managesc.dto.ChkmangeTab;
import cn.fyg.pa.interfaces.web.module.yearpk.managesc.dto.PersonPointBean;
import cn.fyg.pa.interfaces.web.shared.tool.Constant;
import cn.fyg.pa.interfaces.web.shared.tool.Tool;

@Component
public class YearchkFacade {
	
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
	
	/**
	 * 获得部门员工考核列表
	 * @param year
	 * @param department
	 * @return
	 */
	public List<PersonPointBean> getPersonList(Long year, String department) {
		List<Person> departmentPersons=personRepository.getStaffByDeptValid(department);
		List<Object[]> personPointArray=yearMangeChkRepositroy.getPseronPointsByDepartment(year,department);
		Map<Long,Long> personPointMap=getPersonPointMap(personPointArray);
		List<PersonPointBean> personPointList=caretPersonPointList(departmentPersons,personPointMap);
		return personPointList;
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

	
	/**
	 * 获得展示页面信息
	 * @param year
	 * @param checkPerson
	 * @return
	 */
	public List<ChkmangeTab> getChkmangeTab(Long year, Person checkPerson) {
		List<Fychkitem> yearItems=chkitemRepository.getItemsByYear(year);
		List<Fychkmange> chkmanges=yearMangeChkRepositroy.getPseronChkmange(year, checkPerson);
		Map<Long,Fychkmange> hasCheckMap=chkitemToMap(chkmanges);
		List<ChkmangeTab> chkmangeTabs=createChkmangeTabs(yearItems,hasCheckMap);
		return chkmangeTabs;
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
			fychkmange.setVal(new Long(3));
		}
		return fychkmange;
	}
}
