package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;


@WebServlet("/uploadurl")
public class UploadServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		MultipartFormDataRequest nreq=null;
		UploadBean bean=null;
		Hashtable<String, UploadFile> ht=null;
		Enumeration<UploadFile> e=null;
		UploadFile file=null;
		res.setContentType("text/html");
		try {
			pw=res.getWriter();
		     nreq=new MultipartFormDataRequest(req);
		     bean=new UploadBean();
		     bean.setFilesizelimit(100*1024);
		     bean.setMaxfiles(6);
		     bean.setFolderstore("f:/uploads");
		     bean.setOverwrite(false);
		     bean.store(nreq);
		     pw.println("<h1>Files Uploaded successfully</h1>");
		     ht=nreq.getFiles();
		     e=ht.elements();
		     while(e.hasMoreElements()) {
		    	 file=e.nextElement();
		    	 pw.println("<h1   style='text-align:center;color:blue'>"+file.getFileName()+"    "+file.getContentType()+"        "+file.getFileSize()+"</h1>");
		     }
		}
		catch (Exception ex) {
			ex.printStackTrace();
			pw.println("<h1 style='text-align:center;color:red'>Problem while uploading file</h1>");
		}
		
		pw.println("<a href='input.html'>home</a>");
		pw.close();
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
