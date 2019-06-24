package com.nt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.BookDetailsDTO;
import com.nt.service.BookStoreService;
import com.nt.service.BookStoreServiceImpl;

//@WebServlet( name="/controller",loadOnStartup=1)
public class MainController extends HttpServlet {

	private BookStoreService service;

	@Override
	public void init() throws ServletException {
		System.out.println("MainController.init()");
		service = new BookStoreServiceImpl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String category = null, source = null;
		List<BookDetailsDTO> listDtos = null;
		RequestDispatcher rd = null;
		String target=null;
		try {
			System.out.println("MainController.doGet()");
			category = req.getParameter("category");
			source = req.getParameter("source");

			listDtos = service.searchBooksByCategory(category);
			req.setAttribute("booksList", listDtos);
			if (source.equalsIgnoreCase("html")) {
				System.out.println("MainController.doGet() if condition"+source);
				target="/html_print.jsp";
			}
			else {
				System.out.println("MainController.doGet() if condition"+source);
				target="/excel_download.jsp";
			}
			rd = req.getRequestDispatcher(target);
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			rd = req.getRequestDispatcher("/error.html");
			rd.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MainController.doPost()");
		doGet(req, res);
	}

	@Override
	public void destroy() {
		service=null;
	}
	
	

}
