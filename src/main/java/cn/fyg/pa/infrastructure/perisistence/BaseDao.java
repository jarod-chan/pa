package cn.fyg.pa.infrastructure.perisistence;

public interface BaseDao<T> {
    
	T find(Long id);
	
	T save(T t);
	
	void remove(T t);
    
}
