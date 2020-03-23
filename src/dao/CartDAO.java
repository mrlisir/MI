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



public class CartDAO {
	//�����Ʒ�����ﳵ
	public boolean insert(Cart c){
		boolean result = false;			
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		int count = 0;		
		try{
			try{
				String sql = "insert into cart value (null,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,c.getCartname());
				pstmt.setFloat(2,c.getCartprice());
				pstmt.setInt(3,c.getProductID());
				pstmt.setString(4, c.getUname());
				count = pstmt.executeUpdate();
			}finally{
				pstmt.close();
				conn.close();
			}
		}catch(Exception a){				
			a.printStackTrace();
		}				
		if(count > 0){
			result = true;
		}else{
			result = false;
		}
		return result;			
	}
	//�����û���name�ҵ�������Ʒ
	public List<Cart> getProductByuname(String uname){
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		String sql = null;
		List<Cart> cart = new ArrayList<Cart>();
		try {
			try {
				sql = "select * from cart where uname=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uname);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					Cart c = new Cart();
					c.setCartID(rs.getInt("cartID"));
					c.setCartname(rs.getString("cartname"));
					c.setCartprice(rs.getFloat("cartprice"));
					c.setUname(rs.getString("uname"));
					cart.add(c);
				}
				
			}finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return cart;
	}
	//���ݹ��ﳵidɾ����Ʒ
	public boolean delete(int cartid){
		boolean result = false;			
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		int count = 0;		
		try{
			try{
				String sql = "delete from cart where cartID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cartid);						
				count = pstmt.executeUpdate();
			}finally{
				pstmt.close();
				conn.close();
			}
		}catch (Exception a) {				
			a.printStackTrace();
		}				
				if(count>0){
					result = true;
				}else{
					result = false;
				}
		return result;			
	}
	//��ѯ���ﳵ����Ʒ��
	public int shumu(String uname){
	int count = 0 ;
	PreparedStatement pstmt = null;	
	String sql = "select count(*) from cart where uname=?";
	Connection conn = DBManager.getConn();
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uname);		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			count = rs.getInt(1);
		}				
	} catch (Exception a) {
		a.printStackTrace();
	}
	return count;
	
	}
	


	

	

	
	
}
