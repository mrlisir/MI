package dao;

import java.sql.*;
import java.util.*;

import db.DBManager;
import model.Type;


public class TypeDAO {
	// 按分类查询商品
			public List<Type> queryByTypeID(int typeID) {
				Connection conn = DBManager.getConn();
				PreparedStatement pstmt = null;
				List<Type> type = new ArrayList<Type>();
				try {
					try {
						String sql = null;
						if(typeID == -1){
							sql = "select * from type";
							pstmt = conn.prepareStatement(sql);
						}else{
							sql = " select * from type where typeID=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(typeID, 1);						
					}
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							Type type1 = new Type();
							type1.setTypeID(rs.getInt("typeID"));
							type1.setTypename(rs.getString("typename"));
							type.add(type1);
						}
					}finally {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return type;
			}
			
			
}
