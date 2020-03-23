package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.User;
import db.DBManager;

public class UserDAO {
	
	public boolean insert(User u) {
		boolean result = false;

		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		int count = 0;
		
				String sql = "insert into users value (null,?,?)";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getUname());
					pstmt.setString(2, u.getUpwd());
					count = pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		if (count > 0) {
			result = true;
		} else {
			result = false;
		}

		return result;


	}
	
	
	public List<User> select(User u) {
		Connection conn = DBManager.getConn();
		PreparedStatement pstmt = null;
		List<User> at = new ArrayList<User>();
		try {
			try {
				String sql = null;
				if(u.getUid()!=0){
					sql = " select * from users where uid=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(u.getUid(), 1);
				}else if(u.getUpwd()!=null){
					sql = " select * from users where uname=? and upwd=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getUname());
					pstmt.setString(2, u.getUpwd());
				}else{
					sql = " select * from users where uname=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, u.getUname());
				}
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					User te = new User();
					te.setUid(rs.getInt("uid"));
					te.setUname(rs.getString("uname"));
					te.setUpwd(rs.getString("upwd"));
					at.add(te);
				}
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return at;
	}
}