package com.yoriessence.chef.controller.searchChef;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.User;
import com.yoriessence.recipe.model.vo.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortChefRecipeListServlet", value = "/sortchefrecipe.do")
public class SortChefRecipeListServlet extends HttpServlet {
    private static final long serialVersionUID = -7369715487960053005L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 최신순, 조회순, 추천순으로 정렬해서 레시피 가져오는 서블릿
        request.setCharacterEncoding("utf-8");

        String sortVal = request.getParameter("sortVal");
        String chefId = request.getParameter("chefId");

        List<Recipe> r = new UserService().sortRecipe(chefId,sortVal);
        request.setAttribute("recipe",r);
        request.getRequestDispatcher("/view/searchChef/searchChefPage.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
