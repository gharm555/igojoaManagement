package com.itwill.igojoamanagement.Domain;

import com.itwill.igojoamanagement.Domain.key.ReviewPK;
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

    private String review;

    private LocalDateTime modifiedAt;

}
