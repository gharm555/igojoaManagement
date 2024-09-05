package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlackUser is a Querydsl query type for BlackUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackUser extends EntityPathBase<BlackUser> {

    private static final long serialVersionUID = 297505692L;

    public static final QBlackUser blackUser = new QBlackUser("blackUser");

    public final StringPath adminId = createString("adminId");

    public final StringPath confirm = createString("confirm");

    public final StringPath detail = createString("detail");

    public final DateTimePath<java.time.LocalDateTime> processedAt = createDateTime("processedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> reasonCode = createNumber("reasonCode", Integer.class);

    public final StringPath userId = createString("userId");

    public QBlackUser(String variable) {
        super(BlackUser.class, forVariable(variable));
    }

    public QBlackUser(Path<? extends BlackUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlackUser(PathMetadata metadata) {
        super(BlackUser.class, metadata);
    }

}

