package com.yoriessence.message.controller;

import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.message.model.service.MessageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MoveListServlet", value = "/messagelist")
public class MoveListServlet extends HttpServlet {
    private static final long serialVersionUID = -1768300083254068004L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");

        // 보낸 사람들
        List<String> sendMList = new MessageService().sendMList(memberId);

        String mes = "";
        Map<String,String> result = new HashMap<>();

        for(int i = 0; i<sendMList.size(); i++) {
            mes = new MessageService().mesList(memberId, sendMList.get(i));
            // 가장 최근에 보낸 내용 저장
            result.put(sendMList.get(i),mes);

        }

        List<Profile> profile = new UserService().chefProfile(memberId);

        request.setAttribute("profile",profile);
        request.setAttribute("mesList",result);
        request.setAttribute("memberId",memberId);
//        request.setAttribute("targetId",targetId);

        request.getRequestDispatcher("/view/common/message/messageList.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
