package com.study.jsp.command;

import java.io.IOException;

import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	    String mId = (String)session.getAttribute("mId");
		MDao dao = new MDao();
		MDto dto = dao.contentView(mId);
		request.setAttribute("mContent_view", dto);
	}
}