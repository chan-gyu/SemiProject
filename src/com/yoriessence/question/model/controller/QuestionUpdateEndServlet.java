package com.yoriessence.question.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.Question;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/question/questionUpdateEnd")
public class QuestionUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 접근방식입니다.");
			request.setAttribute("loc","/question/questionUpdate?no="+request.getParameter("questionNo"));
			
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		
		String path=getServletContext().getRealPath("/upload/question/");
		int maxSize=1024*1024*10;
		MultipartRequest mr= new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		Question q=new Question();
		q.setMemberId(mr.getParameter("MemberId"));
		q.setQuestionTitle(mr.getParameter("questionTitle"));
		q.setQuestionContent(mr.getParameter("content"));
		String filePath=mr.getFilesystemName("up_file");
		
		//파일이 있는지 없는지 확인
		//File객체를 이용해서 확인함
		File f=mr.getFile("up_file");
		//f가 null이면 파일이없음, file.length()값이 0이면 없는것
		if(f!=null&&f.length()>0) {
			//새로추가된 파일이 있음.
			//이전파일을 지워줌
			File deleteFile=new File(path+mr.getParameter("oriFile"));
//			if(deleteFile.exists()) {
				System.out.println(deleteFile.delete());
//			}
		}else {
			filePath=mr.getParameter("oriFile");		
		}
		q.setQuestionPic(filePath);
		q.setQuestionNumber(Integer.parseInt(mr.getParameter("questionNo")));
		
		int result=new QuestionService().questionUpdate(q);
		String msg="";
		String loc="";
		if(result>0) {
			msg="공지사항 수정 성공";
			loc="/question/questionList";
		}else {
			msg="공지사항 수정 실패";
			loc="/question/questionUpdate?no="+q.getQuestionNumber();
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
