package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.jsp.dao.LikeDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SubmitLikeCommand implements BCommand
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String mId = request.getParameter("mId");  // 좋아요 누른사람 아이디
		String bId = request.getParameter("bId");	 // 게시글 번호
		
		
		LikeDao dao = LikeDao.getInstance();
	
		int count = dao.submitLikeSelect(mId, bId);
		
		String json_data = "";
		if (count == 1)
		{
			json_data = "{\"code\":\"success\", \"desc\":\"좋아요 취소.\"}";
		}
		else
		{
			json_data = "{\"code\":\"success\", \"desc\":\"좋아요\"}";
		}
		
		try
		{
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}