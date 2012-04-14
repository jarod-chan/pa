package cn.fyg.pa.interfaces.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.Department;
import cn.fyg.pa.domain.model.IdrMonthPlanBill;
import cn.fyg.pa.domain.model.IdrTask;
import cn.fyg.pa.domain.model.Person;
import cn.fyg.pa.domain.model.StateChangeException;
import cn.fyg.pa.domain.model.enums.IdrMonthPlanEnum;
import cn.fyg.pa.domain.service.DepartmentService;
import cn.fyg.pa.domain.service.IdrMonthPlanBillService;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.infrastructure.perisistence.PersonDao;

@Controller
@RequestMapping("/mange/{personId}/idrmonthplan")
public class IdrMonthPlanCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(IdrMonthPlanCtl.class);
	
	public static Map<IdrMonthPlanEnum,String> PAGEMAP=new HashMap<IdrMonthPlanEnum,String>();
	
	static{
		PAGEMAP.put(IdrMonthPlanEnum.SAVED, "idrmonthplan/edit");
		PAGEMAP.put(IdrMonthPlanEnum.SUBMITTED, "idrmonthplan/view");
		PAGEMAP.put(IdrMonthPlanEnum.EXECUTE, "idrmonthplan/summary");
		PAGEMAP.put(IdrMonthPlanEnum.FINISHED, "idrmonthplan/view");
	}
	
	@Resource
	PersonDao personDao;
	
	@Resource
	IdrMonthPlanBillService idrMonthPlanBillService; 
	
	@Resource
	DepartmentService departmentService; 
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personDao.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		String departmentName=person.getDepartment();
		Department department=departmentService.findByName(departmentName);
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillService.getCurrentIdrMonthPlanBill(department);
		//XXX 
		Long contextSize=0L;
		for (IdrTask idrTask : idrMonthPlanBill.getIdrTasks()) {
			if(idrTask.getContext()!=null){
				contextSize=idrTask.getSn();
			}
		}
		map.put("contextSize", contextSize);
		map.put("person", person);
		map.put("idrMonthPlanBill", idrMonthPlanBill);
		map.put("message", new SessionMPR(session).getMessage());
		return PAGEMAP.get(idrMonthPlanBill.getState());
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrMonthPlanBill idrMonthPlanBill,HttpSession session){
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		idrMonthPlanBill=idrMonthPlanBillService.save(idrMonthPlanBill);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../idrmonthplan";
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(IdrMonthPlanBill idrMonthPlanBill,HttpSession session){
		idrMonthPlanBill.setState(IdrMonthPlanEnum.SAVED);
		idrMonthPlanBill=idrMonthPlanBillService.save(idrMonthPlanBill);
		String message="提交成功！";
		try {
			idrMonthPlanBillService.next(idrMonthPlanBill.getId());
		} catch (StateChangeException e) {
			message=String.format("提交失败，原因：%s", e.getMessage());
		}
		new SessionMPR(session).setMessage(message);
		return "redirect:../idrmonthplan";
	}
	
	@RequestMapping(value="/{idrmonthplanId}/summary",method=RequestMethod.POST)
	public String summary(@PathVariable("idrmonthplanId")Long idrmonthplanId,HttpServletRequest request,HttpSession session){
		String message="保存成功！";
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillService.find(idrmonthplanId);
		//TODO 此处有待进一步修改
		Iterator<IdrTask> iterator=idrMonthPlanBill.getIdrTasks().iterator();
		while(iterator.hasNext()){
			IdrTask idrTask=iterator.next();
			if(idrTask.getContext()==null){
				iterator.remove();
			}
		}
        ServletRequestDataBinder binder = new ServletRequestDataBinder(idrMonthPlanBill);  
        binder.bind(request);  
        idrMonthPlanBillService.save(idrMonthPlanBill);
		new SessionMPR(session).setMessage(message);
		return "redirect:../../idrmonthplan";
	}
	
	@RequestMapping(value="/{idrmonthplanId}/finish",method=RequestMethod.POST)
	public String finish(@PathVariable("idrmonthplanId")Long idrmonthplanId,HttpServletRequest request,HttpSession session){
		String message="工作计划完成！";
		IdrMonthPlanBill idrMonthPlanBill=idrMonthPlanBillService.find(idrmonthplanId);
		//TODO 此处有待进一步修改
		Iterator<IdrTask> iterator=idrMonthPlanBill.getIdrTasks().iterator();
		while(iterator.hasNext()){
			IdrTask idrTask=iterator.next();
			if(idrTask.getContext()==null){
				iterator.remove();
			}
		}
		ServletRequestDataBinder binder = new ServletRequestDataBinder(idrMonthPlanBill);  
        binder.bind(request);  
        idrMonthPlanBill=idrMonthPlanBillService.save(idrMonthPlanBill);
        try {
			idrMonthPlanBillService.next(idrMonthPlanBill.getId());
		} catch (StateChangeException e) {
			logger.error("", e);
			message=String.format("工作计划完成失败，原因：%s", e.getMessage());
		}
        new SessionMPR(session).setMessage(message);
		return "redirect:../../idrmonthplan";
	}
	
	@RequestMapping(value="/history",method=RequestMethod.GET)
	public String history(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		String departmentName=person.getDepartment();
		Department department=departmentService.findByName(departmentName);
		List<IdrMonthPlanBill> idrMonthPlanBills=idrMonthPlanBillService.getIdrMonthPlanBillByDepartmentAndState(department,IdrMonthPlanEnum.FINISHED);
		map.put("person", person);
		map.put("idrMonthPlanBills", idrMonthPlanBills);
		return "idrmonthplan/histroy";
	}

}
