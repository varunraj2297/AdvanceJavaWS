package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import com.nt.bo.BookDetailsBO;
import com.nt.dao.BookStoreDAO;
import com.nt.dao.BookStoreDAOImpl;
import com.nt.dto.BookDetailsDTO;

public class BookStoreServiceImpl implements BookStoreService{
	
	private BookStoreDAO dao;

	public BookStoreServiceImpl() {
		dao=new BookStoreDAOImpl();
	}

	@Override
	public List<BookDetailsDTO> searchBooksByCategory(String category) throws Exception {
		  List<BookDetailsBO> listBos=null;
		  List<BookDetailsDTO> listDtos=null;
		  BookDetailsDTO dto=null;
		  
		  listBos=dao.getBooksByCategory(category);
		  listDtos=new ArrayList<BookDetailsDTO>();
		  
		  for(BookDetailsBO bo:listBos) {
			  dto=new BookDetailsDTO();
			  
			  dto.setSerNo(listDtos.size()+1);
			  dto.setBookId(bo.getBookId());
			  dto.setBookName(bo.getBookName());
			  dto.setAuthor(bo.getAuthor());
			  dto.setPrice(bo.getPrice());
			  dto.setStatus(bo.getStatus());
			  dto.setCategory(bo.getCategory());
			  listDtos.add(dto);
		  }
		  return listDtos;
	}
        
}
