package org.HotelRecommand.service;

import org.HotelRecommand.model.ReviewModel;
import org.HotelRecommand.repository.ReviewRepository;

public class ReviewService {
    ReviewRepository revRepo=new ReviewRepository();
	public boolean AddReview( int hid, int custid,ReviewModel rmodel) {
		
		return revRepo.AddReview(hid,custid,rmodel);
	}

}
