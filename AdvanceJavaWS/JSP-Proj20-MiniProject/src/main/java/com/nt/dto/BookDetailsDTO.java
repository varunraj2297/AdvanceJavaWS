package com.nt.dto;

import lombok.Data;

@Data
public class BookDetailsDTO {
	      private int serNo;
          private int bookId;
          private String bookName;
          private String author;
          private String status;
          private float price;
          private String category;
}
