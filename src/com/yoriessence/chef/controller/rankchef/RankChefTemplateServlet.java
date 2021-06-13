package com.yoriessence.chef.controller.rankchef;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RankChefTemplateServlet", value = "/RankChefTemplateServlet")
public class RankChefTemplateServlet extends HttpServlet {
    private static final long serialVersionUID = -8049197022796856175L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cPage;
        int numPerPage=10;
        // 한페이지에 10명씩 보여줌
        try{
            cPage= Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

        // 셰프등급회원 전체 추천수 순으로 가져옴
        List<User> chefInfo = new UserService().chefRankList(cPage,numPerPage);

        // 셰프등급회원 전체 프로필을 추천순으로 가져옴
        List<Profile> chefProfileAll = new UserService().chefProfileAll();

        int totalData = new UserService().countChefList();
        int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
        int pageBarSize = 5;
        int pageNo = ((cPage-1)/pageBarSize) * pageBarSize +1;
        int pageEnd = pageNo+pageBarSize-1;

        String pageBar = "";

        if(pageNo == 1){
            pageBar+="<span></span>";

        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/rankchef.do?cPage="+(cPage-1)+"'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/rankchef.do?cPage="+pageNo+"'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/chef/rankchef.do?cPage="+cPage+"'>다음</a></span>";
        }


        request.setAttribute("chefProfileAll",chefProfileAll);
        request.setAttribute("pageBar",pageBar);
        request.setAttribute("chefInfo",chefInfo);
        request.setAttribute("trophyRef",cPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
