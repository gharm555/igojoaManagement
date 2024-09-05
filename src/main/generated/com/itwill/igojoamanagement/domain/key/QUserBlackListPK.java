package com.itwill.igojoamanagement.domain.key;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBlackListPK is a Querydsl query type for UserBlackListPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserBlackListPK extends BeanPath<UserBlackListPK> {

    private static final long serialVersionUID = 419823184L;

    public static final QUserBlackListPK userBlackListPK = new QUserBlackListPK("userBlackListPK");

    public final DateTimePath<java.time.LocalDateTime> processedAt = createDateTime("processedAt", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public QUserBlackListPK(String variable) {
        super(UserBlackListPK.class, forVariable(variable));
    }

    public QUserBlackListPK(Path<? extends UserBlackListPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBlackListPK(PathMetadata metadata) {
        super(UserBlackListPK.class, metadata);
    }

}

