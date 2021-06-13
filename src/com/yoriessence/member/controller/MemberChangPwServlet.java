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
 * Servlet implementation class MemberChangPwServlet
 */
@WebServlet(name="ChangePwservlet",urlPatterns="/member/memberChangPw")
public class MemberChangPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberChangPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String newPw=request.getParameter("password");
		Member m = new MemberService().checkDuplicateId(userId);
		
		String msg="";
		String loc="";
		String script="";
		
		if(m!=null) {
			int result=new MemberService().updatePassword(userId, newPw);
			msg=result>0?"비밀번호를 수정하였습니다.":"비밀번호 수정에 실패하였습니다.";
			loc="/";
			script="window.close()";
		}else {
			msg="현재 비밀번호가 일치하지 않아 비밀번호룰 수정할 수 없습니다.";
			loc="/EnrollEmail";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("script", script);
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
