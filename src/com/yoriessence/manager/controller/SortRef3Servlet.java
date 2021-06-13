package com.yoriessence.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoriessence.manager.model.service.ManagerService;
import com.yoriessence.manager.model.vo.ManagerPage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SortRef3Servlet", value = "/manager/sort3")
public class SortRef3Servlet extends HttpServlet {
    private static final long serialVersionUID = 346219128268763542L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String searchDate = request.getParameter("searchDate");
        String endDate = request.getParameter("endDate");
        String searchCondition= request.getParameter("searchCondition");
        String searchVal= request.getParameter("searchVal");

        int cPage;
        int numPerPage=15;
        try{
            cPage= Integer.parseInt(request.getParameter("cPage"));
        }catch (NumberFormatException e){
            cPage=1;
        }

        int totalData = new ManagerService().countOrder();
        int totalPage = (int)(Math.ceil((double)totalData/numPerPage));
        int pageBarSize= 5;
        int pageNo=((cPage-1)/pageBarSize) * pageBarSize+1;
        int pageEnd=pageNo+pageBarSize-1;

        String pageBar = "";

        if(pageNo == 1){
            pageBar+="<span></span>";

        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort3?cPage="+(cPage-1)+"'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort3?cPage="+pageNo+"'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort3?cPage="+cPage+"'>다음</a></span>";
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8;");

        List<ManagerPage> getSortRef3 = new ManagerService().getSortRef3(searchDate,endDate,searchCondition,searchVal,cPage,numPerPage);

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();

        Map<String,Object> data = new HashMap<>();
        data.put("getSortRef3",getSortRef3);
        data.put("pageBar",pageBar);

        out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
