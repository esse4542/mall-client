package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CartDao {
	private DBUtil dbUtil;
	public List<Map<String, Object>> selectCartList(String clientMail) {
		
		// 배열 선언 및 변수 초기화
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// DB
		try {
			conn = this.dbUtil.getConnection();										
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no WHERE c.client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			rs = stmt.executeQuery();

			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}

		return list;
	}
}