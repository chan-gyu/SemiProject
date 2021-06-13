package com.yoriessence.chef.controller.searchChef;

import com.yoriessence.chef.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChefRecommendServlet", value = "/recommendChef.do")
public class ChefRecommendServlet extends HttpServlet {
    private static final long serialVersionUID = 7921870686554057478L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 추천수 증감시키는 서블릿
        String chefId =request.getParameter("chefId");
        int recommendYN =Integer.parseInt(request.getParameter("recommendYN"));
//
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();

        // 추천을 했을때
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if( !(c.getName().equals("checkRecommend"+chefId)) ){
                // 쿠키가 없다면 생성
                Cookie checkRecommend = new Cookie("checkRecommend"+chefId,"yes");
                checkRecommend.setMaxAge(60*60*24*30);
                response.addCookie(checkRecommend);

            }else{
                // 있다면 삭제
                Cookie checkRecommend = new Cookie("checkRecommend"+chefId,"yes");
                checkRecommend.setMaxAge(0);
                response.addCookie(checkRecommend);
            }

        }

//        System.out.println(out +"쿠키?");
        new UserService().recommendChef(chefId,recommendYN);
        new UserService().userRecommendUpdate(chefId);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
