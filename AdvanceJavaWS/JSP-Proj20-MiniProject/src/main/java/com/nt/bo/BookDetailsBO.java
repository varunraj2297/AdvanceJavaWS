package com.nt.bo;

import lombok.Data;

@Data
public class BookDetailsBO {
          private int bookId;
          private String bookName;
          private String author;
          private String status;
          private float price;
          private String category;
}
