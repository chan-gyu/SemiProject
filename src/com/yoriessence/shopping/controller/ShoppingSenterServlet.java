package com.yoriessence.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.Refund;

/**
 * Servlet implementation class ShoppingSenterServlet
 */
@WebServlet("/shoppingsenter")
public class ShoppingSenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingSenterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "업로드실패 다시 진행해주세요");
			request.setAttribute("loc", "/index.jsp");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		
		String path=request.getServletContext().getRealPath("/image/");
		int maxSize=1024*1024*10;
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		Refund rd=new Refund();
		
		rd.setMemberid(mr.getParameter("memberid"));
		rd.setProductname(mr.getParameter("productname"));
		rd.setOrdernumber(Integer.parseInt(mr.getParameter("ordernumber")));
		rd.setRefundinfo(mr.getParameter("kategorie"));
		rd.setRefundpic(mr.getFilesystemName("productimage"));
		rd.setRefundreason(mr.getParameter("titlesenter"));
		
		int result=new ShoppingCartService().RefundInsert(rd);
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="문의사항등록 성공";
			loc="/index.jsp";
		}else {
			msg="문의사항등록 실패";
			loc="/index.jsp";
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
