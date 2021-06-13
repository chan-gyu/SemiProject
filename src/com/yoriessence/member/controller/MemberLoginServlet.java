package com.yoriessence.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yoriessence.member.service.MemberService;
import com.yoriessence.member.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="loginservlet",urlPatterns="/member/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("password");
	
		Member m = new MemberService().login(userId,userPwd);
	
		System.out.println("===login logic===");
		
		String saveId=request.getParameter("saveId");
		System.out.println("체크박스 : "+saveId);
		
		if(saveId!=null) {
			Cookie c=new Cookie("saveId", userId);
			//쿠키에 설정 -> 7일
			c.setMaxAge(60*60*24*7);
			c.setPath("/");
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
		if(m!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("msg", "로그인 실패, 아이디/패스워드르 확인하세요.");
			request.setAttribute("loc","/");
			
			RequestDispatcher rd= request.getRequestDispatcher("/view/common/msg.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
