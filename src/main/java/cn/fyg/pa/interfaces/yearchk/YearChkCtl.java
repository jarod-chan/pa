package cn.fyg.pa.interfaces.yearchk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.person.PersonRepository;
import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.yearchk.Fycheck;
import cn.fyg.pa.domain.yearchk.FycheckFactory;
import cn.fyg.pa.domain.yearchk.YearChkRepositroy;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.infrastructure.perisistence.FycheckDao;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.page.Cell;


/**
 * 重构年度考核类
 */
@Controller
@RequestMapping("/person/{personId}/yearchk")
public class YearChkCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(YearChkCtl.class);
	
	@Resource
	PersonRepository personRepository;
	@Resource
	YearConfigService yearConfigService;
	@Resource 
	YearChkRepositroy yearChkRepositroy;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		logger.info("initPerson");
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		try {
			Long year=yearConfigService.getEnableYear();
			List<PersonChkBean> yearChkBeans = yearChkRepositroy.getPersonYearChkResult(year, person);
			PageBeanBuilder builder=new PageBeanBuilder(yearChkBeans);
			int needChkPersons=personRepository.countStaffByType(person.getType())-1;
			PageBean pageBean = builder.builder(year, needChkPersons);
			map.put("person", person);
			map.put("pageBean", pageBean);
		} catch (EnableYearNotExist e) {
			new SessionMPR(session).setMessage("当前时间无法进行年终员工考核");
		}
		map.put("message",new SessionMPR(session).getMessage());
		return "yearchk/list";
	}

	@RequestMapping(value="/personchk/${colid}",method=RequestMethod.GET)
	public String personchk(@PathVariable("colid") Long colPersonId,@ModelAttribute("person")Person chkPerson,Map<String,Object> map,HttpSession session){
		Long year=0L;
		try {
			year=yearConfigService.getEnableYear();
		}catch (EnableYearNotExist e) {
			new SessionMPR(session).setMessage("当前时间无法进行年终员工考核");
		}
		
		List<Person> sameTypePerson=personRepository.getStaffByType(chkPerson.getType());
		Person colPerson=personRepository.find(colPersonId);
		List<CellBean> cellBeans=createDefaultList(year,chkPerson,colPerson,sameTypePerson);
		List<Fycheck> hasChecks=yearChkRepositroy.getPersonYearChkAboutPerson(year, colPerson, chkPerson);
		List<Fycheck> hasChecksForColPerson=changeChecksForColPerson(colPersonId,hasChecks);
		Map<String,Fycheck> hasChecksValues=changeChecksToMap(hasChecksForColPerson);
		cellBeans=setValueTocellBeans(cellBeans,hasChecksValues);
		map.put("cellBeans", cellBeans);
		return "yearchk/personchk";
	}

	private List<CellBean> setValueTocellBeans(List<CellBean> cellBeans,
			Map<String, Fycheck> hasChecksValues) {
		for (CellBean cellBean : cellBeans) {
			String key=cellBean.getColPerson().getId()+":"+cellBean.getRowPerson().getId();
			Fycheck fycheck=hasChecksValues.get(key);
			if(fycheck!=null){
				cellBean.setFycheck(fycheck);
			}
		}
		return cellBeans;
	}

	private Map<String, Fycheck> changeChecksToMap(
			List<Fycheck> hasChecksForColPerson) {
		Map<String, Fycheck> returnMap=new HashMap<String, Fycheck>();
		for (Fycheck fycheck : hasChecksForColPerson) {
			returnMap.put(fycheck.getColId()+":"+fycheck.getRowId(), fycheck);
		}
		return returnMap;
	}

	private List<Fycheck> changeChecksForColPerson(Long colPersonId,List<Fycheck> hasChecks) {
		for (Fycheck fycheck : hasChecks) {
			if(!fycheck.getColId().equals(colPersonId)){
				swapColIdAndRowId(fycheck);
			}
		}
		return hasChecks;
	}

	private void swapColIdAndRowId(Fycheck fycheck) {
		Long tempRowId=fycheck.getRowId();
		fycheck.setRowId(fycheck.getColId());
		fycheck.setColId(tempRowId);
	}

	private List<CellBean> createDefaultList(Long year,Person chkPerson,Person colPerson,List<Person> rowPersons) {
		List<CellBean> retList=new ArrayList<CellBean>();
		for(Person rowPerson:rowPersons){
			CellBean cellBean=new CellBean();
			cellBean.setColPerson(colPerson);
			cellBean.setRowPerson(rowPerson);
			Fycheck fycheck=FycheckFactory.createFycheck(year, colPerson.getId(), rowPerson.getId(), chkPerson.getId());
			cellBean.setFycheck(fycheck);
			retList.add(cellBean);
		}
		
		return retList;
	}






	@Resource
	private FycheckDao fycheckDao;
	
	@Resource
	private PersonDao fypersonDao;
	
	private Map<String,Fycheck> hasCheckMap;
	
	@RequestMapping(value="/showall",method=RequestMethod.GET)
	public String listAllFycheck(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		logger.info("listAllFycheck");
		
		Long personId=person.getId();
		
		List<Fycheck> check=fycheckDao.getFycheckByUserid(personId);
		hasCheckMap=changeCheckToMap(check);
		
		List<Person> people = fypersonDao.getAllFypersonSameTypeAndNotSelfAndNotManage(personId,person.getType());
		List<List<Cell>> tableData=makeTableList(people);
		
		List<Cell> tableHead=tableData.get(0);
		
		map.put("currPerson",person);
		map.put("tableData", tableData);
		map.put("tableHead",tableHead);
		map.put("message",new SessionMPR(session).getMessage());
		return "yearchk/list_check";
	}
	
	private Map<String,Fycheck> changeCheckToMap(List<Fycheck> check) {
		Map<String,Fycheck> map=new HashMap<String,Fycheck>();
		for (Fycheck fycheck : check) {
			map.put(fycheck.getColId()+";"+fycheck.getRowId(), fycheck);
		}
		return map;
	}

	private List<List<Cell>> makeTableList(List<Person> people){
		
		List<List<Cell>> retList=new ArrayList<List<Cell>>();
		
		List<Person> rowPeople=new ArrayList<Person>();
		List<Person> colPeople=new ArrayList<Person>();
		rowPeople.add(null);
		colPeople.add(null);
		for (Person fyperson : people) {
			rowPeople.add(fyperson);
			colPeople.add(fyperson);
		}
		Iterator<Person> iterator=rowPeople.iterator();
		for (int i=0;iterator.hasNext();i++) {
			Person rowPerson = (Person) iterator.next();
			List<Cell> cellList=makeRowList(i,rowPerson,colPeople);
			retList.add(cellList);
		}
				
		return retList;
	}

	private List<Cell> makeRowList(int row, Person rowPerson, List<Person> colPeople) {
		List<Cell> retList=new ArrayList<Cell>();
		Iterator<Person> iterator=colPeople.iterator();
		for(int col=0;iterator.hasNext();col++){
			Person colPerson = (Person) iterator.next();
			Cell cell = makeCellList(row, rowPerson, col, colPerson);
			retList.add(cell);
		}
		return retList;
	}

	private Cell makeCellList(int row, Person rowPerson, int col,Person colPerson) {
		Cell cell=new Cell();
		if(row==0&&col==0){
			cell.setType("empty");
		}else if (row==0){
			cell.setType("person");
			cell.setFyperson(colPerson);
		}else if(col==0){
			cell.setType("person");
			cell.setFyperson(rowPerson);
		}else if(col>row){
			cell.setType("data");
			Fycheck fycheck = getFycheck(colPerson.getId(),rowPerson.getId());
			cell.setDataCell(fycheck);
			cell.setFyperson(colPerson);
			cell.setScperson(rowPerson);
		}else{
			cell.setType("empty");
		}
		return cell;
	}

	private Fycheck getFycheck(Long cowId, Long rowId) {
		Fycheck dataCell=hasCheckMap.get(cowId+";"+rowId);
		if(dataCell==null){
			dataCell=new Fycheck();
			dataCell.setColId(cowId);
			dataCell.setRowId(rowId);
			dataCell.setVal(new Long(0));
		}
		return dataCell;
	}

}
