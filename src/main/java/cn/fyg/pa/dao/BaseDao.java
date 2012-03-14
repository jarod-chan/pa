package cn.fyg.pa.dao;

public interface BaseDao<T> {
    
	T find(Long id);
	
	T save(T t);
	
	void remove(T t);
    
}
