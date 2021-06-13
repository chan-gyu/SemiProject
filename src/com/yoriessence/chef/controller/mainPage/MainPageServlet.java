package com.yoriessence.chef.controller.mainPage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.RecipeRecommend;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MainPageServlet", value = "/mainpage.do")
public class MainPageServlet extends HttpServlet {
    private static final long serialVersionUID = -4536054122213709081L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 셰프 5명
        List<RecipeRecommend> bestFiveChef = new UserService().bestFiveChef();

        // 레시피 3개 ( 추천순 )
        List<RecipeRecommend> bestThreeRecipe = new UserService().bestThreeRecipe();

        // 레시피 3개 ( 그냥 )
        List<RecipeRecommend> threeRecipe = new UserService().threeRecipe();

        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> data = new HashMap<>();

        data.put("bestFiveChef",bestFiveChef);
        data.put("bestThreeRecipe",bestThreeRecipe);
        data.put("threeRecipe",threeRecipe);


        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
//        mapper.convertValue(data,Map.class);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
