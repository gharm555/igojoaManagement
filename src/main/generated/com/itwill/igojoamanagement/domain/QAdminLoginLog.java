package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdminLoginLog is a Querydsl query type for AdminLoginLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminLoginLog extends EntityPathBase<AdminLoginLog> {

    private static final long serialVersionUID = -1441197636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdminLoginLog adminLoginLog = new QAdminLoginLog("adminLoginLog");

    public final QAdmin admin;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath logId = createString("logId");

    public final StringPath loginLogCategory = createString("loginLogCategory");

    public QAdminLoginLog(String variable) {
        this(AdminLoginLog.class, forVariable(variable), INITS);
    }

    public QAdminLoginLog(Path<? extends AdminLoginLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdminLoginLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdminLoginLog(PathMetadata metadata, PathInits inits) {
        this(AdminLoginLog.class, metadata, inits);
    }

    public QAdminLoginLog(Class<? extends AdminLoginLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
    }

}

