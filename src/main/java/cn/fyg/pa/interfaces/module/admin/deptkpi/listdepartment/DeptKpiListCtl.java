package cn.fyg.pa.interfaces.module.admin.deptkpi.listdepartment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.department.DepartmentRepository;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/deptkpi/{year}")
public class DeptKpiListCtl {
	
	private interface Page {
		String PATH = "deptkpi/listdepartment/";
		String LIST = PATH + "list";
	}
	
	@Resource
	MessagePasser messagePasser;
	@Resource 
	DepartmentRepository departmentRepository;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(@PathVariable("year")Long year,Map<String,Object> map){
		List<Department> departments = departmentRepository.findAllDepartmentsOrderById();
		map.put("departments", departments);
		map.put("year", year);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.LIST;
	}

}
