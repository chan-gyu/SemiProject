package com.yoriessence.chef.controller.recommendRecipe;

import com.google.gson.Gson;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.recipe.model.vo.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortDayRecipeAjaxServlet", value = "/sortday.do")
public class SortDayRecipeAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 7025554937988521475L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        int sortday1;
        int sortday2;
        try{
            sortday1=Integer.parseInt(request.getParameter("sortday1"));
            sortday2=Integer.parseInt(request.getParameter("sortday2"));
        }catch (NumberFormatException e){
            sortday1 = 2;
            sortday2 = 1;
        }
        List<Recipe> todayRecipe = new UserService().todayRecipe(sortday1,sortday2);
        Gson gson = new Gson();
        String jsonList = gson.toJson(todayRecipe);

        response.getWriter().write(jsonList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
