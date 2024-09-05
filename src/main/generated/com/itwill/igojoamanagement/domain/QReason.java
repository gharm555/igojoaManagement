package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReason is a Querydsl query type for Reason
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReason extends EntityPathBase<Reason> {

    private static final long serialVersionUID = 585561170L;

    public static final QReason reason = new QReason("reason");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> reasonCode = createNumber("reasonCode", Integer.class);

    public QReason(String variable) {
        super(Reason.class, forVariable(variable));
    }

    public QReason(Path<? extends Reason> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReason(PathMetadata metadata) {
        super(Reason.class, metadata);
    }

}

