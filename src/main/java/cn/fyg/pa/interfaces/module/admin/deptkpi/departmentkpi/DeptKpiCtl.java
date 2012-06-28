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

import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.companykpiitem.IdrCompanyRepository;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemFactory;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.edit.EditPage;
import cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.list.ListPage;
import cn.fyg.pa.interfaces.module.admin.deptkpi.departmentkpi.dto.preview.PreviewPage;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

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
	@Resource
	MessagePasser messagePasser;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		ListPage listPage=deptKpiFacade.getDeptKpiByYearAndDepartment(year,departmentId);
		map.put("listPage", listPage);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "deptkpi/list";
	}
	
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String toPreview(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		PreviewPage previewPage = deptKpiFacade.getDeptKpiForPreview(year, departmentId);
		map.put("previewPage", previewPage);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "deptkpi/preview";
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		Result result = deptKpiFacade.commitDeptKpi(year, departmentId);
		if(result.pass()){
			messagePasser.setMessage("提交通过！");
		}else{
			messagePasser.setMessage(String.format("提交失败，%s!", result.cause()));
		}
		return "redirect:../"+departmentId;
	}
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId,Map<String,Object> map){
		Department department=departmentRepository.find(departmentId);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		List<DeptKpiItem> deptKpiItems = deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(year, department,idrCompany);
		map.put("year", year);
		map.put("department", department);
		map.put("idrCompany", idrCompany);
		map.put("deptKpiItems", deptKpiItems);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "deptkpi/edit";
	}
	
	
	@RequestMapping(value="/idrcompany/{idrcompanyId}/save",method=RequestMethod.POST)
	public String save(HttpServletRequest request,@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,@PathVariable("idrcompanyId")Long idrcompanyId){

		Department department=departmentRepository.find(departmentId);
		IdrCompany idrCompany = idrCompanyRepository.find(idrcompanyId);
		List<DeptKpiItem> deptKpiItems = deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(year, department,idrCompany);
		String[] deptKpiItemsKey = request.getParameterValues("deptKpiItemsKey");
		List<DeptKpiItem> receiveDeptKpiItems=createDeptKpiItems(deptKpiItemsKey,deptKpiItems);
		EditPage editPage=new EditPage();
		editPage.setDeptKpiItems(receiveDeptKpiItems);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(editPage);//XXX 能否修改这里的逻辑？
		binder.bind(request);
		deptKpiFacade.saveDeptKpiItems(year,departmentId,idrcompanyId,editPage.getDeptKpiItems());
		messagePasser.setMessage("保存成功！");
		return "redirect:../../../"+departmentId;
	}

	private List<DeptKpiItem> createDeptKpiItems(String[] deptKpiItemsKey,List<DeptKpiItem> deptKpiItems) {
		Map<String,DeptKpiItem> deptKpiItemsMap=new HashMap<String,DeptKpiItem>();
		for (DeptKpiItem deptKpiItem : deptKpiItems) {
			deptKpiItemsMap.put(String.valueOf(deptKpiItem.getId()), deptKpiItem);
		}
		List<DeptKpiItem> retList=new ArrayList<DeptKpiItem>();
		for (String key : deptKpiItemsKey) {
			DeptKpiItem deptKpiItem=deptKpiItemsMap.get(key);
			if(deptKpiItem==null){
				deptKpiItem=DeptKpiItemFactory.createDeptKpiItem();
			}
			retList.add(deptKpiItem);
		}
		return retList;
	}

}
