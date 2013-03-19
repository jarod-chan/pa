package cn.fyg.pa.interfaces.module.atten.process;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.pa.interfaces.module.shared.tool.FlowConstant;

@Component
public class TaskFacade {
	
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	FormService formService;
	@Autowired
	RepositoryService repositoryService;
	
	public List<ProcessTaskBean> getProcessTasks(String userKey){
		List<ProcessTaskBean> result=new ArrayList<ProcessTaskBean>();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userKey).list();
		for (Task task : tasks) {
			ProcessTaskBean processTaskBean=new ProcessTaskBean();
			
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
			processTaskBean.setProcessName(processDefinition.getName());
			
			processTaskBean.setTaskName(task.getName());
			processTaskBean.setTaskId(task.getId());

			String formKey= formService.getTaskFormData(task.getId()).getFormKey();
			processTaskBean.setFormKey(formKey);
			
			String executionId = task.getExecutionId();
			Object obj=runtimeService.getVariableLocal(executionId, FlowConstant.BUSINESS_ID);
			String businessId=obj==null?"":obj.toString();
			processTaskBean.setBusinessId(businessId);

			result.add(processTaskBean);
		}
		return result;
	}

}
