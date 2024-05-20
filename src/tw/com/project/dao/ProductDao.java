package tw.com.project.dao;

import java.sql.ResultSet;

import tw.com.project.base.BaseDao;
import tw.com.project.domain.Product;

public interface ProductDao extends BaseDao<Product>{
	//取得商品列表
	public ResultSet columnName();
}
