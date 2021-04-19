package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class CategoryDao {
	private DBUtil dbUtil;

	// 카테고리 name 리스트 매소드
	public List<String> categoryNameList() {
		// List, DBUtil, Connection, PreparedStatement, ResultSet 객체 생성
		List<String> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.dbUtil.getConnection();
			String sql = "select category_name categoryName from category order by category_weight desc";
			stmt = conn.prepareStatement(sql);
			System.out.println("stmt(categoryName) : " + stmt);
			rs = stmt.executeQuery();

			while(rs.next()) {
				String cn = rs.getString("category_name");
				list.add(cn);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}

		return list;
	}
}