package com.yoriessence.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.recipe.model.vo.Recipe;
import com.yoriessence.recipe.model.vo.RecipeRecommend;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/recipe/recommend")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeService service=new RecipeService();
		
		String loginId=request.getParameter("loginId");
		int recipeEnrollNo=Integer.parseInt(request.getParameter("recipeEnrollNo"));
		boolean check=Boolean.parseBoolean(request.getParameter("check"));

		RecipeRecommend rr=new RecipeRecommend();
		rr.setRecipeEnrollNo(recipeEnrollNo);
		rr.setMemberId(loginId);
		
		int result=0;
		if(!check) {
			result=service.deleteRecommend(rr);
		}else {
			result=service.insertRecommend(rr);
		}
		
		request.setAttribute("recommend", service.selectRecommendList(recipeEnrollNo));
		request.getRequestDispatcher("/view/recipe/recipeRecommendAjax.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
