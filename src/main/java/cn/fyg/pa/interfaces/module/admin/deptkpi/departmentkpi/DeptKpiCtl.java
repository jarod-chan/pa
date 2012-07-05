package cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.model.companykpiitem.IdrCompanyRepository;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.model.deptkpi.DeptKpiRepository;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemFactory;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.list.ListPage;
import cn.fyg.pa.interfaces.module.admin.deptkpi.evaluate.dto.edit.PageEdit;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class DeptKpiCtl {
	
	private interface Page {
		String PATH = "deptkpi/breakdown/";
		String BREAKDOWN = PATH + "breakdown";
	}
	
	
	@Resource 
	IdrCompanyRepository idrCompanyRepository;	
	@Resource 
	DepartmentRepository departmentRepository;
	@Resource
	DeptKpiItemRepository deptKpiItemRepository;
	@Resource
	DeptKpiFacade deptKpiFacade;
	@Resource
	DeptKpiService deptKpiService;
	@Resource
	MessagePasser messagePasser;
	@Resource
	DeptKpiRepository deptKpiRepository;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		ListPage listPage=deptKpiFacade.getDeptKpiByYearAndDepartment(year,departmentId);
		map.put("listPage", listPage);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.BREAKDOWN;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		saveDeptKpiFromPage(request, year, departmentId);
		messagePasser.setMessage("保存成功！");
		return "redirect:/admin/deptkpi/{year}/department/{departmentId}";
	}

	private DeptKpi saveDeptKpiFromPage(HttpServletRequest request, Long year,Long departmentId) {
		Department department = departmentRepository.find(departmentId);
		DeptKpi deptKpi = deptKpiService.getDeptKpiByYearAndDepartment(year, department);
		String[] deptKpiItemsKey = request.getParameterValues("deptKpiItemsKey");
		List<DeptKpiItem> receiveDeptKpiItems=prepareDeptKpiItems(deptKpiItemsKey,deptKpi.getDeptKpiItems());

		PageEdit pageEdit=new PageEdit();
		pageEdit.setDeptKpiItems(receiveDeptKpiItems);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(pageEdit);//XXX 能否修改这里的逻辑？
		binder.bind(request);
		
		deptKpi.setDeptKpiItems(pageEdit.getDeptKpiItems());
		return deptKpiService.save(deptKpi);
	}
	
	private List<DeptKpiItem> prepareDeptKpiItems(String[] deptKpiItemsKey,List<DeptKpiItem> deptKpiItems) {
		List<DeptKpiItem> retList=new ArrayList<DeptKpiItem>();
		if(deptKpiItemsKey==null) return retList;
		if(deptKpiItems==null) return retList;
		
		Map<String,DeptKpiItem> deptKpiItemsMap=new HashMap<String,DeptKpiItem>();
		for (DeptKpiItem deptKpiItem : deptKpiItems) {
			deptKpiItemsMap.put(String.valueOf(deptKpiItem.getId()), deptKpiItem);
		}
		
		for (String key : deptKpiItemsKey) {
			DeptKpiItem deptKpiItem=deptKpiItemsMap.get(key);
			if(deptKpiItem==null){
				deptKpiItem=DeptKpiItemFactory.createDeptKpiItem();
			}
			retList.add(deptKpiItem);
		}
		return retList;
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(HttpServletRequest request,@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		DeptKpi deptKpi = saveDeptKpiFromPage(request, year, departmentId);
		Result result = deptKpiService.commitDeptKpi(deptKpi);
		if(result.pass()){
			messagePasser.setMessage("提交通过！");
		}else{
			messagePasser.setMessage(String.format("提交失败，%s!", result.cause()));
		}
		return "redirect:../"+departmentId;
	}

}
