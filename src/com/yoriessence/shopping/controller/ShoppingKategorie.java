package com.yoriessence.shopping.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class ShoppingKategorie
 */
@WebServlet("/ShoppingKategorie")
public class ShoppingKategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingKategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerpage=15;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=15;
		}
		
		String kategori=request.getParameter("kategori");
		
		List<Product> list=new ShoppingCartService().Shoppingkategori(cPage,numPerpage,kategori);
		
		int totalData=new ShoppingCartService().selectProductCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		System.out.println(totalData);
		System.out.println(totalPage);
		int pageBarSize=4;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<span><a href='"+request.getContextPath()
			+"/ShoppingKategorie?cPage="+(pageNo-1)
			+"&numPerpage="+numPerpage+"'>[이전]</a></span>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span style='background-color:#8CC7BC;'>"+pageNo+"</span>";
			}else {
				pageBar+="<span><a href='"+request.getContextPath()
				+"/ShoppingKategorie?cPage="+pageNo
				+"&numPerpage="+numPerpage+"'>"+pageNo+"</a></span>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<span><a href='"+request.getContextPath()+"/ShoppingKategorie?cPage="+pageNo+"&numPerpage="+numPerpage+"'>[다음]</a></span>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("kategori", list);
		
		request.getRequestDispatcher("view/shopping/shoppingkategorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
