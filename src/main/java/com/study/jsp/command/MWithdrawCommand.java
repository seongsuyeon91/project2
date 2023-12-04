package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import com.study.jsp.dao.MDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MWithdrawCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		
		String mPwd = request.getParameter("mPwd");
		System.out.println("mId");
		System.out.println("mPwd");
		MDao dao =MDao.getInstance();
		int rn = dao.withdraw(mPwd);
		String json_data = "";
		
		if(rn == 1)
		{
			json_data = "{\"code\":\"ok\", \"desc\":\"회원 탈퇴되었습니다.\"}";
		}
		else
		{
			json_data = "{\"code\":\"fail\", \"desc\":\"회원 탈퇴에 실패했습니다.\"}";
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
