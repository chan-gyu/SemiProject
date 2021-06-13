package com.yoriessence.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.recipe.model.service.RecipeService;
import com.yoriessence.recipe.model.vo.Recipe;
import com.yoriessence.recipe.model.vo.RecipeIngredient;
import com.yoriessence.recipe.model.vo.RecipeProcedure;

/**
 * Servlet implementation class RecipeFormEndServlet
 */
@WebServlet("/recipe/recipeFormEnd")
public class RecipeFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 받아오기
		String path=request.getServletContext().getRealPath("/upload/recipe/");
		int maxSize=1024*1024*10;
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, "utf-8", new MyRename());
	
		//dao로 저장
		Recipe r=new Recipe();
		r.setMemberId(mr.getParameter("member_id"));
		r.setRecipeTitle(mr.getParameter("recipe_title"));
		r.setRecipeIntro(mr.getParameter("recipe_intro"));
		r.setRepresentPicture(mr.getFilesystemName("represent_picture"));
		r.setRecipeVideoAddress(mr.getParameter("recipe_video_address"));
		r.setRecipeCategory(mr.getParameter("recipe_category"));
		r.setRecipeInfoHowmany(Integer.parseInt(mr.getParameter("recipe_info_howmany")));
		r.setRecipeInfoTime(Integer.parseInt(mr.getParameter("recipe_info_time")));
		r.setRecipeDifficult(mr.getParameter("recipe_difficult"));
		r.setMainIngredient(mr.getParameter("main_ingredient"));
		
		int result=new RecipeService().insertRecipe(r);
		int recipeEnrollNo=new RecipeService().selectRecipeEnrollNo(r);
		
		//재료 parsing
		Map<String, List<RecipeIngredient>> ingMap=new HashMap<String, List<RecipeIngredient>>();
		//bundle 이름 담은 배열 생성
		String[] bundle=mr.getParameter("hidden_name").split(",");
		for(String b:bundle) {
			if(b!=null) {
				//category명 통해 가져온 각 재료 배열
//				System.out.println("ingredient : "+mr.getParameter(b));
				String[] ing=mr.getParameter(b).split(",");
				List<RecipeIngredient> ingList=new ArrayList();
				//재료 순회해 list에 넣기
				for(String i:ing) {
					if(i!=null) {
						//각 bundle을 : 기준으로 나눠 얻은 재료의 이름, 양 배열
						String[] ingredient=i.split(":");
						RecipeIngredient ri=new RecipeIngredient();
						ri.setIngredientCategory(i);
						ri.setIngredientName(ingredient[0]);
						ri.setIngredientAmount(ingredient[1]);
						ingList.add(ri);
					}
				}
				//순회해 만든 list를 ingMap에 넣기
				ingMap.put(b, ingList);
			}
		}
		
		result=new RecipeService().insertIngredientMap(ingMap, recipeEnrollNo);
		
		//과정 저장
		List<RecipeProcedure> procedure=new ArrayList();
		int procedureLength=Integer.parseInt(mr.getParameter("procedure_count"));
		for(int i=0;i<procedureLength;i++) {
			RecipeProcedure rp=new RecipeProcedure();
			rp.setRecipeEnrollNo(recipeEnrollNo);
			rp.setProcedureNo(i+1);
			rp.setProcedureContent(mr.getParameter("procedure_content"+(i+1)));
			rp.setProcedurePicture(mr.getFilesystemName("procedure_picture"+(i+1)));
			procedure.add(rp);
		}
		
		for(int i=0;i<procedure.size();i++) {
			if(procedure.get(i)!=null) {
				new RecipeService().insertProcedure(procedure.get(i));
			}
		}
		
		//결과 출력
		String msg="";
		String loc="";
		if(result>0) {
			msg="등록을 성공했습니다.";
			loc="/recipe/recipeView?recipeEnrollNo="+recipeEnrollNo;
		}else {
			msg="등록을 실패했습니다.";
			loc="/recipe/recipeList";
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
