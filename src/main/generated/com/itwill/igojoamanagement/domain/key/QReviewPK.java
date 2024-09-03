package com.itwill.igojoamanagement.domain.key;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewPK is a Querydsl query type for ReviewPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReviewPK extends BeanPath<ReviewPK> {

    private static final long serialVersionUID = 539085200L;

    public static final QReviewPK reviewPK = new QReviewPK("reviewPK");

    public final StringPath placeName = createString("placeName");

    public final StringPath userId = createString("userId");

    public QReviewPK(String variable) {
        super(ReviewPK.class, forVariable(variable));
    }

    public QReviewPK(Path<? extends ReviewPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewPK(PathMetadata metadata) {
        super(ReviewPK.class, metadata);
    }

}

