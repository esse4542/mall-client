package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao { 
   private DBUtil dbUtil;
   
   // 회원정보 매소드
   
   
   
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
	         String sql = "SELECT client_mail clientMail FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, client.getClientMail());
	         stmt.setString(2, client.getClientPw());
	         rs = stmt.executeQuery();
	         
	         while(rs.next()) {
	            returnClient = new Client();
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