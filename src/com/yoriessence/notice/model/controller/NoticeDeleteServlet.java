package com.yoriessence.notice.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.notice.model.service.NoticeService;

/**
 * Servlet implementation class QuestionDeleteServlet
 */
@WebServlet("/notice/noticeDelete")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int NoticeNumber=Integer.parseInt(request.getParameter("no"));
		int result = new NoticeService().noticeDelete(NoticeNumber);
		
		String msg="";
		String loc="";
		System.out.println(result);
		if(result>0) {
			msg="문의를 삭제하였습니다.";
			loc="/notice/noticeList";
		}else {
			msg="문의삭제를 실패하였습니다.";
			loc="/notice/noticeList";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
