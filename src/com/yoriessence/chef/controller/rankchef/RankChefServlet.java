package com.yoriessence.chef.controller.rankchef;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RankChefServlet", value = "/rankchef.do")
public class RankChefServlet extends HttpServlet {
    private static final long serialVersionUID = 3779996671072247270L;
    // 셰프랭킹 서블릿

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 가져올때 추천수 30 이상은 셰프로 업데이트 시킴
        new UserService().updateChefGrade();

        // 셰프 리스트 받아와서 페이지 처리함
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

        request.getRequestDispatcher("/view/searchChef/RankChef.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
