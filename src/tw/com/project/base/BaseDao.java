package tw.com.project.base;

import java.sql.ResultSet;


import tw.com.project.domain.Product;

public interface BaseDao<T> {
	public abstract void add(T t);
	public abstract void delete(int id);
	public abstract void update(T t);
	public abstract ResultSet Search(T t);
}
