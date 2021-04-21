package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Stats;

public class StatsDao {
	private DBUtil dbutil;

	//오늘 날짜 방문자 보기
	public Stats selectStatsByToday() {
	    Stats stats = null;
	    this.dbutil = new DBUtil();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	       conn = this.dbutil.getConnection();
	       String sql = "SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day = DATE(NOW())";
	       stmt = conn.prepareStatement(sql);
			System.out.println("selectStatsByToday stmt: "+stmt);
	       rs = stmt.executeQuery();
	       if (rs.next()) {
	          stats = new Stats();
	          stats.setStatsCount(rs.getLong("statsCount"));
	          stats.setStatsDay(rs.getString("statsDay"));
	       }

	    } catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	    	this.dbutil.close(rs, stmt, conn);
	    }
	    return stats;

	   }

	//insertStats
	public void insertStats() {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbutil.getConnection();
			String sql="INSERT INTO stats(stats_day, stats_count) VALUES(DATE(NOW()), 1)";
			stmt = conn.prepareStatement(sql);
			System.out.println("insertStats stmt: "+stmt);
			stmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();

		} finally {
			this.dbutil.close(null, stmt, conn);
		}

	}
	//updateStats
	public void updateStats() {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbutil.getConnection();
			String sql="UPDATE stats SET stats_count = stats_count+1 WHERE stats_day = DATA(NOW())";
			stmt = conn.prepareStatement(sql);
			System.out.println("updateStats stmt: "+stmt);
			stmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();

		} finally {
			this.dbutil.close(null, stmt, conn);
		}
	}

	//StatsTotal 보기
	public long selectStatsTotal() {
		long total = 0;
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql="SELECT COUNT(stats_count) total FROM stats";
			stmt = conn.prepareStatement(sql);
			System.out.println("totalStats stmt:"+stmt);
			rs = stmt.executeQuery();

			if(rs.next()) {
				total = rs.getLong("total");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally { 
			this.dbutil.close(rs, stmt, conn);
		}
		return total; 
	}
}