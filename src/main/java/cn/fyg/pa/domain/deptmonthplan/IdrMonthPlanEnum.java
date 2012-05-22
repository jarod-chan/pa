package cn.fyg.pa.domain.deptmonthplan;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.domain.shared.CommonEnum;
import cn.fyg.pa.domain.shared.state.StateChangeException;

public enum IdrMonthPlanEnum implements CommonEnum{
	NEW("新建"){

		@Override
		void doNext() {
		}

		@Override
		void doback() {
			
		}
		
	},
	SAVED("暂存") {
		
		@Override
		protected boolean checkNext() throws StateChangeException {
			if(idrMonthPlanBill.getIdrTasks().isEmpty()){
				throw new StateChangeException("工作计划不能为空");
			}
			for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
				if(StringUtils.isBlank(idrTask.getContext())){
					throw new StateChangeException("工作计划内容不能为空");
				}
			}
			return true;
		}

		@Override
		void doNext() {
			this.idrMonthPlanBill.setState(SUBMITTED);
		}

		@Override
		void doback() {
		}

	},
	
	SUBMITTED("已提交"){
		@Override
		protected boolean checkNext() throws StateChangeException {
			return true;
		}
		@Override
		void doNext() {
			this.idrMonthPlanBill.setState(EXECUTE);
		}
		
		@Override
		protected boolean checkBack() throws StateChangeException{
			return true;
		}

		@Override
		void doback() {
			this.idrMonthPlanBill.setState(SAVED);
		}
		
	},
	
	
	EXECUTE("执行中") {
		
		@Override
		protected boolean checkNext() throws StateChangeException {
			for(IdrTask idrTask:idrMonthPlanBill.getIdrTasks()){
				if(StringUtils.isBlank(idrTask.getSummary())){
					throw new StateChangeException("工作总结不能为空");
				}
			}
			return true;
		}
		
		@Override
		void doNext() {
			this.idrMonthPlanBill.setState(FINISHED);
		}

		@Override
		void doback() {
		}
	},
	
	
	FINISHED("已完成") {
		@Override
		void doNext() {
		}

		@Override
		void doback() {
		}
	};

	protected String name;
	
	protected IdrMonthPlanBill idrMonthPlanBill;
	
	protected boolean checkNext()throws StateChangeException{
		throw new StateChangeException(String.format("状态【%s】无法支持该操作 ", this.name));
	};
	
	abstract void doNext();
	
	public void next() throws StateChangeException{
		if(checkNext()){
			doNext();
		}
	};
	
	protected boolean checkBack() throws StateChangeException{
		throw new StateChangeException(String.format("状态【%s】无法支持该操作！ ", this.name));
	}
	
	abstract void doback();
	
	public void back() throws StateChangeException{
		if(checkBack()){
			doback();
		}
	}
	
	
	private IdrMonthPlanEnum(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public IdrMonthPlanBill getIdrMonthPlanBill() {
		return idrMonthPlanBill;
	}

	public void setIdrMonthPlanBill(IdrMonthPlanBill idrMonthPlanBill) {
		this.idrMonthPlanBill = idrMonthPlanBill;
	}
	
	

}
