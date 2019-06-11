package com.nt.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.GenericFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter({ "/secondurl", "/thirdurl" })
public class DoublePostingPreventionFilter extends GenericFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest hreq=null;
		    HttpSession ses=null;
		    int ctoken=0,stoken=0;
		    RequestDispatcher rd=null;
		    
		    hreq=(HttpServletRequest)req;
		    System.out.println(hreq.getServletPath());
		    if(hreq.getServletPath().equalsIgnoreCase("/secondurl")) {
		    	ses=hreq.getSession();
		    	ses.setAttribute("stoken",new Random().nextInt(9999));
		    	chain.doFilter(req, res);
		    }
		    else if(hreq.getServletPath().equalsIgnoreCase("/thirdurl")){
		    	ctoken=Integer.parseInt(hreq.getParameter("ctoken"));
		    	ses=hreq.getSession();
		    	stoken=(int)ses.getAttribute("stoken");
		    	if(ctoken==stoken) {
		    		ses=hreq.getSession();
			    	ses.setAttribute("stoken",new Random().nextInt(9999));
		    		chain.doFilter(req, res);
		    	}
		    	else {
		    		rd=hreq.getRequestDispatcher("/error.html");
		    		rd.forward(req, res);
		    	}
		    }
	}

}
