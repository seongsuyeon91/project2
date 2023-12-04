package com.study.jsp.command;

import java.io.IOException;

import com.study.jsp.dao.BDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FDeleteCommand implements BCommand
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		String bId = request.getParameter("bId");
		HttpSession session = request.getSession();
		String bName= (String)session.getAttribute("bName");
		
				
		BDao dao = new BDao();
		dao.delete2(bId, bName);
	}
}
