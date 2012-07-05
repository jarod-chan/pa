package cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.DeptKpiItemService;
import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.companykpiitem.IdrCompanyRepository;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.edit.PageEdit;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.list.PageBean;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class DeptKpiEvaluateCtl {
	
	private interface Page {
		String PATH = "deptkpi/evaluate/";
		String LIST = PATH + "list";
		String EVALUATE=PATH+"evaluate";
	}
	
	@Resource
	MessagePasser messagePasser;
	@Resource 
	IdrCompanyRepository idrCompanyRepository;
	@Resource 
	DepartmentRepository departmentRepository;
	@Resource
	DeptKpiItemRepository deptKpiItemRepository;
	@Resource
	DeptKpiItemService deptKpiItemService;
	@Resource
	DeptKpiEvaluateFacade deptKpiEvaluateFacade;
	
	
	@RequestMapping(value="/evaluate",method=RequestMethod.GET)
	public String toList(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		PageBean pageBean=deptKpiEvaluateFacade.getDeptKpiForEvaluateList(year,departmentId);
		map.put("pageBean", pageBean);
		map.put(Constant.MESSAGE_NAME,messagePasser.getMessage());
		return Page.LIST;
	}
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}/evaluate",method=RequestMethod.GET)
	public String toEvaluate(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId,Map<String,Object> map){
		Department department=departmentRepository.find(departmentId);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		List<DeptKpiItem> deptKpiItems = deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(year, department,idrCompany);
		map.put("year", year);
		map.put("department", department);
		map.put("idrCompany", idrCompany);
		map.put("deptKpiItems", deptKpiItems);
		map.put(Constant.MESSAGE_NAME,messagePasser.getMessage());
		return Page.EVALUATE;
	}
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}/evaluate",method=RequestMethod.POST)
	public String save(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId,HttpServletRequest request,Map<String,Object> map){
		Department department=departmentRepository.find(departmentId);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		List<DeptKpiItem> deptKpiItems = deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(year, department,idrCompany);
		PageEdit pageEdit=new PageEdit();
		pageEdit.setDeptKpiItems(deptKpiItems);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(pageEdit);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		deptKpiItemService.saveDeptKpiItems(pageEdit.getDeptKpiItems());
		return "redirect:../../evaluate";
	}

}
