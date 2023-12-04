package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.BPageInfo;
import com.study.jsp.dto.BDto;

public class BDao
{
	private static BDao instance = new BDao();
	DataSource dataSource;
	
	int listCount = 10;	//한 페이지당 보여줄 게시물의 개수
	int pageCount = 10; //하단에 보여줄 페이지 리스트의 개수

	public BDao()
	{
		try
		{
			//lookup 함수의 파라메터는 context.tml에 설정된
			//name(jdbc/Oracle21c)와 동일해야 한다.
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle21c");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static BDao getInstance()
	{
		return instance;
	}

	public int write(String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;
		try
		{
			con = dataSource.getConnection();
			String query = "insert into nboard" + "(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike) "
					+ "values" + "(nboard_seq.nextval, ?, ?, ?, sysdate, 0, nboard_seq.currval, 0,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			rn = pstmt.executeUpdate();
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
	
	public int write2(String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;
		try
		{
			con = dataSource.getConnection();
			String query = "insert into fboard" + "(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike) "
					+ "values" + "(fboard_seq.nextval, ?, ?, ?, sysdate, 0, fboard_seq.currval, 0,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			rn = pstmt.executeUpdate();
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
	
	public int write3(String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;
		try
		{
			
			con = dataSource.getConnection();
			
			String query = "insert into archive " + " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bLike) "
					+ " values" + " (archive_seq.nextval, ?, ?, ?, 0, archive_seq.currval, 0,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			rn = pstmt.executeUpdate();
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


	public ArrayList<BDto> list1(int curPage)
	{

		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1) * listCount + 1;
		int nEnd = (curPage -1) * listCount + listCount;

		try
		{
			con = dataSource.getConnection();

			String query = "select *" +
							" from ( " +
							"  select rownum num, A.*" +
							" 	from ( " +
							" 	 select * " +
							"		from nboard" +
							"		  order by bGroup desc, bStep asc) A" +
							"		where rownum <= ? ) B" +
							" where B.num >=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String filename = resultSet.getString("filename");
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike,filename);
				dtos.add(dto);
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
		return dtos;
	}
	

	
	public ArrayList<BDto> list2(int curPage)
	{

		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1) * listCount + 1;
		int nEnd = (curPage -1) * listCount + listCount;

		try
		{
			con = dataSource.getConnection();

			String query = "select *" +
							" from ( " +
							"  select rownum num, A.*" +
							" 	from ( " +
							" 	 select * " +
							"		from fboard" +
							"		  order by bGroup desc, bStep asc) A" +
							"		where rownum <= ? ) B" +
							" where B.num >=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = likeCount(bId);
				String filename = resultSet.getString("filename");
				int bDelete=resultSet.getInt("bDelete");
				if(bDelete != 1)
				{
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike, filename);
					dtos.add(dto);
				}
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
		return dtos;
	}
	
	
	public int likeCount(int bId) {
		

		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count=0;
		try
		{
			con = dataSource.getConnection();
			String query = "select count(*) from board_like where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bId);
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next())
			{
				count=resultSet.getInt(1);
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
		return count;
	}
	
	public ArrayList<BDto> list3(int curPage)
	{

		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1) * listCount + 1;
		int nEnd = (curPage -1) * listCount + listCount;

		try
		{
			con = dataSource.getConnection();

			String query = "select *" +
							" from ( " +
							"  select rownum num, A.*" +
							" 	from ( " +
							" 	 select * " +
							"		from archive" +
							"		  order by bGroup desc, bStep asc) A" +
							"		where rownum <= ? ) B" +
							" where B.num >=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike =likeCount2(bId);
				System.out.println(bId);
				String filename = resultSet.getString("filename");
				int bDelete=resultSet.getInt("bDelete");
				if(bDelete != 1)
				{
					BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike, filename);
					dtos.add(dto);
				}
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
		return dtos;
	}

	public int likeCount2(int bId) {
		

		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count=0;
		try
		{
			con = dataSource.getConnection();
			String query = "select count(*) from archive_like where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bId);
			System.out.println(bId);
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next())
			{
				count=resultSet.getInt(1);
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
		return count;
	}

	 public ArrayList<BDto> userAList(int curPage, String word){
	      
	      ArrayList<BDto> dtos = new ArrayList<BDto>();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet resultSet = null;
	      
	      int nStart = (curPage - 1) * listCount + 1; 
	      int nEnd = (curPage - 1) * listCount + listCount;
	      
	      try {
	         con = dataSource.getConnection();
	         
	         String query = " select * "
	               + "  from( "
	               + "    select rownum num, A.* "
	               + "     from( "
	               + "      select * "
	               + "       from nBoard where bName like ? "
	               + "          order by bGroup desc, bStep asc ) A "
	               + "      where rownum <= ? ) B "
	               + " where B.num >= ? ";
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, "%"+word+"%");
	         pstmt.setInt(2, nEnd);
	         pstmt.setInt(3, nStart);
	         resultSet = pstmt.executeQuery();
	         
	         while(resultSet.next()) {
	            int bId = resultSet.getInt("bId");
	            String bName = resultSet.getString("bName");
	            String bTitle = resultSet.getString("bTitle");
	            String bContent = resultSet.getString("bContent");
	            Timestamp bDate = resultSet.getTimestamp("bDate");
	            int bHit = resultSet.getInt("bHit");
	            int bGroup = resultSet.getInt("bGroup");
	            int bStep = resultSet.getInt("bStep");
	            int bIndent = resultSet.getInt("bIndent");
	            
	            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
	                  bHit, bGroup, bStep, bIndent);
	            dtos.add(dto);
	            
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	         if (resultSet != null) resultSet.close();
	         if(pstmt != null) pstmt.close();
	         if(con != null) con.close();   
	         } catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return dtos;
	   }
	   
	   public BPageInfo userAArticlePage(int curPage, String word) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet resultSet = null;
	      
	      int totalCount = 0;
	      try {
	         con = dataSource.getConnection();
	         
	         String query = "select count(*) as total from nBoard where bName like ?";
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, "%"+word+"%");
	         resultSet = pstmt.executeQuery();
	         
	         if(resultSet.next()) {
	            totalCount = resultSet.getInt("total");
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(resultSet != null) resultSet.close();
	            if(pstmt != null) pstmt.close();
	            if(con != null) con.close();
	         } catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      // 총 페이지 수
	      int totalPage = totalCount / listCount;  //54/10 = 5
	      if(totalCount % listCount > 0) // 나머지는 4
	         totalPage++; //6페이지
	      
	      //현재 페이지
	      int myCurPage = curPage; //2페이지
	      if(myCurPage > totalPage) // 2페이지> 6페이지 false
	         myCurPage = totalPage;
	      if(myCurPage < 1)
	         myCurPage = 1;
	      // 시작 페이지
	      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
	      
	      //끝 페이지
	      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
	      if(endPage > totalPage) //11 > 6
	         endPage = totalPage; // 6 = 6
	      
	      BPageInfo pinfo = new BPageInfo();
	      pinfo.setTotalCount(totalCount);
	      pinfo.setListCount(listCount);
	      pinfo.setTotalPage(totalPage);
	      pinfo.setCurPage(myCurPage);
	      pinfo.setPageCount(pageCount);
	      pinfo.setStartPage(startPage);
	      pinfo.setEndPage(endPage);
	      
	      return pinfo;
	      
	   }
	
	   public ArrayList<BDto> tItleAList(int curPage, String word){
		      
		      ArrayList<BDto> dtos = new ArrayList<BDto>();
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet resultSet = null;
		      
		      int nStart = (curPage - 1) * listCount + 1; 
		      int nEnd = (curPage - 1) * listCount + listCount;
		      
		      try {
		         con = dataSource.getConnection();
		         
		         String query = " select * "
		               + "  from( "
		               + "    select rownum num, A.* "
		               + "     from( "
		               + "      select * "
		               + "       from nBoard where bTitle like ? "
		               + "          order by bGroup desc, bStep asc ) A "
		               + "      where rownum <= ? ) B "
		               + " where B.num >= ? ";
		         pstmt = con.prepareStatement(query);
		         pstmt.setString(1, "%"+word+"%");
		         pstmt.setInt(2, nEnd);
		         pstmt.setInt(3, nStart);
		         resultSet = pstmt.executeQuery();
		         
		         while(resultSet.next()) {
		            int bId = resultSet.getInt("bId");
		            String bName = resultSet.getString("bName");
		            String bTitle = resultSet.getString("bTitle");
		            String bContent = resultSet.getString("bContent");
		            Timestamp bDate = resultSet.getTimestamp("bDate");
		            int bHit = resultSet.getInt("bHit");
		            int bGroup = resultSet.getInt("bGroup");
		            int bStep = resultSet.getInt("bStep");
		            int bIndent = resultSet.getInt("bIndent");
		            
		            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
		                  bHit, bGroup, bStep, bIndent);
		            dtos.add(dto);
		            
		         }
		      } catch(Exception e) {
		         e.printStackTrace();
		      }finally {
		         try {
		         if (resultSet != null) resultSet.close();
		         if(pstmt != null) pstmt.close();
		         if(con != null) con.close();   
		         } catch(Exception e2) {
		            e2.printStackTrace();
		         }
		      }
		      return dtos;
		   }
		   
		   public BPageInfo tItleAArticlePage(int curPage, String word) {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet resultSet = null;
		      
		      int totalCount = 0;
		      try {
		         con = dataSource.getConnection();
		         
		         String query = "select count(*) as total from nBoard where bName like ?";
		         pstmt = con.prepareStatement(query);
		         pstmt.setString(1, "%"+word+"%");
		         resultSet = pstmt.executeQuery();
		         
		         if(resultSet.next()) {
		            totalCount = resultSet.getInt("total");
		         }
		      } catch(Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            if(resultSet != null) resultSet.close();
		            if(pstmt != null) pstmt.close();
		            if(con != null) con.close();
		         } catch(Exception e2) {
		            e2.printStackTrace();
		         }
		      }
		      // 총 페이지 수
		      int totalPage = totalCount / listCount;  //54/10 = 5
		      if(totalCount % listCount > 0) // 나머지는 4
		         totalPage++; //6페이지
		      
		      //현재 페이지
		      int myCurPage = curPage; //2페이지
		      if(myCurPage > totalPage) // 2페이지> 6페이지 false
		         myCurPage = totalPage;
		      if(myCurPage < 1)
		         myCurPage = 1;
		      // 시작 페이지
		      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
		      
		      //끝 페이지
		      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
		      if(endPage > totalPage) //11 > 6
		         endPage = totalPage; // 6 = 6
		      
		      BPageInfo pinfo = new BPageInfo();
		      pinfo.setTotalCount(totalCount);
		      pinfo.setListCount(listCount);
		      pinfo.setTotalPage(totalPage);
		      pinfo.setCurPage(myCurPage);
		      pinfo.setPageCount(pageCount);
		      pinfo.setStartPage(startPage);
		      pinfo.setEndPage(endPage);
		      
		      return pinfo;
		      
		   }
		
		   public ArrayList<BDto> userFList(int curPage, String word){
			      
			      ArrayList<BDto> dtos = new ArrayList<BDto>();
			      Connection con = null;
			      PreparedStatement pstmt = null;
			      ResultSet resultSet = null;
			      
			      int nStart = (curPage - 1) * listCount + 1; 
			      int nEnd = (curPage - 1) * listCount + listCount;
			      
			      try {
			         con = dataSource.getConnection();
			         
			         String query = " select * "
			               + "  from( "
			               + "    select rownum num, A.* "
			               + "     from( "
			               + "      select * "
			               + "       from fBoard where bName like ? "
			               + "          order by bGroup desc, bStep asc ) A "
			               + "      where rownum <= ? ) B "
			               + " where B.num >= ? ";
			         pstmt = con.prepareStatement(query);
			         pstmt.setString(1, "%"+word+"%");
			         pstmt.setInt(2, nEnd);
			         pstmt.setInt(3, nStart);
			         resultSet = pstmt.executeQuery();
			         
			         while(resultSet.next()) {
			            int bId = resultSet.getInt("bId");
			            String bName = resultSet.getString("bName");
			            String bTitle = resultSet.getString("bTitle");
			            String bContent = resultSet.getString("bContent");
			            Timestamp bDate = resultSet.getTimestamp("bDate");
			            int bHit = resultSet.getInt("bHit");
			            int bGroup = resultSet.getInt("bGroup");
			            int bStep = resultSet.getInt("bStep");
			            int bIndent = resultSet.getInt("bIndent");
			            
			            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
			                  bHit, bGroup, bStep, bIndent);
			            dtos.add(dto);
			            
			         }
			      } catch(Exception e) {
			         e.printStackTrace();
			      }finally {
			         try {
			         if (resultSet != null) resultSet.close();
			         if(pstmt != null) pstmt.close();
			         if(con != null) con.close();   
			         } catch(Exception e2) {
			            e2.printStackTrace();
			         }
			      }
			      return dtos;
			   }
			   
			   public BPageInfo userFArticlePage(int curPage, String word) {
			      Connection con = null;
			      PreparedStatement pstmt = null;
			      ResultSet resultSet = null;
			      
			      int totalCount = 0;
			      try {
			         con = dataSource.getConnection();
			         
			         String query = "select count(*) as total from fBoard where bName like ?";
			         pstmt = con.prepareStatement(query);
			         pstmt.setString(1, "%"+word+"%");
			         resultSet = pstmt.executeQuery();
			         
			         if(resultSet.next()) {
			            totalCount = resultSet.getInt("total");
			         }
			      } catch(Exception e) {
			         e.printStackTrace();
			      } finally {
			         try {
			            if(resultSet != null) resultSet.close();
			            if(pstmt != null) pstmt.close();
			            if(con != null) con.close();
			         } catch(Exception e2) {
			            e2.printStackTrace();
			         }
			      }
			      // 총 페이지 수
			      int totalPage = totalCount / listCount;  //54/10 = 5
			      if(totalCount % listCount > 0) // 나머지는 4
			         totalPage++; //6페이지
			      
			      //현재 페이지
			      int myCurPage = curPage; //2페이지
			      if(myCurPage > totalPage) // 2페이지> 6페이지 false
			         myCurPage = totalPage;
			      if(myCurPage < 1)
			         myCurPage = 1;
			      // 시작 페이지
			      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
			      
			      //끝 페이지
			      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
			      if(endPage > totalPage) //11 > 6
			         endPage = totalPage; // 6 = 6
			      
			      BPageInfo pinfo = new BPageInfo();
			      pinfo.setTotalCount(totalCount);
			      pinfo.setListCount(listCount);
			      pinfo.setTotalPage(totalPage);
			      pinfo.setCurPage(myCurPage);
			      pinfo.setPageCount(pageCount);
			      pinfo.setStartPage(startPage);
			      pinfo.setEndPage(endPage);
			      
			      return pinfo;
			      
			   }
			
			   public ArrayList<BDto> tItleFList(int curPage, String word){
				      
				      ArrayList<BDto> dtos = new ArrayList<BDto>();
				      Connection con = null;
				      PreparedStatement pstmt = null;
				      ResultSet resultSet = null;
				      
				      int nStart = (curPage - 1) * listCount + 1; 
				      int nEnd = (curPage - 1) * listCount + listCount;
				      
				      try {
				         con = dataSource.getConnection();
				         
				         String query = " select * "
				               + "  from( "
				               + "    select rownum num, A.* "
				               + "     from( "
				               + "      select * "
				               + "       from fBoard where bTitle like ? "
				               + "          order by bGroup desc, bStep asc ) A "
				               + "      where rownum <= ? ) B "
				               + " where B.num >= ? ";
				         pstmt = con.prepareStatement(query);
				         pstmt.setString(1, "%"+word+"%");
				         pstmt.setInt(2, nEnd);
				         pstmt.setInt(3, nStart);
				         resultSet = pstmt.executeQuery();
				         
				         while(resultSet.next()) {
				            int bId = resultSet.getInt("bId");
				            String bName = resultSet.getString("bName");
				            String bTitle = resultSet.getString("bTitle");
				            String bContent = resultSet.getString("bContent");
				            Timestamp bDate = resultSet.getTimestamp("bDate");
				            int bHit = resultSet.getInt("bHit");
				            int bGroup = resultSet.getInt("bGroup");
				            int bStep = resultSet.getInt("bStep");
				            int bIndent = resultSet.getInt("bIndent");
				            
				            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
				                  bHit, bGroup, bStep, bIndent);
				            dtos.add(dto);
				            
				         }
				      } catch(Exception e) {
				         e.printStackTrace();
				      }finally {
				         try {
				         if (resultSet != null) resultSet.close();
				         if(pstmt != null) pstmt.close();
				         if(con != null) con.close();   
				         } catch(Exception e2) {
				            e2.printStackTrace();
				         }
				      }
				      return dtos;
				   }
				   
				   public BPageInfo tItleFArticlePage(int curPage, String word) {
				      Connection con = null;
				      PreparedStatement pstmt = null;
				      ResultSet resultSet = null;
				      
				      int totalCount = 0;
				      try {
				         con = dataSource.getConnection();
				         
				         String query = "select count(*) as total from fBoard where bTitle like ?";
				         pstmt = con.prepareStatement(query);
				         pstmt.setString(1, "%"+word+"%");
				         resultSet = pstmt.executeQuery();
				         
				         if(resultSet.next()) {
				            totalCount = resultSet.getInt("total");
				         }
				      } catch(Exception e) {
				         e.printStackTrace();
				      } finally {
				         try {
				            if(resultSet != null) resultSet.close();
				            if(pstmt != null) pstmt.close();
				            if(con != null) con.close();
				         } catch(Exception e2) {
				            e2.printStackTrace();
				         }
				      }
				      // 총 페이지 수
				      int totalPage = totalCount / listCount;  //54/10 = 5
				      if(totalCount % listCount > 0) // 나머지는 4
				         totalPage++; //6페이지
				      
				      //현재 페이지
				      int myCurPage = curPage; //2페이지
				      if(myCurPage > totalPage) // 2페이지> 6페이지 false
				         myCurPage = totalPage;
				      if(myCurPage < 1)
				         myCurPage = 1;
				      // 시작 페이지
				      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
				      
				      //끝 페이지
				      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
				      if(endPage > totalPage) //11 > 6
				         endPage = totalPage; // 6 = 6
				      
				      BPageInfo pinfo = new BPageInfo();
				      pinfo.setTotalCount(totalCount);
				      pinfo.setListCount(listCount);
				      pinfo.setTotalPage(totalPage);
				      pinfo.setCurPage(myCurPage);
				      pinfo.setPageCount(pageCount);
				      pinfo.setStartPage(startPage);
				      pinfo.setEndPage(endPage);
				      
				      return pinfo;
				      
				   }
				   public ArrayList<BDto> userArchiveList(int curPage, String word){
					      
					      ArrayList<BDto> dtos = new ArrayList<BDto>();
					      Connection con = null;
					      PreparedStatement pstmt = null;
					      ResultSet resultSet = null;
					      
					      int nStart = (curPage - 1) * listCount + 1; 
					      int nEnd = (curPage - 1) * listCount + listCount;
					      
					      try {
					         con = dataSource.getConnection();
					         
					         String query = " select * "
					               + "  from( "
					               + "    select rownum num, A.* "
					               + "     from( "
					               + "      select * "
					               + "       from archive where bName like ? "
					               + "          order by bGroup desc, bStep asc ) A "
					               + "      where rownum <= ? ) B "
					               + " where B.num >= ? ";
					         pstmt = con.prepareStatement(query);
					         pstmt.setString(1, "%"+word+"%");
					         pstmt.setInt(2, nEnd);
					         pstmt.setInt(3, nStart);
					         resultSet = pstmt.executeQuery();
					         
					         while(resultSet.next()) {
					            int bId = resultSet.getInt("bId");
					            String bName = resultSet.getString("bName");
					            String bTitle = resultSet.getString("bTitle");
					            String bContent = resultSet.getString("bContent");
					            Timestamp bDate = resultSet.getTimestamp("bDate");
					            int bHit = resultSet.getInt("bHit");
					            int bGroup = resultSet.getInt("bGroup");
					            int bStep = resultSet.getInt("bStep");
					            int bIndent = resultSet.getInt("bIndent");
					            
					            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
					                  bHit, bGroup, bStep, bIndent);
					            dtos.add(dto);
					            
					         }
					      } catch(Exception e) {
					         e.printStackTrace();
					      }finally {
					         try {
					         if (resultSet != null) resultSet.close();
					         if(pstmt != null) pstmt.close();
					         if(con != null) con.close();   
					         } catch(Exception e2) {
					            e2.printStackTrace();
					         }
					      }
					      return dtos;
					   }
					   
