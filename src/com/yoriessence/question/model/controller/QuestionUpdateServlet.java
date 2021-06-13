package com.yoriessence.question.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.Question;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/question/questionUpdate")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자가 수정할 noticeNo를 보냄 -> notice를 가져와 넘겨주기
		Question q = new QuestionService().selectQuestion(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("question", q);
	
		request.getRequestDispatcher("/view/question/questionUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
