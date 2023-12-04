package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import com.study.jsp.dao.MDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MModifyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String mPwd = request.getParameter("mPwd");
		String phone1= request.getParameter("phone1");
		String phone2= request.getParameter("phone2");
		String phone3= request.getParameter("phone3");
		String mEmail= request.getParameter("mEmail");
		
		MDao dao =MDao.getInstance();
		int rn = dao.modify(mId, mName, mPwd, phone1, phone2, phone3, mEmail);
		String json_data = "";
		
		if(rn == 1)
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"회원정보가 수정되었습니다.\"}";
		}
		else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"정보 수정에 실패했습니다.\"}";
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
