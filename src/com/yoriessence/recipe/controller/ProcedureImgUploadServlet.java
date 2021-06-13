package com.yoriessence.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yoriessence.recipe.model.service.RecipeService;

/**
 * Servlet implementation class ProcedureImgUploadServlet
 */
@WebServlet("/recipe/procedureImgUpload")
public class ProcedureImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcedureImgUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/upload/recipe/procedure/");
		MultipartRequest mr=new MultipartRequest(request,path,1024*1024*10, "utf-8", new MyRename());
		Enumeration e=mr.getFileNames();
//		List<String filenames=new ArrayList();
		String filenames="";
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
			filenames+=mr.getFilesystemName((String)e.nextElement())+",";
		}
		
		request.setAttribute("procedure_picture", filenames);
		
//		int result=new RecipeService().insertProcedurePicture(recipeEnrollNo,  filenames);
//		String msg="";
//		if(result>0) msg="등록 성공"; 
//		else msg="등록 실패";
//		request.setAttribute("msg", msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
