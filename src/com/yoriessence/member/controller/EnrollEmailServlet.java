package com.yoriessence.member.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.member.service.MemberService;
import com.yoriessence.member.vo.Member;

/**
 * Servlet implementation class EnrollEmailServlet
 */
@WebServlet("/member/email")
public class EnrollEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Properties prop = new Properties();
		try {
			String filePath=EnrollEmailServlet.class.getResource("/email/email.properties").getPath();
			prop.load(new FileReader(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String id=prop.getProperty("id");
		String pw=prop.getProperty("pw");
		
		String memberId = request.getParameter("email");

		// 먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
		/*
		 * Member m = new MemberService().memberLogin(memberId); if(m==null ||
		 * !m.getEmail().equals(email)) { req.setAttribute("msg",
		 * "아이디나 이메일 정보가 맞지 않습니다"); req.setAttribute("loc", "/member/searchPw");
		 * req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp); return;
		 * }
		 */
		// mail server 설정
		String host = "smtp.naver.com";
		String user = id;// 자신의 네이버 계정
		String password = pw;// 자신의 네이버 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		String to_email = memberId;

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		
		/*
		 * props.put("mail.smtp.ssl.enable", "true");
		 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.debug",
		 * "true");
		 */

		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();
		System.out.println(AuthenticationKey);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });

		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "yoriessence"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("안녕하세요  yoriessence 인증 메일입니다.");
			// 메일 내용
			msg.setText("인증 번호는 :" + AuthenticationKey);

			Transport.send(msg);
			System.out.println("이메일 전송");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
				
		String userName = request.getParameter("name");
		String userId = request.getParameter("id");
		
		Member mName = new MemberService().checkDuplicateName(userName);
		Member mId = new MemberService().checkDuplicateId(userId);
		
		request.setAttribute("mName", mName);
		request.setAttribute("mId", mId);
		request.setAttribute("userName", userName);
		request.setAttribute("userId", userId);
		
		request.setAttribute("AuthenticationKey", AuthenticationKey);
		request.getRequestDispatcher("/view/member/EnrollEmail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
