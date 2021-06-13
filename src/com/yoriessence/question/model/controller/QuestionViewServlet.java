package com.yoriessence.question.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.Question;
import com.yoriessence.question.model.vo.QuestionComment;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet("/question/questionView")
public class QuestionViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no=Integer.parseInt(request.getParameter("questionNo"));
		Question q = new QuestionService().selectQuestion(no);
		QuestionComment qc = new QuestionService().selectQuestionComment(no);
//		request.setAttribute("comments", comments);
		request.setAttribute("questionComment", qc);
		request.setAttribute("question", q);
		
		request.getRequestDispatcher("/view/question/questionView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
