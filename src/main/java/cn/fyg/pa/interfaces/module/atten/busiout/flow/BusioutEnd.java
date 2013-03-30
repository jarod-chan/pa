package cn.fyg.pa.interfaces.module.atten.busiout.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class BusioutEnd implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("\n\n\n\n流程执行完成\n\n\n\n");
	}

}
