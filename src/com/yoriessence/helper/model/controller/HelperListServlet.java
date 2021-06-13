package com.yoriessence.helper.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.helper.model.service.HelperService;
import com.yoriessence.helper.model.vo.Helper;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/helper/helperList")
public class HelperListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelperListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage=5;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=5;
		}
		
		List<Helper> list=new HelperService().helperList(cPage,numPerpage);
		
		int totalData=new HelperService().helperCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=4;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/helper/helperList?cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/helper/helperList?cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/helper/helperList?cPage="+pageNo+"&numPerpage="+numPerpage+"'>[다음]</a>";
		}
		
		
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("helper", list);
		request.getRequestDispatcher("/view/helper/helperList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
