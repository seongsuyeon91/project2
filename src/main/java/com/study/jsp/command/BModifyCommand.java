package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.jsp.dao.BDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BModifyCommand implements BCommand
{
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = new BDao();
		int rn = dao.modify(bId,bName,bTitle,bContent);
		
		String json_data = "";
		if(rn ==1 )
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"수정되었습니다.\"}";
		}
		else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"수정에 실패했습니다.\"}";
		}
		
		try 
		{
			response.setContentType("application; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}