package com.yoriessence.question.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.Question;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/question/questionWriteEnd")
public class QuestionWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항 작성오류 [form:ectype] 관리자에게 문의하세요 :(");
			request.setAttribute("loc", "/question/questionList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String path=request.getServletContext().getRealPath("/upload/question/");
		System.out.println("경로 : " + path);
		int maxSize=1024*1024*10;//10MB

		String encode="utf-8";

		MultipartRequest mr=new MultipartRequest(request,path,maxSize,encode,new DefaultFileRenamePolicy());

		
		Question q=new Question();
		q.setQuestionTitle(mr.getParameter("questionTitle"));
		q.setMemberId(mr.getParameter("questionWrite"));
		q.setQuestionContent(mr.getParameter("content"));;
		q.setQuestionPic(mr.getFilesystemName("up_file"));
		
		System.out.println(q.getMemberId());
		System.out.println(q.getQuestionTitle());
		System.out.println(q.getQuestionContent());
		System.out.println(q.getQuestionPic());
		
		
		int result=new QuestionService().insertQuestion(q);
		
		//등록성공하면 등록성공 메세지출력 후 리스트화면으로 이동 //등록실패하면 등록실패 메세지출력 후 등록화면으로 이동 String
		 String msg=""; 
		 String loc=""; if(result>0) { 
			 msg="공지사항등록 성공";
			 loc="/question/questionList";
		 }else { 
			 msg="공지사항등록 실패"; 
			 loc="/question/questionForm";
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
