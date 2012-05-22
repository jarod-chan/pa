package cn.fyg.pa.interfaces.deptkpi;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.companykpi.IdrCompany;
import cn.fyg.pa.domain.companykpi.IdrCompanyRepository;
import cn.fyg.pa.domain.department.Department;
import cn.fyg.pa.domain.department.DepartmentRepository;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.deptkpiitem.DeptKpiItemRepository;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class DeptKpiCtl {
	
	@Resource 
	IdrCompanyRepository idrCompanyRepository;
	
	@Resource 
	DepartmentRepository departmentRepository;
	
	@Resource
	DeptKpiItemRepository deptKpiItemRepository;
	
	@Resource
	DeptKpiFacade deptKpiFacade;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map,HttpSession session){
		ListPage listPage=deptKpiFacade.getDeptKpiByYearAndDepartment(year,departmentId);
		map.put("listPage", listPage);
		map.put("message", new SessionMPR(session).getMessage());
		return "deptkpi/list";
	}
	
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String preview(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map,HttpSession session){
		PreviewPage previewPage = deptKpiFacade.getDeptKpiForPreview(year, departmentId);
		map.put("previewPage", previewPage);
		map.put("message", new SessionMPR(session).getMessage());
		return "deptkpi/preview";
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map,HttpSession session){
		Result result = deptKpiFacade.commitDeptKpi(year, departmentId);
		if(result.pass()){
			new SessionMPR(session).setMessage("提交通过！");
		}else{
			new SessionMPR(session).setMessage(String.format("提交失败，%s!", result.cause()));
		}
		return "redirect:../"+departmentId;
	}
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId,Map<String,Object> map,HttpSession session){
		Department department=departmentRepository.find(departmentId);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		List<DeptKpiItem> deptKpiItems = deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(year, department,idrCompany);
		map.put("year", year);
		map.put("department", department);
		map.put("idrCompany", idrCompany);
		map.put("deptKpiItems", deptKpiItems);
		map.put("message", new SessionMPR(session).getMessage());
		return "deptkpi/edit";
	}
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}/save",method=RequestMethod.POST)
	public String save(EditPage editPage,@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId,HttpSession session){
		deptKpiFacade.saveDeptKpiItems(year,departmentId,idrcompanyId,editPage.getDeptKpiItems());
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:../../../"+departmentId;
	}

}