					   public BPageInfo userArchivePage(int curPage, String word) {
					      Connection con = null;
					      PreparedStatement pstmt = null;
					      ResultSet resultSet = null;
					      
					      int totalCount = 0;
					      try {
					         con = dataSource.getConnection();
					         
					         String query = "select count(*) as total from archive where bName like ?";
					         pstmt = con.prepareStatement(query);
					         pstmt.setString(1, "%"+word+"%");
					         resultSet = pstmt.executeQuery();
					         
					         if(resultSet.next()) {
					            totalCount = resultSet.getInt("total");
					         }
					      } catch(Exception e) {
					         e.printStackTrace();
					      } finally {
					         try {
					            if(resultSet != null) resultSet.close();
					            if(pstmt != null) pstmt.close();
					            if(con != null) con.close();
					         } catch(Exception e2) {
					            e2.printStackTrace();
					         }
					      }
					      // 총 페이지 수
					      int totalPage = totalCount / listCount;  //54/10 = 5
					      if(totalCount % listCount > 0) // 나머지는 4
					         totalPage++; //6페이지
					      
					      //현재 페이지
					      int myCurPage = curPage; //2페이지
					      if(myCurPage > totalPage) // 2페이지> 6페이지 false
					         myCurPage = totalPage;
					      if(myCurPage < 1)
					         myCurPage = 1;
					      // 시작 페이지
					      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
					      
					      //끝 페이지
					      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
					      if(endPage > totalPage) //11 > 6
					         endPage = totalPage; // 6 = 6
					      
					      BPageInfo pinfo = new BPageInfo();
					      pinfo.setTotalCount(totalCount);
					      pinfo.setListCount(listCount);
					      pinfo.setTotalPage(totalPage);
					      pinfo.setCurPage(myCurPage);
					      pinfo.setPageCount(pageCount);
					      pinfo.setStartPage(startPage);
					      pinfo.setEndPage(endPage);
					      
					      return pinfo;
					      
					   }
					
