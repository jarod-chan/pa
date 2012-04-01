package cn.fyg.pa.model.enums;

import org.apache.commons.lang.StringUtils;

import cn.fyg.pa.model.MonthChk;
import cn.fyg.pa.model.MonthChkItem;
import cn.fyg.pa.model.StateChangeException;


public enum MonthChkEnum implements CommonEnum {
	SAVED("暂存"){
		
		@Override
		protected boolean checkNext() throws StateChangeException {
			if(this.monthChk.getMonthChkItems().isEmpty()){
				throw new StateChangeException("月度工作任务不能为空");
			}
			for(MonthChkItem monthChkItem:this.monthChk.getMonthChkItems()){
				if(StringUtils.isBlank(monthChkItem.getTask())){
					throw new StateChangeException("工作内容不能为空");
				}
				if(monthChkItem.getWorkhour()==null){
					throw new StateChangeException("用时不能为空");
				}
			}
			return true;
		}

		@Override
		void doNext() {
			this.monthChk.setState(SUBMITTED);
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
			this.monthChk.setState(FINISHED);
			
		}
		
		@Override
		protected boolean checkBack() throws StateChangeException {
			return true;
		}

		@Override
		void doback() {
			for(MonthChkItem monthChkItem:this.monthChk.getMonthChkItems()){
				monthChkItem.setPoint(null);
			}
			this.monthChk.setState(SAVED);
		}
		
	},
	
	FINISHED("已完成"){

		@Override
		void doNext() {
		}

		@Override
		void doback() {			
		}
		
	};
	
	protected String name;
	protected MonthChk monthChk;
	
	private MonthChkEnum(String name){
		this.name=name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}
	
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

	public MonthChk getMonthChk() {
		return monthChk;
	}

	public void setMonthChk(MonthChk monthChk) {
		this.monthChk = monthChk;
	}
	

}
