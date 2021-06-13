package com.yoriessence.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.shopping.service.ShoppingCartService;

/**
 * Servlet implementation class DeleteShoppingCartServlet
 */
@WebServlet(name = "DeleteShoppingCart", urlPatterns = { "/DeleteShopping" })
public class DeleteShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productname=request.getParameter("productname");
		
		int result=new ShoppingCartService().deleteShopping(productname);
		String msg=result>0?"정상적으로 삭제되었습니다":"삭제에 실패했습니다 다시 시도해주세요";
		String loc="/view/shopping/shopping.jsp";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/shopping/cart").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
