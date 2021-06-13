package com.yoriessence.manager.controller;

import com.yoriessence.manager.model.service.ManagerService;
import com.yoriessence.manager.model.vo.ManagerPage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManagerServlet", value = "/manager/main.do")
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = -5679409497482320683L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/main.do?cPage="+(cPage-1)+"'>이전</a></span>";
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                pageBar+="<span>"+pageNo+"</span>";
            }else{
                pageBar+="<span><a href='"+request.getContextPath()+"/manager/main.do?cPage="+pageNo+"'>"+pageNo+"</a></span>";
            }
            pageNo++;
        }

        if(pageNo>totalPage){
            pageBar+="<span></span>";
        }else{
            pageBar+="<span><a href='"+request.getContextPath()+"/manager/main.do?cPage="+cPage+"'>다음</a></span>";
        }
        request.setAttribute("pageBar",pageBar);

        // 기본 화면에 뿌리 데이터 가져옴
        List<ManagerPage> getOrderList = new ManagerService().getOrderList(cPage,numPerPage);
        request.setAttribute("getOrderList",getOrderList);

        request.getRequestDispatcher("/view/common/manager/managerMain.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
