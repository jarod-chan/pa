package cn.fyg.pa.domain.shared.state;



public abstract class AbstractStateAction<T> implements StateAction<T> {
	
	@Override
	public void next(T t) throws StateChangeException {
		if(checkNext(t)){
			doNext(t);
		}
	}

	protected abstract boolean checkNext(T t) throws StateChangeException;

	protected abstract void doNext(T t);
	
	public void back(T t) throws StateChangeException {
		if(checkBack(t)){
			doBack(t);
		}
	};
	
	protected abstract boolean checkBack(T t) throws StateChangeException;
	
	protected abstract void doBack(T t);
}
