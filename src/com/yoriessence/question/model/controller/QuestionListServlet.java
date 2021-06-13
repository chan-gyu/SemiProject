package com.yoriessence.question.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yoriessence.member.vo.Member;
import com.yoriessence.question.model.service.QuestionService;
import com.yoriessence.question.model.vo.Question;

/**
 * Servlet implementation class QuestionListServlet
 */
@WebServlet("/question/questionList")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		String msg="";
		if(loginMember==null) {
			msg="로그인이 필요합니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
		}
		
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage=5;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=5;
		}
		
		String loginMemberId=loginMember.getUserId();
		List<Question> list=null;
		
		if(loginMemberId.equals("ADMIN")) { //나중에 admin으로 바꿀것
			list=new QuestionService().questionAdminList(cPage, numPerpage);
		}else {			
			list=new QuestionService().questionList(cPage, numPerpage, loginMemberId);
		}
		
		
		int totalData=new QuestionService().questionCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=4;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/question/questionList?cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/question/questionList?cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/question/questionList?cPage="+pageNo+"&numPerpage="+numPerpage+"'>[다음]</a>";
		}
		
		
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("question", list);
		request.getRequestDispatcher("/view/question/questionList.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
