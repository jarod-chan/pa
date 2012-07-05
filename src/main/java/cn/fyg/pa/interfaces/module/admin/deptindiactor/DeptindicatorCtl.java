package cn.fyg.pa.interfaces.module.admin.deptindiactor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/deptindicator")
public class DeptindicatorCtl {
	
	@Resource
	MessagePasser messagePasser;
	@Resource
	DeptindiactorFacade deptindiactorFacade;
	
	@RequestMapping(value="/{year}",method=RequestMethod.GET)
	public String toList(@PathVariable("year")Long year,Map<String,Object> map){
		List<ListBean> listBeans = deptindiactorFacade.getListBeans(year);
		map.put("listBeans", listBeans);
		map.put("year", year);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "deptindicator/list";
	}
	
	@RequestMapping(value="/{year}/department/{departmentId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year")Long year,@PathVariable("departmentId") Long departmentId,Map<String,Object> map){
		DeptIndicator deptIndicator=deptindiactorFacade.getByYearAndDepartment(year, departmentId);
		map.put("deptIndicator", deptIndicator);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "deptindicator/edit";
	}
	
	@RequestMapping(value="/{year}/department/{departmentId}/save",method=RequestMethod.POST)
	public String save(DeptIndicator deptIndicator,Map<String,Object> map){
		deptIndicator=deptindiactorFacade.saveDeptIndicator(deptIndicator);
		messagePasser.setMessage("保存成功");
		return "redirect:/admin/deptindicator/"+deptIndicator.getYear();
	}

	
}
