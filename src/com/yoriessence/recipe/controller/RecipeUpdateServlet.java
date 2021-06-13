package com.yoriessence.recipe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.recipe.model.vo.Recipe;
import com.yoriessence.recipe.model.vo.RecipeIngredient;
import com.yoriessence.recipe.model.vo.RecipePicture;
import com.yoriessence.recipe.model.vo.RecipeProcedure;

/**
 * Servlet implementation class RecipeUpdateServlet
 */
@WebServlet("/recipe/recipeUpdate")
public class RecipeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recipeEnrollNo=Integer.parseInt(request.getParameter("recipeEnrollNo"));
		
		RecipeService service=new RecipeService();
		Recipe r=service.selectRecipe(recipeEnrollNo);
		
		//재료 분류 가져옴
		List<String> ingCategory=service.selectIngredientCategory(recipeEnrollNo);
		
		//재료 분류를 통해 재료의 이름과 양을 list로 가져옴
		Map<String, List<RecipeIngredient>> ing=new HashMap();
		for(String s:ingCategory) {
			ing.put(s, service.selectIngredient(s, recipeEnrollNo));
		}
		
		//레시피의 과정 사진 가져오기
//		List<RecipePicture> pictures=service.selectProcedurePicture(recipeEnrollNo);
		List<RecipeProcedure> procedure=service.selectProcedure(recipeEnrollNo);
//		for(RecipeProcedure rp:procedure) {
//			System.out.println(rp);
//		}
		
		if(r!=null) {
			request.setAttribute("recipeView", r);
			request.setAttribute("category", ingCategory);
			request.setAttribute("ingredient", ing);
			request.setAttribute("procedure", procedure);

			request.setAttribute("recipe", r);
			request.getRequestDispatcher("/view/recipe/recipeUpdate.jsp").forward(request, response);
			
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
