package mall.client.commons;

import java.sql.*;

public class DBUtil {
	// 1. db연결
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
	//2. db자원(conn, stmt, rs) 해제
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) { // 뒤에서부터 차례대로
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
