package cn.fyg.pa.interfaces.module.atten.busiout.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.pa.application.BusioutService;
import cn.fyg.pa.domain.model.atten.busiout.BusiState;
import cn.fyg.pa.domain.model.atten.busiout.Busiout;
import cn.fyg.pa.domain.model.atten.opinion.ResultEnum;
import cn.fyg.pa.interfaces.module.atten.busiout.BusioutVarName;
import cn.fyg.pa.interfaces.module.shared.tool.FlowConstant;

public class BusioutEnd implements JavaDelegate {

	private Expression busioutServiceExp;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		BusioutService busioutService=(BusioutService)busioutServiceExp.getValue(execution);
		Long businessId = (Long) execution.getVariableLocal(FlowConstant.BUSINESS_ID);
		String opinion=(String) execution.getVariableLocal(BusioutVarName.OPINION);
		Busiout busiout = busioutService.find(businessId);
		//同意则状态变为确认，不同意则变为作废
		if(ResultEnum.agree.val().equals(opinion)){
			busiout.setBusiState(BusiState.passed);
		}
		if(ResultEnum.disagree.val().equals(opinion)){
			busiout.setBusiState(BusiState.voided);
		}
		busioutService.save(busiout);
	}

}
