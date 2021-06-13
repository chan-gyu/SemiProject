package com.yoriessence.recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.recipe.model.vo.RecipeComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet("/recipe/insertComment")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeService service=new RecipeService();
		RecipeComment rc=new RecipeComment();
		rc.setRecipeEnrollNo(Integer.parseInt(request.getParameter("recipeEnrollNo")));
		rc.setRecipeComment(request.getParameter("recipeComment"));
		rc.setRecipeCommentWriter(request.getParameter("recipeCommentWriter"));
		int result=service.insertComment(rc);
		if(result>0) {
//			response.setContentType("application/json;charset=utf-8");
//			new Gson().toJson(service.selectComment(rc.getRecipeEnrollNo()), response.getWriter());
//			response.sendRedirect(request.getContextPath()+"/recipe/recipeView?recipeEnrollNo="+rc.getRecipeEnrollNo());
			request.setAttribute("comments", service.selectComment(rc.getRecipeEnrollNo()));
			request.getRequestDispatcher("/view/recipe/recipeCommentAjax.jsp").forward(request, response);;
		}else {
			request.setAttribute("msg", "등록 실패");
			request.setAttribute("loc", "/recipe/recipeView?recipeEnrollNo="+rc.getRecipeEnrollNo());
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
