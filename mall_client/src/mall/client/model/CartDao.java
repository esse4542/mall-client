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
	
	// 장바구니 전체 목록 제거
	public void deleteCartByClient(String clientMail) {
		// dbutil, rowCnt, conn, stmt 객체, flag 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// sql실행 , db연결
			conn = this.dbUtil.getConnection();
			String sql = "DELETE FROM cart WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			System.out.println("stmt : " + stmt); // 디버깅
			stmt.setString(1, clientMail);
			stmt.executeUpdate();		
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(null, stmt, conn);
		}
	}

	
	
	// 장바구니 목록 상품 제거
	public void deleteCart(Cart cart) {
		// dbutil, rowCnt, conn, stmt 객체, flag 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// sql실행 , db연결
			conn = this.dbUtil.getConnection();
			String sql = "DELETE FROM cart WHERE client_mail = ? AND ebook_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.println("stmt : " + stmt); // 디버깅
			
			stmt.executeUpdate();		
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(null, stmt, conn);
		}
	}

	
	
	
	// 중복 이메일 매소드
	public boolean selectCilentMail(Cart cart) {
		boolean flag = true; // 중복 없음
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();										
			String sql =  "SELECT * FROM cart WHERE client_mail=? AND ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.println("insertCart stmt: "+ stmt);
			rs = stmt.executeQuery();
	        if(rs.next()) {
	        	flag = false; //중복있음
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		} 
		return flag;
	}
	
	
	// 입력 매소드
	public int insertCart(Cart cart) {
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();										
			String sql =  "INSERT INTO cart(client_mail, ebook_no, cart_date) VALUES(?, ?, NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.println("insertCart stmt: "+ stmt);
	        rowCnt = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		} 
		return rowCnt;
	}
	
	// 리스트
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