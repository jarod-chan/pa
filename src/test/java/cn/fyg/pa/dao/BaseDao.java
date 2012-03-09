package cn.fyg.pa.dao;

public interface BaseDao<T> {
    public void create (T t);
    public void delete (T t);
    public void update (T t);
    
}
