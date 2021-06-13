package com.yoriessence.question.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.QuestionComment;

/**
 * Servlet implementation class insertQuestionComment
 */
@WebServlet("/question/insertQuestionComment")
public class insertQuestionComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertQuestionComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String commentNumber=request.getParameter("commentNumber");
		String commentMemberId=request.getParameter("CommentMemberId");
		String commentContent=request.getParameter("commentContent");
		String commentPic="";
		
		QuestionComment qc=new QuestionComment();
		qc.setCommentNumber(commentNumber);
		qc.setCommentMemberId(commentMemberId);
		qc.setCommentContent(commentContent);
		qc.setCommentPic(commentPic);
		
		int result = new QuestionService().insertQuestionComment(qc);
		String msg="";
		String loc="/question/questionView?questionNo="+commentNumber;
		if(result>0) {
			msg="답변등록 성공";
		}else {
			msg="답변등록 실패";
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
