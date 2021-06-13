package com.yoriessence.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoriessence.manager.model.service.ManagerService;
import com.yoriessence.manager.model.vo.ManagerPage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SortRef2Servlet", value = "/manager/sort2")
public class SortRef2Servlet extends HttpServlet {
    private static final long serialVersionUID = 7496583547943033613L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String searchDate = request.getParameter("searchDate");
        String endDate = request.getParameter("endDate");
        String delivery= request.getParameter("delivery");

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
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort2?cPage="+(cPage-1)+"'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort2?cPage="+pageNo+"'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/sort2?cPage="+cPage+"'>다음</a></span>";
        }


        List<ManagerPage> getSortRef2 = new ManagerService().getSortRef2(searchDate,endDate,delivery,cPage,numPerPage);

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8;");

        Map<String,Object> data = new HashMap<>();
        data.put("getSortRef2",getSortRef2);
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
