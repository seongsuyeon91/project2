package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.jsp.dao.MDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MLoginCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mPwd = request.getParameter("mPwd");
		
		MDao dao =MDao.getInstance();
		int rn = dao.login(mId, mPwd);		
		String json_data = "";
		
		if(rn == 1)
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"로그인 되었습니다.\"}";
			HttpSession session = request.getSession();
			session.setAttribute("mId", mId);
			session.setAttribute("ValidMem", "suyeon");
		}else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"로그인에 실패했습니다.\"}";
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
