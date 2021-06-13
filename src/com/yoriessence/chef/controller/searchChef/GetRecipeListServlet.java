package com.yoriessence.chef.controller.searchChef;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.recipe.model.vo.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetRecipeListServlet", value = "/GetRecipeListServlet")
public class GetRecipeListServlet extends HttpServlet {
    private static final long serialVersionUID = 4565311633727901297L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        List<Recipe> recipes = new UserService().getRecipe();

        request.getRequestDispatcher("/view/searchChef/searchChefPage.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
