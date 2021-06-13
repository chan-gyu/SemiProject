package com.yoriessence.chef.controller.recommendRecipe;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.recipe.model.vo.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BestRecipeAjaxServlet", value = "/sortrecommend.do")
public class BestRecipeAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 5820813639080134857L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int cPage;
        int numPerPage = 12;

        try{
            cPage = Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

        Gson gson = new Gson();

        // 레시피 추천 가져옴(주간, 월간, 전체)
        String sortRef = request.getParameter("sortVal");

        if(sortRef == null){
            sortRef = "전체";
        }

        int totalData = new UserService().countRecipeList();
        int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
        int pageBarSize = 5;
        int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
        int pageEnd = pageNo+pageBarSize-1;

        String pageBar = "";

        if(pageNo == 1){
            pageBar+="<span></span>";

        }else{
            pageBar+="<span><a href='javascript:sortRecipe(cPage-1)'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='javascript:sortRecipe(pageNo)'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='javascript:sortRecipe(cPage)'>다음</a></span>";
        }

        List<Recipe> periodRecipe = new UserService().periodRecipe(cPage,numPerPage,sortRef);

        for(Recipe r :periodRecipe){
            r.setRecipeCategory(URLEncoder.encode(r.getRecipeCategory(),"utf-8"));
            r.setRecipeDifficult(URLEncoder.encode(r.getRecipeDifficult(),"utf-8"));
            r.setRecipeIntro(URLEncoder.encode(r.getRecipeIntro(),"utf-8"));
            r.setRecipeProcedure(URLEncoder.encode(r.getRecipeProcedure(),"utf-8"));
            r.setRecipeTip(URLEncoder.encode(r.getRecipeTip(),"utf-8"));
            r.setRecipeTitle(URLEncoder.encode(r.getRecipeTitle(),"utf-8"));
        }

        // 좋아요수 가져옴
        List<Integer> countRecipeLike = new UserService().countRecipeLike();

        // 댓글 수 가져옴
        List<Integer> countRecipeComment = new UserService().countRecipeComment();

        Map<String,Object> data = new HashMap<>();
        data.put("periodRecipeJson",periodRecipe);
        data.put("countRecipeLikeJson",countRecipeLike);
        data.put("countRecipeCommentJson",countRecipeComment);
        data.put("pageBarJson",pageBar);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        out.println(gson.toJson(data));

        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
