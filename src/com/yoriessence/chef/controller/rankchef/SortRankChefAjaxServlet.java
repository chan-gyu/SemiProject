package com.yoriessence.chef.controller.rankchef;

import com.google.gson.Gson;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.SortRankChef;
import com.yoriessence.chef.model.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SortRankChefAjaxServlet", value = "/sortchefajax.do")
public class SortRankChefAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 8976734873666637346L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String chefRankPageSortRef = URLEncoder.encode(request.getParameter("chefRankPageSortRef"),"utf-8");

        int cPage;
        int numPerPage=10;
        // 한페이지에 10명씩 보여줌
        try{
            cPage= Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

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

        List<SortRankChef> sortRank = new UserService().SortRankChefAJax(cPage,numPerPage,chefRankPageSortRef);
        for (SortRankChef p : sortRank){
            p.setMemberId(URLEncoder.encode(p.getMemberId(),"utf-8"));
            p.setMemberName(URLEncoder.encode(p.getMemberName(),"utf-8"));
            p.setMemberNickName(URLEncoder.encode(p.getMemberNickName(),"utf-8"));
            p.setProfileName(URLEncoder.encode(p.getProfileName(),"utf-8"));
        }


        Map<String,Object> data = new HashMap<>();
        data.put("chefInfo",sortRank);
        data.put("pageBar",pageBar);

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        out.println(gson.toJson(data));

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
