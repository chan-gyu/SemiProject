package com.yoriessence.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class ShoppingShoppingServlet
 */
@WebServlet("/shopping/shopping")
public class ShoppingShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productno=Integer.parseInt(request.getParameter("productNo"));
		
		Product pd=new ShoppingCartService().selectProduct(productno);
		
		request.setAttribute("product", pd);
		
		request.getRequestDispatcher("/view/shopping/shoppingpurchase.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
