package com.yoriessence.manager.controller;

import com.yoriessence.manager.model.service.ManagerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EnrollWaybillServlet", value = "/manager/updateWaybill")
public class EnrollWaybillServlet extends HttpServlet {
    private static final long serialVersionUID = -7657382691830630087L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String waybill = request.getParameter("waybill");
        int orderNum = Integer.parseInt(request.getParameter("orderNum"));
        int result = new ManagerService().updateWaybill(waybill,orderNum);

        String msg ="";
        String loc="/";
        if(result>0){
            msg="운송장이 등록되었습니다.";
        }else{
            msg="운송장이 등록되지 않았습니다.";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("loc",loc);
        request.getRequestDispatcher("/view/common/msg.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
