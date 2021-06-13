package com.yoriessence.message.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoriessence.message.model.service.MessageService;
import com.yoriessence.message.model.vo.Message;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetMessageServlet", value = "/getMessage.do")
public class GetMessageServlet extends HttpServlet {
    private static final long serialVersionUID = -7668914943084522384L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String messageVal = request.getParameter("messageVal");
        String memberId = request.getParameter("memberId");
        String targetId = request.getParameter("targetId");

        // memberId -> targetId로 내용을 입력할 메소드가 1개 필요
        Message m = new Message();
        m.setMemberId(memberId);
        m.setTargetId(targetId);

        // 입력값이 있으면
        if(messageVal != null) {
            m.setSendMessage(messageVal);
            new MessageService().inputMessage(m);
        }

        // memberId -> targetId로 보낸 메세지 전체를 가져올 메소드 1개 필요
        Map<String,List<Message>> getMessage = new MessageService().getMessage(m);
        // K String -> i"번"
        // V

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getMessage);
        PrintWriter out = response.getWriter();

        out.println(json);

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
