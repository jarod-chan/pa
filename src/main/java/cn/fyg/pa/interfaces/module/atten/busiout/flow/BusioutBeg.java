package cn.fyg.pa.interfaces.module.atten.busiout.flow;

import java.math.BigDecimal;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class BusioutBeg implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariableLocal("isAggree", Boolean.TRUE);
		execution.setVariableLocal("acturlDay", new BigDecimal("7.0"));

	}

}
