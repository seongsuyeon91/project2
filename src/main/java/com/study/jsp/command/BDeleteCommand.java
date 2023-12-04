package com.study.jsp.command;

import java.io.IOException;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BDeleteCommand implements BCommand
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		String bId = request.getParameter("bId");
		String bName= request.getParameter("bName");
		
		BDao dao = new BDao();
		dao.delete(bId, bName);
		
	}
}
