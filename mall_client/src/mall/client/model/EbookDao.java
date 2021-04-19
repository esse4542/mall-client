package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	private DBUtil dbUtil;
	
	// 전체 행의 개수 구하는 메서드
	public int totalCount(String searchTitle, String categoryName) {
		// DBUtil, Connection, PreparedStatement, ResultSet, rowCnt 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int rowCnt = 0;

		try {
			// DB 연결 및 SQL문 실행
			conn = this.dbUtil.getConnection();
			String sql = "";

			if(searchTitle.equals("") && categoryName.equals("")) {
				sql = "SELECT count(*) cnt FROM ebook";
				stmt = conn.prepareStatement(sql);
			}
			else if(searchTitle.equals("") && !categoryName.equals("")) {
				sql = "SELECT count(*) cnt FROM ebook WHERE category_name like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+categoryName+"%");

			}
			else if(!searchTitle.equals("") && categoryName.equals("")) {
				sql = "SELECT count(*) cnt FROM ebook WHERE ebook_title like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
			}
			else if(!searchTitle.equals("") && !categoryName.equals("")) {
				sql = "SELECT count(*) cnt FROM ebook WHERE ebook_title like ? AND category_name like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
				stmt.setString(2, "%"+categoryName+"%");
			}

			System.out.println("stmt(totalCount) : " + stmt); // 디버깅
			rs = stmt.executeQuery();

			while(rs.next()) {
				rowCnt = rs.getInt("cnt");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return rowCnt;
	}
		
	
	
	
	// ebookOne 매소드
	public Ebook selectEbookOne(int ebookNo) {
		this.dbUtil = new DBUtil();
		Ebook ebook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT ebook_no ebookNo, ebook_isbn ebookISBN, category_name categoryName, ebook_title ebookTitle, ebook_author ebookAuthor, ebook_company ebookCompany, ebook_page_count ebookPageCount, ebook_price ebookPrice, ebook_img ebookImg, ebook_summary ebookSummary, ebook_date ebookDate,ebook_state ebookState FROM ebook WHERE ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ebookNo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ebook = new Ebook();
		        ebook.setEbookNo(rs.getInt("ebookNo"));
		        ebook.setCategoryName(rs.getString("categoryName"));
		        ebook.setEbookISBN(rs.getString("ebookISBN"));
		        ebook.setEbookTitle(rs.getString("ebookTitle"));
		        ebook.setEbookAuthor(rs.getString("ebookAuthor"));
		        ebook.setEbookCompany(rs.getString("ebookCompany"));
		        ebook.setEbookPageCount(rs.getInt("ebookPageCount"));
		        ebook.setEbookPrice(rs.getInt("ebookPrice"));
		        ebook.setEbookSummary(rs.getString("ebookSummary"));
		        ebook.setEbookImg(rs.getString("ebookImg"));
		        ebook.setEbookDate(rs.getString("ebookDate"));
		        ebook.setEbookState(rs.getString("ebookState"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return ebook;
	}
	
	
	// 리스트
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage, String searchTitle, String categoryName) {
		this.dbUtil = new DBUtil();
		List<Ebook> list = new ArrayList<Ebook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "";
			if(searchTitle.equals("") && categoryName.equals("")) {
				sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
			} else if(searchTitle.equals("") && !categoryName.equals("")){
				sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE category_name like ? ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(!searchTitle.equals("") && categoryName.equals("")) {
				sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title like ? ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(!searchTitle.equals("") && !categoryName.equals("")) {
				sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title like ? AND category_name like ? ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
				stmt.setString(2, categoryName);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}

			System.out.println("stmt : " + stmt); // 디버깅
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebooKNo"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				list.add(ebook);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return list;
	}
}

