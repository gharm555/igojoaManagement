package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportLog is a Querydsl query type for ReportLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportLog extends EntityPathBase<ReportLog> {

    private static final long serialVersionUID = -1388178270L;

    public static final QReportLog reportLog = new QReportLog("reportLog");

    public final StringPath confirm = createString("confirm");

    public final StringPath logId = createString("logId");

    public final StringPath placeName = createString("placeName");

    public final NumberPath<Integer> reasonCode = createNumber("reasonCode", Integer.class);

    public final StringPath reportedId = createString("reportedId");

    public final StringPath reportedNickname = createString("reportedNickname");

    public final StringPath reporterId = createString("reporterId");

    public final StringPath reportReason = createString("reportReason");

    public final DateTimePath<java.time.LocalDateTime> reportTime = createDateTime("reportTime", java.time.LocalDateTime.class);

    public final StringPath review = createString("review");

    public QReportLog(String variable) {
        super(ReportLog.class, forVariable(variable));
    }

    public QReportLog(Path<? extends ReportLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportLog(PathMetadata metadata) {
        super(ReportLog.class, metadata);
    }

}

