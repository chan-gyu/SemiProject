package com.yoriessence.message.controller;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MoveMessageServlet", value = "/message")
public class MoveMessageServlet extends HttpServlet {
    private static final long serialVersionUID = -6616764046437021287L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId= request.getParameter("memberId");
        String targetId=request.getParameter("targetId");

        User user = new UserService().userInfo(memberId);

        List<Profile> profile = new UserService().chefProfile(user.getMemberNickName());

        request.setAttribute("profile",profile);
        request.setAttribute("memberId",memberId);
        request.setAttribute("targetId",targetId);
        request.getRequestDispatcher("/view/common/message/message.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
