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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="memberupdateservlet",urlPatterns="/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("password");
		String userName = request.getParameter("userName");
		String userNick = request.getParameter("userNick");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String grade = request.getParameter("grade");
		int point = Integer.parseInt(request.getParameter("point"));
		String address = request.getParameter("address");
		String snsconn = request.getParameter("snsconn");
		
		Member m = new Member(userId, userName, userPw, userNick, email, address, grade, point, phone, snsconn);
		
		int result = new MemberService().updateMember(m);
		
		String msg="";
		String loc="";
		System.out.println(result);
		if(result>0) {
			msg="회원정보가 수정되었습니다.";
			loc="/";
		}else {
			msg="회원정보 수정에 실패하였습니다.";
//			loc="/view/member/memberInfoUpdate.jsp";
			loc="/";
		}
		
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
