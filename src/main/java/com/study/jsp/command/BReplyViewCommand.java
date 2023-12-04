package com.study.jsp.command;

import java.io.IOException;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BReplyViewCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		String bId = request.getParameter("bId");
	
		
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
		
		request.setAttribute("reply_view", dto);
	}


}
