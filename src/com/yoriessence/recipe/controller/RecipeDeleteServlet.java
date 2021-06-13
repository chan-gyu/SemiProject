package com.yoriessence.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.recipe.model.vo.Recipe;

/**
 * Servlet implementation class RecipeDeleteServlet
 */
@WebServlet("/recipe/recipeDelete")
public class RecipeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recipeEnrollNo=Integer.parseInt(request.getParameter("recipeEnrollNo"));
		int result=new RecipeService().deleteRecipe(recipeEnrollNo);
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="삭제가 완료되었습니다.";
			loc="/recipe/recipeList";
		}else {
			msg="삭제를 실패했습니다.";
			loc="/recipe/recipeView?recipeEnrollNo="+recipeEnrollNo;
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
