package com.yoriessence.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.member.service.MemberService;
import com.yoriessence.member.vo.Member;

/**
 * Servlet implementation class MemberPwCheck
 */
@WebServlet("/member/memberPwCheck")
public class MemberPwCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		
		Member m = new MemberService().checkDuplicateId(userId);
		
		String view="";
		String msg="";
		
		if(m!=null) {
			view="/view/member/memberPwCheck.jsp";
		}else {
			view="/view/common/msg.jsp";
			msg="가입된 회원이 아닙니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("member", m);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
