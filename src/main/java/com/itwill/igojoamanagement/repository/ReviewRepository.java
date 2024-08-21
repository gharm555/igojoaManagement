package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.Domain.Review;
import com.itwill.igojoamanagement.Domain.key.ReviewPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewPK> {


}
