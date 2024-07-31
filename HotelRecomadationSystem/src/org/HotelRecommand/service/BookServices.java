package org.HotelRecommand.service;

import org.HotelRecommand.model.BookModel;
import org.HotelRecommand.repository.BookRepository;

public class BookServices {
    BookRepository bookRepo=new BookRepository();
	public boolean BookHotel(BookModel bmodel,int hid,int custid) {
		
		return bookRepo.BookHotel(bmodel,hid,custid);
	}

}
