package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewPK>, ReviewRepositoryCustom {

}
