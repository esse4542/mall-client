package mall.client.commons;

import java.sql.*;

public class DBUtil {
	// 1. db����
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall", "root", "java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//2. db�ڿ�(conn, stmt, rs) ����
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) { // �ڿ������� ���ʴ��
		if(rs != null) {	
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		if(conn != null) {	
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
