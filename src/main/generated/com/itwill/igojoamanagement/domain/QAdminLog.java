package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdminLog is a Querydsl query type for AdminLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminLog extends EntityPathBase<AdminLog> {

    private static final long serialVersionUID = -32684029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdminLog adminLog = new QAdminLog("adminLog");

    public final StringPath action = createString("action");

    public final QAdmin admin;

    public final StringPath adminLogCategory = createString("adminLogCategory");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath logId = createString("logId");

    public final StringPath reason = createString("reason");

    public final StringPath target = createString("target");

    public QAdminLog(String variable) {
        this(AdminLog.class, forVariable(variable), INITS);
    }

    public QAdminLog(Path<? extends AdminLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdminLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdminLog(PathMetadata metadata, PathInits inits) {
        this(AdminLog.class, metadata, inits);
    }

    public QAdminLog(Class<? extends AdminLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
    }

}

