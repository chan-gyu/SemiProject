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
import com.yoriessence.shopping.vo.ShoppingCart;

/**
 * Servlet implementation class ShoppingCartInsertServlet
 */
@WebServlet("/insertcart")
public class ShoppingCartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String memberid=request.getParameter("userid");
		System.out.println(memberid);
		String productname=request.getParameter("productname");
		String productexplanation=request.getParameter("productexplanation");
		int productprice=Integer.parseInt(request.getParameter("productprice"));
		int su=Integer.parseInt(request.getParameter("su"));
		int productshopify=Integer.parseInt(request.getParameter("productshopify"));
		int productno=Integer.parseInt(request.getParameter("productno"));
		
		ShoppingCart sc=new ShoppingCart();
		sc.setMemberid(memberid);
		sc.setProductname(productname);
		sc.setProductprice(productprice);
		sc.setProductnumber(su);
		sc.setProductshopify(productshopify);
		sc.setProductno(productno);
		
		int result=new ShoppingCartService().insertShoppingCart(sc);
		
		productno=Integer.parseInt(request.getParameter("productno"));
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="장바구니에 추가 성공";
			loc="/shopping/mall";
		}else {
			msg="장바구니에 추가 실패";
			loc="/shopping/shopping?productNo="+productno;
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
