package cn.fyg.pa.interfaces.module.admin.history;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/workflow/history")
public class HistoryCtl {
	
	private static final String PATH = "workflow/history/";
	private interface Page {
		String HISTORY = PATH + "history";
	}
	
	@Autowired
	HistoryService historyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toHistory(Map<String,Object> map){
		List<HistoricProcessInstance> history = historyService.createHistoricProcessInstanceQuery().list();
		map.put("history", history);
		return Page.HISTORY;
	}

}
