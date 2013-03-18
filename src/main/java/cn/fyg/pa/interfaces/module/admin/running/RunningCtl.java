package cn.fyg.pa.interfaces.module.admin.running;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/workflow/running")
public class RunningCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(RunningCtl.class);
	
	private static final String PATH = "workflow/running/";
	private interface Page {
		String RUNNING = PATH + "running";
	}
	
	@Autowired
	RuntimeService runtimeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
		map.put("processInstances", processInstances);
		return Page.RUNNING;
	}
	

}
