package com.yoriessence.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoriessence.shopping.service.ShoppingCartService;
import com.yoriessence.shopping.vo.OrderDetails;

/**
 * Servlet implementation class ShoppingListServlet
 */
@WebServlet("/ShoppingListServlet")
public class ShoppingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * List<ShoppingCart> memberid= new ArrayList();
		 * memberid.add(request.getParameterValues("memberid")); String[]
		 * productname=request.getParameterValues("proname"); String[]
		 * productnumber=request.getParameterValues("pronumber"); String[]
		 * productprice=request.getParameterValues("proprice"); String[]
		 * productshopify=request.getParameterValues("proshopify");
		 * 
		 * for(int i=0;i<memberid.length;i++) { System.out.println(memberid[0]); }
		 */
		int sc=Integer.parseInt(request.getParameter("sc"));
		String memberid=request.getParameter("memberid");
		List<String> proname=new ArrayList();
		List<Integer> pronumber=new ArrayList();
		List<Integer> productprice=new ArrayList();
		List<Integer> prono=new ArrayList();
		for(int i=0;i<sc;i++) {
			proname.add(request.getParameter("proname"+i));
			pronumber.add(Integer.parseInt(request.getParameter("pronumber"+i)));
			productprice.add(Integer.parseInt(request.getParameter("proprice"+i)));
			prono.add(Integer.parseInt(request.getParameter("prono"+i)));
			
		}
		for(int i=0;i<proname.size();i++) {
			proname.get(i);
			pronumber.get(i);
			productprice.get(i);
			prono.get(i);
		}
		for(int i=0;i<proname.size();i++) {
			new ShoppingCartService().shoplist(memberid,proname.get(i),pronumber.get(i),productprice.get(i),prono.get(i));
		}
		
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
