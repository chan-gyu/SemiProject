package com.yoriessence.recipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class ProductPageMoveServlet
 */
@WebServlet("/recipe/productPageMove")
public class ProductPageMoveServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPageMoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword")!=null?request.getParameter("keyword"):"";
		int cPage=0;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e ) {
			cPage=1;
		}
		int numPerpage=3;
		
		//관련제품 받아오기
		List<Product> product=new RecipeService().selectProduct(keyword, cPage, numPerpage);
		
		String beforeBtn="";
		String afterBtn="";
		
		if(cPage!=1) {
			beforeBtn="<button id='product_before' onclick='productPageMove("+(cPage-1)+")'><i class='fas fa-chevron-left'></i></button>";
		}else {
			beforeBtn="<button id='product_before' onclick='productPageMove("+3+")'><i class='fas fa-chevron-left'></i></button>";
		}

		if(cPage!=3) {
			afterBtn="<button id='product_before' onclick='productPageMove("+(cPage+1)+")'><i class='fas fa-chevron-right'></i></button>";
		}else {
			afterBtn="<button id='product_before' onclick='productPageMove(1)'><i class='fas fa-chevron-right'></i></button>";
		}
		

		request.setAttribute("productList", product);
		request.setAttribute("beforeBtn", beforeBtn);
		request.setAttribute("afterBtn", afterBtn);
		
		
		request.getRequestDispatcher("/view/recipe/productPageMove.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
