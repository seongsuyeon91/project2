package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.BPageInfo;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.LikeDto;

public class LikeDao
{
	private static LikeDao instance = new LikeDao();
	DataSource dataSource = null;
	 
	public LikeDao() {
		try {
			
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle21c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LikeDao getInstance()
	{
		return instance;
	}
	
	public void write(String mId, String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board_like " +
						   " (likeId, mId, bId) " +
						   " values " +
						   " (board_like_seq.nextval, ?, ? )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);
			pstmt.executeUpdate();
			
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int submitLikeSelect(String mId, String bId) 
	{
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) from board_like where mId = ? and bId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			if(count==0) {
				write(mId, bId);
				
			} else {
				delete(mId, bId);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	
	public void delete(String mId, String bId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from board_like where mId = ? and bId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);
			//pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void write2(String mId, String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into archive_like " +
						   " (likeId, mId, bId) " +
						   " values " +
						   " (archive_like_seq.nextval, ?, ? )";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);
			pstmt.executeUpdate();
			
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int submitLikeSelect2(String mId, String bId) 
	{
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) from archive_like where mId = ? and bId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//System.out.println(rs.getInt(1));
				count = rs.getInt(1);
			}
			
			if(count==0) {
				write2(mId, bId);
				
			} else {
				delete2(mId, bId);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	
	public void delete2(String mId, String bId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from archive_like where mId = ? and bId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, bId);
			//pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}




