package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictionLog is a Querydsl query type for RestrictionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestrictionLog extends EntityPathBase<RestrictionLog> {

    private static final long serialVersionUID = -1989515290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictionLog restrictionLog = new QRestrictionLog("restrictionLog");

    public final StringPath adminId = createString("adminId");

    public final StringPath detail = createString("detail");

    public final NumberPath<Integer> reasonCode = createNumber("reasonCode", Integer.class);

    public final com.itwill.igojoamanagement.domain.key.QRestrictionLogPK restrictionLogPK;

    public QRestrictionLog(String variable) {
        this(RestrictionLog.class, forVariable(variable), INITS);
    }

    public QRestrictionLog(Path<? extends RestrictionLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestrictionLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestrictionLog(PathMetadata metadata, PathInits inits) {
        this(RestrictionLog.class, metadata, inits);
    }

    public QRestrictionLog(Class<? extends RestrictionLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restrictionLogPK = inits.isInitialized("restrictionLogPK") ? new com.itwill.igojoamanagement.domain.key.QRestrictionLogPK(forProperty("restrictionLogPK")) : null;
    }

}

