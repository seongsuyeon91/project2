package com.study.jsp.command;

import java.io.IOException;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView2(bId);
		
		HttpSession session = request.getSession();
		session.setAttribute("bName", dto.getbName());
		
		
		request.setAttribute("content_view2", dto);
	
	}
}