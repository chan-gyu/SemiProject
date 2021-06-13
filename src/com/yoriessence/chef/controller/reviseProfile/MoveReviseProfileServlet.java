package com.yoriessence.chef.controller.reviseProfile;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviseProfileServlet", value = "/movereviseprofile")
public class MoveReviseProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 9091499305333444133L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chefId = request.getParameter("chefId");

        List<Profile> chefProfile = new UserService().chefProfile(chefId);

        request.setAttribute("chefProfile",chefProfile);
        request.getRequestDispatcher("/view/searchChef/reviseProfile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
