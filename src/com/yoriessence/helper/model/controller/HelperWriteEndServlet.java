package com.yoriessence.helper.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.helper.model.service.HelperService;
import com.yoriessence.helper.model.vo.Helper;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/helper/helperWriteEnd")
public class HelperWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelperWriteEndServlet() {
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
		
		int result=new HelperService().insertHelper(h);
		
		//등록성공하면 등록성공 메세지출력 후 리스트화면으로 이동 //등록실패하면 등록실패 메세지출력 후 등록화면으로 이동 String
		 String msg=""; 
		 String loc=""; if(result>0) { 
			 msg="공지사항등록 성공";
			 loc="/helper/helperList";
		 }else { 
			 msg="공지사항등록 실패"; 
			 loc="/helper/helperForm";
		 } 
		 request.setAttribute("msg", msg); 
		 request.setAttribute("loc", loc);
		 request.getRequestDispatcher("/view/common/msg.jsp").forward(request,response);
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
