package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao { 
   private DBUtil dbUtil;
   
   
   // 비밀번호 변경 매소드
   public void updateClientPw(Client client) {
	   
		// dbutil, rowCnt, conn, stmt 객체, flag 초기화
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null; // rs = null;
		try {
			// sql실행 , db연결
			conn = this.dbUtil.getConnection();
			String sql = "UPDATE client SET client_pw = PASSWORD(?) WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			System.out.println("stmt : " + stmt); // 디버깅
			stmt.setString(1, client.getClientPw());
			stmt.setString(2, client.getClientMail());
			stmt.executeUpdate();		
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(null, stmt, conn);
		}
	}

   
   
   
	// clinet 삭제 매소드
	public void deleteCartByClient(String clientMail) {

		// dbutil, rowCnt, conn, stmt 객체 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null; // rs = null;
		try {
			// sql실행 , db연결
			conn = this.dbUtil.getConnection();
			String sql = "DELETE FROM client WHERE client_mail = ?";
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
   
   
   
   
   // 회원정보 매소드 , 리스트 매소드
   public Client selectClientOne(String clientMail) {

		// dbutil, rowCnt, conn, stmt 객체 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Client client = null;

		try {
			// DB 연결 및 SQL문 실행
			conn = dbUtil.getConnection();
			String sql = "SELECT client_no clientNo, client_mail clientMail, date_format(client_date,'%Y-%m-%d') clientDate FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			rs = stmt.executeQuery();

			while(rs.next()) {
				client = new Client();
				client.setClientNo(rs.getInt("clientNo"));
				client.setClientMail(rs.getString("clientMail"));
				client.setClientDate(rs.getString("clientDate"));
			}
		} catch (Exception e) { //  예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			dbUtil.close(rs, stmt, conn);
		}

		return client;
	}
   
   
   
   
   // 회원가입, insert 매소드
	public int insertClient(Client client) {

		// dbutil, rowCnt, conn, stmt 객체 초기화
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// sql문 실행, db연결
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO client(client_mail, client_pw, client_date) VALUES(?,PASSWORD(?),now())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			stmt.executeUpdate();
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(rs, stmt, conn);
		}

		return rowCnt;
	}

	
	
	
	// 이메일 중복검사
	public String selectClientMail(String clientMail) {

		// dbutil, rowCnt, conn, stmt, rs 객체, return타입 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String returnClientMail = null;


		try {
		    // sql문 실행, db연결
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			rs = stmt.executeQuery();

			if(rs.next()) {
				returnClientMail = rs.getString("client_mail");
			}
		} catch (Exception e) { // 예외 처리
			e.printStackTrace();
		} finally { // 할당값 해제
			this.dbUtil.close(rs, stmt, conn);
		}

		return returnClientMail;
	}
	
	
	
	
   // 로그인 매소드
   public Client login(Client client) {
	   
      this.dbUtil = new DBUtil();
      Client returnClient = null;
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
    
	         conn = this.dbUtil.getConnection();
	         String sql = "SELECT  client_No clientNo, client_mail clientMail FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, client.getClientMail());
	         stmt.setString(2, client.getClientPw());
	         rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	            returnClient = new Client();
	            returnClient.setClientNo(rs.getInt("clientNo"));
	            returnClient.setClientMail(rs.getString("clientMail"));
	            
	         }
	  } catch (Exception e) {
	     e.printStackTrace();
	  } finally {
	     this.dbUtil.close(rs, stmt, conn);
	  }
	  
	  return returnClient;
   }
}