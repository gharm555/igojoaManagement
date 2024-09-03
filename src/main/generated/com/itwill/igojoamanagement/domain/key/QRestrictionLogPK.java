package com.itwill.igojoamanagement.domain.key;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestrictionLogPK is a Querydsl query type for RestrictionLogPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRestrictionLogPK extends BeanPath<RestrictionLogPK> {

    private static final long serialVersionUID = -1778181872L;

    public static final QRestrictionLogPK restrictionLogPK = new QRestrictionLogPK("restrictionLogPK");

    public final StringPath reportedId = createString("reportedId");

    public final DateTimePath<java.time.LocalDateTime> restrictionDate = createDateTime("restrictionDate", java.time.LocalDateTime.class);

    public QRestrictionLogPK(String variable) {
        super(RestrictionLogPK.class, forVariable(variable));
    }

    public QRestrictionLogPK(Path<? extends RestrictionLogPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestrictionLogPK(PathMetadata metadata) {
        super(RestrictionLogPK.class, metadata);
    }

}

