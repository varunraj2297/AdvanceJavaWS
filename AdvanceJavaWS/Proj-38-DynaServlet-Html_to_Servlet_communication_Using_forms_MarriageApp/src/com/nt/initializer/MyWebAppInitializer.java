package com.nt.initializer;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.nt.servlet.MarriageServlet;

public class MyWebAppInitializer implements ServletContainerInitializer {

	static {
		System.out.println("MyWebAppInitializer class is loaded");
		
	}
	
	public MyWebAppInitializer() {
		System.out.println("MyWebAppInitializer-0-param con");
	}

	@Override
	public void onStartup(Set<Class<?>> arg0, ServletContext ctx) throws ServletException {
		MarriageServlet ms=null;
		ServletRegistration.Dynamic reg=null;
		
		ms=new MarriageServlet();
       reg=ctx.addServlet("marriage",ms);
       reg.addMapping("/marriageurl");
       reg.setLoadOnStartup(1);
	}
}
