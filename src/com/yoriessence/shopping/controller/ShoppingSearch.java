package com.yoriessence.shopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class ShoppingSearch
 */
@WebServlet("/shopping/search")
public class ShoppingSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		
		Product pds=new ShoppingCartService().Productsearch(search);
		
		if(pds!=null) {
			request.setAttribute("pds", pds);
			request.getRequestDispatcher("/view/shopping/shoppingsearch.jsp").forward(request, response);;
			
		}else {
			request.setAttribute("msg", "존재하지 않는 상품입니다.");
			request.setAttribute("loc", "/shopping/mall");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
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
