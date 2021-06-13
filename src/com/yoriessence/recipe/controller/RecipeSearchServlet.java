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
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class RecipeSearchServlet
 */
@WebServlet("/recipe/recipeSearch")
public class RecipeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword")!=null?request.getParameter("keyword"):"";
		String category=request.getParameter("category_hidden");
		String ingredient=request.getParameter("ingredient_hidden");
		String order=request.getParameter("order_by");

		int cPage;
		int numPerpage=15;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		List<Recipe> list=new RecipeService().searchRecipe(keyword, category, ingredient, order, cPage, numPerpage);
		
		int totalData=new RecipeService().selectRecipeCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>이전</span>";
		}else {
//			pageBar+="<a href='"+request.getContextPath()
//				+"/point/pointView?cPage='"+(pageNo-1)+">이전</a>";
			pageBar+="<a onclick='pageMove("+(pageNo-1)+")'>이전</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span class='cPage'>"+pageNo+"</span>";
			}else {
//				pageBar+="<a href='"+request.getContextPath()+"/point/pointView?cPage="+pageNo+"'>"+pageNo+"</a>";
				pageBar+="<a onclick='pageMove("+pageNo+")'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>다음</span>";
		}else {
//			pageBar+="<a href='"+request.getContextPath()
//				+"/point/pointView?cPage="+pageNo+"'>다음</a>";
			pageBar+="<a onclick='pageMove("+pageNo+")'>다음</a>";
		}
		
		//닉네임 가져오기
		Map<String, String> nicknameMap=new HashMap();
		for(Recipe r:list) {
			String nickname=new RecipeService().memberNickname(r.getMemberId());
			nicknameMap.put(r.getMemberId(), nickname);
		}
		
		//관련제품 받아오기
		cPage=1;
		numPerpage=3;
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

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("recipeList", list);
		
		request.setAttribute("nicknameMap", nicknameMap);
		
		request.getRequestDispatcher("/view/recipe/recipeList.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
