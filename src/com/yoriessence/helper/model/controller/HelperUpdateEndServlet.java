package com.yoriessence.helper.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.helper.model.service.HelperService;
import com.yoriessence.helper.model.vo.Helper;

/**
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/helper/helperUpdateEnd")
public class HelperUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelperUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Helper h=new Helper();
		h.setTitle(request.getParameter("helperTitle"));
		h.setContent(request.getParameter("content"));
		h.setNumber(Integer.parseInt(request.getParameter("helperNo")));
		
		int result=new HelperService().HelperUpdate(h);
		String msg="";
		String loc="";
		if(result>0) {
			msg="공지사항 수정 성공";
			loc="/helper/helperList";
		}else {
			msg="공지사항 수정 실패";
			loc="/helper/helperList";
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
