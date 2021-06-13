package com.yoriessence.chef.controller.rankchef;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SortRankChefServlet", value = "/sortrankchef.do")
public class SortRankChefServlet extends HttpServlet {
    private static final long serialVersionUID = -8793022441670777066L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String chefRankPageSortRef = request.getParameter("chefRankPageSortRef");

        System.out.println(chefRankPageSortRef);

        int cPage;
        int numPerPage=10;
        // 한페이지에 10명씩 보여줌
        try{
            cPage= Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

        // 페이지 처리하고 request에 정보 담는 클래스
        new RankChefTemplateServlet().doPost(request,response);

        List<User> sortRank = new UserService().SortRankChef(cPage,numPerPage,chefRankPageSortRef);
        request.setAttribute("chefInfo",sortRank);

        request.getRequestDispatcher("/view/searchChef/RankChef.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
