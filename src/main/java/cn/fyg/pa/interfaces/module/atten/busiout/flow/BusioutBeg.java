package cn.fyg.pa.interfaces.module.atten.busiout.flow;

import java.math.BigDecimal;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import cn.fyg.pa.application.UsershipService;
import cn.fyg.pa.domain.model.atten.opinion.ResultEnum;
import cn.fyg.pa.interfaces.module.atten.busiout.BusioutVarName;

public class BusioutBeg implements JavaDelegate {
	
	private Expression usershipServiceExp;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		UsershipService usershipService = (UsershipService) usershipServiceExp.getValue(execution);
		String checker = (String) execution.getVariableLocal(BusioutVarName.CHECKER);
		BigDecimal outDays=(BigDecimal) execution.getVariableLocal(BusioutVarName.OUTDAYS);
		String parentKey=usershipService.parent("T1", checker);
		//无上级则是总经理，直接审批通过
		if(parentKey==null){
			execution.setVariableLocal(BusioutVarName.IS_TMANAGE, true);
			execution.setVariableLocal(BusioutVarName.OPINION, ResultEnum.agree.val());
			return;
		}
		
		//判断是否最后一个节点
		execution.setVariableLocal(BusioutVarName.IS_TMANAGE, false);
		String tag=usershipService.userTag("T1", parentKey);
		RangeEnum level=RangeEnum.valueOf(tag);
		RangeEnum requireLevel=RangeEnum.requireLevel(outDays);
		if(level.compareLevel(requireLevel)>=0){
			execution.setVariableLocal(BusioutVarName.IS_LAST, true);
			execution.setVariableLocal(BusioutVarName.CHECKER, parentKey);
		}else{
			execution.setVariableLocal(BusioutVarName.IS_LAST, false);
			execution.setVariableLocal(BusioutVarName.CHECKER, parentKey);
		}
		
	}

}
