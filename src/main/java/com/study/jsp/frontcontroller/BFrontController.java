package com.study.jsp.frontcontroller;

import java.io.IOException;

import com.study.jsp.command.AContentCommand;
import com.study.jsp.command.ADeleteCommand;
import com.study.jsp.command.AListCommand;
import com.study.jsp.command.AModifyCommand;
import com.study.jsp.command.ArListCommand;
import com.study.jsp.command.BCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.BWriteCommand2;
import com.study.jsp.command.BWriteCommand3;
import com.study.jsp.command.FContentCommand;
import com.study.jsp.command.FDeleteCommand;
import com.study.jsp.command.FListCommand;
import com.study.jsp.command.FModifyCommand;
import com.study.jsp.command.MContentCommand;
import com.study.jsp.command.MJoinCommand;
import com.study.jsp.command.MLoginCommand;
import com.study.jsp.command.MModifyCommand;
import com.study.jsp.command.MWithdrawCommand;
import com.study.jsp.command.SubmitLikeCommand;
import com.study.jsp.command.SubmitLikeCommand2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("*.do")
public class BFrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BFrontController()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		BCommand command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		
		
		if (com.equals("/join_view.do"))
		{
			viewPage = "join_view.jsp";
		} else if(com.equals("/join.do"))
		{
			command = new MJoinCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/loginTotal_view.do"))
		{
			command = new MLoginCommand();
			command.execute(request, response);
			return;
		} else if (com.equals("/loginTotal.do"))
		{
			command = new MLoginCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/mContent_view.do")) {
			command = new MContentCommand();
			command.execute(request, response);
			viewPage = "mContent_view.jsp";
		}else if(com.equals("/memModify.do"))
		{
			command = new MModifyCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/memModify_view.do"))
		{
			command = new MContentCommand();
			command.execute(request, response);
			viewPage = "memModify_view.jsp";
		}else if(com.equals("/withdraw_view.do")) {
			command = new MContentCommand();
			command.execute(request, response);
			viewPage = "withdraw_view.jsp";
		}else if(com.equals("/withdraw.do"))
		{
			command = new MWithdrawCommand();
			command.execute(request, response);
			return;
		}else if (com.equals("/archive_list.do"))
		{
			command = new ArListCommand();
			command.execute(request, response);
			viewPage="archive_list.jsp";
		}else if (com.equals("/write_view3.do"))
		{
			viewPage = "write_view3.jsp";
		} else if (com.equals("/write3.do"))
		{
			command = new BWriteCommand3();
			command.execute(request, response);
			return;
		}else if (com.equals("/content_view3.do"))
		{
			command = new AContentCommand();
			command.execute(request, response);
			viewPage="content_view3.jsp";
		}else if(com.equals("/modify_view3.do")) {
			command = new AContentCommand();
			command.execute(request, response);
			viewPage ="modify_view3.jsp";
		}else if(com.equals("/modify3.do")) {
			command = new AModifyCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/delete3.do")) {
			command = new ADeleteCommand();
			command.execute(request, response);
			viewPage = "archive_list.do?page="+curPage;
		}else if (com.equals("/announce_list.do"))
		{
			command = new AListCommand();
			command.execute(request, response);
			viewPage="announce_list.jsp";
		} else if (com.equals("/write_view.do"))
		{
			viewPage = "write_view.jsp";
		} else if (com.equals("/write.do"))
		{
			command = new BWriteCommand();
			command.execute(request, response);
			return;
		}else if (com.equals("/free_list.do"))
		{
			command = new FListCommand();
			command.execute(request, response);
			viewPage="free_list.jsp";
		} else if (com.equals("/write_view2.do"))
		{
			viewPage = "write_view2.jsp";
		} else if (com.equals("/write2.do"))
		{
			command = new BWriteCommand2();
			command.execute(request, response);
			return;
		}else if (com.equals("/content_view2.do"))
		{
			command = new FContentCommand();
			command.execute(request, response);
			viewPage="content_view2.jsp";
		}else if(com.equals("/modify_view2.do")) {
			command = new FContentCommand();
			command.execute(request, response);
			viewPage ="modify_view2.jsp";
		}else if(com.equals("/modify2.do")) {
			command = new FModifyCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/delete2.do")) {
			command = new FDeleteCommand();
			command.execute(request, response);
			viewPage = "free_list.do?page="+curPage;
		}else if (com.equals("/list.do"))
		{
			viewPage="list.jsp";
		}else if(com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage ="content_view.jsp";
		}else if(com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage ="modify_view.jsp";
		}else if(com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage;
		}else if(com.equals("/logout.do")) {
			session.invalidate();
			viewPage="loginTotal_view.jsp";			
		} else if (com.equals("/submitLike.do"))
		{
			command = new SubmitLikeCommand();
			command.execute(request, response);
			return;
		}else if (com.equals("/submitLike2.do"))
		{
			command = new SubmitLikeCommand2();
			command.execute(request, response);
			return;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	}
