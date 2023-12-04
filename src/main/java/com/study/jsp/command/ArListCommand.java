package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;

import com.study.jsp.BPageInfo;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArListCommand implements BCommand
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String word = null;
		String choice = null;

		if (request.getParameter("SearchChoice") != null || session.getAttribute("SearchChoice") != null) {
			if (request.getParameter("SearchChoice") != null) {
				word = request.getParameter("searchword").trim();
				choice = request.getParameter("SearchChoice").trim();
			} else {
				word = (String) session.getAttribute("searchword");
				choice = (String) session.getAttribute("SearchChoice");
			}
			int nPage = 1;
			try {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} catch (Exception e) {

			}

			if ((choice).equals("bTitle")) {
				BDao dao = BDao.getInstance();
				BPageInfo pinfo = dao.tItleAArticlePage(nPage, word);
				request.setAttribute("page", pinfo);

				nPage = pinfo.getCurPage();

				session = null;
				session = request.getSession();
				session.setAttribute("cpage", nPage);
				session.setAttribute("searchword", word);
				session.setAttribute("SearchChoice", choice);
				System.out.println(session.getAttribute("searchword"));
				System.out.println(session.getAttribute("SearchChoice"));
				ArrayList<BDto> dtos = dao.tItleArchiveList(nPage, word);
				request.setAttribute("list3", dtos);
				System.out.println(word + " : " + choice);

			} else if (choice.equals("bName")) {
				BDao dao = BDao.getInstance();
				BPageInfo pinfo = dao.userArchivePage(nPage, word);
				request.setAttribute("page", pinfo);

				nPage = pinfo.getCurPage();

				session = null;
				session = request.getSession();
				session.setAttribute("cpage", nPage);
				session.setAttribute("searchword", word);
				session.setAttribute("SearchChoice", choice);
				System.out.println(session.getAttribute("searchword"));
				System.out.println(session.getAttribute("SearchChoice"));
				ArrayList<BDto> dtos = dao.userArchiveList(nPage, word);
				request.setAttribute("list3", dtos);
				System.out.println(word + " : " + choice);
			}
		} else {

			int nPage = 1;
			try {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} catch (Exception e) {

			}

			BDao dao = BDao.getInstance();
			BPageInfo pinfo = dao.articlePage3(nPage);
			request.setAttribute("page", pinfo);

			nPage = pinfo.getCurPage();

			session = null;
			session = request.getSession();
			session.setAttribute("cpage", nPage);

			ArrayList<BDto> dtos = dao.list3(nPage);
			request.setAttribute("list3", dtos);
		}
	}
}
