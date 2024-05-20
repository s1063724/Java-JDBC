package tw.com.project.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.project.dao.ProductDao;
import tw.com.project.domain.Product;
import tw.com.project.utils.DBUtils;

public class ProductDaoImpl implements ProductDao{

	@Override
	public void add(Product p) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String addSQL="insert into product(name,price,intro)values(?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(addSQL);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getPrice());
			ps.setString(3, p.getIntro());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String deleteSQL="delete from product where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(deleteSQL);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Product p) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String updateSQL="update product set name=?,price=?,intro=? where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(updateSQL);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getPrice());
			ps.setString(3, p.getIntro());
			ps.setInt(4, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultSet Search(Product p) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String searchSQL="select id as 編號,name as 商品名稱,price as 商品價錢,intro as 商品種類 from product where intro=?";
		ResultSet rs=null;
		try {
			PreparedStatement ps = conn.prepareStatement(searchSQL);
			ps.setString(1, p.getIntro());
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	


	@Override
	public ResultSet columnName() {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String searchSQL="select id as 編號,name as 商品名稱,price as 商品價錢,intro as 商品種類 from product";
		ResultSet rs=null;
		PreparedStatement ps;
		try {
			ps=conn.prepareStatement(searchSQL);
			rs=ps.executeQuery();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rs;
	}

	

}
