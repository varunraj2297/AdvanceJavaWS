package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dto.JobSeekerDTO;
import com.nt.service.JobSeekerMgmtService;
import com.nt.service.JobSeekerMgmtServiceImpl;
import com.sun.xml.internal.ws.developer.StreamingAttachment;

@WebServlet("/thirdurl")
public class ThirdServlet extends HttpServlet {
	JobSeekerMgmtService service=null; 
	
	@Override
	public void init() throws ServletException {
	     service=new JobSeekerMgmtServiceImpl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	       PrintWriter pw=null;
	       String loc=null,name=null,addrs=null,skill=null;
	       int expsal=0,age=0,exp=0;
	       HttpSession ses=null;
	       JobSeekerDTO dto=null;
	       String result=null;
	       
	       pw=res.getWriter();
	       res.setContentType("text/html");
	       loc=req.getParameter("loc");
	       expsal=Integer.parseInt(req.getParameter("expsal"));
	       
	       ses=req.getSession(false);
	       
	       name=(String) ses.getAttribute("name");
	       age=(int) ses.getAttribute("age");
	       addrs=(String) ses.getAttribute("addrs");
	       
	       skill=(String) ses.getAttribute("skill");
	       exp=(int) ses.getAttribute("exp");
	       
	       dto=new JobSeekerDTO();
	       dto.setJname(name);
	       dto.setAge(age);
	       dto.setAddrs(addrs);
	       dto.setSkill(skill);
	       dto.setExp(exp);
	       dto.setLoc(loc);
	       dto.setExpsal(expsal);

	       try {
			result=service.registerJobSeeker(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       pw.println("<h1  style='color:red;text-align:center'>Result</h1>");
	       pw.println("<h1 style='color:blue;text-align:center'>"+result+"</h1>");
	       pw.println("form1/req1 data<br>           "+name+"....."+age+"....."+addrs+"<br>");
	       pw.println("form2/req2 data<br>           "+skill+"....."+exp+".....<br>");
	       pw.println("form3/req3 data<br>           "+loc+"....."+expsal+".....<br>");
	       
	       pw.println("<a href='personal.html'>home</a>");
	       
	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
