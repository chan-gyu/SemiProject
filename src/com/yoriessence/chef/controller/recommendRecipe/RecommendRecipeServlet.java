package com.yoriessence.chef.controller.recommendRecipe;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.recipe.model.vo.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecommendRecipeServlet", value = "/bestrecipe.do")
public class RecommendRecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 778835965731549362L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 하루씩 뒤 날짜로
        int sortday1;
        int sortday2;
        try{
            sortday1=Integer.parseInt(request.getParameter("sortday1"));
            sortday2=Integer.parseInt(request.getParameter("sortday2"));
        }catch (NumberFormatException e){
            sortday1 = 2;
            sortday2 = 1;
        }

        int cPage;
        int numPerPage = 12;

        try{
            cPage = Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

        // 날짜별 최고 추천받은 레시피 3개를 가져옴(추천메뉴)
        List<Recipe> todayRecipe = new UserService().todayRecipe(sortday1,sortday2);
        request.setAttribute("todayRecipe",todayRecipe);

        // 레시피 추천 가져옴(주간, 월간, 전체)
        String sortRef = request.getParameter("sortVal");

        if(sortRef == null){
            sortRef = "전체";
        }

        List<Recipe> periodRecipe = new UserService().periodRecipe(cPage,numPerPage,sortRef);
        request.setAttribute("periodRecipe",periodRecipe);

        // 좋아요수 가져옴
        List<Integer> countRecipeLike = new UserService().countRecipeLike();
        request.setAttribute("countRecipeLike",countRecipeLike);

        // 댓글 수 가져옴
        List<Integer> countRecipeComment = new UserService().countRecipeComment();
        request.setAttribute("countRecipeComment",countRecipeComment);

        int totalData = new UserService().countRecipeList();
        int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
        int pageBarSize = 5;
        int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
        int pageEnd = pageNo+pageBarSize-1;

        String pageBar = "";

        if(pageNo == 1){
            pageBar+="<span></span>";

        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/bestrecipe.do?cPage="+(cPage-1)+"'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/bestrecipe.do?cPage="+pageNo+"'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/bestrecipe.do?cPage="+cPage+"'>다음</a></span>";
        }

        request.setAttribute("pageBar",pageBar);
        request.getRequestDispatcher("/view/recommendRecipe/recommendRecipe.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
