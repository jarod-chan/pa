package cn.fyg.pa.domain.shared.state;

public interface StateEnum<T> {

	void next(T t) throws StateChangeException;
	
	void back(T t) throws StateChangeException;
}
