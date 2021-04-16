package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;
import mall.client.vo.Orders;

public class OrdersDao {
	private DBUtil dbUtil;
	
	public List<Map<String, Object>> selectOrdersListByClient(int clientNo) {
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT o.orders_no ordersNo, o.ebook_no ebookNo, o.orders_date ordersDate, o.orders_state ordersState, e.ebook_title ebookTitle, e.ebook_price ebookPrice FROM orders o INNER JOIN ebook e ON o.ebook_no = e.ebook_no WHERE o.client_no =?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clientNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ordersNo", rs.getInt("ordersNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ordersDate", rs.getString("ordersDate"));
				map.put("ordersState", rs.getString("ordersState"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookPrice", rs.getInt("ebookPrice"));
				list.add(map);
			}
			
		} catch(Exception e) { //예외처리
			// 예외가 발생하면 시스템 멈추고 함수 호출 스텍의 구조를 콘솔창에 그대로 출력한다.
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(null, stmt, conn);
		}
		return list;
	}
	
	
	
	
	public int insertOrders(Orders orders) {
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try { 
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO orders(ebook_no, client_no, orders_date, orders_state) VALUES (?, ? ,NOW(), '주문완료')";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getEbookNo());
			stmt.setInt(2, orders.getClientNo());
			System.out.println(stmt + "<-- stmt : insertOrders()");
			rowCnt = stmt.executeUpdate();
		} catch(Exception e) { //예외처리
			// 예외가 발생하면 시스템 멈추고 함수 호출 스텍의 구조를 콘솔창에 그대로 출력한다.
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(null, stmt, conn);
		}
		return rowCnt;
	}
}
