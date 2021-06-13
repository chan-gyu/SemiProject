package com.yoriessence.manager.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "WaybillServlet", value = "/manager/waybill")
public class WaybillServlet extends HttpServlet {
    private static final long serialVersionUID = 3268796739672979889L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String waybill= request.getParameter("waybill");
        int orderNum = Integer.parseInt(request.getParameter("orderNum"));

        if(waybill == null){
            waybill = "";
        }
        request.setAttribute("waybill",waybill);
        request.setAttribute("orderNum",orderNum);
        request.getRequestDispatcher("/view/common/manager/waybill.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
