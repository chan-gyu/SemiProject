package com.yoriessence.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.Product;

/**
 * Servlet implementation class ProductInsert
 */
@WebServlet("/ProductInsert")
public class ProductInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "업로드실패 다시 진행해주세요");
			request.setAttribute("loc", "/index.jsp");
			request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
			return;
		}
		
		String path=request.getServletContext().getRealPath("/image/");
		int maxSize=1024*1024*10;
		MultipartRequest mr=new MultipartRequest(request,path,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		Product pd=new Product();
		pd.setStock(Integer.parseInt(mr.getParameter("stock")));
		pd.setPrice(Integer.parseInt(mr.getParameter("price")));
		pd.setExplanation(mr.getParameter("explantion"));
		pd.setProductName(mr.getParameter("productname"));
		pd.setProductkategorie(mr.getParameter("kategorie"));
		pd.setProductshopify(Integer.parseInt(mr.getParameter("productshopify")));
		pd.setProductImage(mr.getFilesystemName("productimage"));
		
		
		int result=new ShoppingCartService().ProductInsert(pd);
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="상품등록 성공";
			loc="/index.jsp";
		}else {
			msg="상품등록 실패";
			loc="/index.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/view/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
