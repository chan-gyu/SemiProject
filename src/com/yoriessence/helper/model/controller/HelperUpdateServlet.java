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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/helper/helperUpdate")
public class HelperUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelperUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//사용자가 수정할 noticeNo를 보냄 -> notice를 가져와 넘겨주기
		Helper h = new HelperService().selectHelper(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("helper", h);
	
		request.getRequestDispatcher("/view/helper/helperUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
