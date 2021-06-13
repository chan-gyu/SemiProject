package com.yoriessence.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yoriessence.member.service.MemberService;
import com.yoriessence.member.vo.Member;

/**
 * Servlet implementation class MemberKakaoLoginServlet
 */
@WebServlet("/member/memberKakaoLogin")
public class MemberKakaoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberKakaoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String kakaoUserId = request.getParameter("kakao_id");
		String kakaoUserEmail = request.getParameter("kakao_email");
		String kakaoUserName = request.getParameter("kakao_name");
		
		Member m = new MemberService().SearchKakaoMember(kakaoUserId, kakaoUserEmail, kakaoUserName);
		
		if(m==null) {
			request.setAttribute("kakaoUserId", kakaoUserId);
			request.setAttribute("kakaoUserEmail", kakaoUserEmail);
			request.setAttribute("kakaoUserName", kakaoUserName);
			request.getRequestDispatcher("/view/member/kakaoEnroll.jsp").forward(request, response);
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
			response.sendRedirect(request.getContextPath());
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
