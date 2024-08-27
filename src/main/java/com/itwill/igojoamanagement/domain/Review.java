package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.ReviewPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "reviews")
public class Review {

    @EmbeddedId
    private ReviewPK reviewId;

    @Column(name = "review")
    private String reviewContent;

    private LocalDateTime modifiedAt;
}
