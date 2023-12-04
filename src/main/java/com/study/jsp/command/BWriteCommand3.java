package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.jsp.dao.BDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BWriteCommand3 implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
	
		BDao dao = new BDao();
		int rn = dao.write3(bName, bTitle, bContent);
		System.out.println(rn);
		String json_data = "";
		if(rn ==1 )
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"등록되었습니다.\"}";
		}
		else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"게시물 작성에 실패했습니다.\"}";
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
