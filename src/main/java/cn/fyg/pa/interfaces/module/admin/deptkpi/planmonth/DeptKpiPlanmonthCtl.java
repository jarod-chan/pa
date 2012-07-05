package cn.fyg.pa.interfaces.module.admin.deptkpi.planmonth;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.DeptKpiItemService;
import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.edit.PageEdit;
import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.PreviewFacade;
import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.dto.PreviewPage;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class DeptKpiPlanmonthCtl {
	
	private interface Page {
		String PATH = "deptkpi/planmonth/";
		String PLANMONTH = PATH + "planmonth";
	}
	
	@Resource
	MessagePasser messagePasser;
	@Resource
	PreviewFacade reviewFacade;
	@Resource
	DeptKpiService deptKpiService;
	@Resource
	DeptKpiItemService deptKpiItemService;
	@Resource
	DepartmentRepository departmentRepository;
	
	

	@RequestMapping(value="/planmonth",method=RequestMethod.GET)
	public String toPlanmonth(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		PreviewPage previewPage = reviewFacade.getDeptKpiForPreview(year, departmentId);
		map.put("previewPage", previewPage);
		map.put(Constant.MESSAGE_NAME,messagePasser.getMessage());
		return Page.PLANMONTH;
	}
	
	@RequestMapping(value="/planmonth",method=RequestMethod.POST)
	public String save(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,HttpServletRequest request,Map<String,Object> map){
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi = deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		PageEdit pageEdit=new PageEdit();
		pageEdit.setDeptKpiItems(deptKpi.getDeptKpiItems());
		ServletRequestDataBinder binder = new ServletRequestDataBinder(pageEdit);//XXX 能否修改这里的逻辑？
		binder.bind(request);	
		deptKpiItemService.saveDeptKpiItems(pageEdit.getDeptKpiItems());
		messagePasser.setMessage("保存成功！");
		return "redirect:planmonth";
	}
}
