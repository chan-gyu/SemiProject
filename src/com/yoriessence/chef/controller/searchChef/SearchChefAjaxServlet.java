package com.yoriessence.chef.controller.searchChef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.RecipeComment;
import com.yoriessence.chef.model.vo.RecipeRecommend;
import com.yoriessence.chef.model.vo.User;
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

@WebServlet(name = "SearchChefAjaxServlet", value = "/searchchefajax.do")
public class SearchChefAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 305761468582492401L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String chefId = request.getParameter("chefsearch");
        String sortVal = request.getParameter("sortVal");

        int cPage;
        int numPerPage = 12;

        try{
            cPage = Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage =1;
        }

        int totalData = new UserService().countRecipeList();
        int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
        int pageBarSize = 5;
        int pageNo = ((cPage-1)/pageBarSize) * pageBarSize +1;
        int pageEnd = pageNo+pageBarSize-1;

        String pageBar = "";

        if(pageNo == 1){
            pageBar+="<span></span>";

        }else{
            pageBar+="<span><a href='javascript:sortAjax("+(cPage-1)+",\""+chefId+"\""+",\""+sortVal+"\""+")'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='javascript:sortAjax("+pageNo+",\""+chefId+"\""+",\""+sortVal+"\""+")'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='<span><a href='javascript:sortAjax("+cPage+",\""+chefId+"\""+",\""+sortVal+"\""+")'>다음</a></span>";
        }

        List<RecipeRecommend> recipeInfo = new UserService().recipeRecommendNum(chefId,sortVal,cPage,numPerPage);
        Map<Integer,Object> countComment = new HashMap<>();
        Map<Integer,Object> countRecommend = new HashMap<>();

        for(int i = 0; i<recipeInfo.size(); i++){
            int getRecommendNum = new UserService().getRecommendNum(chefId,recipeInfo.get(i).getRecipeEnrollNum());

            countRecommend.put(recipeInfo.get(i).getRecipeEnrollNum(),getRecommendNum);
        }

        for(int i = 0; i<recipeInfo.size(); i++){
            int getCommentNum = new UserService().getCommentNum(chefId,recipeInfo.get(i).getRecipeEnrollNum());

            countComment.put(recipeInfo.get(i).getRecipeEnrollNum(),getCommentNum);
        }


        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        Map<String,Object> data = new HashMap<>();

        data.put("recipeInfo",recipeInfo);
        data.put("countComment",countComment);
        data.put("countRecommend",countRecommend);
        data.put("pageBar",pageBar);

        out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
