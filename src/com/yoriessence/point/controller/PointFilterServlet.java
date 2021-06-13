package com.yoriessence.point.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.point.model.service.MemberPointService;
import com.yoriessence.point.model.vo.MemberPoint;

/**
 * Servlet implementation class PointFilterServlet
 */
@WebServlet("/point/pointFilter")
public class PointFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointFilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String memberId=request.getSession().getAttribute("loginMember").getMemberId();
		String memberId="testId";
		String filter=request.getParameter("filter");
		
		int cPage;
		int numPerpage=5;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		List<MemberPoint> list=new MemberPointService().selectPointList(cPage, numPerpage, memberId, filter);
		
		int totalData=new MemberPointService().selectPointCount(memberId, filter);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		System.out.println(totalData);
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>이전</span>";
		}else {
//			pageBar+="<a href='"+request.getContextPath()
//				+"/point/pointView?cPage='"+(pageNo-1)+">이전</a>";
			pageBar+="<a onclick='pageMove("+(pageNo-1)+")'>이전</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span class='cPage'>"+pageNo+"</span>";
			}else {
//				pageBar+="<a href='"+request.getContextPath()+"/point/pointView?cPage="+pageNo+"'>"+pageNo+"</a>";
				pageBar+="<a onclick='pageMove("+pageNo+")'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>다음</span>";
		}else {
//			pageBar+="<a href='"+request.getContextPath()
//				+"/point/pointView?cPage="+pageNo+"'>다음</a>";
			pageBar+="<a onclick='pageMove("+pageNo+")'>다음</a>";
		}
		
	
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("pointList", list);
		request.setAttribute("pointSum", new MemberPointService().selectPointSum(memberId));
		request.getRequestDispatcher("/view/point/pointFilterAjax.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
