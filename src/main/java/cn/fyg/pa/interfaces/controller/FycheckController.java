package cn.fyg.pa.interfaces.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import cn.fyg.pa.domain.person.Person;
import cn.fyg.pa.domain.yearchk.Fycheck;
import cn.fyg.pa.infrastructure.perisistence.FycheckDao;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;
import cn.fyg.pa.interfaces.page.Cell;
import cn.fyg.pa.interfaces.page.DataPage;
import cn.fyg.pa.interfaces.tool.CommonModelAndView;
import cn.fyg.pa.interfaces.tool.CookieUtil;


@Controller
@RequestMapping("/fycheck/")
public class FycheckController {
	
	private static final Logger logger = LoggerFactory.getLogger(FycheckController.class);

	@Autowired
	private PersonDao fypersonDao;
	@Autowired
	private FycheckDao fycheckDao;
	
	private Map<String,Fycheck> hasCheckMap;
	
	@RequestMapping(method=RequestMethod.POST,value="save") 
	public ModelAndView saveFycheck(@ModelAttribute DataPage datapage,@RequestParam(value="personId",required=false) Long personId,@CookieValue(value="chkstr",required=false) String cookieChkstr) {
		logger.info("Received request to fycheck save");
		Person currPerson=fypersonDao.find(personId);
		if(!CookieUtil.checkLogin(cookieChkstr, currPerson))
			return CommonModelAndView.getHomeModelAndView();
		
		List<Fycheck> list=new ArrayList<Fycheck>();
		for (int i = 0,len=datapage.getId().size(); i < len; i++) {
			Fycheck fycheck=new Fycheck();
			fycheck.setId(datapage.getId().get(i));
			fycheck.setColId(datapage.getColId().get(i));
			fycheck.setRowId(datapage.getRowId().get(i));
			fycheck.setVal(datapage.getVal().get(i));
			fycheck.setChkId(personId);
			list.add(fycheck);
		}
		fycheckDao.saveAll(list);
		return listAllFycheck(personId,"考核保存成功，可以继续操作或者退出。",cookieChkstr);
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listAllFycheck(@RequestParam(value="personId",required=true) Long personId,String msg,@CookieValue(value="chkstr",required=false) String cookieChkstr) {
		logger.info("Received request to fycheck list");
		
		Person currPerson=fypersonDao.find(personId);
		if(!CookieUtil.checkLogin(cookieChkstr, currPerson))
			return CommonModelAndView.getHomeModelAndView();

		
		List<Fycheck> check=fycheckDao.getFycheckByUserid(personId);
		hasCheckMap=changeCheckToMap(check);
		
		List<Person> people = fypersonDao.getAllFypersonSameTypeAndNotSelfAndNotManage(personId,currPerson.getType());
		List<List<Cell>> tableData=makeTableList(people);
		
		List<Cell> tableHead=tableData.get(0);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("currPerson",currPerson);
		mav.addObject("tableData", tableData);
		mav.addObject("tableHead",tableHead);
		mav.addObject("msg",msg);
		mav.setViewName("fy/list_check");
		return mav;
		
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
