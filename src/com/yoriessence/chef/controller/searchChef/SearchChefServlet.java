package com.yoriessence.chef.controller.searchChef;


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
import java.util.List;

@WebServlet(name = "SearchChefServlet", value = "/searchchef.do")
public class SearchChefServlet extends HttpServlet {
    private static final long serialVersionUID = -1448507338993502574L;
    // 셰프랭킹 화면에서 셰프검색 했을 때

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chefName = request.getParameter("chefsearch");
        String sortVal = request.getParameter("sortVal");

        if(sortVal == null){
            sortVal="최신순";
        }

        List<Profile> chefProfile = new UserService().chefProfile(chefName);
        // 셰프 닉네임으로 검색해서 프로필 가져옴
        request.setAttribute("chefProfile",chefProfile);

        if(chefProfile == null){

            String id = request.getParameter("memberId");
            new UserService().createProfile(id);
            // 기본 프로필 생성함
            // 로그인 후 프로필 눌렀을 때 프로필 없으면 생성
            // 넘어오는 값은 아이디로 고정
            // 다시 프로필 가져와서 저장함
            List<Profile> reGetChefProfile = new UserService().chefProfile(chefName);
            request.setAttribute("chefProfile",reGetChefProfile);

            if(reGetChefProfile == null){
                request.setAttribute("msg","검색 결과가 존재하지 않습니다. 다시 검색해주세요");
                request.setAttribute("loc","/rankchef.do");
                request.getRequestDispatcher("/view/common/msg.jsp").forward(request,response);
            }
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
                pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+(cPage-1)+"&chefsearch="+chefName+"'>이전</a></span>";
            }

            while(!(pageNo>pageEnd||pageNo>totalPage)){
                if(cPage==pageNo){
                    pageBar+="<span style='color:#1f695b'>"+pageNo+"</span>";
                }else{
                    pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+pageNo+"&chefsearch="+chefName+"'>"+pageNo+"</a></span>";
                }
                pageNo++;
            }

            if(pageNo>totalPage){
                pageBar+="<span></span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+cPage+"&chefsearch="+chefName+"'>다음</a></span>";
            }
            request.setAttribute("pageBar",pageBar);


            // 셰프가 올린 레시피에 달린 댓글 개수 + 정보들 가져옴
            List<RecipeComment> recipeComments = new UserService().recipeCommentNum(chefName);
            request.setAttribute("countComment",recipeComments);

            if(recipeComments.size()==0){
                // 레시피가 없으면 저 로직이 안도니까
                // 프로필만 가져오는 로직추가
                List<Profile> recipeComment = new UserService().chefProfile(chefName);
                request.setAttribute("countComment2",recipeComment);

            }


            // 댓글 수 포함 레시피를 정렬해서 가져옴
            List<RecipeRecommend> recipeRecommend = new UserService().recipeRecommendNum(chefName,sortVal,cPage,numPerPage);

            if(recipeRecommend.size() == 0){
                recipeRecommend = new UserService().chefProfile2(chefName);
            }

            request.setAttribute("recipeRecommends",recipeRecommend);


            // 유저 정보를 가져옴
            User userInfo = new UserService().userInfo(chefName);
            request.setAttribute("userInfo",userInfo);



            List<Recipe> recipes = new UserService().getRecipe(chefName,cPage,numPerPage);
            // 그 셰프가 올린 레시피를 가져옴
            request.setAttribute("recipe",recipes);




            request.getRequestDispatcher("/view/searchChef/searchChefPage.jsp").forward(request,response);

        }else{



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
                pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+(cPage-1)+"&chefsearch="+chefName+"'>이전</a></span>";
            }

            while(!(pageNo>pageEnd||pageNo>totalPage)){
                if(cPage==pageNo){
                    pageBar+="<span style='color:#1f695b'>"+pageNo+"</span>";
                }else{
                    pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+pageNo+"&chefsearch="+chefName+"'>"+pageNo+"</a></span>";
                }
                pageNo++;
            }

            if(pageNo>totalPage){
                pageBar+="<span></span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/searchchef.do?cPage="+cPage+"&chefsearch="+chefName+"'>다음</a></span>";
            }
            request.setAttribute("pageBar",pageBar);


            // 셰프가 올린 레시피에 달린 댓글 개수 + 정보들 가져옴
            List<RecipeComment> recipeComments = new UserService().recipeCommentNum(chefName);
            request.setAttribute("countComment",recipeComments);

            if(recipeComments.size()==0){
                // 레시피가 없으면 저 로직이 안도니까
                // 프로필만 가져오는 로직추가
                List<Profile> recipeComment = new UserService().chefProfile(chefName);
                request.setAttribute("countComment2",recipeComment);

            }


            // 댓글 수 포함 레시피를 정렬해서 가져옴
            List<RecipeRecommend> recipeRecommend = new UserService().recipeRecommendNum(chefName,sortVal,cPage,numPerPage);

            if(recipeRecommend.size() == 0){
                recipeRecommend = new UserService().chefProfile2(chefName);
            }

            request.setAttribute("recipeRecommends",recipeRecommend);


            // 유저 정보를 가져옴
            User userInfo = new UserService().userInfo(chefName);
            request.setAttribute("userInfo",userInfo);



            List<Recipe> recipes = new UserService().getRecipe(chefName,cPage,numPerPage);
            // 그 셰프가 올린 레시피를 가져옴
            request.setAttribute("recipe",recipes);




            request.getRequestDispatcher("/view/searchChef/searchChefPage.jsp").forward(request,response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
