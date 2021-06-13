package com.yoriessence.chef.controller.reviseProfile;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.chef.model.service.UserService;
import com.yoriessence.chef.model.vo.Profile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DoReviseProfileServlet", value = "/reviseProfile")
public class DoReviseProfileServlet extends HttpServlet {
    private static final long serialVersionUID = -3446222245092957802L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String path= getServletContext().getRealPath("/upload/profile");
//        String path = "/Users/jang/Desktop/semi_intellij/src/main/webapp/upload/profile";
        MultipartRequest mr = new MultipartRequest(request,path,1024*1024*3
                ,"utf-8",new DefaultFileRenamePolicy());

        Profile p = new Profile();

        p.setMemberId(mr.getParameter("memberId"));
        p.setProfileName(mr.getParameter("title"));
        p.setSelfIntro(mr.getParameter("intro"));
        p.setProfilePic(mr.getFilesystemName("pic"));
        p.setProfileSnsUrl1(mr.getParameter("sns1"));
        p.setProfileSnsUrl2(mr.getParameter("sns2"));

        System.out.println(mr.getFilesystemName("pic") + " : pic");

//        String id = p.getMemberId();

        new UserService().reviseProfile(p);

//        request.getRequestDispatcher("/searchchef.do?chefsearch="+id).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
