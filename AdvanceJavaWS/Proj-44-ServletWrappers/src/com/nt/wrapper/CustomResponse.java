package com.nt.wrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomResponse extends HttpServletResponseWrapper {

	private HttpServletResponse res;
	private 	CharArrayWriter caw=null;
	public CustomResponse(HttpServletResponse res) {
		super(res);
		this.res=res;
		caw=new CharArrayWriter();
		System.out.println("CustomResponse.CustomResponse()");
	}
	@Override
	public PrintWriter getWriter() throws IOException {
		PrintWriter pw=null;
		
		System.out.println("CustomResponse.getWriter()");
		pw=new PrintWriter(caw);
 		return pw;
	}
	
	@Override
	public String toString() {
		System.out.println("CustomResponse.toString()");
		return caw.toString();
	}
	
	
	
	

}
