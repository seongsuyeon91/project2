package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.BDto;
import com.study.jsp.dto.MDto;

public class MDao
{
	private static MDao instance = new MDao();
	DataSource dataSource;
	
	int listCount = 10;	//한 페이지당 보여줄 게시물의 개수
	int pageCount = 10; //하단에 보여줄 페이지 리스트의 개수

	public MDao()
	{
		
		int listCount = 10;	//한 페이지당 보여줄 게시물의 개수
		int pageCount = 10; //하단에 보여줄 페이지 리스트의 개수
		
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle21c");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static MDao getInstance()
	{
		return instance;
	}

	public int join(String mId, String mName, String mPwd, String phone1, String phone2, String phone3, String mEmail)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;
		
		try
		{
			con = dataSource.getConnection();
			String query = "insert into bmember" + "(mId, mName, mPwd, mPhone, mEmail) " + "values" + "(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, mPwd);
			pstmt.setString(4, phone1+"-"+phone2+"-"+phone3);
			pstmt.setString(5, mEmail);
			rn = pstmt.executeUpdate();
			System.out.println(rn);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return rn;
	}

	/*
	 * public int join2(String mId, String mName, String mEmail) { Connection con =
	 * null; PreparedStatement pstmt = null;
	 * 
	 * int rn = 0; try { con = dataSource.getConnection(); String query =
	 * "insert into bmember" + "(mId, mName, mEmail) " + "values" + "(?, ?, ?)";
	 * pstmt = con.prepareStatement(query); pstmt.setString(1, mId);
	 * pstmt.setString(2, mName); pstmt.setString(3, mEmail); rn =
	 * pstmt.executeUpdate(); System.out.println(rn); } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if (pstmt != null) pstmt.close(); if
	 * (con != null) con.close(); } catch (Exception e2) { e2.printStackTrace(); } }
	 * return rn; }
	 */
	
	
	public int login(String mId, String mPwd)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int rn = 0;
		try
		{
			con = dataSource.getConnection();

			String query = "select * from bmember where mid = ? and mpwd = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPwd);
			resultSet = pstmt.executeQuery();

			if (resultSet.next())
			{
				String wId = resultSet.getString("wId");
				if(wId != null)
				{
					return rn;
				}
				rn++;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();

			}
		}
		return rn;
	}
	
	public int modify(String mId, String mName, String mPwd, String phone1,String phone2,String phone3, String mEmail)
	{
	Connection con = null;
	PreparedStatement pstmt = null;
	
	int rn = 0;
	try
	{
		con = dataSource.getConnection();

		String modify = "update bmember set mname = ?, mpwd = ?, mphone=?, memail=? where mid = ?";
		pstmt = con.prepareStatement(modify);
	    pstmt.setString(1, mName);
	    pstmt.setString(2, mPwd);
        pstmt.setString(3, phone1+"-"+phone2+"-"+phone3);
        pstmt.setString(4, mEmail);
        pstmt.setString(5, mId);
        
        rn = pstmt.executeUpdate();
		
		
	} catch (Exception e)
	{
		e.printStackTrace();
	} 	
	finally
	{
		try
		{
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e2)
		{
			e2.printStackTrace();

		}
	}
	return rn;
    }
	
	public int withdraw(String mPwd)
	{
	Connection con = null;
	PreparedStatement pstmt = null;
	
	int rn = 0;
	try
	{
		con = dataSource.getConnection();

		String withdraw = "update bmember set wid = 1 where mpwd = ?";
		pstmt = con.prepareStatement(withdraw);
	    pstmt.setString(1, mPwd);
        rn = pstmt.executeUpdate();
		
		
	} catch (Exception e)
	{
		e.printStackTrace();
	} 	
	finally
	{
		try
		{
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e2)
		{
			e2.printStackTrace();

		}
	}
	return rn;
    }
	
	
	
	
	public MDto contentView(String strId)
	{

		MDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			con = dataSource.getConnection();
			String query = "select * from bmember where mId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, strId);
			resultSet = pstmt.executeQuery();
			
			
		
			if (resultSet.next())
			{
				String mId = resultSet.getString("mId");
				String mName = resultSet.getString("mName");
				String mPwd = resultSet.getString("mPwd");
				String mPhone = resultSet.getString("mPhone");
				String mEmail = resultSet.getString("mEmail");

				dto = new MDto(mId, mName, mPwd, mPhone, mEmail);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();

			}
		}
		return dto;
	}
	
	}
