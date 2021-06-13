package com.yoriessence.notice.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.notice.model.service.NoticeService;
import com.yoriessence.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/noticeUpdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자가 수정할 noticeNo를 보냄 -> notice를 가져와 넘겨주기
		Notice n = new NoticeService().selectNotice(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("notice", n);
	
		request.getRequestDispatcher("/view/notice/noticeUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
