package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Product;
import db.DBManager;


public class ProductDAO {
	//根据typeID找到分类商品
	public List<Product> getProductByTypeId(int typeID){
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		String sql = null;
		List<Product> product = new ArrayList<Product>();
		try {
			try {
				sql = "select * from product where typeID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, typeID);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Product p=new Product();
					p.setProductID(rs.getInt("productID"));
					p.setProductname(rs.getString("productname"));
					p.setPrice(rs.getFloat("price"));
					p.setDiscribe(rs.getString("discribe"));
					p.setImage(rs.getString("Image"));
					p.setTypeID(rs.getInt("typeID"));
					p.setDiscribe2(rs.getString("discribe2"));
					p.setColor(rs.getString("Color"));
					p.setNum(rs.getInt("num"));
					p.setEdition(rs.getString("edition"));
					product.add(p);
				}
				
			}finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return product;
	}
	//根据productID找到此商品
	public List<Product> getProductByproductID(int productID){
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		String sql = null;
		List<Product> product = new ArrayList<Product>();
		try {
			try {
				sql = "select * from product where productID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, productID);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Product p=new Product();
					p.setProductID(rs.getInt("productID"));
					p.setProductname(rs.getString("productname"));
					p.setPrice(rs.getFloat("price"));
					p.setDiscribe(rs.getString("discribe"));
					p.setImage(rs.getString("Image"));
					p.setTypeID(rs.getInt("typeID"));
					p.setDiscribe2(rs.getString("discribe2"));
					p.setColor(rs.getString("Color"));
					p.setNum(rs.getInt("num"));
					p.setEdition(rs.getString("edition"));
					product.add(p);
				}
				
			}finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return product;
	}
	//根据productname找到此商品
	public List<Product> getProductByproductname(String searchname){
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		String sql = null;
		List<Product> product = new ArrayList<Product>();
		try {
			try {
				sql = "select * from product where productname=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchname);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Product p=new Product();
					p.setProductID(rs.getInt("productID"));
					p.setProductname(rs.getString("productname"));
					p.setPrice(rs.getFloat("price"));
					p.setDiscribe(rs.getString("discribe"));
					p.setImage(rs.getString("Image"));
					p.setTypeID(rs.getInt("typeID"));
					p.setDiscribe2(rs.getString("discribe2"));
					p.setColor(rs.getString("Color"));
					p.setNum(rs.getInt("num"));
					p.setEdition(rs.getString("edition"));
					product.add(p);
				}
				
			}finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return product;
	}
	
	
//	/**
//	 * 查询数据库中部分数据 参数1：开始位置，从0开始 参数2：几条记录
//	 */
	public List<Product> getPageList (int begin, int count){
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;	
		List<Product> products = new ArrayList<Product>();
		String sql = "select * from product limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, count);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setProductID(rs.getInt("productID"));
				p.setProductname(rs.getString("productname"));
				p.setPrice(rs.getFloat("price"));
				p.setDiscribe(rs.getString("discribe"));
				p.setImage(rs.getString("Image"));
				p.setTypeID(rs.getInt("typeID"));
				p.setDiscribe2(rs.getString("discribe2"));
				p.setColor(rs.getString("Color"));
				p.setNum(rs.getInt("num"));
				p.setEdition(rs.getString("edition"));
				products.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
//	/**
//	 * 查询表中的记录总数
//	 */
	public int getSum(){
		int count = 0 ;
		PreparedStatement pstmt = null;	
		String sql = "select count(*) from product";
		Connection conn = DBManager.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}
//	/**
//	 * 方法2/查询所有
//	 */
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		PreparedStatement pstmt = null;	
		String sql = "select * from product";
		Connection conn = DBManager.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Product p=new Product();
				p.setProductID(rs.getInt("productID"));
				p.setProductname(rs.getString("productname"));
				p.setPrice(rs.getFloat("price"));
				p.setDiscribe(rs.getString("discribe"));
				p.setImage(rs.getString("Image"));
				p.setTypeID(rs.getInt("typeID"));
				p.setDiscribe2(rs.getString("discribe2"));
				p.setColor(rs.getString("Color"));
				p.setNum(rs.getInt("num"));
				p.setEdition(rs.getString("edition"));
				products.add(p);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