					   public ArrayList<BDto> tItleArchiveList(int curPage, String word){
						      
						      ArrayList<BDto> dtos = new ArrayList<BDto>();
						      Connection con = null;
						      PreparedStatement pstmt = null;
						      ResultSet resultSet = null;
						      
						      int nStart = (curPage - 1) * listCount + 1; 
						      int nEnd = (curPage - 1) * listCount + listCount;
						      
						      try {
						         con = dataSource.getConnection();
						         
						         String query = " select * "
						               + "  from( "
						               + "    select rownum num, A.* "
						               + "     from( "
						               + "      select * "
						               + "       from archive where bTitle like ? "
						               + "          order by bGroup desc, bStep asc ) A "
						               + "      where rownum <= ? ) B "
						               + " where B.num >= ? ";
						         pstmt = con.prepareStatement(query);
						         pstmt.setString(1, "%"+word+"%");
						         pstmt.setInt(2, nEnd);
						         pstmt.setInt(3, nStart);
						         resultSet = pstmt.executeQuery();
						         
						         while(resultSet.next()) {
						            int bId = resultSet.getInt("bId");
						            String bName = resultSet.getString("bName");
						            String bTitle = resultSet.getString("bTitle");
						            String bContent = resultSet.getString("bContent");
						            Timestamp bDate = resultSet.getTimestamp("bDate");
						            int bHit = resultSet.getInt("bHit");
						            int bGroup = resultSet.getInt("bGroup");
						            int bStep = resultSet.getInt("bStep");
						            int bIndent = resultSet.getInt("bIndent");
						            
						            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate,
						                  bHit, bGroup, bStep, bIndent);
						            dtos.add(dto);
						            
						         }
						      } catch(Exception e) {
						         e.printStackTrace();
						      }finally {
						         try {
						         if (resultSet != null) resultSet.close();
						         if(pstmt != null) pstmt.close();
						         if(con != null) con.close();   
						         } catch(Exception e2) {
						            e2.printStackTrace();
						         }
						      }
						      return dtos;
						   }
						   
						   public BPageInfo tItleArchiveArticlePage(int curPage, String word) {
						      Connection con = null;
						      PreparedStatement pstmt = null;
						      ResultSet resultSet = null;
						      
						      int totalCount = 0;
						      try {
						         con = dataSource.getConnection();
						         
						         String query = "select count(*) as total from archive where bTitle like ?";
						         pstmt = con.prepareStatement(query);
						         pstmt.setString(1, "%"+word+"%");
						         resultSet = pstmt.executeQuery();
						         
						         if(resultSet.next()) {
						            totalCount = resultSet.getInt("total");
						         }
						      } catch(Exception e) {
						         e.printStackTrace();
						      } finally {
						         try {
						            if(resultSet != null) resultSet.close();
						            if(pstmt != null) pstmt.close();
						            if(con != null) con.close();
						         } catch(Exception e2) {
						            e2.printStackTrace();
						         }
						      }
						      // 총 페이지 수
						      int totalPage = totalCount / listCount;  //54/10 = 5
						      if(totalCount % listCount > 0) // 나머지는 4
						         totalPage++; //6페이지
						      
						      //현재 페이지
						      int myCurPage = curPage; //2페이지
						      if(myCurPage > totalPage) // 2페이지> 6페이지 false
						         myCurPage = totalPage;
						      if(myCurPage < 1)
						         myCurPage = 1;
						      // 시작 페이지
						      int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1; //(2-1) /10 * 10+1 = 2
						      
						      //끝 페이지
						      int endPage = startPage + pageCount - 1; // 2 + 10 - 1 // 11
						      if(endPage > totalPage) //11 > 6
						         endPage = totalPage; // 6 = 6
						      
						      BPageInfo pinfo = new BPageInfo();
						      pinfo.setTotalCount(totalCount);
						      pinfo.setListCount(listCount);
						      pinfo.setTotalPage(totalPage);
						      pinfo.setCurPage(myCurPage);
						      pinfo.setPageCount(pageCount);
						      pinfo.setStartPage(startPage);
						      pinfo.setEndPage(endPage);
						      
						      return pinfo;
						      
						   }		
	
	public BDto contentView(String strID)
	{
		upHit(strID);

		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			con = dataSource.getConnection();

			String query = "select * from nboard where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();

			if (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String filename = resultSet.getString("filename");
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike,filename);
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
	
	
	public BDto contentView2(String strID)
	{
		upHit2(strID);

		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			con = dataSource.getConnection();
			String query = "select * from fboard where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();

			if (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String filename = resultSet.getString("filename");
				int bDelete = resultSet.getInt("bDelete");
				if(bDelete == 0)
				{
					dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike,filename);	
				}else
				{ }
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
	
	public BDto contentView3(String strID)
	{
		upHit3(strID);

		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try
		{
			con = dataSource.getConnection();

			String query = "select * from archive where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();

			if (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String filename = resultSet.getString("filename");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, bLike, filename);
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



	public int modify(String bId, String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;

		try
		{
			con = dataSource.getConnection();

			String query = "update nboard set bName = ?, bTitle =? , bContent=? where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			rn = pstmt.executeUpdate();
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

	public int modify2(String bId, String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;

		try
		{
			con = dataSource.getConnection();

			String query = "update fboard set bName = ?, bTitle =? , bContent=? where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			rn = pstmt.executeUpdate();
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
	
	public int modify3(String bId, String bName, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn = 0;

		try
		{
			con = dataSource.getConnection();

			String query = "update archive set bName = ?, bTitle =? , bContent=? where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			rn = pstmt.executeUpdate();
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
	
	public void upHit(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update nboard set bHit = bHit +1 where bId =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);

			int rn = pstmt.executeUpdate();
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

	}
	
	public void upHit2(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update fboard set bHit = bHit +1 where bId =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);

			int rn = pstmt.executeUpdate();
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

	}

	
	
	public void upHit3(String bId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update archive set bHit = bHit +1 where bId =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);

			int rn = pstmt.executeUpdate();
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

	}


	public void delete(String bId, String bName)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update nboard set bDelete=1 where bId =? and bName =?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.setString(2, bName);
			int rn = pstmt.executeUpdate();

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
	}
	
	public void delete2(String bId, String bName)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update fboard set bDelete=1 where bId =? and bName =?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.setString(2, bName);

			int rn = pstmt.executeUpdate();

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
	}
	
	public void delete3(String bId, String bName)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = dataSource.getConnection();
			String query = "update archive set bDelete=1 where bId =? and bName =?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.setString(2, bName);
			int rn = pstmt.executeUpdate();

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
	}
	

	public BDto reply_view(String str)
	{
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try
		{
			con = dataSource.getConnection();
			String query = "select * from nboard where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();

			if (resultSet.next())
			{
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				int bLike = resultSet.getInt("bLike");
				String filename = resultSet.getString("filename");
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,bLike, filename);

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return dto;
	}
	public int reply(String bId, String bName, String bTitle, String bContent,
						String bGroup, String bStep, String bIndent)
	{
		
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn =0;
		try
		{
			con = dataSource.getConnection();
			String query = "insert into nboard" +
							"(bId, bName, bTitle, bContent, bGroup, bStep, bIndent)"+ 
							"values (nboard_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			
			rn = pstmt.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return rn;
	}
	
	private void replyShape (String strGroup, String strStep) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = dataSource.getConnection();
			String query = "update nboard" +
			
							"	set bStep = bStep + 1 "+ 
							"where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	public BPageInfo articlePage1(int curPage) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		//총 게시물의 갯수
		int totalCount = 0;
		try
		{
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from nboard";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if(resultSet != null) resultSet.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		//총 페이지 수
		int totalPage = totalCount/listCount;
		if( totalCount % listCount> 0)
			totalPage++;
			
		//현재 페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage<1)
			myCurPage=1;
		
		//시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		//끝 페이지
		int endPage = startPage + pageCount -1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}



public BPageInfo articlePage2(int curPage) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	//총 게시물의 갯수
	int totalCount = 0;
	try
	{
		con = dataSource.getConnection();
		
		String query = "select count(*) as total from fboard";
		pstmt = con.prepareStatement(query);
		resultSet = pstmt.executeQuery();
		
		if(resultSet.next()) {
			totalCount = resultSet.getInt("total");
		}
		
	} catch (Exception e)
	{
		e.printStackTrace();
	} finally
	{
		try
		{
			if(resultSet != null) resultSet.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
	
	//총 페이지 수
	int totalPage = totalCount/listCount;
	if( totalCount % listCount> 0)
		totalPage++;
		
	//현재 페이지
	int myCurPage = curPage;
	if(myCurPage > totalPage)
		myCurPage = totalPage;
	if(myCurPage<1)
		myCurPage=1;
	
	//시작 페이지
	int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
	
	//끝 페이지
	int endPage = startPage + pageCount -1;
	if(endPage > totalPage)
		endPage = totalPage;
	
	BPageInfo pinfo = new BPageInfo();
	pinfo.setTotalCount(totalCount);
	pinfo.setListCount(listCount);
	pinfo.setTotalPage(totalPage);
	pinfo.setCurPage(myCurPage);
	pinfo.setPageCount(pageCount);
	pinfo.setStartPage(startPage);
	pinfo.setEndPage(endPage);
	
	return pinfo;
}


public BPageInfo articlePage3(int curPage) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	//총 게시물의 갯수
	int totalCount = 0;
	try
	{
		con = dataSource.getConnection();
		
		String query = "select count(*) as total from archive";
		pstmt = con.prepareStatement(query);
		resultSet = pstmt.executeQuery();
		
		if(resultSet.next()) {
			totalCount = resultSet.getInt("total");
		}
		
	} catch (Exception e)
	{
		e.printStackTrace();
	} finally
	{
		try
		{
			if(resultSet != null) resultSet.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
	
	//총 페이지 수
	int totalPage = totalCount/listCount;
	if( totalCount % listCount> 0)
		totalPage++;
		
	//현재 페이지
	int myCurPage = curPage;
	if(myCurPage > totalPage)
		myCurPage = totalPage;
	if(myCurPage<1)
		myCurPage=1;
	
	//시작 페이지
	int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
	
	//끝 페이지
	int endPage = startPage + pageCount -1;
	if(endPage > totalPage)
		endPage = totalPage;
	
	BPageInfo pinfo = new BPageInfo();
	pinfo.setTotalCount(totalCount);
	pinfo.setListCount(listCount);
	pinfo.setTotalPage(totalPage);
	pinfo.setCurPage(myCurPage);
	pinfo.setPageCount(pageCount);
	pinfo.setStartPage(startPage);
	pinfo.setEndPage(endPage);
	
	return pinfo;
}

}


