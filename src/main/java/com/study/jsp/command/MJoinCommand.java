package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.jsp.dao.MDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MJoinCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String mPwd = request.getParameter("mPwd");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String mEmail = request.getParameter("mEmail");
		System.out.println(mId);
		
		MDao dao =MDao.getInstance();
		
		int rn = dao.join(mId, mName, mPwd, phone1,phone2,phone3,mEmail);
		System.out.println(mId);
		String json_data = "";
		
		if(rn ==1 )
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"회원가입 되었습니다.\"}";
			HttpSession session = request.getSession();
			session.setAttribute("mId", mId);
		}
		else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"회원가입에 실패했습니다.\"}";
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
