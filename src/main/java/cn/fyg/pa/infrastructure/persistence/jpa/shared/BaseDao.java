package cn.fyg.pa.infrastructure.persistence.jpa.shared;

public interface BaseDao<T> {
    
	T find(Long id);
	
	T save(T t);
	  
}
